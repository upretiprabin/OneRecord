package com.prabin.onerecord

import grails.converters.JSON
import org.grails.web.json.JSONObject
import org.grails.web.json.parser.JSONParser

class DashBoardController {

    def chartService;

    def index = {

        // read from json file
        JSONParser parser = new JSONParser(new FileReader("onerecord.json"));
        JSONObject a = (JSONObject) parser.parse();

        String chartScript = chartService.getDashBoardChartScript();
        [chartOptions: chartScript]
    }
}
