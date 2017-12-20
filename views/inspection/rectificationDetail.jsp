<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/9/9
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-整改任务</title>
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
                    <li class="active">整改任务</li>
                    <li class="active">整改任务</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        整改任务
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 整改任务详情
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal"
                              action="#" method="post">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">整改任务名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.name }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">整改任务描述：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.remark }"><label
                                        id="remarkTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门名称：</label>
                                <div class="col-sm-9">
                                    <input type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.departmentName }"><label
                                        id="departmentNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">岗位名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.jobsName }"><label
                                        id="jobsNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备名称：</label>
                                <div class="col-sm-9">
                                    <input type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.equipmentName }"><label
                                        id="equipmentNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.objectName }"><label
                                        id="objectNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查点名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.pointName }"><label
                                        id="pointNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">异常图片：</label>
                                <div class="col-sm-9">
                                    <img style="max-width: 58.3%" src="${imageServer}${rectification.abnormalityImg }"
                                         alt="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">整改任务人：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.rectificationUserName }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">专项资金：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.specialFunds }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否整改完成：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="<c:if test="${rectification.status==0}">未整改</c:if>
<c:if test="${rectification.status==1}">已整改</c:if>"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">整改时间：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${rectification.rectificationDate}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">整改后图片：</label>
                                <div class="col-sm-9">
                                    <img style="max-width: 58.3%" src="${imageServer}${rectification.rectificationImg }"
                                         alt="">
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-5 col-md-7">
                                    <button class="btn" type="button"
                                            onclick="backIndex()">
                                        <i class="ace-icon fa fa-undo bigger-110"></i> 返回
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
<script type="text/javascript">

    function backIndex() {
        window.location.href = "${staticServer}/portal/inspection/rectification/index.do";
    }

</script>
</body>
</html>

