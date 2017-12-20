<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/6/22
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-通知公告</title>
    <%@ include file="../common/header.jsp" %>
    <link rel="stylesheet"
          href="${staticServer}/assets/components/bootstrap-multiselect/dist/css/bootstrap-multiselect.css"/>
    <link rel="stylesheet" href="${staticServer}/assets/select2/select2.css"/>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>

<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">通知公告</li>
                    <li class="active"><c:if test="${type==1}">报警预警</c:if>
                        <c:if test="${type==2}">系统通知</c:if>
                        <c:if test="${type==3}">命令通知</c:if></li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <c:if test="${type==1}">警告通知</c:if>
                        <c:if test="${type==2}">系统通知</c:if>
                        <c:if test="${type==3}">命令通知</c:if>
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 新增通知
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="userForm" name="userForm" class="form-horizontal"
                              action="${staticServer}/portal/base/notice/add.do" method="post">
                            <input id="type" name="type" value="${type}" type="hidden"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通知名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="name" name="name" maxlength="256" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           title="" value="">&nbsp;&nbsp;<label id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通知内容：</label>
                                <div class="col-sm-9">
                                    <textarea id="content" name="content" class="col-xs-12 col-sm-7" rows="5"
                                              placeholder="请输入通知内容..."
                                              maxlength="500"></textarea>
                                    <label id="contentTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">附件：</label>
                                <input type="hidden" id="filePath" name="filePath" value=""/>
                                <input type="hidden" id="fileName" name="fileName" value=""/>
                                <div class="col-sm-5 input-group" id="fileDiv" style="padding: 0 15px 0 15px">
                                    <button id="checkFile" href="#material-modal" type="button"
                                            class="btn btn-info btn-sm "
                                            data-toggle="modal">
                                        <i class="ace-icon glyphicon glyphicon-picture bigger-110"></i> 选择文件
                                    </button>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否需要确认：</label>
                                <div class="col-sm-9">
                                    <div class="radio">
                                        <label>
                                            <input name="isConfirm" type="radio" class="ace" value="1">
                                            <span class="lbl">是</span>
                                        </label>
                                        <label>
                                            <input name="isConfirm" type="radio" class="ace" value="0">
                                            <span class="lbl">否</span>
                                        </label>
                                        <label id="isConfirmTip"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通知部门：</label>
                                <div class="col-sm-9">
                                    <select id="departmentIds" name="departmentIds"
                                            class="col-xs-12 col-sm-7 col-lg-7"></select>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn btn-primary" type="submit">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 保存
                                    </button>
                                    <button class="btn" type="button" onclick="history.back()">
                                        <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                    </button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <!-- /.main-content -->
            </div>
        </div>
    </div>
</div>
<div id="material-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    上传附件
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <c:url value="${staticServer}/common/upload.do" var="upload_url"/>
                            <form:form action="${upload_url}"
                                       enctype="multipart/form-data" method="post" id="file-form">
                                <input type="hidden" name="file_type" value="contingencyInfo">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <input name="file" type="file" id="file-input"/>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 上传
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button"
                                                id="close-modal"
                                                data-dismiss="modal">
                                            <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer no-margin-top"></div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.main-container -->
<%@ include file="../common/js.jsp" %>
<script src="${staticServer }/assets/select2/select-topic-tags.js"></script>
<script src="${staticServer }/assets/select2/select2.full.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        var data = [];
        <c:forEach items="${departmentList}" var="department">
        data.push({id: '${department.id}', text: '${department.name}'});
        </c:forEach>
        //部门 select2 显示
        var $departmentIds = $('#departmentIds');
        $departmentIds.select2({
            tags: true,
            data: data,
            multiple: true,
            createTag: function (params) {
                return null;
            },
            allowClear: true,
            placeholder: '请输入要关联的部门名称',
            language: {
                noResults: function () {
                    return '没有找到该部门';
                },
                inputTooShort: function () {
                    return '请至少输入1个字符';
                }
            }
        });


        $("#userForm").validate({
            //debug : true,
            errorElement: "label",
            errorClass: "valiError",
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
                form.submit();
            }
        });

        //操作背景图片
        $('#file-input').ace_file_input({
            style: 'well',
            btn_choose: '点击选择',
            btn_change: null,
            no_icon: 'ace-icon fa fa-folder-open-o',
            droppable: false,
            thumbnail: 'large',
            maxSize: 1024 * 1000 * 10,
            before_remove: function () {
                return true;
            }
        }).on('change', function () {
            var $file_input = $('#file-input');
            if ($file_input.val() == '') {
                $file_input.ace_file_input('reset_input');
            }
        }).on('file.error.ace', function (ev, info) {
            if (info.error_count['size'])
                $.notify({message: "文件大小不超过10M!!", z_index: 1051});
        });
        var $file_form = $("#file-form");
        $file_form.on('submit', function () {
            if (!$("#file-input").val()) return false;

            $file_form.ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: $file_form.attr('action'),
                dataType: 'json',
                success: function (result) {
                    if (result && result.success) {
                        var fileName = result.createFileName;
                        var filePath = result.createFilePath;
                        $("#close-modal").click();
                        var vHTML = "<input type=\"text\" readonly class=\"form-control search-query\" value='" + fileName + "'>\
                            <span class=\"input-group-btn\">\
                                <button id='delFile' type=\"button\" class=\"btn btn-purple btn-sm\">\
                                <span class=\"ace-icon fa fa-trash-o icon-on-right bigger-110\"></span>\
                                删除\
                                </button>\
                                </span>";
                        $("#fileDiv").append(vHTML);
                        $("#checkFile").addClass("hidden");
                        $("#fileName").val(fileName);
                        $("#filePath").val(filePath);
                        return;
                    }
                    $.notify((result && result.message) || '上传失败');
                }
            });
            return false;
        });


        $("#fileDiv").on("click", "#delFile", function () {
            bootbox.confirm('你确定要删除该附件吗("<b style="color: red">删除以后将会彻底删除附件文件</b>")？', function (result) {
                if (result) {
                    $.ajax({
                        url: '${staticServer}/common/delFile.do',
                        data: {
                            path: $("#filePath").val()
                        },
                        type: 'GET',
                        success: function (result) {
                            var id = $("#id").val();
                            if (result && result.success) {
                                $("#checkFile").removeClass("hidden");
                                $("#fileDiv").find("input").remove();
                                $("#fileDiv").find("span").remove();
                                $("#filePath").val("");
                                $("#fileName").val("");
                            } else {
                                $.notify({message: "文件路径错误!", z_index: 15111});
                            }
                        }
                    })
                }
            });
        });

    });
</script>
</body>
</html>
