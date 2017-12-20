<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-操作失败</title>
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
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <!-- #section:pages/error -->
                        <div class="error-container">
                            <div class="well">
                                <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-sitemap"></i>
												404
											</span>
                                    找不到页面
                                </h1>
                                <hr>
                                <h3 class="lighter smaller">页面在努力加载，但是仍然找不到这个页面</h3>

                                <div>
                                    <div class="space"></div>
                                    <h4 class="smaller">试试下面的操作</h4>

                                    <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            检查是否错误的请求
                                        </li>
                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            返回首页
                                        </li>
                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            告诉我们这个错误
                                        </li>
                                    </ul>
                                </div>
                                <hr>
                                <div class="space"></div>
                                <div class="center">
                                    <a href="${sataticServer}/portal/index.do" class="btn btn-primary">
                                        <i class="ace-icon fa fa-tachometer"></i>
                                        首页
                                    </a>
                                    <a href="javascript:history.back()" class="btn btn-grey">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        返回
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- /section:pages/error -->

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div>
            </div>
            <!-- /.main-container -->
            <%@ include file="common/js.jsp" %>
</body>
</html>
