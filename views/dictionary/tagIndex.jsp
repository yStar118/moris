<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/8/31
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-tag管理</title>
    <%@ include file="../common/header.jsp" %>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>
<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
            </div>
            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box" id="widget-box-search">
                            <div class="widget-header">
                                <div class="widget-toolbar">
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main no-padding">
                                    <form:form action="/" method="post"
                                               cssClass="form-horizontal"
                                               cssStyle="padding-top: 10px;">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-6 col-lg-5">tag序号：</label>
                                                    <div class="col-xs-6 col-lg-7">
                                                        <input type="text" id="nameSearch"
                                                               class="col-xs-12 col-sm-12 col-lg-10"
                                                               value="" title=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            id="btn-search">
                                                        <i class="ace-icon fa fa-search"></i> 查询
                                                    </button>&nbsp;&nbsp;
                                                    <a type="button" class="btn btn-success btn-sm"
                                                       href="#modal-edit" data-toggle="modal"
                                                       data-backdrop="static" onclick="flushForm()"
                                                       id="btn-add">
                                                        <i class="ace-icon fa fa-plus"></i> 新增
                                                    </a>
                                                    <a href="#modal-import" class="btn btn-warning btn-sm"
                                                       data-toggle="modal">
                                                        <i class="ace-icon fa fa-file-excel-o"></i>导入</a>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div class="row">
                            <div class="col-xs-12">
                                <table id="tagTable" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="background-color: #E5E5E5">tag</th>
                                        <th style="background-color: #E5E5E5">tag值</th>
                                        <th style="background-color: #E5E5E5">操作</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                            <!-- /.span -->
                        </div>
                    </div>
                    <!-- /.span -->
                </div>
            </div>
            <!-- /.main-content -->
        </div>
        <!-- /.main-container -->
    </div>
</div>
<div id="modal-edit" class="modal fade" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 50%;top: 18%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only"></span>
                </button>
                <h4 class="modal-title">编辑tag</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <form id="tagForm" name="tagForm" class="form-horizontal"
                              action="${staticServer}/portal/dictionary/tag/save.do" method="post">
                            <input id="id" name="id" value="" type="hidden" title=""/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">tag编号：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="tag" name="tag" maxlength="256" class="col-xs-10 col-sm-7"
                                           placeholder=""
                                           title="" value=""><label id="tagip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">tag值：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="tagValue" name="tagValue" maxlength="256"
                                           class="col-xs-10 col-sm-7"
                                           placeholder=""
                                           title="" value=""><label id="tagValueTip"></label>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-4 col-md-8">
                                    <button class="btn btn-primary btn-sm" type="submit">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 保存
                                    </button>&nbsp;&nbsp;
                                    <button class="btn btn-sm" type="button" data-dismiss="modal">
                                        <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="modal-import" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    导入tag
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <c:url value="${staticServer}/portal/dictionary/tag/import.do"
                                   var="import_url"/>
                            <form:form action="${import_url}"
                                       enctype="multipart/form-data" method="post" id="upload-form">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <input name="excel_file" type="file" id="import-input"
                                               accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 确定
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button" id="closeicon-modal"
                                                data-dismiss="modal">
                                            <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer no-margin-top"></div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div id="import-result-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    导入结果
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <form:form action="" id="import-result-form">
                                <div class="form-group">
                                    <div class="col-xs-12" id="result-text">
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="button" id="result-btn">
                                            <i class="ace-icon fa fa-check bigger-110"></i> 确认
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer no-margin-top"></div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%@ include file="../common/js.jsp" %>
<script src="${staticServer }/assets/js/jquery.form.js"></script>
<script src="${staticServer }/assets/js/jquery.validation/jquery.validate.js"></script>
<script src="${staticServer }/assets/js/jquery.validation/jquery.validate.zh-CN.js"></script>
<script>
    $(function () {

        /*把要初始化的table的对象赋值*/
        var $table_id = $("#tagTable");
        /*自定义查询参数*/
        var tagParam = {
            getQueryCondition: function (data) {
                var param = {};
                param.name = $("#nameSearch").val();
                param.index = data.start;
                param.length = data.length;
                return param;
            }
        };
        //object可以如下初始化表格
        window.table = $table_id.DataTable({
            dom: '<".table_area_top" B and >t<".table_area_bottom" pi>',
            Filter: false, //列筛序功能
            searching: false,//本地搜索
            ordering: false, //排序功能
            Info: true,//页脚信息
            lengthMenu: [15],
            scrollY: "auto",
            Paginate: true, //翻页功能
            sortable: false,
            processing: true,
            serverSide: true,
            ajax: function (data, callback) {//ajax配置为function,手动调用异步查询
                $.ajax({
                    type: "GET",
                    url: '${staticServer}/portal/dictionary/tag/getList.do',//请求数据的参数
                    data: tagParam.getQueryCondition(data),
                    cache: false,  //禁用缓存
                    dataType: "json",
                    success: function (result) {
                        callback(result);
                    }
                });
            },
            //使用对象数组，一定要配置columns，告诉 DataTables 每列对应的属性
            //data 这里是固定不变的，id，name，age，sex等为你数据里对应的属性
            columns: [
                {
                    data: "tag",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "tagValue",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "id",
                    width: "155px",
                    render: function (data, type, row) {
                        return '<a class="btn  btn-sm btn-info" href="#modal-edit" data-toggle="modal"  data-backdrop="static" ' +
                            'onclick="updatetag(\'' + data + '\')">' +
                            '<i class="ace-icon fa fa-pencil-square-o "></i>修改</a>' +
                            '&nbsp;&nbsp;' +
                            '<a class="btn btn-sm btn-danger" onclick="deltag(\'' + data + '\')">' +
                            '<i class="ace-icon fa fa-pencil-square-o "></i>删除</a>';
                    }
                }
            ],
            language: { //国际语言转化
                aria: {
                    sortAscending: " - click/return to sort ascending",
                    sortDescending: " - click/return to sort descending"
                },
                lengthMenu: "显示 _MENU_ 记录",
                zeroRecords: "对不起，查询不到任何相关数据",
                emptyTable: "未有相关数据",
                loadingRecords: "正在加载数据-请等待...",
                info: "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
                infoEmpty: "当前显示0到0条，共0条记录",
                infoFiltered: "（数据库中共为 _MAX_ 条记录）",
                processing: " 正在加载数据...",
                //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
                paginate: {
                    first: "首页",
                    previous: " 上一页 ",
                    next: " 下一页 ",
                    last: " 尾页 "
                }
            }
        });
        /*条件查询*/
        $("#btn-search").on("click", function () {
            table.draw();
        });
        /*validate验证*/
        $("#tagForm").validate({
            errorElement: "label",
            errorClass: "validError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                tag: {
                    required: true,
                    maxlength: 255
                },
                tagValue: {
                    required: true,
                    maxlength: 255
                }
            },
            submitHandler: function () {
                $("#tagForm").ajaxSubmit({
                    success: function () {
                        $('#modal-edit').modal('hide');
                        table.draw();
                    }
                });
            }
        });

        var $import_input = $('#import-input');
        $import_input.ace_file_input({
            style: 'well',
            btn_choose: '点击选择Excel文件',
            btn_change: null,
            no_icon: 'ace-icon fa fa-file-excel-o',
            droppable: false,
            thumbnail: 'small',//large | fit
            maxSize: 10240000,
            allowExt: ['xls', 'xlsx', 'xlt', 'xlw', 'xlc', 'xlm'],
            allowMime: ['application/vnd.ms-excel', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'],
            before_remove: function () {
                return true;
            }

        }).on('change', function () {
            if ($import_input.val() == '') {
                resetFileinput();
                return false;
            }
        }).on('file.error.ace', function (ev, info) {
            if (info.error_count['ext'] || info.error_count['mime']) {
                $.notify("请选择Excel文件!");
                return false;
            }
            if (info.error_count['size']) {
                $.notify("文件不超过100M!");
                return false;
            }
        });


        var subject_upload_form = $("#upload-form");
        //导入提交
        subject_upload_form.on('submit', function () {
            var upload_check = $import_input.val();
            if (upload_check == '') {
                resetFileinput();
                $.notify("请选择文件!");
                return false;
            }
            $import_input.ace_file_input('loading', true);
            $(".ace-file-overlay").append('<div class="overlay-text">正在导入中...</div>');
            subject_upload_form.ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: subject_upload_form.attr('action'),
                dataType: 'json',
                success: function (result) {
                    $import_input.ace_file_input('loading', false);
                    var msg = '';
                    if (result && result.success && result.data) {
                        $("#subject-modal").modal("hide");  //关闭上传窗口
                        $import_input.val('');
                        resetFileinput();

                        if (result.data.length == 0) {
                            msg = result.message;
                        } else {
                            $.each(result.data, function (i, item) {
                                var index = parseInt(item.rowIndex);
                                msg += item.reason + '<br/>';
                            });
                            msg += '其余导入成功';
                        }
                        $("#result-text").html(msg);
                        $("#import-result-modal").modal("show");
//
                        //$.notify(msg);
                    } else {
                        $("#subject-modal").modal("hide");   //关闭上传窗口
                        $import_input.val('');
                        resetFileinput();
                        $import_input.ace_file_input('loading', false);
                    }
                }
            });
            return false;
        });
        $("#result-btn").on('click', function () {
            $("#modal-import").modal("hide");
            $("#import-result-modal").modal("hide");
            table.draw();
        })
    });

    /*新增时清空表单数据*/
    function flushForm() {
        $("#tagForm").find("input").each(function () {
            var id = $(this).attr("id");
            $("#" + id).val("");
        });
    }

    /*修改时需要回显数据*/
    function updatetag(id) {
        $("#tagForm").find("input").each(function () {
            var id = $(this).attr("id");
            $("#" + id).val("");
        });
        $.ajax({
            type: "GET",
            url: "${staticServer}/portal/dictionary/tag/findOne.do",
            data: {
                "id": id
            },
            contentType: 'application/json',
            success: function (data) {
                for (var key in data) {
                    $("#" + key).val(data[key]);
                }

            }
        });
    }

    /*删除*/
    function deltag(id) {
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要删除该部门信息？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/dictionary/tag/delete.do",
                    data: {
                        "id": id
                    },
                    contentType: 'application/json',
                    success: function () {
                        table.draw();
                    }
                });
            }
        });
    }

    //重置上传窗口
    function resetFileinput() {
        var $import_input = $("#upload-form");
        $import_input.parent().find(".ace-file-container").removeClass("hide-placeholder").removeClass("selected");
        $import_input.parent().find(".ace-file-container").attr("data-title", '点击选择Excel文件');
        $import_input.parent().find(".ace-file-name").find("i").addClass("fa-file-excel-o").removeClass("fa-file");
        $import_input.parent().find(".ace-file-name").find("img").remove();
        $import_input.parent().find(".ace-file-name").attr("data-title", 'No File ...');
    }

    function exportTable() {
        window.location = "${staticServer}/portal/dictionary/tag/export.do";
    }
</script>
</body>
</html>




