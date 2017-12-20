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
    <link rel="stylesheet"
          href="${staticServer}/assets/components/bootstrap-multiselect/dist/css/bootstrap-multiselect.css"/>
    <link rel="stylesheet" href="${staticServer}/assets/select2/select2.css"/>
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
                    <li class="active">隐患排查</li>
                    <li class="active">检查方案</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        检查方案
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 编辑检查方案
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="planForm" name="planForm" class="form-horizontal"
                              action="${staticServer}/portal/inspection/plan/saveScreening.do" method="post">
                            <input type="hidden" name="id" value="${inspectionPlan.id }">
                            <input type="hidden" name="externalCode" value="${inspectionPlan.externalCode }">
                            <input type="hidden" name="type"
                                   value="<c:if test="${inspectionPlan.type == null}">${type}</c:if><c:if test="${inspectionPlan.type  != null}">${inspectionPlan.type}</c:if>">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查方案名称：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name" type="text" class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPlan.name }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案描述：</label>
                                <div class="col-sm-9">
                                     <textarea id="content" name="content" class="col-xs-12 col-sm-7" rows="5"
                                               placeholder="请输入方案描述..."
                                               maxlength="255">${inspectionPlan.content}</textarea>
                                    <label id="contentTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案检查周期：</label>
                                <div class="col-sm-9">
                                    <select name="checkCycle"
                                            class="col-xs-12 col-sm-7"
                                            id="checkCycle">
                                        <option value="">请选择检测周期</option>
                                        <option value="年">年</option>
                                        <option value="月">月</option>
                                        <option value="周">周</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案检查次数：</label>
                                <div class="col-sm-9">
                                    <input id="checkFrequency" name="checkFrequency" type="text"
                                           class="col-xs-12 col-sm-7"
                                           placeholder=""
                                           value="${inspectionPlan.checkFrequency }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方案开始时间：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="startDate" name="startDate"
                                           data-date-format="yyyy-mm-dd hh:ii"
                                           class="col-xs-12 col-sm-7 " title="" value="${inspectionPlan.startDate }">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-sm-3 control-label">是否启用：</label>
                                <div class="col-sm-9">
                                    <div class="radio">
                                        <label>
                                            <input name="isStart" id="isStartYes"
                                                   type="radio" class="ace"
                                                   value="1">
                                            <span class="lbl">是</span>
                                        </label>
                                        <label>
                                            <input name="isStart" id="isStartNo"
                                                   type="radio" class="ace"
                                                   value="0">
                                            <span class="lbl">否</span>
                                        </label>
                                        <label id="isStartTip"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">筛选执行部门：</label>
                                <div class="col-sm-9">
                                    <select id="departmentCodes" name="departmentCodes"
                                            class="col-xs-12 col-sm-7 col-lg-7"></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象分类：</label>
                                <div class="col-sm-9">
                                    <select id="categoryList" name="categoryList"
                                            class="col-xs-12 col-sm-7 col-lg-7"></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检查对象属性：</label>
                                <div class="col-sm-9">
                                    <select id="attributeList" name="attributeList"
                                            class="col-xs-12 col-sm-7 col-lg-7"></select>
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
<script src="${staticServer}/assets/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="${staticServer }/assets/select2/select-topic-tags.js"></script>
<script src="${staticServer }/assets/select2/select2.full.js"></script>
<script type="text/javascript">
    $(function () {
        if (${ not empty inspectionPlan.id}) {
            if (${inspectionPlan.isStart ==1}) {
                $("#isStartYes").checked = true;
                $("#isStartYes").click();
            } else {
                $("#isStartNo").checked = true;
                $("#isStartNo").click();
            }
        }
        /*部门select2数据*/
        var department = [];
        <c:forEach items="${departmentList}" var="department">
        department.push({id: '${department.code}', text: '${department.name}'});
        </c:forEach>
        var $departmentCodes = $('#departmentCodes');
        $departmentCodes.select2({
            tags: true,
            data: department,
            multiple: true,
            createTag: function (params) {
                return null;
            },
            allowClear: true,
            placeholder: '请输入要关联的部门名称',
            language: {
                noResults: function () {
                    return '没有找到该部门';
                },
                inputTooShort: function () {
                    return '请至少输入1个字符';
                }
            }
        });
        if (${not empty inspectionPlan.id}) {
            var checkedDepartment = [];
            <c:forEach items="${departmentCodeForDB}" var="department">
            checkedDepartment.push("${department}");
            </c:forEach>
            $departmentCodes.val(checkedDepartment).trigger('change');
        }

        /*检查对象分类select2 显示*/
        var category = [];
        <c:forEach items="${dictionaryPointList}" var="dictionaryPoint">
        <c:if test="${dictionaryPoint.type ==1}">
        category.push({id: '${dictionaryPoint.name}', text: '${dictionaryPoint.name}'});
        </c:if>
        </c:forEach>
        var $categoryList = $('#categoryList');
        $categoryList.select2({
            tags: true,
            data: category,
            multiple: true,
            createTag: function (params) {
                return null;
            },
            allowClear: true,
            placeholder: '请输入要关联的部门名称',
            language: {
                noResults: function () {
                    return '没有找到该部门';
                },
                inputTooShort: function () {
                    return '请至少输入1个字符';
                }
            }
        });
        if (${not empty inspectionPlan.id}) {
            var checkedCategory = [];
            <c:forEach items="${categoryListForDB}" var="category">
            checkedCategory.push("${category}");
            </c:forEach>
            $categoryList.val(checkedCategory).trigger('change');
        }

        /*检查对象属性select2 显示*/
        var attributeArray = [];
        <c:forEach items="${dictionaryPointList}" var="dictionaryPoint">
        <c:if test="${dictionaryPoint.type ==2}">
        attributeArray.push({id: '${dictionaryPoint.name}', text: '${dictionaryPoint.name}'});
        </c:if>
        </c:forEach>
        var $attributeList = $('#attributeList');
        $attributeList.select2({
            tags: true,
            data: attributeArray,
            multiple: true,
            createTag: function (params) {
                return null;
            },
            allowClear: true,
            placeholder: '请输入要关联的部门名称',
            language: {
                noResults: function () {
                    return '没有找到该部门';
                },
                inputTooShort: function () {
                    return '请至少输入1个字符';
                }
            }
        });
        if (${not empty inspectionPlan.id}) {
            var checkedAttribute = [];
            <c:forEach items="${attributeListForDB}" var="attribute">
            checkedAttribute.push("${attribute}");
            </c:forEach>
            $attributeList.val(checkedAttribute).trigger('change');
        }

        if (${not empty inspectionPlan.checkCycle}) {
            $("#checkCycle").find("option[value=${inspectionPlan.checkCycle}]").attr("selected", true);
        }

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
                },
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

