<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/9/4
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="site_name" type="java.lang.String"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${site_name}进度管理- 交房进度</title>
    <link rel="stylesheet" href="<c:url value="/statics/components/highcharts/css/highcharts.css"/>" />
    <link rel="stylesheet" href="<c:url value="/statics/css/bootstrap-datepicker/bootstrap-datepicker3.css"/>"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/statics/css/jquery.datatables/jquery.dataTables.css"/>"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/statics/css/jquery.datatables/jquery.dataTables.fixedColums.css"/>">
</head>
<body>
<div class="page-header">
    <h4>
        进度管理
        <small><i class="ace-icon fa fa-angle-double-right"></i> 交房进度</small>
    </h4>
</div>
<!-- PAGE CONTENT BEGINS -->
<div class="row">
    <div class="col-xs-12">
        <div class="widget-box" id="widget-box-search">
            <div class="widget-header">
                <div class="widget-toolbar">
                    <a href="#" data-action="collapse">
                        <i class="ace-icon fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="widget-body">
                <div class="widget-main no-padding">
                    <form:form servletRelativeAction="/progress/handover" method="get"
                               cssClass="form-horizontal" role="form" cssStyle="padding-top: 10px;" commandName="progressSearchDTO">
                        <div class="row">
                            <div class="col-xs-4 col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-5 col-lg-5">起始日期：</label>
                                    <div class="col-xs-7  col-lg-7">
                                        <input type="text" id="startDate" name="startDate"
                                               class="col-xs-10 col-sm-10 col-lg-8"
                                               value="<fmt:formatDate value="${progressSearchDTO.startDate}" pattern="yyyy-MM-dd"/>"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-4 col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-5 col-lg-5">结束日期：</label>
                                    <div class="col-xs-7  col-lg-7">
                                        <input type="text" id="endDate" name="endDate"
                                               class="col-xs-10 col-sm-10 col-lg-8"
                                               value="<fmt:formatDate value="${progressSearchDTO.endDate}" pattern="yyyy-MM-dd"/>"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4 col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-5 col-lg-5">公司：</label>
                                    <div class="col-xs-7  col-lg-7">
                                        <form:select path="company" cssClass="col-xs-10 col-sm-10 col-lg-8">
                                            <c:if test="${empty companyList}"><form:option
                                                    value="">--暂无公司--</form:option></c:if>
                                            <form:option value="" label="--请选择--"/>
                                            <form:options items="${companyList}" itemValue="name" itemLabel="name"/>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-4 col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-5 col-lg-5">标段：</label>
                                    <div class="col-xs-7  col-lg-7">
                                        <input type="text" id="section" name="section"
                                               class="col-xs-10 col-sm-10 col-lg-8"
                                               value="${progressSearchDTO.section}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-4 col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-5 col-lg-4">组别：</label>
                                    <div class="col-xs-7  col-lg-8">
                                        <input type="text" id="group" name="group" class="col-xs-10 col-sm-10 col-lg-8"
                                               value="${progressSearchDTO.group}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions">
                            <div class="row">
                                <div class="col-sm-3 col-sm-offset-9  col-lg-3 col-lg-offset-9">
                                    <button class="btn btn-primary btn-sm" id="btn-search" type="submit">
                                        <i class="ace-icon fa fa-search"></i> 查询
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="hr hr-18 dotted hr-double"></div>
        <div style="overflow: auto">
            <div id="container" style="min-width: 400px;height: 400px;
            <c:if test="${handoverList.size()>15}">width: ${handoverList.size() * 100}px</c:if>
                    "></div>
        </div>
        <table id="simple-table" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>日期</th>
                <th>交房户数</th>
                <th>待拆除面积（m²）</th>
                <c:if test="${!empty progressSearchDTO.company}">
                    <th>公司</th>
                </c:if>
                <c:if test="${!empty progressSearchDTO.section}">
                    <th>标段</th>
                </c:if>
                <c:if test="${!empty progressSearchDTO.group}">
                    <th>组别</th>
                </c:if>
            </tr>
            </thead>
            <tfoot id="table-foot">
            <tr>
                <th>当前页合计：</th>
                <th class="isSum isHandover"></th>
                <th class="isSum isArea"></th>
                <c:if test="${!empty progressSearchDTO.company}">
                    <th></th>
                </c:if>
                <c:if test="${!empty progressSearchDTO.section}">
                    <th></th>
                </c:if>
                <c:if test="${!empty progressSearchDTO.group}">
                    <th></th>
                </c:if>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
</body>
<javascripts>
    <script src="<c:url value="/statics/components/highcharts/js/highcharts.js"/>"></script>
    <script src="<c:url value="/statics/components/highcharts/js/modules/exporting.js"/>"></script>
    <script src="<c:url value="/statics/components/highcharts/js/modules/no-data-to-display.js"/>"></script>
    <script src="<c:url value="/statics/js/bootstrap-datepicker/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/statics/js/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"/>"></script>
    <script src="<c:url value="/statics/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/statics/js/dataTables.fixedColumns.js"/>"></script>
    <script>
        $(function () {
            var $start = $("#startDate");
            var $end = $("#endDate");
            $start.datepicker({
                format: "yyyy-mm-dd",
                autoclose: true,
                todayHighlight: true,
                language: "zh-CN",
                orientation: "bottom auto"
            });
            $end.datepicker({
                format: "yyyy-mm-dd",
                autoclose: true,
                todayHighlight: true,
                language: "zh-CN",
                orientation: "bottom auto"
            });
            /*横轴长度*/
            var xAxis_length = ${handoverList.size()-1};
            /*横轴 各列数据*/
            var xAxis = [], countHosts = [], countBeforeDemolishedArea = [], countRatio = [];
            <c:forEach items="${handoverList}" var="progress">
            xAxis.push('${progress.title}');
            countHosts.push(${progress.countHandover});
            countBeforeDemolishedArea.push(<fmt:formatNumber value="${progress.countBeforeDemolishedArea}" pattern="0.00"/>);
            if ('${count}' == '0') {
                countRatio.push(0);
            } else {
                countRatio.push(<fmt:formatNumber value="${progress.countLeftDay / count  * 100}" pattern="0.00"/>);
            }
            </c:forEach>

            $('#container').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '交房进度图'
                },
                colors: ['#7cb555',  '#90ed7d'],
                xAxis: {
                    categories: xAxis,
                    max: xAxis_length
                },
                yAxis: [{
                    min: 0,
                    title: {
                        text: '统计'
                    }
                }, {
                    title: {
                        text: '总进度',
                        style: {
                            color: '#FFFFFF'
                        }
                    },
                    labels: {
                        max: 100,
                        formatter: function () {
                            if (this.value > 100) {
                                return '100 %';
                            } else {
                                return this.value + ' %';
                            }
                        },
                        style: {
                            color: '#FFFFFF'
                        }
                    },
                    opposite: true,
                    tickPositions: [0, 25, 50, 75, 100]
                }],
                credits: {
                    enabled: false //去除版权信息
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormatter: function () {
                        if (this.series.index == 0) {
                            return '<tr><td style="color:{series.color};padding:0">' + this.series.name + ': </td>' +
                                '<td style="padding:0"><b>' + this.y + ' 户</b></td></tr>';
                        } else if (this.series.index == 1){
                            return '<tr><td style="color:{series.color};padding:0">' + this.series.name + ': </td>' +
                                '<td style="padding:0"><b>' + this.y + ' m²</b></td></tr>';
                        }else {
                            return '<tr><td style="color:{series.color};padding:0">' + this.series.name + ': </td>' +
                                '<td style="padding:0"><b>' + this.y + ' %</b></td></tr>';
                        }
                    },
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y}'
                        }
                    },
                    line: {
                        dataLabels: {
                            enabled: true,
                            format: '{point.y} %'
                        }
                    }
                },
                series: [{
                    name: '交房户数',
                    data: countHosts
                }, {
                    name: '总待拆除面积',
                    data: countBeforeDemolishedArea,
                    visible: false
                }, {
                    name: '总进度',
                    type: 'line',
                    yAxis: 1,
                    data: countRatio,
                    tooltip: {
                        valueSuffix: ' %'
                    }
                }],
                lang: {
                    contextButtonTitle: '导出按钮文字',
                    decimalPoint: '小数点',
                    downloadJPEG: '导出JPEG',
                    downloadPDF: '导出PDF',
                    downloadPNG: '导出PNG',
                    downloadSVG: '导出SVG',
                    drillUpText: '上钻',
                    invalidDate: '无效的时间',
                    loading: '加载中',
                    months: '月份',
                    noData: '没有数据',
                    numericSymbolMagnitude: '国际单位符基数',
                    numericSymbols: '国际单位符',
                    printChart: '打印图表',
                    resetZoom: '重置缩放比例',
                    resetZoomTitle: '重置缩放标题',
                    shortMonths: '月份缩写',
                    shortWeekdays: '星期缩写',
                    thousandsSep: '千分号',
                    weekdays: '星期'
                }
            });
            /*datatables 表格*/
            var $table_id = $("#simple-table");
            var table = $table_id.DataTable({
                dom: '<".table_area_top" B and >rt<".table_area_bottom" pi>',
                Filter: false, //列筛序功能
                searching: false,//本地搜索
                ordering: false, //排序功能
                Info: true,//页脚信息
                autoWidth: false,//自动宽度
                scrollX: true,
                scrollY: 300,
                scrollCollapse: true,
                Paginate: false, //翻页功能
                sortable: false,
                lengthChange: true,
                lengthMenu: [100],
                processing: true,
                serverSide: false,
                data: [
                    <c:forEach items="${handoverList}" var="progressData">
                    {
                        "title": '${progressData.title}',
                        "countHandover": '${progressData.countHandover}',
                        "countBeforeDemolishedArea": '<fmt:formatNumber value="${progressData.countBeforeDemolishedArea}" pattern="0.00"/>',
                        "geoCo": '${progressData.geoCo}'
                    },
                    </c:forEach>
                ],
                columns: [
                    {
                        data: "title",
                        width: "120px",
                        render: function (data) {
                            return data || "";
                        }
                    }
                ],
                language: { //国际语言转化
                    aria: {
                        sortAscending: " - click/return to sort ascending",
                        sortDescending: " - click/return to sort descending"
                    },
                    lengthMenu: "显示 _MENU_ 记录",
                    zeroRecords: "对不起，查询不到任何相关数据",
                    emptyTable: "未有相关数据",
                    loadingRecords: "正在加载数据-请等待...",
                    info: "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
                    infoEmpty: "当前显示 0 到 0 条，共 0 条记录",
                    infoFiltered: "（共 _MAX_ 条记录）",
                    processing: " 正在加载数据...",
                    search: "全局查询：",
                    url: "",
                    paginate: {
                        first: "首页",
                        previous: " 上一页 ",
                        next: " 下一页 ",
                        last: " 尾页 "
                    }
                }
            });
        })
    </script>
</javascripts>
</html>

