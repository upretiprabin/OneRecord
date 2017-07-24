<!doctype html>
<html>
<head>
        <meta name="layout" content="masterLayout"/>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        %{--<asset:javascript src="highchart/highcharts.js"/>--}%
        %{--<asset:javascript src="highchart/modules/exporting.js"/>--}%
        <script>
                $(document).ready(function () {
                        var chartScript = {

                                chart: {
                                        type: 'column'
                                },

                                title: {
                                        text: 'Total fruit consumtion, grouped by gender'
                                },

                                xAxis: {
                                        categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
                                },

                                yAxis: {
                                        allowDecimals: false,
                                        min: 0,
                                        title: {
                                                text: 'Number of fruits'
                                        }
                                },

                                tooltip: {
                                        formatter: function () {
                                                return '<b>' + this.x + '</b><br/>' +
                                                        this.series.name + ': ' + this.y + '<br/>' +
                                                        'Total: ' + this.point.stackTotal;
                                        }
                                },

                                plotOptions: {
                                        column: {
                                                stacking: 'normal'
                                        }
                                },
                                credits:{enabled:false},

                                series: [{
                                        name: 'John',
                                        data: [5, 3, 4, 7, 2],
                                        stack: 'male'
                                }, {
                                        name: 'Joe',
                                        data: [3, 4, 4, 2, 5],
                                        stack: 'male'
                                }, {
                                        name: 'Jane',
                                        data: [2, 5, 6, 2, 1],
                                        stack: 'female'
                                }, {
                                        name: 'Janet',
                                        data: [3, 0, 4, 4, 3],
                                        stack: 'female'
                                }]
                        };

                        var chart1 = new Highcharts.Chart('firstChart',chartScript);
                        var chart2 = new Highcharts.Chart('secondChart',chartScript);

                });
        </script>
</head>
<body>
<div>
        <div id="firstChart" style="margin: 5px 5px 5px 5px; border: groove;" class="col-lg-5">

        </div>
        <div class="col-lg-1"></div>
        <div id="secondChart" style="margin: 5px 5px 5px 5px; border: groove;" class="col-lg-5">

        </div>
</div>
</body>
</html>
