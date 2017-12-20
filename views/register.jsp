<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/3/7
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>默锐安全后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%@ include file="common/header.jsp" %>
    <script src="${staticServer }/assets/js/userBrower.js"></script>
    <script src="${staticServer }/assets/js/jCookie.js"></script>
</head>

<body class="login-layout">
<div class="main-container login-main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="space-6"></div>

                    <div class="position-relative" style="width :400px">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger" style="text-align: center">
                                        <i class="ace-icon fa fa-train green"></i> <b>爱秀魔方注册用户</b>
                                    </h4>
                                    <div class="space-6"></div>
                                    <form id="loginForm" action="toRegister.do" method="post">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   name="username" id="username"
                                                                   placeholder="用户名">
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   name="companyName" id="companyName"
                                                                   placeholder="公司名称">
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   name="companyAddress" id="companyAddress"
                                                                   placeholder="公司地址">
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   name="companyTel" id="companyTel"
                                                                   placeholder="公司电话">
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   name="remark" id="remark"
                                                                   placeholder="备注信息">
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>
                                            <label class="block" style="text-align: center">
                                                <input type="checkbox" class="ace">
                                                <span class="lbl">
															我同意用户注册协议
														</span>
                                            </label>
                                            <div class="clearfix">
                                                <button type="button" class="width-100  btn btn-sm btn-success">
                                                    <span class="bigger-110">注册</span>

                                                    <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                                <!-- /.widget-main -->
                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.login-box -->
                    </div>
                    <!-- /.position-relative -->
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->


<%@ include file="common/js.jsp" %>

<!-- inline scripts related to this page -->
<script type="text/javascript">

</script>
</body>
</html>
