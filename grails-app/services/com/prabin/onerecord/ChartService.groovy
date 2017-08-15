package com.prabin.onerecord

import grails.converters.JSON
import grails.transaction.Transactional
import org.grails.web.json.JSONObject

@Transactional
class ChartService {

    public String getDashBoardChartScript(JSON series){


        String chartScript = """{

                chart: {
                    type: 'column'
                },

                credits:{
                    enabled: false
                },

                title: {
                    text: 'Total Paid Amount By Month'
                },

                xAxis: {
                    categories: ['Jan', 'Feb', 'Mar', 'April', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                },

                yAxis: {
                    allowDecimals: false,
                    min: 0,
                    title: {
                        text: 'Paid Amount'
                    }
                },

                tooltip: {
                    formatter: function () {
                        return '<b>' + this.x + '</b><br/>' +
                                this.series.name + ': ' + this.y + '<br/>' +
                                'Total: ' + this.point.stackTotal;
                    }
                },
                series: [{
                    name: 'John',
                    data: [5, 3, 4, 7, 2],
                    stack: 'male'
                }]
            }""";

        return chartScript;
    }
}
