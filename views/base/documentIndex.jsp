<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/6/20
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-文件路径</title>
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
                                                    <label class="control-label col-xs-6 col-lg-5">文件名称：</label>
                                                    <div class="col-xs-6 col-lg-7">
                                                        <input type="text" id="fileNameSearch"
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
                                                       href="#file-modal" data-toggle="modal"
                                                       data-backdrop="static" onclick="flushForm()"
                                                       id="btn-add">
                                                        <i class="ace-icon fa fa-search"></i> 新增文件
                                                    </a>
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
                                <table id="document" style="width: 100%"
                                       class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="background-color: #E5E5E5">文件分类</th>
                                        <th style="background-color: #E5E5E5">文件名称</th>
                                        <th style="background-color: #E5E5E5">上传时间</th>
                                        <th style="background-color: #E5E5E5">上传人</th>
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
<div id="file-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    新增文件文档
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <c:url value="${staticServer}/common/upload.do"
                                   var="upload_url"/>
                            <form:form action="${upload_url}" cssClass="form-horizontal"
                                       enctype="multipart/form-data" method="post" id="file-upload-form">
                                <input type="hidden" name="file_type" value="document">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">文件分类：</label>
                                    <div class="col-sm-9">
                                        <input type="text" id="category" maxlength="256" class="col-xs-12"
                                               placeholder=""
                                               title="" value="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">上传文件：</label>
                                    <div class="col-xs-9">
                                        <input name="file" type="file" id="file-upload-input"/>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 上传
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
<%@ include file="../common/js.jsp" %>
<script src="${staticServer }/assets/js/jquery.form.js"></script>
<script>
    $(function () {

        /*把要初始化的table的对象赋值*/
        var $table_id = $("#document");
        /*自定义查询参数*/
        var documentParam = {
            getQueryCondition: function (data) {
                var param = {};
                param.fileName = $("#fileNameSearch").val();
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
                    url: '${staticServer}/portal/base/document/getList.do',//请求数据的参数
                    data: documentParam.getQueryCondition(data),
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
                    data: "category",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "fileName",
                    render: function (data, type, row) {
                        return '<a download="' + row.fileName + '" href="${imageServer}' + row.filePath + '">' + data + '</a>';
                    }
                },
                {
                    data: "createDate",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "createUserName",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "id",
                    width: "70px",
                    render: function (data, type, row) {
                        return '<a class="btn btn-sm btn-danger" onclick="delDocument(\'' + data + '\',\'' + row.filePath + '\')">' +
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


        var $upload_input = $('#file-upload-input');
        $upload_input.ace_file_input({
            style: 'well',
            btn_choose: '点击选择文件',
            btn_change: null,
            no_icon: 'ace-icon fa fa-file-excel-o',
            droppable: false,
            thumbnail: 'large',//large | fit
            maxSize: 1024 * 1000 * 10,
            before_remove: function () {
                return true;
            }

        }).on('change', function () {
            if ($upload_input.val() == '') {
                resetFileinput();
                return false;
            }
        }).on('file.error.ace', function (ev, info) {
            if (info.error_count['size']) {
                $.notify("文件不超过100M!");
                return false;
            }
        });


        var file_upload_form = $("#file-upload-form");
        //导入提交
        file_upload_form.on('submit', function () {
            var upload_check = $upload_input.val();
            if (upload_check == '') {
                resetFileinput();
                $.notify("请选择文件!");
                return false;
            }
            $upload_input.ace_file_input('loading', true);
            $(".ace-file-overlay").append('<div class="overlay-text" style="text-align:center;color: yellow">正在上传中...</div>');
            file_upload_form.ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: file_upload_form.attr('action'),
                dataType: 'json',
                success: function (result) {
                    var fileName = result.createFileName;
                    var filePath = result.createFilePath;
                    $upload_input.ace_file_input('loading', false);
                    if (result && result.success) {
                        $.ajax({
                            type: "POST",
                            url: "${staticServer}/portal/base/document/save.do",
                            data: {
                                "category": $("#category").val(),
                                "fileName": fileName,
                                "filePath": filePath
                            },
                            success: function () {
                                $("#file-modal").modal("hide");   //关闭上传窗口
                                table.draw();
                            }, error: function () {
                                $("#file-modal").modal("hide");   //关闭上传窗口
                                $upload_input.val('');
                                resetFileinput();
                                $upload_input.ace_file_input('loading', false);
                            }
                        });
                    } else {
                        $("#file-modal").modal("hide");   //关闭上传窗口
                        $upload_input.val('');
                        resetFileinput();
                        $upload_input.ace_file_input('loading', false);
                    }
                }
            });
            return false;
        });
    });

    /*新增时清空表单数据*/
    function flushForm() {
        resetFileinput();
    }

    /*删除*/
    function delDocument(id, filePath) {
        console.log(id);
        console.log(filePath);
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要删除该文件文档？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/common/delFile.do",
                    data: {
                        "path": filePath
                    },
                    success: function (result) {
                        if (result && result.success) {
                            $.ajax({
                                type: "GET",
                                url: "${staticServer}/portal/base/document/delete.do",
                                data: {
                                    "id": id
                                },
                                success: function () {
                                    table.draw();
                                }
                            });
                        } else {
                            $.notify({
                                message: result.message
                            }, {
                                type: 'danger'
                            });
                        }
                    },
                    error: function (result) {
                    }
                });
            }
        });
    }

    //重置上传窗口
    function resetFileinput() {
        var $import_input = $("#file-upload-form");
        $import_input.parent().find(".ace-file-container").removeClass("hide-placeholder").removeClass("selected");
        $import_input.parent().find(".ace-file-container").attr("data-title", '点击选择文件');
        $import_input.parent().find(".ace-file-name").find("i").addClass("fa-file-excel-o").removeClass("fa-file");
        $import_input.parent().find(".ace-file-name").find("img").remove();
        $import_input.parent().find(".ace-file-name").attr("data-title", 'No File ...');
    }
</script>
</body>
</html>


