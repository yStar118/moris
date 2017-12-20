<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/6/25
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-应急预案</title>
    <%@ include file="../common/header.jsp" %>
    <style>
        .control-label {
            padding-top: 4px !important;
        }
    </style>
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
                    <li class="active">应急预案</li>
                    <li class="active">应急预案</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        应急预案
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 编辑应急预案
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="infoForm" name="infoForm" class="form-horizontal"
                              action="${staticServer}/portal/contingency/info/save.do" method="post">
                            <input type="hidden" name="id" value="${contingencyInfo.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${contingencyInfo.name }"><label
                                        id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择分类：</label>
                                <div class="col-sm-9 ">
                                    <form:select path="contingencyInfo.categoryId" name="categoryId"
                                                 class="col-xs-12 col-sm-7"
                                                 id="categoryId">
                                        <option value="">请选择分类</option>
                                        <form:options items="${categoryList }" itemValue="id" itemLabel="name"/>
                                    </form:select>
                                    <label id="categoryIdTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-9">
                                    <div class="col-xs-12 col-sm-7" style="padding: 0">
                                        <script id="content" name="content"
                                                type="text/plain">${contingencyInfo.content}</script>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">附件：</label>
                                <input type="hidden" id="filePath" name="filePath" value="${contingencyInfo.filePath}"/>
                                <input type="hidden" id="fileName" name="fileName" value="${contingencyInfo.fileName}"/>
                                <div class="col-sm-5 input-group" id="fileDiv" style="padding: 0 15px 0 15px">
                                    <button id="checkFile" href="#material-modal" type="button"
                                            class="btn btn-info btn-sm <c:if test="${not empty contingencyInfo.filePath}">hidden</c:if>"
                                            onclick="resetFileinput()"
                                            data-toggle="modal">
                                        <i class="ace-icon glyphicon glyphicon-picture bigger-110"></i> 选择文件
                                    </button>
                                    <c:if test="${not empty contingencyInfo.filePath}">
                                        <input type="text" title="" readonly class="form-control search-query"
                                               value="${contingencyInfo.fileName}">
                                        <span class="input-group-btn">
                                <button id='delFile' type="button" class="btn btn-purple btn-sm">
                                <span class="ace-icon fa fa-trash-o icon-on-right bigger-110"></span>删除</button>
                     </span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn btn-primary" type="submit">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 保存
                                    </button>
                                    <button class="btn" type="button"
                                            onclick="backIndex()">
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
                                        <button class="btn btn-white btn-primary" type="button" id="close-modal"
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
<script>
    window.UEDITOR_HOME_URL = '${staticServer}/assets/ueditor1.4.3/';
</script>
<script src="${staticServer}/assets/js/jquery.validation/jquery.validate.js"></script>
<script src="${staticServer}/assets/js/jquery.validation/jquery.validate.zh-CN.js"></script>
<script src="${staticServer}/assets/ueditor1.4.3/ueditor.config.js"></script>
<script src="${staticServer}/assets/ueditor1.4.3/ueditor.all.min.js"></script>
<script src="${staticServer}/assets/ueditor1.4.3/lang/zh-cn/zh-cn.js"></script>
<script src="${staticServer }/assets/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        UE.getEditor('content', {
            toolbars: [
                ['fullscreen', 'source', 'undo', 'redo'],
                ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript',
                    'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain',
                    '|', 'forecolor', 'backcolor', 'selectall',
                    'insertimage', 'cleardoc']
            ],
            initialFrameHeight: 300,
            imagePath: '${pageContext.request.contextPath}/upload'
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
                                if (id != "" && id != null) {
                                    $.ajax({
                                        url: '${staticServer}/portal/contingency/info/delFileName.do',
                                        data: {
                                            id: id
                                        },
                                        type: 'GET',
                                        success: function () {
                                            $("#checkFile").removeClass("hidden");
                                            $("#fileDiv input").remove();
                                            $("#fileDiv span").remove();
                                            $("#filePath").val("");
                                            $("#fileName").val("");
                                        }
                                    });
                                } else {
                                    $("#checkFile").removeClass("hidden");
                                    $("#fileDiv input").remove();
                                    $("#fileDiv span").remove();
                                    $("#filePath").val("");
                                    $("#fileName").val("");
                                }
                            } else {
                                $.notify({message: "文件路径错误!", z_index: 15111});
                            }
                        }
                    })
                }
            });
        });

        $("#infoForm").validate({
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
                categoryId: {
                    required: true
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });

    function backIndex() {
        window.location.href = "${staticServer}/portal/contingency/info/index.do";
    }

    //重置上传窗口
    function resetFileinput() {
        $('#file-input').ace_file_input('reset_input');
    }
</script>
</body>
</html>
