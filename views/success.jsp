<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-操作成功</title>
    <%@ include file="common/header.jsp" %>
</head>

<body class="no-skin">
<%@ include file="common/top.jsp" %>

<div class="main-container" id="main-container">
    <%@ include file="common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="${dynamicServer}/portal/index.do">首页</a></li>
                    <li class="active">操作结果</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <div class="alert alert-success" style="text-align: center;">
                    <h4>
                        <i class="fa fa-check-circle"></i> 操作成功
                    </h4>
                    <a href="${backUrl}">点击返回</a>
                    <script type="text/javascript">
                        setTimeout(function () {
                            location.href = "${backUrl}";
                        }, 2000);
                    </script>
                </div>


                <!-- /.main-content -->
            </div>
            <!-- /.main-container -->
            <%@ include file="common/js.jsp" %>
</body>
</html>
