<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/8/25
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-整改任务</title>
    <%@ include file="../common/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticServer }/assets/datetimepicker/jquery.datetimepicker.css"/>
    <style>
        .control-label {
            padding-top: 4px !important;
        }
    </style>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>

<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">整改任务</li>
                    <li class="active">整改任务</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        整改任务
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 编辑整改任务
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="rectificationForm" name="rectificationForm" class="form-horizontal"
                              action="${staticServer}/portal/inspection/rectification/save.do" method="post">
                            <input type="hidden" name="id" value="${rectification.id }">
                            <input type="hidden" name="status" value="${rectification.status }">
                            <input type="hidden" name="rectificationDate" value="${rectification.rectificationDate }">
                            <input type="hidden" name="rectificationImg" value="${rectification.rectificationImg }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">整改任务名称：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.name }"><label
                                        id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">整改任务描述：</label>
                                <div class="col-sm-9">
                                    <input id="remark" name="remark" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.remark }"><label
                                        id="remarkTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门名称：</label>
                                <div class="col-sm-9">
                                    <input id="departmentName" name="departmentName" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.departmentName }"><label
                                        id="departmentNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">岗位名称：</label>
                                <div class="col-sm-9">
                                    <input id="jobsName" name="jobsName" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.jobsName }"><label
                                        id="jobsNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备名称：</label>
                                <div class="col-sm-9">
                                    <input id="equipmentName" name="equipmentName" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.equipmentName }"><label
                                        id="equipmentNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象名称：</label>
                                <div class="col-sm-9">
                                    <input id="objectName" name="objectName" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.objectName }"><label
                                        id="objectNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查点名称：</label>
                                <div class="col-sm-9">
                                    <input id="pointName" name="pointName" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.pointName }"><label
                                        id="pointNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">异常图片：</label>
                                <div class="col-sm-9">
                                    <input id="abnormalityImg" name="abnormalityImg" type="hidden"
                                           value="${rectification.abnormalityImg }">
                                    <img style="max-width: 58.3%" src="${imageServer}${rectification.abnormalityImg }"
                                         alt="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择整改人：</label>
                                <div class="col-sm-9 ">
                                    <form:select path="rectification.rectificationUserId" name="rectificationUserId"
                                                 class="col-xs-12 col-sm-7"
                                                 id="rectificationUserId">
                                        <option value="">请选择整改人</option>
                                        <form:options items="${userList }" itemValue="id" itemLabel="realname"/>
                                    </form:select>
                                    <label id="rectificationUserIdTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">专项资金：</label>
                                <div class="col-sm-9">
                                    <input id="specialFunds" name="specialFunds" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${rectification.specialFunds }"><label
                                        id="specialFundsTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">截止日期：</label>
                                <div class="col-sm-9">
                                    <input id="lastDate" name="lastDate" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${rectification.lastDate }"><label
                                        id="lastDateTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">预警通知号码：</label>
                                <div class="col-sm-9">
                                    <input id="telephone" name="telephone" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="请填写预警通知人的手机号"
                                           value="${rectification.telephone }"><label
                                        id="telephoneTip"></label>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn btn-primary" type="submit">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 保存
                                    </button>
                                    <button class="btn" type="button"
                                            onclick="backIndex()">
                                        <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                    </button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <!-- /.main-content -->
            </div>
        </div>
    </div>
</div>
<!-- /.main-container -->
<%@ include file="../common/js.jsp" %>
<script src="${staticServer}/assets/js/jquery.validation/jquery.validate.js"></script>
<script src="${staticServer}/assets/js/jquery.validation/jquery.validate.zh-CN.js"></script>
<script src="${staticServer }/assets/datetimepicker/jquery.datetimepicker.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {

        $("#lastDate").datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d',
            formatDate: 'Y-m-d'
        });

        $.validator.addMethod("isMobile", function (value, element) {
            var length = value.length;
            var mobile = /^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\d{8}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");

        $("#rectificationForm").validate({
            //debug : true,
            errorElement: "label",
            errorClass: "valiError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                name: {
                    required: true,
                    maxlength: 255
                },
                categoryId: {
                    required: true
                },
                telephone: {
                    required: true,
                    isMobile: true
                }
            },
            submitHandler: function (form) {
                return true;
            }
        });
    });

    function backIndex() {
        window.location.href = "${staticServer}/portal/inspection/rectification/index.do";
    }

</script>
</body>
</html>
