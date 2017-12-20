<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/7/9
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-检查对象</title>
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
                                <table id="object" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="background-color: #E5E5E5">部门名称</th>
                                        <th style="background-color: #E5E5E5">岗位名称</th>
                                        <th style="background-color: #E5E5E5">设备名称</th>
                                        <th style="background-color: #E5E5E5">检查对象名称</th>
                                        <th style="background-color: #E5E5E5">型号规格</th>
                                        <th style="background-color: #E5E5E5">检查对象分类</th>
                                        <th style="background-color: #E5E5E5">创建时间</th>
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
    <div class="modal-dialog" style="width: 50%;top: 7%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only"></span>
                </button>
                <h4 class="modal-title">编辑检查对象</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <form id="objectForm" name="objectForm" class="form-horizontal"
                              action="${staticServer}/portal/inspection/object/save.do" method="post">
                            <input id="id" name="id" value="" type="hidden"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门名称：</label>
                                <div class="col-sm-9" id="departmentCodeDivView" style="display: none"></div>
                                <div class="col-sm-9" id="departmentCodeDiv" style="display: block">
                                    <input type="hidden" id="departmentName" name="departmentName" value=""/>
                                    <select id="departmentCode" name="departmentCode"
                                            class="col-xs-10 col-sm-7">
                                        <option>暂无部门</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">岗位名称：</label>
                                <div class="col-sm-9" id="jobsCodeDivView" style="display: none"></div>
                                <div class="col-sm-9" id="jobsCodeDiv" style="display: block">
                                    <input type="hidden" id="jobsName" name="jobsName" value=""/>
                                    <select id="jobsCode" name="jobsCode" class="col-xs-10 col-sm-7">
                                        <option>暂无岗位</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备编号：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="equipmentCode" name="equipmentCode" maxlength="256"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="equipmentCodeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="equipmentName" name="equipmentName" maxlength="256"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="equipmentNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象编号：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="code" name="code" maxlength="256" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="codeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="name" name="name" maxlength="256" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">型号规格：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="specifications" name="specifications" maxlength="256"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="specificationsTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-9">
                                      <textarea id="remark" name="remark" class="col-xs-12 col-sm-7" rows="5"
                                                placeholder="请输入备注..." maxlength="255"></textarea>
                                    <label id="remarkTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">tag：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="tag" name="tag" maxlength="256" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="tagTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">gps：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="gps" name="gps" maxlength="256" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="gpsTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象分类：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="categoryName" name="categoryName" maxlength="256"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="categoryNameTip"></label>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-4 col-md-8">
                                    <button class="btn btn-primary btn-sm" type="submit" id="formSubmit">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 保存
                                    </button>&nbsp;&nbsp;
                                    <button class="btn  btn-sm" type="button" data-dismiss="modal" id="formBack">
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
<script src="${staticServer }/assets/js/jquery.form.js"></script>
<script src="${staticServer }/assets/js/jquery.validation/jquery.validate.js"></script>
<script src="${staticServer }/assets/js/jquery.validation/jquery.validate.zh-CN.js"></script>
<script src="${staticServer}/assets/components/bootstrap-multiselect/dist/js/bootstrap-multiselect.js"></script>
<script>
    $(function () {

        <c:if test="${departmentList.size()>0}">
        var vHTML = '<option value="">请选择部门</option>';
        <c:forEach items="${departmentList}" var="department">
        vHTML += '<option value="${department.code}">${department.name}</option>';
        </c:forEach>
        $("#departmentCode").html(vHTML);
        </c:if>

        /*把要初始化的table的对象赋值*/
        var $table_id = $("#object");
        /*自定义查询参数*/
        var objectParam = {
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
                    url: '${staticServer}/portal/inspection/object/getList.do',//请求数据的参数
                    data: objectParam.getQueryCondition(data),
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
                    data: "equipmentName",
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
                    data: "specifications",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "categoryName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "createDate",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "id",
                    width: "230px",
                    render: function (data, type, row) {
                        return '<a class="btn  btn-sm btn-info" href="#modal-edit" data-toggle="modal"' +
                            '  data-backdrop="static"  onclick="detailObject(\'' + data + '\')">' +
                            '<i class="ace-icon fa fa-pencil-square-o "></i>详情</a>' +
                            '&nbsp;&nbsp;' +
                            '<a class="btn  btn-sm btn-success" href="#modal-edit" data-toggle="modal" ' +
                            ' data-backdrop="static"  onclick="updateObject(\'' + data + '\')">' +
                            '<i class="ace-icon fa fa-pencil-square-o "></i>修改</a>' +
                            '&nbsp;&nbsp;' +
                            '<a class="btn btn-sm btn-danger" onclick="delObject(\'' + data + '\')">' +
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
        $("#objectForm").validate({
            errorElement: "label",
            errorClass: "validError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                name: {
                    required: true,
                    maxlength: 255
                },
                content: {
                    required: true,
                    maxlength: 500
                }
            },
            submitHandler: function (form) {
                $("#objectForm").ajaxSubmit({
                    success: function () {
                        $('#modal-edit').modal('hide');
                        table.draw();
                    }
                });
            }
        });

        $("#departmentCode").change(function () {
            var departmentCode = $(this).val();
            $.ajax({
                type: "GET",
                url: "${staticServer}/portal/organization/jobs/getList.do",
                data: {
                    "departmentCode": departmentCode,
                    "page": 0,
                    "length": 9999
                },
                contentType: 'application/json',
                success: function (data) {
                    if (data.recordsTotal > 0) {
                        var vHTML = '<option value="">请选择岗位</option>';
                        $.each(data.data, function (i, item) {
                            vHTML += '<option value="' + item.code + '">' + item.name + '</option>';
                            $("#jobsCode").html(vHTML);
                        })
                    }
                }
            });
        })
    });

    /*新增时清空表单数据*/
    function flushForm() {
        $("#formSubmit").attr("style", "display:black");
        $("#formBack").html('<i class="ace-icon fa fa-undo bigger-110"></i>取消');
        $("#code").removeAttr("readonly");
        $("#objectForm").find("input,select,textarea").each(function () {
            var id = $(this).attr("id");
            $("#" + id).val("");
        });
    }

    /*修改时需要回显数据*/
    function updateObject(id) {
        flushForm();
        $("#jobsCodeDivView").empty();
        $("#departmentCodeDivView").empty();
        $("#departmentCodeDiv").attr("style", "display:none");
        $("#jobsCodeDiv").attr("style", "display:none");
        $("#code").attr("readonly", "true");
        $.ajax({
            type: "GET",
            url: "${staticServer}/portal/inspection/object/findOne.do",
            data: {
                "id": id
            },
            contentType: 'application/json',
            success: function (data) {
                $("#departmentCodeDiv").attr("style", "display:block");
                $("#jobsCodeDiv").attr("style", "display:block");
                var departmentCode = data.departmentCode;
                var object = data;
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/organization/jobs/getList.do",
                    data: {
                        "departmentCode": departmentCode,
                        "page": 0,
                        "length": 9999
                    },
                    contentType: 'application/json',
                    success: function (data) {
                        if (data.recordsTotal > 0) {
                            var vHTML = '<option value="">请选择岗位</option>';
                            $.each(data.data, function (i, item) {
                                vHTML += '<option value="' + item.code + '">' + item.name + '</option>';
                                $("#jobsCode").html(vHTML);
                            })
                        }
                        for (var key in object) {
                            $("#" + key).val(object[key]);
                            $("#" + key).removeAttr("readonly");
                        }
                    }
                });
            }
        });
    }

    /*详情*/
    function detailObject(id) {
        flushForm();
        $("#jobsCodeDivView").empty();
        $("#departmentCodeDivView").empty();
        $("#formSubmit").attr("style", "display:none");
        $("#formBack").html('<i class="ace-icon fa fa-undo bigger-110"></i>返回');
        $.ajax({
            type: "GET",
            url: "${staticServer}/portal/inspection/object/findOne.do",
            data: {
                "id": id
            },
            contentType: 'application/json',
            success: function (data) {
                $("#departmentCodeDiv").attr("style", "display:none");
                $("#jobsCodeDiv").attr("style", "display:none");
                for (var key in data) {
                    $("#" + key).val(data[key]);
                    $("#" + key).attr("readonly", "true");
                }
                $("#jobsCodeDivView").append("<input type='text' class='col-xs-12 col-sm-7'" +
                    " readonly value='" + data.jobsName + "'/>");
                $("#departmentCodeDivView").append("<input type='text' class='col-xs-12 col-sm-7'" +
                    " readonly value='" + data.departmentName + "'/>");
                $("#jobsCodeDivView").attr("style", "display:block");
                $("#departmentCodeDivView").attr("style", "display:block");

            }
        });
    }

    /*删除*/
    function delObject(id) {
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要删除该应急预案分类？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/inspection/object/delete.do",
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



