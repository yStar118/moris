<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/8/5
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-检查方案</title>
    <link rel="stylesheet" href="${staticServer}/assets/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"/>
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
                    <li class="active">巡检单元</li>
                    <li class="active">检查方案</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        检查方案
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 检查方案详情
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查方案名称：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPlan.name }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案内容：</label>
                                <div class="col-sm-9">
                                    <input id="content" name="content" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPlan.content }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案检查周期：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPlan.checkCycle }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案检查次数：</label>
                                <div class="col-sm-9">
                                    <input id="checkFrequency" name="checkFrequency" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPlan.checkFrequency }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案开始时间：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="startDate" name="startDate" readonly
                                           class="col-xs-12 col-sm-7 " title="" value="${inspectionPlan.startDate }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案执行部门：</label>
                                <div class="col-sm-9">
                                    <input type="text" readonly value="${inspectionPlan.departmentName }"
                                           class="col-xs-12 col-sm-7"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案执行岗位：</label>
                                <div class="col-sm-9">
                                    <input type="text" readonly value="${inspectionPlan.jobsName }"
                                           class="col-xs-12 col-sm-7"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否启用：</label>
                                <div class="col-sm-9">
                                    <input type="text" readonly
                                           value="<c:if test="${inspectionPlan.isStart == 0 }">否</c:if>
<c:if test="${inspectionPlan.isStart == 1 }">是</c:if>"
                                           class="col-xs-12 col-sm-7"/>
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
<script src="${staticServer}/assets/js/jquery.validation/jquery.validate.js"></script>
<script src="${staticServer}/assets/js/jquery.validation/jquery.validate.zh-CN.js"></script>
<script src="${staticServer}/assets/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script type="text/javascript">
    $(function () {
        $("#departmentCode").on("change", function () {
            var $optionSelected = $("#departmentCode option:selected");
            $("#departmentName").val($optionSelected.text());
            $.ajax({
                type: "GET",
                url: "${staticServer}/portal/organization/jobs/findByDepartmentCode.do",
                data: {
                    "departmentCode": $optionSelected.val()
                },
                contentType: 'application/json',
                success: function (data) {
                    $("#jobsCode").empty();
                    $("#jobsCode").append('<option value="">请选择岗位</option>');
                    $.each(data, function (i, item) {
                        $("#jobsCode").append("<option value='" + item.code + "'>" + item.name + "</option>")
                    })
                }
            });
        });

        $("#jobsCode").on("change", function () {
            $("#jobsName").val($("#jobsCode option:selected").text());
        });

        $("#departmentCode2").on("change", function () {
            var $optionSelected = $("#departmentCode2 option:selected");
            $("#departmentName2").val($optionSelected.text());
            $.ajax({
                type: "GET",
                url: "${staticServer}/portal/organization/jobs/findByDepartmentCode.do",
                data: {
                    "departmentCode": $optionSelected.val()
                },
                contentType: 'application/json',
                success: function (data) {
                    $("#jobsCode2").empty();
                    $("#jobsCode2").append('<option value="">请选择岗位</option>');
                    $.each(data, function (i, item) {
                        $("#jobsCode2").append("<option value='" + item.code + "'>" + item.name + "</option>")
                    })
                }
            });
        });

        $("#jobsCode2").on("change", function () {
            $("#jobsName2").val($("#jobsCode2 option:selected").text());
        });
        $.fn.datetimepicker.dates['zh'] = {
            days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
            daysShort: ["日", "一", "二", "三", "四", "五", "六", "日"],
            daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
            months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            monthsShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
            meridiem: ["上午", "下午"],
            today: "今天"
        };

        $('#startDate').datetimepicker({
            language: 'zh',
            autoclose: true,
            todayBtn: "linked"
        });
        $("#planForm").validate({
            errorElement: "label",
            errorClass: "valiError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                departmentName: {
                    required: true,
                    maxlength: 255
                },
                subjectiveJudgment: {
                    required: true
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });

    function backIndex() {
        window.location.href = "${staticServer}/portal/inspection/plan/index.do?type=${type}";
    }


</script>
</body>
</html>


