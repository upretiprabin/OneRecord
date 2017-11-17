package com.prabin.onerecord

import grails.converters.JSON
import grails.core.GrailsApplication
import org.elasticsearch.action.ActionRequestBuilder
import org.elasticsearch.action.ActionResponse
import org.elasticsearch.action.search.MultiSearchRequestBuilder
import org.elasticsearch.action.search.MultiSearchResponse
import org.elasticsearch.action.search.SearchRequestBuilder
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.action.search.SearchType
import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.common.xcontent.XContentBuilder
import org.elasticsearch.common.xcontent.XContentFactory
import org.elasticsearch.index.query.MatchAllQueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.SearchHit
import org.elasticsearch.search.aggregations.Aggregation
import org.elasticsearch.search.aggregations.AggregationBuilders
import org.elasticsearch.search.aggregations.bucket.filters.Filters
import org.grails.web.json.JSONElement
import org.grails.web.json.JSONObject
import org.grails.web.json.parser.JSONParser
import org.joda.time.DateTime
import org.joda.time.Interval

class DashBoardController {

    def chartService;
    GrailsApplication grailsApplication;

    def index = {

        // read from json file
        String jsonFileName = grailsApplication.config.jsonFileName;
        JSONParser parser = new JSONParser(new FileReader(new File(jsonFileName).getAbsolutePath()));
        JSONObject jsonObj = (JSONObject) parser.parse();
        Map<String,Object> chartSeries = new HashMap<String,Object>();
        chartSeries.put("name",jsonObj.memberFullName)
        ArrayList<Double> tempList = new ArrayList<Double>();
        jsonObj.monthlyData.forEach{key,value->
            tempList.add((Double)value.getAt("medicalPaidAmount"))
        }
        chartSeries.put("data",tempList)
        String chartScript = chartService.getDashBoardChartScript(chartSeries as JSON);
        [chartOptions: chartScript]
    }

    def getEsData = {

        Map<String, ResponseObj> responseObj = new HashMap<String, ResponseObj>();

        ActionRequestBuilder builder = makeRequest();
        ActionResponse response = builder.execute().actionGet();
        makeResponse(response,responseObj);

//        SearchResponse searchResponse = client.prepareSearch("bank")
//                                                .setTypes("account")
//                                                .execute()
//                                                .actionGet()
//        SearchHit[] hits = searchResponse.getHits().getHits();
//        Map<String,Object> result = new HashMap<String,Object>();
//        for (SearchHit hit : hits) {
//            result.putAll(hit.getSource());
//        }
//        println "result = $result"
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder();
        writeContent(responseObj,contentBuilder,'default');
        println "contentBuilder = ${contentBuilder.bytes().toUtf8()}"
        def result = JSON.parse(contentBuilder.bytes().toUtf8());
        println "result = ${result as JSON}"
        render result as JSON
    }

    public ActionRequestBuilder makeRequest(){
        return multiSearchQuery(getClient());
    }

    public void makeResponse(ActionResponse actionResponse, Map<String, Object> responseObj){
        MultiSearchResponse response = (MultiSearchResponse) actionResponse;
        for (MultiSearchResponse.Item item : response) {
            if (item.isFailure()) {
                throw new RuntimeException(item.getFailureMessage());
            }
            for(Aggregation aggregation : item.getResponse().getAggregations()){
                if(aggregation.getName().equals("filterAge")){
                    Filters filters = (Filters) aggregation;
                    for (Filters.Bucket bucket : filters.getBuckets()){
                        ResponseObj record = new ResponseObj();
                        record.ageRange = bucket.getKeyAsString();
                        record.count = (double)bucket.getDocCount();
                        responseObj.put(record.ageRange,record);
                    }
                }
            }

        }
    }

    protected XContentBuilder writeContent(Map<String, ResponseObj> recordMaps, XContentBuilder contentBuilder, String period) throws Exception {
        contentBuilder.startObject(period);
//        println "recordMaps = $recordMaps"

        for (ResponseObj r : recordMaps.values()) {
            println "r = $r.ageRange"
            println "r = $r.count"
            contentBuilder.startObject(r.ageRange);
            contentBuilder.field("ageRange", r.ageRange);
            contentBuilder.field("totalMembers", r.count);
            contentBuilder.endObject();
        }
        contentBuilder.endObject();
        return contentBuilder;
    }

    public Client getClient(){
        Settings settings = Settings.settingsBuilder()
              .put("cluster.name", "prabin-es").build();
        TransportClient client = TransportClient.builder().settings(settings).build()
              .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        return  client;
    }

    public MultiSearchRequestBuilder multiSearchQuery(Client client) {
        MultiSearchRequestBuilder builder = client.prepareMultiSearch();
        SearchRequestBuilder searchRequestBuilder = getSearchRequestBuilder(client);

        searchRequestBuilder.addAggregation(
              AggregationBuilders.filters("filterAge")
                    .filter("10-20",QueryBuilders.rangeQuery("age")
                    .from("10")
                    .to("20")
              )
                    .filter("20-30",QueryBuilders.rangeQuery("age")
                    .from("20")
                    .to("30")
              )
                    .filter("30-40",QueryBuilders.rangeQuery("age")
                    .from("30")
                    .to("40")
              )
                    .filter("40-50",QueryBuilders.rangeQuery("age")
                    .from("40")
                    .to("50")
              )
        );
        builder.add(searchRequestBuilder);
        return builder;
    }

    public SearchRequestBuilder getSearchRequestBuilder(Client client) {
        SearchRequestBuilder builder = buildEndSearch(client);
        builder.setTypes("account").setSize(0);
        builder.setQuery(new MatchAllQueryBuilder());
        return builder;
    }

    public SearchRequestBuilder buildEndSearch(Client client) {
        SearchRequestBuilder requestBuilder = client.prepareSearch("bank");
        requestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);
        return requestBuilder;
    }

    protected class ResponseObj {
        String ageRange;
        Double count;
    }
}
