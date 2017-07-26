<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>One Record</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    %{--<script src="https://code.highcharts.com/highcharts.js"></script>--}%
    %{--<script src="https://code.highcharts.com/modules/exporting.js"></script>--}%
    <script>
        $(document).ready(function () {
            var chartOptions ={

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

            var chart1 = new Highcharts.Chart('sampleChart1',chartOptions);
            var chart2 = new Highcharts.Chart('sampleChart2',chartOptions);
            var chart3 = new Highcharts.Chart('sampleChart3',chartOptions);
            var chart4 = new Highcharts.Chart('sampleChart4',chartOptions);
        });
    </script>
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
            <li><a href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li><a href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>
</content>

%{--<div class="svg" role="presentation">--}%
%{--<div class="grails-logo-container">--}%
%{--<asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>--}%
%{--</div>--}%
%{--</div>--}%

<div id="content" role="main">
    %{--<section class="row colset-2-its">--}%
    %{--<h1>Welcome to Grails</h1>--}%

    %{--<p>--}%
    %{--Congratulations, you have successfully started your first Grails application! At the moment--}%
    %{--this is the default page, feel free to modify it to either redirect to a controller or display--}%
    %{--whatever content you may choose. Below is a list of controllers that are currently deployed in--}%
    %{--this application, click on each to execute its default action:--}%
    %{--</p>--}%

    %{--<div id="controllers" role="navigation">--}%
    %{--<h2>Available Controllers:</h2>--}%
    %{--<ul>--}%
    %{--<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">--}%
    %{--<li class="controller">--}%
    %{--<g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>--}%
    %{--</li>--}%
    %{--</g:each>--}%
    %{--</ul>--}%
    %{--</div>--}%
    %{--</section>--}%

    <section class="row">
        <div class="col-lg-4" id = "sampleChart1" style="height: 40%; width: 48%; margin: 2px 2px 2px 2px; border: groove;">
            <div class="chartloading"></div>
        </div>
    <div  class="col-lg-4" id= "sampleChart2" style="height: 40%; width: 48%; margin: 2px 2px 2px 2px; border: groove;">
        <div class="chartloading"></div>
    </div>
    <div class="col-lg-4" id = "sampleChart3" style="height: 40%; width: 48%; margin: 2px 2px 2px 2px; border: groove;">
            <div class="chartloading"></div>
        </div>
    <div  class="col-lg-4" id= "sampleChart4" style="height: 40%; width: 48%; margin: 2px 2px 2px 2px; border: groove;">
        <div class="chartloading"></div>
    </div>

    </section>

</div>

</body>
</html>
