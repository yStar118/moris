<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/6/25
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-应急预案</title>
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
                                                    <a type="button" class="btn btn-success btn-sm"
                                                       onclick="saveInfo('')" id="btn-add">
                                                        <i class="ace-icon fa fa-search"></i> 新增
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
                                <table id="info" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="background-color: #E5E5E5">分类名称</th>
                                        <th style="background-color: #E5E5E5">名称</th>
                                        <th style="background-color: #E5E5E5">附件</th>
                                        <th style="background-color: #E5E5E5">创建时间</th>
                                        <th style="background-color: #E5E5E5">创建人</th>
                                        <th style="background-color: #E5E5E5">修改时间</th>
                                        <th style="background-color: #E5E5E5">修改人</th>
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
        var $table_id = $("#info");
        /*自定义查询参数*/
        var infoParam = {
            getQueryCondition: function (data) {
                var param = {};
                param.name = $("#nameSearch").val();
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
                    url: '${staticServer}/portal/contingency/info/getList.do',//请求数据的参数
                    data: infoParam.getQueryCondition(data),
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
                    data: "categoryName",
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
                    data: "fileName",
                    render: function (data, type, row) {
                        if (data !== null && data !== "") {
                            var strFilter = ".jpeg|.gif|.jpg|.png|.bmp|.pic|.JPEG|.GIF|.JPG|.PNG|.BPM|.PIC|";
                            if (data.indexOf(".") > -1) {
                                var p = data.lastIndexOf(".");
                                var strPostfix = data.substring(p, data.length) + '|';
                                strPostfix = strPostfix.toLowerCase();
                                if (strFilter.indexOf(strPostfix) > -1) {
                                    return '<a target="_black" href="${imageServer}' + row.filePath + '">' + data + '</a>';
                                }
                            }
                            return '<a download="'+ row.fileName+'" href="${imageServer}' + row.filePath + '">' + data + '</a>';
                        } else {
                            return "";
                        }
                    }
                },
                {
                    data: "createDate",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "createUserName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "updateDate",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "updateUserName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "id",
                    width: "230px",
                    render: function (data, type, row) {
                        return '<a class="btn  btn-sm btn-info" onclick="detailInfo(\'' + data + '\')">' +
                            '<i class="ace-icon fa fa-pencil-square-o "></i>详情</a>' +
                            '&nbsp;&nbsp;' +
                            '<a class="btn  btn-sm btn-success" onclick="saveInfo(\'' + data + '\')">' +
                            '<i class="ace-icon fa fa-pencil-square-o "></i>修改</a>' +
                            '&nbsp;&nbsp;' +
                            '<a class="btn btn-sm btn-danger" onclick="delInfo(\'' + data + '\')">' +
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
    function saveInfo(id) {
        window.location.href = "${staticServer}/portal/contingency/info/toSave.do?id=" + id;
    }

    /*详情*/
    function detailInfo(id) {
        window.location.href = "${staticServer}/portal/contingency/info/detail.do?id=" + id;
    }

    /*删除*/
    function delInfo(id) {
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要删除该应急预案？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/contingency/info/delete.do",
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



