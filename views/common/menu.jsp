<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="taglib.jsp" %>
<script src="${staticServer }/assets/js/jCookie.js"></script>
<!-- /section:basics/navbar.layout -->
<!-- #section:basics/sidebar -->
<div id="sidebar" class="sidebar responsive ace-save-state  sidebar-fixed" style="top : 45px">
    <script type="text/javascript">
        try {
            ace.settings.loadState('sidebar')
        } catch (e) {
        }
    </script>
    <!-- /.sidebar-shortcuts -->
    <ul class="nav nav-list">
        <li class="active" id="menu-statistic"><a href="${dynamicServer}/portal/index.do" id="module_statistic"> <i
                class="menu-icon fa fa-tachometer"></i> <span class="menu-text"> 首页 </span>
        </a> <b class="arrow"></b>
            ${bln:createMenu(pageContext.request) }
        </li>
    </ul>

    <!-- #section:basics/sidebar.layout.minimize -->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
           data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
    <script type="text/javascript">
        $(".nav-list").find("a").click(function () {
            //移除a里面的样式
            $(this).find("b").toggleClass("fa-angle-down").toggleClass("fa-angle-left");
            //当前选择的下标
            var index = $(this).parent().attr("id");
            //记录下标
            jQuery.jCookie('current', index, 30, {
                path: '/'
            });
        });
        var current = jQuery.jCookie('current');  //如果不是信息检索跳转过来，按照之前选中菜单
        if (current != null && current != '') {
            $('.nav-list .active').removeClass('active');
            var $current = $("#" + current);
            $current.addClass("active");
            if ($current.parent().hasClass("submenu")) {
                $current.parent().parent().addClass("active open");
                $current.parent().parent().find("a").find("b").removeClass("fa-angle-left").addClass("fa-angle-down");
            }
        }
    </script>
    <!-- /section:basics/sidebar.layout.minimize -->
</div>