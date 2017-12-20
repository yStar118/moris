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
                    <li class="active"><c:if test="${baseNotice.type==1}">警告通知</c:if>
                        <c:if test="${baseNotice.type==2}">系统通知</c:if>
                        <c:if test="${baseNotice.type==3}">命令通知</c:if></li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <c:if test="${baseNotice.type==1}">警告通知</c:if>
                        <c:if test="${baseNotice.type==2}">系统通知</c:if>
                        <c:if test="${baseNotice.type==3}">命令通知</c:if>
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 通知详情
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通知名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7" readonly
                                           title="" value="${baseNotice.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通知内容：</label>
                                <div class="col-sm-9">
                                    <textarea class="col-xs-12 col-sm-7" rows="5"
                                              readonly>${baseNotice.content}</textarea>
                                    <label id="contentTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">附件：</label>
                                <div class="col-sm-5 input-group" id="fileDiv" style="padding: 0 15px 0 15px">
                                    <a download="${baseNotice.fileName}"
                                       href="${imageServer}/${baseNotice.filePath}">${baseNotice.fileName}</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">相关信息：</label>
                                <div class="col-sm-9">
                                    <table class="table table-striped table-bordered table-hover"
                                           style="width: 58.333%">
                                        <thead class="thin-border-bottom">
                                        <tr>
                                            <th><i class="ace-icon fa fa-cogs"></i>职工部门</th>
                                            <th><i class="ace-icon fa fa-cog"></i>职工岗位</th>
                                            <th><i class="ace-icon fa fa-user"></i>职工姓名</th>
                                            <th><i class="ace-icon fa fa-folder-open"></i>是否已读</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${baseNoticeUsers}" var="baseNoticeUser" varStatus="">
                                            <tr>
                                                <th>${baseNoticeUser.departmentName}</th>
                                                <th>${baseNoticeUser.jobsName}</th>
                                                <th>${baseNoticeUser.realName}</th>
                                                <th>
                                                    <c:if test="${baseNoticeUser.isRead == 1}"><span
                                                            class="label label-success arrowed">已读</span></c:if>
                                                    <c:if test="${baseNoticeUser.isRead == 0}"><span
                                                            class="label label-warning">未读</span></c:if>
                                                </th>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-5 col-md-7">
                                    <button class="btn" type="button" onclick="history.back()">
                                        <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                                    </button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <!-- /.main-content -->
            </div>
            <!-- /.main-container -->
            <%@ include file="../common/js.jsp" %>

            <script type="text/javascript">
                $(document).ready(function () {
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
                });
            </script>
</body>
</html>
