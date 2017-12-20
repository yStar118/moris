<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/8/10
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-检查任务</title>
    <%@ include file="../common/header.jsp" %>
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
                                                    <label class="control-label col-xs-6 col-lg-5">名称：</label>
                                                    <div class="col-xs-6 col-lg-7">
                                                        <input type="text" id="nameSearch"
                                                               class="col-xs-12 col-sm-12 col-lg-10"
                                                               value="" title=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            id="btn-search">
                                                        <i class="ace-icon fa fa-search"></i> 查询
                                                    </button>&nbsp;&nbsp;
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
                                <table id="task" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="background-color: #E5E5E5">部门名称</th>
                                        <th style="background-color: #E5E5E5">检查任务名称</th>
                                        <th style="background-color: #E5E5E5">检查人姓名</th>
                                        <th style="background-color: #E5E5E5">开始时间</th>
                                        <th style="background-color: #E5E5E5">结束时间</th>
                                        <th style="background-color: #E5E5E5">上传时间</th>
                                        <th style="background-color: #E5E5E5">检查点数量</th>
                                        <th style="background-color: #E5E5E5">任务状态</th>
                                        <th style="background-color: #E5E5E5">操作</th>
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
<script>
    $(function () {

        /*把要初始化的table的对象赋值*/
        var $table_id = $("#task");
        /*自定义查询参数*/
        var taskParam = {
            getQueryCondition: function (data) {
                var param = {};
                param.name = $("#nameSearch").val();
                param.type = ${type};
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
            Info: true,//页脚信息
            lengthMenu: [15],
            scrollY: "auto",
            Paginate: true, //翻页功能
            sortable: false,
            processing: true,
            serverSide: true,
            ajax: function (data, callback) {//ajax配置为function,手动调用异步查询
                $.ajax({
                    type: "GET",
                    url: '${staticServer}/portal/inspection/task/getList.do',//请求数据的参数
                    data: taskParam.getQueryCondition(data),
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
                    data: "name",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "checkUserName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "startDate",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "endDate",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "uploadDate",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "pointQuantity",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "status",
                    render: function (data) {
                        switch (data) {
                            case 0 :
                                return '<span class="label label-warning">未分配待分配</span>';
                            case 1 :
                                return '<span class="label ">已分配待确认</span>';
                            case 2 :
                                return '<span class="label label-info">已确认待检查</span>';
                            case 3 :
                                return '<span class="label label-success arrowed">检查完成</span>';
                            case 4 :
                                return '<span class="label label-danger arrowed-in">空检</span>';
                            default :
                                return "";
                        }
                    }
                },
                {
                    data: "id",
                    width: "80px",
                    render: function (data, type, row) {
                        return '<a class="btn btn-sm btn-danger" onclick="delTask(\'' + data + '\')">' +
                            '<i class="ace-icon fa fa-pencil-square-o "></i>删除</a>';
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

    /*编辑*/
    function saveTask(id) {
        window.location.href = "${staticServer}/portal/inspection/task/toSave.do?id=" + id;
    }


    /*删除*/
    function delTask(id) {
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要删除该检查任务吗？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/inspection/task/delete.do",
                    data: {
                        "id": id
                    },
                    contentType: 'application/json',
                    success: function () {
                        table.draw();
                    }
                });
            }
        });
    }

</script>
</body>
</html>



