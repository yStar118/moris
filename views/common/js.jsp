<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!-- basic scripts -->
<script src="${staticServer }/assets/components/bootstrap/dist/js/bootstrap.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/components/jquery-validation/dist/jquery.validate.js?versionNo=${versionNo}"
        type="text/javascript"></script>

<script src="${staticServer }/assets/js/src/elements.scroller.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/components/bootbox.js/bootbox.js?versionNo=${versionNo}"></script>

<script src="${staticServer }/assets/js/src/elements.colorpicker.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/elements.fileinput.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/elements.typeahead.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/elements.wysiwyg.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/elements.spinner.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/elements.treeview.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/elements.wizard.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/elements.aside.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.basics.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.scrolltop.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.ajax-content.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.touch-drag.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.sidebar.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.sidebar-scroll-1.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.submenu-hover.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.widget-box.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.settings.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.settings-rtl.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.settings-skin.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.widget-on-reload.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/src/ace.searchbox-autocomplete.js?versionNo=${versionNo}"></script>

<%--datatables的JS文件--%>
<script src="${staticServer }/assets/dataTables/js/jquery.dataTables.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/dataTables/js/dataTables.fixedColumns.js?versionNo=${versionNo}"></script>
<script src="${staticServer }/assets/js/bootstrap-notify.js?versionNo=${versionNo}"></script>


<script src="${staticServer }/assets/js/jquery.form.js?versionNo=${versionNo}"></script>


<script>

    $(function () {

        console.log("开发者，你好。欢迎进入调试模式\n" +
            " %c 本系统出现所有问题请发送至邮箱：1553280431@qq.com \n " +
            "软件版权所有权归中软动力。", "color:red");

        $.notifyDefaults({
            type: 'danger',
            placement: {
                from: 'top',
                align: 'center'
            },
            position: 'absolute',
            z_index: 1099
        });
    });

    function dateFormat(time) {
        time = new Date(time);
        var y = time.getFullYear();
        var M = time.getMonth() + 1;
        var d = time.getDate();
        var h = time.getHours();
        var m = time.getMinutes();
        var s = time.getSeconds();
        return y + '-' + add0(M) + '-' + add0(d) + " " + add0(h) + ":" + add0(m) + ":" + add0(s);
    }

    function dateFormatDay(time) {
        time = new Date(time);
        var y = time.getFullYear();
        var M = time.getMonth() + 1;
        var d = time.getDate();
        return y + '-' + add0(M) + '-' + add0(d);
    }
    function add0(m) {
        return m < 10 ? '0' + m : m
    }

</script>



