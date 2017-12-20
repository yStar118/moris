<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>默锐安全后台管理系统</title>
    <%@ include file="common/header.jsp" %>
    <link rel="stylesheet" href="${staticServer }/assets/components/jquery-ui/jquery-ui.css?version=${versionNo}"/>
    <link rel="stylesheet" href="${staticServer }/assets/highcharts/css/highcharts.css?version=${versionNo}"/>
    <link rel="stylesheet" type="text/css" href="${staticServer }/assets/datetimepicker/jquery.datetimepicker.css"/>
    <style type="text/css">
        #highChartsTable {
            border-collapse: collapse;
            width: 100%;
            margin: 0 auto
        }
    </style>
</head>

<body class="no-skin">
<%@ include file="common/top.jsp" %>
<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
            ace.settings.loadState('main-container')
        } catch (e) {
        }


    </script>
    <%@ include file="common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                </ul>
                <!-- /.breadcrumb -->

                <!-- /section:basics/content.searchbox -->
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">


                <!-- /section:settings.box -->
                <div class="page-header">
                    <h1>
                        后台管理系统
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 首页
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box widget-color-blue">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <table class="searchField" style="margin: 4px; padding: 4px;">
                                        <tr style="margin-top: 5px;">
                                            <td>起始日期:</td>
                                            <td><input type="text" id="startDate" class="form-control " placeholder=""
                                                       value=""></td>
                                            <td>结束日期:</td>
                                            <td><input type="text" id="endDate" class="form-control " placeholder=""
                                                       value=""></td>
                                            <td>
                                                <button class="btn btn-primary btn-sm" id="btnSearch">
                                                    <i class="ace-icon fa fa-search"></i> 查询
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div id="container" style="float: left;width:45%;height: 400px;margin-left: 3%"></div>
                    <div id="container2" style="float: left;width:45%;height: 400px;margin-left: 3%"></div>
                    <div id="container3" style="float: left;width:45%;height: 400px;margin-left: 3%"></div>
                    <div id="container4" style="float: left;width:45%;height: 400px;margin-left: 3%"></div>
                </div>
            </div>

        </div>
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->
<%@ include file="common/js.jsp" %>
<script src="${staticServer }/assets/components/jquery-ui/jquery-ui.js"></script>
<script src="${staticServer }/assets/highcharts/js/highcharts.js"></script>
<script src="${staticServer }/assets/highcharts/js/highcharts-3d.js"></script>
<script src="${staticServer }/assets/highcharts/js/modules/exporting.js"></script>
<script src="${staticServer }/assets/datetimepicker/jquery.datetimepicker.js" type="text/javascript"></script>
</body>
<script>
    $(function () {
        var $start = $("#startDate");
        var $end = $("#endDate");
        $start.datetimepicker({
            lang: 'ch',
            timepicker: true,
            format: 'Y-m-d H:i:s',
            formatDate: 'Y-m-d H:i:s'
        });

        $end.datetimepicker({
            lang: 'ch',
            timepicker: true,
            format: 'Y-m-d H:i:s',
            formatDate: 'Y-m-d H:i:s'
        });

//11111
        var chart = new Highcharts.Chart('container', {
            title: {
                text: '部门异常点次趋势',
                x: -20
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                categories: ['一月', '二月', '三月', '四月', '五月', '六月']
            },
            yAxis: {
                title: {
                    text: '次数（次）'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '次'
            },
            legend: {
                align: 'center', //水平方向位置
                verticalAlign: 'bottom', //垂直方向位置
                x: 0, //距离x轴的距离
                y: 0 //距离Y轴的距离
            },
            series: [{
                name: '一车间',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5]
            }, {
                name: '二车间',
                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0]
            }, {
                name: '原药车间',
                data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0]
            }, {
                name: '保全车间',
                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2]
            }]
        });

        //222222
        $('#container2').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '最近7日异常总点次'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: [
                    '2017-08-11',
                    '2017-08-12',
                    '2017-08-13',
                    '2017-08-14',
                    '2017-08-15',
                    '2017-08-16'
                ],
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: '次'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '东京',
                data: [140, 120, 106, 129, 144, 176]
            }]
        });

        //3333333
        $('#container3').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '部门每月异常点次'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: [
                    '一月',
                    '二月',
                    '三月',
                    '四月',
                    '五月',
                    '六月'
                ],
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: '次数（次）'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 次</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '一车间',
                data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0]
            }, {
                name: '二车间',
                data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5]
            }, {
                name: '保全车间',
                data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3]
            }, {
                name: '原药车间',
                data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5]
            }]
        });

        //4444444
        $('#container4').highcharts({
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            title: {
                text: '各部门异常点次'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '异常点次占比',
                data: [
                    ['一车间', 25],
                    ['二车间', 33],
                    ['原药车间', 11],
                    ['保全车间', 31]
                ]
            }]
        });
    });


</script>
</html>
