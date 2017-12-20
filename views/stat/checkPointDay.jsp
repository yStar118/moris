<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/11/7
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-检查行为日报表</title>
    <%@ include file="../common/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticServer }/assets/datetimepicker/jquery.datetimepicker.css"/>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>
<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
            </div>
            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
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
                                    <form:form action="/" method="post"
                                               cssClass="form-horizontal"
                                               cssStyle="padding-top: 10px;">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-6 col-lg-5">部门名称：</label>
                                                    <div class="col-xs-6 col-lg-7">
                                                        <input type="text" id="departmentNameSearch"
                                                               class="col-xs-12 col-sm-12 col-lg-10"
                                                               value="" title=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-6 col-lg-5">开始时间：</label>
                                                    <div class="col-xs-6 col-lg-7">
                                                        <input type="text" id="statStartDate" name="statStartDate"
                                                               class="col-xs-12 col-sm-12 col-lg-10"
                                                               value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-6 col-lg-5">结束时间：</label>
                                                    <div class="col-xs-6 col-lg-7">
                                                        <input type="text" id="statEndDate" name="statEndDate"
                                                               class="col-xs-12 col-sm-12 col-lg-10"
                                                               value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            id="btn-search">
                                                        <i class="ace-icon fa fa-search"></i> 查询
                                                    </button>&nbsp;&nbsp;
                                                    <a type="button" class="btn btn-success btn-sm"
                                                       onclick="prints()" id="btn-prints">
                                                        <i class="ace-icon fa fa-search"></i> 导出
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div class="row">
                            <div class="col-xs-12">
                                <table id="statCheckPoint" class="table table-striped table-bordered table-hover"
                                       style="width: 100%">
                                    <thead>
                                    <tr>
                                        <th style="background-color: #E5E5E5">部门名称</th>
                                        <th style="background-color: #E5E5E5">岗位名称</th>
                                        <th style="background-color: #E5E5E5">检查点名称</th>
                                        <th style="background-color: #E5E5E5">完成数量</th>
                                        <th style="background-color: #E5E5E5">完成率</th>
                                        <th style="background-color: #E5E5E5">漏检数量</th>
                                        <th style="background-color: #E5E5E5">漏检率</th>
                                        <th style="background-color: #E5E5E5">空检数量</th>
                                        <th style="background-color: #E5E5E5">空检率</th>
                                        <th style="background-color: #E5E5E5">弱检数量</th>
                                        <th style="background-color: #E5E5E5">弱检率</th>
                                        <th style="background-color: #E5E5E5">总数</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                            <!-- /.span -->
                        </div>
                    </div>
                    <!-- /.span -->
                </div>
            </div>
            <!-- /.main-content -->
        </div>
        <!-- /.main-container -->
    </div>
</div>
<%@ include file="../common/js.jsp" %>
<script src="${staticServer }/assets/datetimepicker/jquery.datetimepicker.js" type="text/javascript"></script>
<script>

    var statCheckPointParam;
    $(function () {

        var $start = $("#statStartDate");
        var $end = $("#statEndDate");
        $start.datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d',
            formatDate: 'Y-m-d'
        });

        $end.datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d',
            formatDate: 'Y-m-d'
        });

        /*把要初始化的table的对象赋值*/
        var $table_id = $("#statCheckPoint");
        /*自定义查询参数*/
        statCheckPointParam = {
            getQueryCondition: function (data) {
                var param = {};
                param.departmentName = $("#departmentNameSearch").val();
                param.statStartDate = $("#statStartDate").val();
                param.statEndDate = $("#statEndDate").val();
                param.index = data.start;
                param.length = data.length;
                return param;
            }
        };
        //object可以如下初始化表格
        window.table = $table_id.DataTable({
            dom: '<".table_area_top" B and >t<".table_area_bottom" pi>',
            Filter: false, //列筛序功能
            searching: false,//本地搜索
            ordering: false, //排序功能
            Info: false,//页脚信息
            lengthMenu: [15],
            scrollX: true,
            scrollY: true,
            Paginate: true, //翻页功能
            sortable: false,
            processing: true,
            serverSide: true,
            ajax: function (data, callback) {//ajax配置为function,手动调用异步查询
                $.ajax({
                    type: "GET",
                    url: '${staticServer}/portal/stat/checkPoint/getListForDay.do',//请求数据的参数
                    data: statCheckPointParam.getQueryCondition(data),
                    cache: false,  //禁用缓存
                    dataType: "json",
                    success: function (result) {
                        callback(result);
                    }
                });
            },
            //使用对象数组，一定要配置columns，告诉 DataTables 每列对应的属性
            //data 这里是固定不变的，id，name，age，sex等为你数据里对应的属性
            columns: [
                {
                    data: "departmentName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "jobsName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "pointName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "finishQuantity",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "finishRate",
                    render: function (data) {
                        return data + "%" || "";
                    }
                },
                {
                    data: "leakageQuantity",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "leakageRate",
                    render: function (data) {
                        return data + "%" || "";
                    }
                },
                {
                    data: "emptyQuantity",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "emptyRate",
                    render: function (data) {
                        return data + "%" || "";
                    }
                },
                {
                    data: "weakQuantity",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "weakRate",
                    render: function (data) {
                        if (data != null) {
                            return data + "%" || "";
                        } else {
                            return "";
                        }

                    }
                },
                {
                    data: "totalQuantity",
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
                infoEmpty: "当前显示0到0条，共0条记录",
                infoFiltered: "（数据库中共为 _MAX_ 条记录）",
                processing: " 正在加载数据...",
                //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
                paginate: {
                    first: "首页",
                    previous: " 上一页 ",
                    next: " 下一页 ",
                    last: " 尾页 "
                }
            }
        });
        /*条件查询*/
        $("#btn-search").on("click", function () {
            table.draw();
        });
    });

    /*导出*/
    function prints() {
        var url = "${staticServer}/portal/stat/checkPoint/print.do?type=1&index=0&length=99999";
        if ($("#departmentNameSearch").val() != '')
            url += "&departmentName=" + $("#departmentNameSearch").val();
        if ($("#statStartDate").val() != '')
            url += "&statStartDate=" + $("#statStartDate").val();
        if ($("#statEndDate").val() != '')
            url += "&statEndDate=" + $("#statEndDate").val();
        window.location = url;
    }

</script>
<style>
    #statCheckPoint tbody tr {
        min-height: 50px;
    }
</style>
</body>
</html>






