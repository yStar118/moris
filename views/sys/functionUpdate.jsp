<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-模块管理</title>
<%@ include file="../common/header.jsp"%>
</head>

<body class="no-skin">
	<%@ include file="../common/top.jsp"%>

	<div class="main-container" id="main-container">
		<%@ include file="../common/menu.jsp"%>
		<div class="main-content">
			<div class="main-content-inner">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">系统管理</li>
						<li class="active">模块管理</li>
					</ul>
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="page-header">
						<h1>
							模块管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> 修改功能
							</small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<form id="userForm" name="userForm" class="form-horizontal" action="${dynamicServer }/portal/sys/function/update.do?id=${sysFunction.id}"
								method="post">
								<input type="hidden" name="backUrl" value="${backUrl }"> 
								<input type="hidden" name="module_id" value="${sysFunction.module_id }">
								<div class="form-group">
									<label class="col-sm-3 control-label">操作名称：</label>
									<div class="col-sm-9">
										<input id="name" name="name" type="text" class="col-xs-10 col-sm-5" placeholder="" value="${sysFunction.name}"> <label
											id="nameTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">操作代码：</label>
									<div class="col-sm-9">
										<input id="code" type="text" name="code" class="col-xs-10 col-sm-5" placeholder="" value="${sysFunction.code}"><label
											id="codeTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">对应链接：</label>
									<div class="col-sm-9">
										<input id="url" type="text" name="url" class="col-xs-10 col-sm-5" placeholder="" value="${sysFunction.url}"><label
											id="urlTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">操作类型：</label>
									<div class="col-sm-9">
										<select name="type" class="col-xs-10 col-sm-5">
											<option value="0" <c:if test="${sysFunction.type eq 0}  "> selected</c:if>>读</option>
											<option value="1" <c:if test="${sysFunction.type  eq 1} "> selected</c:if>>写</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">描述：</label>
									<div class="col-sm-9 ">
										<input id="description" type="text" name="description" class="col-xs-10 col-sm-5" placeholder=""
											value="${sysFunction.description }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">排序：</label>
									<div class="col-sm-9 ">
										<input id="display_order" type="text" name="display_order" class="col-xs-10 col-sm-5" placeholder=""
											value="${sysFunction.display_order }"> <label id="display_orderTip"></label>
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-primary" type="submit">
											<i class="ace-icon fa fa-save bigger-110"></i> 保存
										</button>
										<button class="btn" type="button" onclick="history.back(-1)">
											<i class="ace-icon fa fa-undo bigger-110"></i> 取消
										</button>
									</div>
								</div>
							</form>

						</div>
					</div>
					<!-- /.main-content -->
				</div>
				<!-- /.main-container -->
				<%@ include file="../common/js.jsp"%>

				<script type="text/javascript">
					
				</script>
</body>
</html>
