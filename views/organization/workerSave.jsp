<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/7/28
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-职工管理</title>
    <%@ include file="../common/header.jsp" %>
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
                    <li class="active">职工管理</li>
                    <li class="active">职工管理</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        职工管理
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 编辑职工信息
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="workerForm" name="workerForm" class="form-horizontal"
                              action="${staticServer}/portal/organization/worker/save.do" method="post">
                            <input type="hidden" name="id" value="${worker.id }">
                            <input type="hidden" name="role_id" value="${worker.role_id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">真实姓名：</label>
                                <div class="col-sm-9">
                                    <input id="realname" name="realname" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" maxlength="10"
                                           value="${worker.realname }"><label
                                        id="realnameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-9">
                                    <input id="telephone" name="telephone" type="text"
                                           <c:if test="${not empty worker.telephone}">readonly</c:if>
                                           class="col-xs-12 col-sm-7" maxlength="11"
                                           placeholder=""
                                           value="${worker.telephone }"><label
                                        id="telephoneTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">email：</label>
                                <div class="col-sm-9">
                                    <input id="email" name="email" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" maxlength="50"
                                           value="${worker.email }"><label
                                        id="emailTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">企业：</label>
                                <div class="col-sm-9 ">
                                    <form:select path="worker.enterpriseId" name="enterpriseId"
                                                 class="col-xs-12 col-sm-7"
                                                 id="enterpriseId">
                                        <option value="">请选择企业</option>
                                        <form:options items="${enterpriseList }" itemValue="id" itemLabel="name"/>
                                    </form:select>
                                    <label id="enterpriseIdTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门：</label>
                                <div class="col-sm-9 ">
                                    <form:select path="worker.departmentId" name="departmentId"
                                                 class="col-xs-12 col-sm-7"
                                                 id="departmentId">
                                        <option value="">请选择部门</option>
                                        <form:options items="${departmentList }" itemValue="id" itemLabel="name"/>
                                    </form:select>
                                    <label id="departmentIdTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">岗位：</label>
                                <div class="col-sm-9 ">
                                    <form:select path="worker.jobsId" name="jobsId"
                                                 class="col-xs-12 col-sm-7"
                                                 id="jobsId">
                                        <option value="">请选择岗位</option>
                                        <form:options items="${jobsList }" itemValue="id" itemLabel="name"/>
                                    </form:select>
                                    <label id="jobsIdTip"></label>
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
<script type="text/javascript">
    $(function () {

        $.validator.addMethod("isMobile", function (value, element) {
            var length = value.length;
            var mobile = /^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\d{8}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");

        $("#workerForm").validate({
            //debug : true,
            errorElement: "label",
            errorClass: "valiError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                realname: {
                    required: true,
                    maxlength: 50
                },
                telephone: {
                    isMobile: true,
                    required: true
                },
                email: {
                    email: true,
                    required: true
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });

    function backIndex() {
        window.location.href = "${staticServer}/portal/organization/worker/index.do";
    }

</script>
</body>
</html>
