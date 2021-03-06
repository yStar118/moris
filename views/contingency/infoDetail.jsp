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
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 应急预案详情
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
                                    <input readonly type="text" class="col-xs-12 col-sm-7"
                                           value="${contingencyInfo.name }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分类名称：</label>
                                <div class="col-sm-9 ">
                                    <input readonly type="text" class="col-xs-12 col-sm-7"
                                           value="${contingencyInfo.categoryName }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-9">
                                    <div class="col-xs-12 col-sm-7" style="padding: 0">
                                        ${contingencyInfo.content}
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">附件：</label>
                                <div class="col-sm-5 input-group" id="fileDiv" style="padding: 0 15px 0 15px">
                                    <a download="${contingencyInfo.fileName}"
                                       href="${imageServer}/${contingencyInfo.filePath}">${contingencyInfo.fileName}</a>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn" type="button"
                                            onclick="backIndex()">
                                        <i class="ace-icon fa fa-undo bigger-110"></i> 返回
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
<script type="text/javascript">
    function backIndex() {
        window.location.href = "${staticServer}/portal/contingency/info/index.do";
    }

</script>
</body>
</html>
