<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<style>

</style>
<div id="navbar" class="navbar navbar-default ace-save-state  navbar-fixed-top">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                class="icon-bar"></span>
        </button>
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="${dynamicServer }/portal/index.do" class="navbar-brand">
                <small><i class="fa fa-train"></i> 默锐安全后台管理系统</small>
            </a>
        </div>
        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation" style="margin-right: -10px">
            <ul class="nav ace-nav">
                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue dropdown-modal"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img
                        class="nav-user-photo" src="${staticServer }/assets/avatars/user.jpg"
                        alt="Jason's Photo"/> <span class="user-info"> <small> ${sessionScope.userSession.role_name }</small> ${sessionScope.userSession.realname }
					</span> <i class="ace-icon fa fa-caret-down"></i>
                </a>
                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

                        <li><a href="${dynamicServer }/portal/toUpdatePass.do"> <i class="ace-icon fa fa-user"></i> 修改密码
                        </a></li>

                    </ul>
                </li>
                <li class="light-blue"><a href="${dynamicServer }/portal/logout.do"> <i class="ace-icon fa fa-power-off"></i>
                    注销
                </a></li>
                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>
        <!-- /section:basics/navbar.dropdown -->
    </div>
    <!-- /.navbar-container -->
</div>
<script>
</script>