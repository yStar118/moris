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
    <style>
        header {
            height: 60px;
            line-height: 60px;
            padding: 0 20px;
            border-bottom: 1px solid rgba(255, 255, 255, 0.3);
        }

        header .h1 {
            line-height: 16px;
            float: left;
            font-size: 26px;
        }

        footer {
            text-align: center;
            background: rgba(0, 0, 0, 0.6);
            height: 68px;
            line-height: 24px;
            color: #FFFFFF;
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
        }

        footer a {
            color: #FFFFFF;
            text-decoration: none;
        }

        p {
            margin: 8px 0 0 0;
        }

        h2 {
            font-size: 30px;
        }

        .leftTxt {
            position: fixed;
            top: 29%;
            left: 25%;
            color: white;
        }
    </style>
</head>

<body class="login-layout">
<header>
    <span class="h1">默锐安全后台管理系统</span>
</header>
<div class="main-container login-main-container">
    <div class="main-content">
        <div class="row">
            <div class="leftTxt">
                <h2>企业生产安全管理系统</h2>
                <ul>
                    <li>实时监控设备，即时报告异常</li>
                    <li>更强计算能力，更优秀的统计分析</li>
                    <li>全自动化任务，文件共享</li>
                </ul>
            </div>

            <div class="col-sm-10 col-sm-offset-1" style="margin:2% 3% 0 0;width: 50%;float: right ">
                <div class="login-container">

                    <div class="position-relative" style="width :400px">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main" style="padding-bottom: 20px">
                                    <h4 class="header blue lighter bigger" style="text-align: center">
                                        <i class="ace-icon fa fa-globe green"></i> <b>默锐安全后台管理系统</b>
                                    </h4>
                                    <div class="space-6"></div>
                                    <form id="loginForm" action="portal/checkLogin.do" method="post">
                                        <fieldset>
                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                    <input type="text" class="form-control" name="username"
                                                           id="username" placeholder="用户名"/>
                                                    <i class="ace-icon fa fa-user"></i>
												</span>
                                            </label>
                                            </label> <label class="block clearfix">
                                            <span class="block input-icon input-icon-right">
                                                <input type="password" class="form-control" name="password"
                                                       id="password" placeholder="密码"/>
                                                <i class="ace-icon fa fa-lock"></i>
												</span>
                                        </label>
                                            <label class="block clearfix" style="text-align: center">
                                                <span class="block input-icon input-icon-right">
                                                    <span id="lblMessage" class="errMsg" style="display: none">
                                                        账号或密码输入错误！
                                                    </span>
												</span>
                                            </label>
                                            <div class="space"></div>
                                            <div class="clearfix" style="text-align: center">
                                                <button type="submit"
                                                        class="width-50  btn btn-sm btn-primary">
                                                    <i class="ace-icon fa fa-key"></i> <span
                                                        class="bigger-110">登录</span>
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
<footer>
    <div>
        <p>
            <a>关于我们</a>
            <a>法律声明</a>
            <a>廉正举报</a>
            <a>友情链接</a>
        </p>
        <p>
            © 2017 航天三院三零四所 版权所有
        </p>
    </div>
</footer>
<%@ include file="common/js.jsp" %>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    var isNeedVerifyCode = false;
    jQuery(function ($) {

        $("#loginForm").validate({
            errorElement: "label",
            errorClass: "valiError",
            errorPlacement: function (error, element) {
            },
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: ""
                },
                password: {
                    required: ""
                }
            },
            submitHandler: function (form) {
                checkLogin();
            }
        });

    });

    function checkLogin() {

        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            type: "post",
            url: "${dynamicServer}/portal/checkLogin.do",
            data: {
                username: username,
                password: password,
                terminal: getUserTerminalType(),
                explorerType: getExplorerInfo().browser,
                explorerVersion: getExplorerInfo().version
            },
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#lblMessage").hide();
                    window.location.href = "${dynamicServer}/portal/index.do";
                } else {
                    $("#lblMessage").html(data.msgText);
                    $("#lblMessage").show();
                }
            },
            error: function (data) {
                $("#lblMessage").html('登录失败');
                $("#lblMessage").show();
            }
        });
    }

</script>
</body>
</html>