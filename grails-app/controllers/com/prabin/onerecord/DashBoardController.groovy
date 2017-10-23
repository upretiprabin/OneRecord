package com.prabin.onerecord

import grails.converters.JSON
import grails.core.GrailsApplication
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


        DateTime today = new DateTime();

        DateTime tomorrow = today.plusDays(1);

        String num = "2/064";

        try{


        System.out.println("parsewithoffset-------------------"+Interval.parseWithOffset(num))
        }catch(e){
            println "no class found----at testbranch-----"
        }

        System.out.println(today.toString("yyyy-MMM-dd"));

        System.out.println(tomorrow.toString("yyyy-MMMM-dd"));

        [chartOptions: chartScript]
    }
}
