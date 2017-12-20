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
    <title>${webTitle}-检查点</title>
    <link rel="stylesheet" href="${staticServer}/assets/components/chosen/chosen.css"/>
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
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 检查点详情
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象：</label>
                                <div class="col-sm-9">
                                    <input type="text" readonly value="${inspectionPoint.objectName}"
                                           class="col-xs-12 col-sm-7"
                                           name="objectName" id="objectName"/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门名称：</label>
                                <div class="col-sm-9">
                                    <input id="departmentName" name="departmentName" type="text" readonly
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.departmentName}"><label
                                        id="departmentNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">岗位名称：</label>
                                <div class="col-sm-9">
                                    <input id="jobsName" name="jobsName" type="text" class="col-xs-12 col-sm-7" readonly
                                           placeholder=""
                                           value="${inspectionPoint.jobsName }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备名称：</label>
                                <div class="col-sm-9">
                                    <input id="equipmentName" name="equipmentName" type="text" readonly
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPoint.equipmentName }">
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
                                    <input id="tag" name="tag" type="text" class="col-xs-12 col-sm-7" readonly
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
                                    <input type="text" readonly value="${inspectionPoint.checkOptionName }"
                                           class="col-xs-12 col-sm-7"
                                           name="checkOptionName" id="checkOptionName"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检测周期：</label>
                                <div class="col-sm-9">
                                    <input type="text" readonly value="${inspectionPoint.checkCycle }"
                                           class="col-xs-12 col-sm-7"
                                           name="checkCycle" id="checkCycle"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查次数：</label>
                                <div class="col-sm-9">
                                    <input id="checkFrequency" name="checkFrequency" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPoint.checkFrequency }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查最小间隔：</label>
                                <div class="col-sm-9">
                                    <input id="checkInterval" name="checkInterval" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPoint.checkInterval }">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-sm-3 control-label">是否主观判断：</label>
                                <div class="col-sm-9">
                                    <div class="radio">
                                        <span class="label label-xlg label-primary arrowed arrowed-right">
                                            <c:if test="${inspectionPoint.isSubJudge==0}">否</c:if>
                                            <c:if test="${inspectionPoint.isSubJudge==1}">是</c:if></span>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${inspectionPoint.isSubJudge==0}">
                                <div class="form-group noSubjective  ">
                                    <label class="col-sm-3 control-label">单位：</label>
                                    <div class="col-sm-9">
                                        <input id="unit" name="unit" type="text" class="col-xs-12 col-sm-7"
                                               placeholder="" readonly
                                               value="${inspectionPoint.unit }">
                                    </div>
                                </div>
                                <div class="form-group noSubjective  ">
                                    <label class="col-sm-3 control-label">最小值：</label>
                                    <div class="col-sm-9">
                                        <input id="minValue" name="minValue" type="text" class="col-xs-12 col-sm-7"
                                               placeholder="" readonly
                                               value="${inspectionPoint.minValue }">
                                    </div>
                                </div>
                                <div class="form-group noSubjective ">
                                    <label class="col-sm-3 control-label">标准值：</label>
                                    <div class="col-sm-9">
                                        <input id="standardValue" name="standardValue" type="text"
                                               class="col-xs-12 col-sm-7" readonly
                                               placeholder=""
                                               value="${inspectionPoint.standardValue }">
                                    </div>
                                </div>
                                <div class="form-group noSubjective ">
                                    <label class="col-sm-3 control-label">最大值：</label>
                                    <div class="col-sm-9">
                                        <input id="bigValue" name="bigValue" type="text" class="col-xs-12 col-sm-7"
                                               placeholder="" readonly
                                               value="${inspectionPoint.bigValue }">
                                    </div>
                                </div>
                                <div class="form-group noSubjective ">
                                    <label class="col-sm-3 control-label">黄色预警值：</label>
                                    <div class="col-sm-9">
                                        <input id="yellowWarning" name="yellowWarning" type="text"
                                               class="col-xs-12 col-sm-7"
                                               placeholder="" readonly
                                               value="${inspectionPoint.yellowWarning }">
                                    </div>
                                </div>
                                <div class="form-group noSubjective ">
                                    <label class="col-sm-3 control-label">橙色预警值：</label>
                                    <div class="col-sm-9">
                                        <input id="orangeWarning" name="orangeWarning" type="text"
                                               class="col-xs-12 col-sm-7"
                                               placeholder="" readonly
                                               value="${inspectionPoint.orangeWarning }">
                                    </div>
                                </div>
                                <div class="form-group noSubjective ">
                                    <label class="col-sm-3 control-label">红色预警值：</label>
                                    <div class="col-sm-9">
                                        <input id="redWarning" name="redWarning" type="text" class="col-xs-12 col-sm-7"
                                               placeholder="" readonly
                                               value="${inspectionPoint.redWarning }">
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${inspectionPoint.isSubJudge==1}">
                                <div class="form-group subjective ">
                                    <label class="col-sm-3 control-label">主观判断结果：</label>
                                    <div class="col-sm-9">
                                        <div class="radio">
                                         <span class="label label-xlg label-primary arrowed arrowed-right"><c:if
                                                 test="${inspectionPoint.subjectiveJudgment==0}">×</c:if><c:if
                                                 test="${inspectionPoint.subjectiveJudgment==1}">√</c:if></span>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">排序值：</label>
                                <div class="col-sm-9">
                                    <input id="displayOrder" name="displayOrder" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPoint.displayOrder }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">重要等级：</label>
                                <div class="col-sm-9">
                                    <input id="importantLevel" name="importantLevel" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPoint.importantLevel }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">危险等级：</label>
                                <div class="col-sm-9">
                                    <input id="dangerLevel" name="dangerLevel" type="text" class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPoint.dangerLevel }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">政府政策：</label>
                                <div class="col-sm-9">
                                    <input id="governmentPolicy" name="governmentPolicy" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder="" readonly
                                           value="${inspectionPoint.governmentPolicy }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">应急预案：</label>
                                <div class="col-sm-9">
                                    <input type="text" value="${inspectionPoint.contingencyInfoName}" readonly
                                           class="col-xs-12 col-sm-7"
                                           name="contingencyInfoName" id="contingencyInfoName"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">现场处置方案：</label>
                                <div class="col-sm-9">
                                    <input type="text" value="${inspectionPoint.contingencyScene}"
                                           class="col-xs-12 col-sm-7"
                                           name="contingencySceneName" readonly
                                           id="contingencySceneName"/>
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
        window.location.href = "${staticServer}/portal/inspection/point/index.do";
    }

</script>
</body>
</html>

