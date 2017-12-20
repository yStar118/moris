<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/7/4
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/taglib.jsp" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-检查项</title>
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
                                                    <label class="control-label col-xs-6 col-lg-5">检查项编号：</label>
                                                    <div class="col-xs-6 col-lg-7">
                                                        <input type="text" id="codeSearch"
                                                               class="col-xs-12 col-sm-12 col-lg-10"
                                                               value="" title=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-6 col-lg-5">检查项名称：</label>
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
                                <table id="options" class="table table-striped table-bordered table-hover"
                                       style="width: 100%">
                                    <thead>
                                    <tr>
                                        <th style="background-color: #E5E5E5">编号</th>
                                        <th style="background-color: #E5E5E5">名称</th>
                                        <th style="background-color: #E5E5E5">内容描述</th>
                                        <th style="background-color: #E5E5E5">检查手段</th>
                                        <th style="background-color: #E5E5E5">是否主观判断</th>
                                        <th style="background-color: #E5E5E5">主观检查标准</th>
                                        <th style="background-color: #E5E5E5">检查项意义</th>
                                        <th style="background-color: #E5E5E5">检查项属性</th>
                                        <th style="background-color: #E5E5E5">是否启用</th>
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
    <div class="modal-dialog" style="width: 50%;top: 4%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only"></span>
                </button>
                <h4 class="modal-title">编辑检查项</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <form id="optionsForm" name="optionsForm" class="form-horizontal"
                              action="${staticServer}/portal/inspection/options/save.do" method="post">
                            <input id="id" name="id" value="" type="hidden" title=""/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查项编号：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="code" name="code" maxlength="256" class="col-xs-10 col-sm-7"
                                           placeholder=""
                                           title="" value=""><label id="codeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查项名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="name" name="name" maxlength="256" class="col-xs-10 col-sm-7"
                                           placeholder=""
                                           title="" value=""><label id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查内容描述：</label>
                                <div class="col-sm-9">
                                    <textarea id="content" name="content" class="col-xs-12 col-sm-7" rows="3"
                                              placeholder="请输入检查项内容描述..."
                                              maxlength="500"></textarea>
                                    <label id="contentTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-9">
                                    <textarea id="remark" name="remark" class="col-xs-12 col-sm-7" rows="3"
                                              placeholder="请输入备注..."
                                              maxlength="500"></textarea>
                                    <label id="remarkTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查手段：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="method" name="method" maxlength="256"
                                           class="col-xs-10 col-sm-7"
                                           placeholder=""
                                           title="" value=""><label id="methodTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否主观判断：</label>
                                <div class="col-sm-9">
                                    <select id="isSubJudge" name="isSubJudge" class="col-xs-10 col-sm-7"
                                            title="">
                                        <option value="0">否</option>
                                        <option value="1">是</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">主观检查标准：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="subJudgeStandard" name="subJudgeStandard" maxlength="256"
                                           class="col-xs-10 col-sm-7" placeholder=""
                                           title="" value=""><label id="subJudgeStandardTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查项意义：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="meaning" name="meaning" maxlength="256"
                                           class="col-xs-10 col-sm-7"
                                           placeholder=""
                                           title="" value=""><label id="meaningTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查项属性：</label>
                                <div class="col-sm-9">
                                    <select id="type" name="type" class="col-xs-10 col-sm-7"
                                            title="">
                                        <option value="1">日常检查</option>
                                        <option value="2">特殊检查</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否启用：</label>
                                <div class="col-sm-9">
                                    <select id="isStart" name="isStart" class="col-xs-10 col-sm-7"
                                            title="">
                                        <option value="0">否</option>
                                        <option value="1" selected>是</option>
                                    </select>
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
                    导入检查项
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <c:url value="${staticServer}/portal/inspection/options/import.do"
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
                            <div class="form-group">
                            </div>
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
        var $table_id = $("#options");
        /*自定义查询参数*/
        var optionsParam = {
            getQueryCondition: function (data) {
                var param = {};
                param.code = $("#codeSearch").val();
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
            scrollX: true,
            scrollY: true,
            Paginate: true, //翻页功能
            sortable: false,
            processing: true,
            serverSide: true,
            ajax: function (data, callback) {//ajax配置为function,手动调用异步查询
                $.ajax({
                    type: "GET",
                    url: '${staticServer}/portal/inspection/options/getList.do',//请求数据的参数
                    data: optionsParam.getQueryCondition(data),
                    cache: false,  //禁用缓存
                    dataType: "json",
                    success: function (result) {
                        callback(result);
                    }
                });
            },
            columns: [
                {
                    data: "code",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "name",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "content",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "method",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "isSubJudge",
                    render: function (data) {
                        switch (data) {
                            case 1:
                                return "是";
                                break;
                            case 0:
                                return "否";
                                break;
                            default:
                                return "否";
                                break;
                        }
                    }
                },
                {
                    data: "subJudgeStandard",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "meaning",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "type",
                    render: function (data) {
                        switch (data) {
                            case 1:
                                return "日常检查";
                                break;
                            case 2:
                                return "特殊检查";
                                break;
                            default:
                                return "否";
                                break;
                        }
                    }
                },
                {
                    data: "isStart",
                    render: function (data) {
                        switch (data) {
                            case 1:
                                return "是";
                                break;
                            case 0:
                                return "否";
                                break;
                            default:
                                return "否";
                                break;
                        }
                    }
                },
                {
                    data: "id",
                    width: "155px",
                    render: function (data, type, row) {
                        return '<a class="btn  btn-sm btn-info" href="#modal-edit" data-toggle="modal"  data-backdrop="static" ' +
                            'onclick="updateOptions(\'' + data + '\')">' +
                            '<i class="ace-icon fa fa-pencil-square-o "></i>修改</a>' +
                            '&nbsp;&nbsp;' +
                            '<a class="btn btn-sm btn-danger" onclick="delOptions(\'' + data + '\')">' +
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
        $("#optionsForm").validate({
            errorElement: "label",
            errorClass: "validError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                code: {
                    required: true,
                    maxlength: 255
                },
                name: {
                    required: true,
                    maxlength: 255
                }
            },
            submitHandler: function () {
                $("#optionsForm").ajaxSubmit({
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


        var upload_form = $("#upload-form");
        //导入提交
        upload_form.on('submit', function () {
            var upload_check = $import_input.val();
            if (upload_check == '') {
                resetFileinput();
                $.notify("请选择文件!");
                return false;
            }
            $import_input.ace_file_input('loading', true);
            $(".ace-file-overlay").append('<div class="overlay-text">正在导入中...</div>');
            upload_form.ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: upload_form.attr('action'),
                dataType: 'json',
                success: function (result) {
                    $import_input.ace_file_input('loading', false);
                    var msg = '';
                    if (result && result.success && result.data) {
                        $("#modal-import").modal("hide");  //关闭上传窗口
                        $import_input.val('');
                        resetFileinput();

                        if (result.data.length == 0) {
                            msg = result.message;
                        } else {
                            $.each(result.data, function (i, item) {
                                var index = parseInt(item.rowIndex);
                                msg= item.reason + '<br/>';
                            });
                            msg += '其余导入成功';
                        }
                        $("#result-text").html(msg);
                        $("#import-result-modal").modal("show");
                    } else {
                        $("#modal-import").modal("hide");   //关闭上传窗口
                        $import_input.val('');
                        resetFileinput();
                        $import_input.ace_file_input('loading', false);
                    }
                }
            });
            return false;
        });
        $("#result-btn").on('click', function () {
            $("#modal-import").modal("hide");   //关闭上传窗口
            $("#import-result-modal").modal("hide");
            table.draw();
        })
    });

    /*新增时清空表单数据*/
    function flushForm() {
        $("#code").removeAttr("readonly");
        $("#optionsForm").find("input").each(function () {
            var id = $(this).attr("id");
            $("#" + id).val("");
            $("#remark").val("");
            $("#content").val("");
        });
    }
    /*修改时需要回显数据*/
    function updateOptions(id) {
        flushForm();
        $("#code").attr("readonly", "true");
        $.ajax({
            type: "GET",
            url: "${staticServer}/portal/inspection/options/findOne.do",
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
    function delOptions(id) {
        bootbox.confirm("<a style='font-size: 17px;color: red'>确定要删除该检查项信息？</a>", function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "${staticServer}/portal/inspection/options/delete.do",
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

</script>
</body>
</html>


