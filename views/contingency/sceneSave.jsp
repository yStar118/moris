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
    <title>${webTitle}-现场处置方案</title>
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
                    <li class="active">现场处置方案</li>
                    <li class="active">现场处置方案</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        现场处置方案
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 编辑现场处置方案
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="sceneForm" name="sceneForm" class="form-horizontal"
                              action="${staticServer}/portal/contingency/scene/save.do" method="post">
                            <input type="hidden" name="id" value="${contingencyScene.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${contingencyScene.name }"><label
                                        id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-9">
                                    <div class="col-xs-12 col-sm-7" style="padding: 0">
                                        <script id="content" name="content"
                                                type="text/plain">${contingencyScene.content}</script>
                                    </div>
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

        $("#sceneForm").validate({
            errorElement: "label",
            errorClass: "valiError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                name: {
                    required: true,
                    maxlength: 255
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });

    function backIndex() {
        window.location.href = "${staticServer}/portal/contingency/scene/index.do";
    }
</script>
</body>
</html>
