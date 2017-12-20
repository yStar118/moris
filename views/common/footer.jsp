<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="taglib.jsp" %>
<!-- BEGIN FOOTER
-->
<style>
    .page-footer {
        position: fixed;
        bottom: 0;
        background: white;
        width: 100%;
        z-index: 99;
        border-top: 3px double #E5E5E5;
        padding-left: 12px

    }
</style>
<script type="text/javascript">
    $(function () {
        var year = new Date().getFullYear();
        var html_ = '版权所有© ' + year + ' <a href="#">中软动力</a>';
        $("#footer-info").html(html_);
    })
    function hideLink() {
        var footerH = $(".page-footer").height();
        if (footerH > 22) {
            $(".page-footer").height("22px");
            $(".main-content-inner").css("margin-bottom", "0px");
        }
        else {
            $(".page-footer").height("100px");
            $(".main-content-inner").css("margin-bottom", "100px");
            $("body").scrollTop($("body").height());  //滚动到底部
        }
        $("#up-icon").toggleClass("fa-chevron-down").toggleClass("fa-chevron-up");
    }
</script>
<!-- END FOOTER -->