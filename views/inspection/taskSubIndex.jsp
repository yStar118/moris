<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/8/19
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-检查结果</title>
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
                                        <input type="hidden" id="typeSearch" value="${type}"/>
                                        <input type="hidden" id="taskIdSearch" value="${taskId}"/>
                                        <input type="hidden" id="checkResultSearch" value="${checkResult}"/>
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-6 col-lg-5">检查点名称：</label>
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
                                        <th style="background-color: #E5E5E5">任务名称</th>
                                        <th style="background-color: #E5E5E5">部门名称</th>
                                        <th style="background-color: #E5E5E5">岗位名称</th>
                                        <th style="background-color: #E5E5E5">设备名称</th>
                                        <th style="background-color: #E5E5E5">检查对象</th>
                                        <th style="background-color: #E5E5E5">检查点名称</th>
                                        <th style="background-color: #E5E5E5">是否主观判断</th>
                                        <th style="background-color: #E5E5E5">检查结果参数</th>
                                        <th style="background-color: #E5E5E5">检查结果</th>
                                        <th style="background-color: #E5E5E5">检查时间</th>
                                        <th style="background-color: #E5E5E5">现场图片</th>
                                        <th style="background-color: #E5E5E5">检查行为</th>
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
<div id="dialog-abnormalityImg" class="hide center" style="overflow-y: scroll;text-align: center">
    <img id="abnormalityImg" src="" style="max-width: 95%">
</div>
<%@ include file="../common/js.jsp" %>
<script src="${staticServer }/assets/components/jquery-ui/jquery-ui.js?versionNo=${versionNo}"></script>
<script>
    $(function () {

        /*把要初始化的table的对象赋值*/
        var $table_id = $("#task");
        /*自定义查询参数*/
        var taskParam = {
            getQueryCondition: function (data) {
                var param = {};
                param.pointName = $("#nameSearch").val();
                param.type = $("#typeSearch").val();
                param.taskId = $("#taskIdSearch").val();
                param.checkResult = $("#checkResultSearch").val();
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
                    url: '${staticServer}/portal/inspection/taskSub/getList.do',//请求数据的参数
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
                    data: "taskName",
                    render: function (data) {
                        return data || "";
                    }
                },
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
                    data: "equipmentName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "objectName",
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
                    data: "isSubJudge",
                    render: function (data) {
                        switch (data) {
                            case  0 :
                                return "否";
                                break;
                            case  1 :
                                return "是";
                                break;
                            default:
                                return "";
                                break;
                        }
                    }
                },
                {
                    data: "checkResultValue",
                    render: function (data, type, row) {
                        if (row.isSubJudge == 1) {
                            if (data == 1) {
                                return "√";
                            } else if (data == 2) {
                                return "备用";
                            } else if (data == 0 && row.checkDate != "" && row.checkDate != null) {
                                return "×";
                            } else {
                                return "";
                            }
                        } else {
                            return data || "";
                        }
                    }
                },
                {
                    data: "checkResult",
                    render: function (data) {
                        switch (data) {
                            case  0 :
                                return "正常";
                                break;
                            case  1 :
                                return "异常";
                                break;
                            case  2 :
                                return "备用";
                                break;
                            default:
                                return "";
                                break;
                        }
                    }
                },
                {
                    data: "checkDate",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "filePath",
                    render: function (data) {
                        if (data != null && data != "") {
                            return '<button class="imgButton btn btn-sm btn-info " onclick="imgButton(\'' + data + '\')" type="button">查看现场图片</button>';
                        } else {
                            return '';
                        }
                    }
                },
                {
                    data: "id",
                    width: "140px",
                    render: function (data, type, row) {
                        if (row.checkBehavior != null && row.checkBehavior != 0) {
                            if (row.checkBehavior == 1) {
                                return "<span class=\"label label-danger arrowed-in\">谎报</span>";
                            } else if (row.checkBehavior == 2) {
                                return "<span class=\"label label-danger arrowed-in\">误判</span>";
                            } else {
                                return "";
                            }
                        } else {
                            return '<a class="btn btn-sm btn-warning" onclick=" lieAboutTask(this,\'' + data + '\')">' +
                                '<i class="ace-icon fa fa-pencil-square-o "></i>谎报</a> &nbsp;&nbsp;' +
                                '<a class="btn btn-sm btn-warning" onclick="BadJudgmentTask(this,\'' + data + '\')">' +
                                '<i class="ace-icon fa fa-pencil-square-o "></i>误判</a>';
                        }
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

    function lieAboutTask(event, id) {
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要标记该检查结果为谎报吗？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/inspection/taskSub/lieAboutTask.do",
                    data: {
                        "id": id
                    },
                    contentType: 'application/json',
                    success: function (data) {
                        $(event).parent("td").html("<span class=\"label label-danger arrowed-in\">谎报</span>");
                    }
                });
            }
        });
    }

    function BadJudgmentTask(event, id) {
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要标记该检查结果为误判吗？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/inspection/taskSub/badJudgmentTask.do",
                    data: {
                        "id": id
                    },
                    contentType: 'application/json',
                    success: function (data) {
                        $(event).parent("td").html("<span class=\"label label-danger arrowed-in\">误判</span>");
                    }
                });
            }
        });
    }

    /*详情*/
    function detailTask(id) {
        window.location.href = "${staticServer}/portal/inspection/task/detail.do?id=" + id;
    }

    /*查看图片*/
    function imgButton(img) {
        $("#abnormalityImg").attr("src", '${imageServer}' + img);
        var dialog = $("#dialog-abnormalityImg").removeClass('hide').dialog({
            modal: true,
            title: "查看现场图片",
            title_html: false,
            buttons: [
                {
                    text: "返回",
                    "class": "btn btn-minier btn-center",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
            ]
        });
        $("[aria-describedby=dialog-abnormalityImg]").css({
            "top": "7%",
            "width": "70%",
            "height": "70%",
            "left": "15%",
            "overflow-y": "scroll",
            "position": "fixed"
        });
    }

</script>
</body>
</html>




