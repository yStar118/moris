<%--
  Created by IntelliJ IDEA.
  User: 1553280431@qq.com
  Date: 2017/7/11
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-检查点</title>
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
                    <li class="active">检查点</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        检查点
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 编辑检查点
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-12">
                        <form id="pointForm" name="pointForm" class="form-horizontal"
                              action="${staticServer}/portal/inspection/point/save.do" method="post">
                            <input type="hidden" name="id" value="${inspectionPoint.id }">
                            <input type="hidden" name="name" value="${inspectionPoint.name }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门名称：</label>
                                <div class="col-sm-9">
                                    <c:if test="${ empty inspectionPoint.id}">
                                        <input type="hidden" value="" name="departmentCode" id="departmentCode"/>
                                        <input type="hidden" value="" name="departmentName" id="departmentName"/>
                                        <select class=" col-xs-12 col-sm-7" id="departmentSelect">
                                            <option value="">请选择部门</option>
                                            <c:forEach items="${departmentList}" var="department">
                                                <option value="${department.code}">${department.name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <c:if test="${not empty inspectionPoint.id}">
                                        <input type="text" readonly value="${inspectionPoint.departmentName}"
                                               class="col-xs-12 col-sm-7"
                                               name="departmentName" id="departmentName"/>
                                        <input type="hidden" value="${inspectionPoint.departmentCode}"
                                               name="departmentCode"
                                               id="departmentCode"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">岗位名称：</label>
                                <div class="col-sm-9">
                                    <c:if test="${ empty inspectionPoint.id}">
                                        <input type="hidden" value="" name="jobsCode" id="jobsCode"/>
                                        <input type="hidden" value="" name="jobsName" id="jobsName"/>
                                        <select class=" col-xs-12 col-sm-7" id="jobsSelect">
                                            <option value="">请选择岗位</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${not empty inspectionPoint.id}">
                                        <input type="text" readonly value="${inspectionPoint.jobsName}"
                                               class="col-xs-12 col-sm-7"
                                               name="jobsName" id="jobsName"/>
                                        <input type="hidden" value="${inspectionPoint.jobsCode}" name="jobsCode"
                                               id="jobsCode"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备名称：</label>
                                <div class="col-sm-9">
                                    <c:if test="${ empty inspectionPoint.id}">
                                        <input type="hidden" value="" name="equipmentCode" id="equipmentCode"/>
                                        <input type="hidden" value="" name="equipmentName" id="equipmentName"/>
                                        <select class=" col-xs-12 col-sm-7" id="equipmentSelect">
                                            <option value="">请选择设备</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${not empty inspectionPoint.id}">
                                        <input type="text" readonly value="${inspectionPoint.equipmentName}"
                                               class="col-xs-12 col-sm-7"
                                               name="equipmentName" id="equipmentName"/>
                                        <input type="hidden" value="${inspectionPoint.equipmentCode}"
                                               name="equipmentCode"
                                               id="equipmentCode"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象：</label>
                                <div class="col-sm-9">
                                    <c:if test="${ empty inspectionPoint.id}">
                                        <input type="hidden" value="" name="objectCode" id="objectCode"/>
                                        <input type="hidden" value="" name="objectName" id="objectName"/>
                                        <select class=" col-xs-12 col-sm-7" id="objectSelect">
                                            <option value="">请选择检查对象</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${not empty inspectionPoint.id}">
                                        <input type="text" readonly value="${inspectionPoint.objectName}"
                                               class="col-xs-12 col-sm-7"
                                               name="objectName" id="objectName"/>
                                        <input type="hidden" value="${inspectionPoint.objectCode}" name="objectCode"
                                               id="objectCode"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">型号规格：</label>
                                <div class="col-sm-9">
                                    <input id="specifications" name="specifications" type="text" readonly
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.specifications }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-9">
                                    <input id="remark" name="remark" type="text" class="col-xs-12 col-sm-7" readonly
                                           placeholder=""
                                           value="${inspectionPoint.remark }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">tag：</label>
                                <div class="col-sm-9">
                                    <input id="tag" name="tag" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.tag }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">gps：</label>
                                <div class="col-sm-9">
                                    <input id="gps" name="gps" type="text" class="col-xs-12 col-sm-7" readonly
                                           placeholder=""
                                           value="${inspectionPoint.gps }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">对象分类名称：</label>
                                <div class="col-sm-9">
                                    <input id="categoryName" name="categoryName" type="text" class="col-xs-12 col-sm-7"
                                           readonly
                                           placeholder=""
                                           value="${inspectionPoint.categoryName }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查项：</label>
                                <div class="col-sm-9">
                                    <c:if test="${ empty inspectionPoint.id}">
                                        <input type="hidden" value="" name="checkOptionName" id="checkOptionName"/>
                                        <select class="col-xs-12 col-sm-7" id="checkOptionId" name="checkOptionId">
                                            <option value="">请选择检查项</option>
                                            <c:forEach items="${optionList}" var="option">
                                                <option value="${option.id}"
                                                        data-isSubJudge="${option.isSubJudge}">${option.name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <c:if test="${not empty inspectionPoint.id}">
                                        <input type="text" readonly value="${inspectionPoint.checkOptionName }"
                                               class="col-xs-12 col-sm-7"
                                               name="checkOptionName" id="checkOptionName"/>
                                        <input type="hidden" value="${inspectionPoint.checkOptionId }"
                                               name="checkOptionId" id="checkOptionId"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检测周期：</label>
                                <div class="col-sm-9">
                                    <select name="checkCycle"
                                            class="col-xs-12 col-sm-7"
                                            id="checkCycle">
                                        <option value="">请选择检测周期</option>
                                        <option value="年">年</option>
                                        <option value="月">月</option>
                                        <option value="周">周</option>
                                        <option value="日">日</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查次数：</label>
                                <div class="col-sm-9">
                                    <input id="checkFrequency" name="checkFrequency" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.checkFrequency }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查最小间隔：</label>
                                <div class="col-sm-9">
                                    <input id="checkInterval" name="checkInterval" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.checkInterval }">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-sm-3 control-label">是否主观判断：</label>
                                <div class="col-sm-9">
                                    <div class="radio">
                                        <label>
                                            <input name="isSubJudge" id="isSubJudgeYes" onclick="subJudge()"
                                                   type="radio" class="ace"
                                                   value="1">
                                            <span class="lbl">√</span>
                                        </label>
                                        <label>
                                            <input name="isSubJudge" id="isSubJudgeNo" onclick="NoSubJudge()"
                                                   type="radio" class="ace"
                                                   value="0">
                                            <span class="lbl">×</span>
                                        </label>
                                        <label id="isSubJudgeTip"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group noSubjective hidden ">
                                <label class="col-sm-3 control-label">单位：</label>
                                <div class="col-sm-9">
                                    <input id="unit" name="unit" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.unit }">
                                </div>
                            </div>
                            <div class="form-group noSubjective hidden ">
                                <label class="col-sm-3 control-label">最小值：</label>
                                <div class="col-sm-9">
                                    <input id="minValue" name="minValue" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.minValue }">
                                </div>
                            </div>
                            <div class="form-group noSubjective hidden">
                                <label class="col-sm-3 control-label">标准值：</label>
                                <div class="col-sm-9">
                                    <input id="standardValue" name="standardValue" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.standardValue }">
                                </div>
                            </div>
                            <div class="form-group noSubjective hidden">
                                <label class="col-sm-3 control-label">最大值：</label>
                                <div class="col-sm-9">
                                    <input id="bigValue" name="bigValue" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.bigValue }">
                                </div>
                            </div>
                            <div class="form-group noSubjective hidden">
                                <label class="col-sm-3 control-label">黄色预警值：</label>
                                <div class="col-sm-9">
                                    <input id="yellowWarning" name="yellowWarning" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.yellowWarning }">
                                </div>
                            </div>
                            <div class="form-group noSubjective hidden">
                                <label class="col-sm-3 control-label">橙色预警值：</label>
                                <div class="col-sm-9">
                                    <input id="orangeWarning" name="orangeWarning" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.orangeWarning }">
                                </div>
                            </div>
                            <div class="form-group noSubjective hidden">
                                <label class="col-sm-3 control-label">红色预警值：</label>
                                <div class="col-sm-9">
                                    <input id="redWarning" name="redWarning" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.redWarning }">
                                </div>
                            </div>
                            <div class="form-group subjective hidden">
                                <label class="col-sm-3 control-label">主观判断结果：</label>
                                <div class="col-sm-9">
                                    <div class="radio">
                                        <label>
                                            <input name="subjectiveJudgment" id="subjectiveJudgmentYes" type="radio"
                                                   class="ace" value="1">
                                            <span class="lbl">√</span>
                                        </label>
                                        <label>
                                            <input name="subjectiveJudgment" id="subjectiveJudgmentNo" type="radio"
                                                   class="ace" value="0">
                                            <span class="lbl">×</span>
                                        </label>
                                        <label id="subjectiveJudgmentTip"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">排序值：</label>
                                <div class="col-sm-9">
                                    <input id="displayOrder" name="displayOrder" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.displayOrder }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">重要等级：</label>
                                <div class="col-sm-9">
                                    <input id="importantLevel" name="importantLevel" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.importantLevel }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">危险等级：</label>
                                <div class="col-sm-9">
                                    <input id="dangerLevel" name="dangerLevel" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.dangerLevel }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">政府政策：</label>
                                <div class="col-sm-9">
                                    <input id="governmentPolicy" name="governmentPolicy" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.governmentPolicy }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">应急预案：</label>
                                <div class="col-sm-9">
                                    <input type="hidden" value="${inspectionPoint.contingencyInfoName}"
                                           name="contingencyInfoName" id="contingencyInfoName"/>
                                    <select class="col-xs-12 col-sm-7" id="contingencyInfoCode"
                                            name="contingencyInfoCode">
                                        <option value="">请选择应急预案</option>
                                        <c:forEach items="${contingencyInfoList}" var="contingencyInfo">
                                            <option value="${contingencyInfo.id}">${contingencyInfo.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">现场处置方案：</label>
                                <div class="col-sm-9">
                                    <input type="text" value="${inspectionPoint.contingencyScene}"
                                           name="contingencyScene" class="col-xs-12 col-sm-7"
                                           id="contingencyScene"/>
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

        /*部门岗位联动*/
        $("#departmentSelect").on("change", function () {
            $("#jobsSelect").empty(); // 清空城市SELECT控件
            if ($("#departmentSelect").val() != '') {
                var url = "${dynamicServer}/portal/organization/jobs/findByDepartmentCode.do?departmentCode=" + $("#departmentSelect").val();
                $("#jobsSelect").append("<option value=''>请选择岗位</option>");
                $.getJSON(url, function (data) {
                    $.each(data, function (i, item) {
                        $("#jobsSelect").append("<option value='" + item["code"] + "'>" + item["name"] + "</option>");
                    });
                });
            }
        });

        /*岗位设备联动*/
        $("#jobsSelect").on("change", function () {
            $("#objectSelect").empty(); // 清空城市SELECT控件
            if ($("#jobsSelect").val() != '') {
                var url = "${dynamicServer}/portal/inspection/object/findByJobsCodeGroupByEquipmentName.do?jobsCode=" + $("#jobsSelect").val();
                $("#equipmentSelect").append("<option value=''>请选择设备</option>");
                $.getJSON(url, function (data) {
                    $.each(data, function (i, item) {
                        $("#equipmentSelect").append("<option value='" + item["equipmentName"] + "'>" + item["equipmentName"] + "</option>");
                    });
                });
            }
        });

        /*设备检查对象联动*/
        $("#equipmentSelect").on("change", function () {
            $("#objectSelect").empty(); // 清空城市SELECT控件
            if ($("#equipmentSelect").val() != '') {
                var url = "${dynamicServer}/portal/inspection/object/findByEquipmentName.do?equipmentName=" +
                    $("#equipmentSelect").val() + "&jobsCode=" + $("#jobsSelect").val();
                $("#objectSelect").append("<option value=''>请选择检查对象</option>");
                $.getJSON(url, function (data) {
                    $.each(data, function (i, item) {
                        $("#objectSelect").append("<option value='" + item["code"] + "'>" + item["name"] + "</option>");
                    });
                });
            }
        });

        /*检查对象详情联动*/
        $("#objectSelect").on("change", function () {
            /*根据检查对象编号获取数据*/
            $.ajax({
                type: "GET",
                url: "${staticServer}/portal/inspection/object/findByCode.do",
                data: {
                    "code": $("#objectSelect").val()
                },
                contentType: 'application/json',
                success: function (data) {
                    $("#departmentCode").val(data.departmentCode);
                    $("#departmentName").val(data.departmentName);
                    $("#jobsCode").val(data.jobsCode);
                    $("#jobsName").val(data.jobsName);
                    $("#equipmentCode").val(data.equipmentCode);
                    $("#equipmentName").val(data.equipmentName);
                    $("#specifications").val(data.specifications);
                    $("#remark").val(data.remark);
                    $("#tag").val(data.tag);
                    $("#gps").val(data.gps);
                    $("#objectCode").val(data.code);
                    $("#objectName").val(data.name);
                    $("#categoryName").val(data.categoryName);
                }
            });
        });

        if (${ not empty inspectionPoint.id}) {
            if (${inspectionPoint.isSubJudge ==1}) {
                $("#isSubJudgeYes").checked = true;
                $("#isSubJudgeYes").click();
            } else {
                $("#isSubJudgeNo").checked = true;
                $("#isSubJudgeNo").click();
            }

            if (${not empty inspectionPoint.subjectiveJudgment &&inspectionPoint.subjectiveJudgment ==1}) {
                $("#subjectiveJudgmentYes").checked = true;
                $("#subjectiveJudgmentYes").click();
            } else {
                $("#subjectiveJudgmentNo").checked = true;
                $("#subjectiveJudgmentNo").click();
            }
            if (${not empty inspectionPoint.checkCycle}) {
                $("#checkCycle").find("option[value=${inspectionPoint.checkCycle}]").attr("selected", true);
            }
            if (${not empty inspectionPoint.contingencyInfoCode}) {
                $("#contingencyInfoCode").find("option[value=${inspectionPoint.contingencyInfoCode}]").attr("selected", true);
            }
        }

        $("#checkOptionId").on("change", function () {
            $("#checkOptionName").val($("#checkOptionId option:selected").text());
        });

        $("#contingencyInfoCode").on("change", function () {
            $("#contingencyInfoName").val($("#contingencyInfoCode option:selected").text());
        });


        $("#pointForm").validate({
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
                },
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });

    function backIndex() {
        window.location.href = "${staticServer}/portal/inspection/point/index.do";
    }

    function subJudge() {
        $(".noSubjective").addClass("hidden");
        $(".subjective").removeClass("hidden");
    }

    function NoSubJudge() {
        $(".noSubjective").removeClass("hidden");
        $(".subjective").addClass("hidden");
    }
</script>
</body>
</html>
