<%--
  Created by IntelliJ IDEA.
  User: Mac
  Date: 2017/9/5
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-系统通知</title>
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
                                            <div class="col-xs-4">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 ">通知等级：</label>
                                                    <div class="col-xs-7 ">
                                                        <select class="col-xs-9"
                                                                id="typeSearch">
                                                            <option value="">请选择通知等级</option>
                                                            <option value="1">第一级</option>
                                                            <option value="2">第二级</option>
                                                            <option value="3">第三级</option>
                                                        </select>
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
                                                       href="#modal-edit" data-toggle="modal"
                                                       data-backdrop="static" onclick="flushForm()"
                                                       id="btn-add">
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
                                <table id="warning" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="background-color: #E5E5E5">部门</th>
                                        <th style="background-color: #E5E5E5">岗位</th>
                                        <th style="background-color: #E5E5E5">姓名</th>
                                        <th style="background-color: #E5E5E5">手机号</th>
                                        <th style="background-color: #E5E5E5">通知等级</th>
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
<div id="modal-edit" class="modal fade" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 50%;top: 18%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only"></span>
                </button>
                <h4 class="modal-title">新增警告通知人</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <form id="warningForm" class="form-horizontal"
                              action="${staticServer}/portal/base/warning/save.do" method="post">
                            <input id="id" name="id" value="" type="hidden"/>
                            <input id="userId" name="userId" value="" type="hidden"/>
                            <input id="userRealName" name="userRealName" value="" type="hidden"/>
                            <input id="userDepartmentName" name="userDepartmentName" value="" type="hidden"/>
                            <input id="userJobsName" name="userJobsName" value="" type="hidden"/>
                            <div class="form-group">
                            <label class="col-sm-3 control-label">通知部门：</label>
                            <div class="col-sm-9">
                                <select class="col-xs-12 col-sm-7"
                                        id="departmentSelect">
                                    <option value="">请选择部门</option>
                                    <c:forEach items="${departmentList}" var="department">
                                        <option value="${department.code}">${department.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通知岗位：</label>
                                <div class="col-sm-9">
                                    <select class="col-xs-12 col-sm-7"
                                            id="jobsSelect">
                                        <option value="">请选择岗位</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通知人：</label>
                                <div class="col-sm-9">
                                    <select class="col-xs-12 col-sm-7"
                                            id="userSelect">
                                        <option value="">请选择职工</option>
                                    </select>
                                    &nbsp;&nbsp;<label id="userSelectTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="telephone" name="telephone" maxlength="256"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="telephoneTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">响应时间：</label>
                                <div class="col-sm-9">
                                    <select class="col-xs-12 col-sm-7"
                                            id="responseTime" name="responseTime">
                                        <option value="">请选择响应时间</option>
                                        <option value="60">1分钟</option>
                                        <option value="120">2分钟</option>
                                        <option value="180">3分钟</option>
                                        <option value="240">4分钟</option>
                                        <option value="300">5分钟</option>
                                    </select>
                                    &nbsp;&nbsp;<label id="responseTimeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通知等级：</label>
                                <div class="col-sm-9">
                                    <select class="col-xs-12 col-sm-7"
                                            id="type" name="type">
                                        <option value="">请选择通知等级</option>
                                        <option value="1">第一通知人</option>
                                        <option value="2">第二通知人</option>
                                        <option value="3">第三通知人</option>
                                    </select>
                                    &nbsp;&nbsp;<label id="typeTip"></label>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-4 col-md-8">
                                    <button class="btn btn-primary btn-sm" type="submit">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 保存
                                    </button>&nbsp;&nbsp;
                                    <button class="btn btn-sm" type="button" data-dismiss="modal">
                                        <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/js.jsp" %>
<script>
    $(function () {

        /*根据部门编号获取岗位list*/
        $("#departmentSelect").on("change", function () {
            var $optionSelected = $("#departmentSelect option:selected");
            $.ajax({
                type: "GET",
                url: "${staticServer}/portal/organization/jobs/findByDepartmentCode.do",
                data: {
                    "departmentCode": $optionSelected.val()
                },
                contentType: 'application/json',
                success: function (data) {
                    $("#jobsSelect").empty();
                    $("#jobsSelect").append('<option value="">请选择岗位</option>');
                    $.each(data, function (i, item) {
                        $("#jobsSelect").append("<option value='" + item.id + "'>" + item.name + "</option>")
                    })
                }
            });
        });

        /*根据岗位id获取用户list*/
        $("#jobsSelect").on("change", function () {
            var $optionSelected = $("#jobsSelect option:selected");
            $.ajax({
                type: "GET",
                url: "${staticServer}/portal/sys/user/findByJobsId.do",
                data: {
                    "jobsId": $optionSelected.val()
                },
                contentType: 'application/json',
                success: function (data) {
                    $("#userSelect").empty();
                    $("#userSelect").append('<option value="">请选择职工</option>');
                    $.each(data, function (i, item) {
                        $("#userSelect").append("<option value='" + item.username + "'>" + item.realname + "</option>")
                    })
                }
            });
        });

        /*根据用户username*/
        $("#userSelect").on("change", function () {
            var $optionSelected = $("#userSelect option:selected");
            $.ajax({
                type: "GET",
                url: "${staticServer}/portal/sys/user/getUserByUsername.do",
                data: {
                    "username": $optionSelected.val()
                },
                contentType: 'application/json',
                success: function (data) {
                    $("#userId").val(data.id);
                    $("#userRealName").val(data.realname);
                    $("#userDepartmentName").val(data.departmentName);
                    $("#userJobsName").val(data.jobsName);
                    $("#telephone").val(data.telephone);
                }
            });
        });

        /*把要初始化的table的对象赋值*/
        var $table_id = $("#warning");
        /*自定义查询参数*/
        var warningParam = {
            getQueryCondition: function (data) {
                var param = {};
                param.type = $("#typeSearch").val();
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
                    url: '${staticServer}/portal/base/warning/getList.do',//请求数据的参数
                    data: warningParam.getQueryCondition(data),
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
                    data: "userDepartmentName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "userJobsName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "userRealName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "telephone",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "type",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "id",
                    width: "80px",
                    render: function (data, type, row) {
                        return '<a class="btn btn-sm btn-danger" onclick="delwarning(\'' + data + '\')">' +
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
        /*validate验证*/
        $("#warningForm").validate({
            errorElement: "label",
            errorClass: "validError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                telephone: {
                    required: true
                },
                responseTimeTip: {
                    required: true
                },
                type: {
                    required: true
                },
                userSelectTip: {
                    required: true
                }
            },
            submitHandler: function (form) {
                $("#warningForm").ajaxSubmit({
                    success: function () {
                        $('#modal-edit').modal('hide');
                        table.draw();
                    }
                });
            }
        });
    });

    /*新增时清空表单数据*/
    function flushForm() {
        $("#warningForm").find("input,select").each(function () {
            var id = $(this).attr("id");
            $("#" + id).val("");
        });
    }


    /*删除*/
    function delwarning(id) {
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要删除该通知信息？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/base/warning/delete.do",
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



