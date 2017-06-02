<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp"%>

<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<title>${systemName}</title>
	<%@ include file="/jsp/common/meta.jsp"%>
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="${base }/resources/common/script/pace/pace.min.js"></script>
	<!-- ================== END BASE JS ================== -->
	<style type="text/css">
		body {
			overflow-y: hidden;
		}
		.content {
			padding: 60px 0px;
		}
	</style>
<body>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<div id="header" class="header navbar navbar-default navbar-fixed-top">
			<!-- begin container-fluid -->
			<div class="container-fluid">
				<!-- begin mobile sidebar expand / collapse button -->
				<div class="navbar-header">
					<a href="${base}/login/gotoMain" class="navbar-brand" style="width: 300px;"><span><img src="${base }/resources/assets/images/icon.png"></span>&nbsp;&nbsp;${systemName }</a>
					<button type="button" class="navbar-toggle" data-click="sidebar-toggled">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<!-- end mobile sidebar expand / collapse button -->
				
				<!-- begin header navigation right -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown navbar-user">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<img src="${sessionScope.manager.logo}" alt=""> 
							<span class="hidden-xs">${sessionScope.manager.name}</span> 
							<!-- <b class="caret"></b> -->
						</a>
						<%-- <ul class="dropdown-menu animated fadeInLeft">
							<li><a href="${base }/logOff.${actionExt}">退出</a></li>
						</ul>  --%>
					</li>
				</ul>
				<!-- end header navigation right -->
			</div>
			<!-- end container-fluid -->
		</div>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<%-- <ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="${sessionScope.manager.logoUrl}" alt=""></a>
						</div>
						<div class="info">
							${sessionScope.role.name }：${sessionScope.manager.name }
						</div>
					</li>
				</ul> --%>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					<li class="nav-header">Navigation</li>
					<!-- 菜单 -->
					<li class="has-sub">
						<a href="${base }/admin/appInfo/onList" target="iframe-content">
							<i class="fa fa-inbox"></i> 
							<b class="caret pull-right"></b>
							<span>APP信息</span>
						</a>
					</li>
					<li class="has-sub">
						<a href="${base }/admin/eventInfo/onList" target="iframe-content">
							<i class="fa fa-inbox"></i> 
							<b class="caret pull-right"></b>
							<span>事件信息</span>
						</a>
					</li>
			        <!-- begin sidebar minify button -->
					<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
			        <!-- end sidebar minify button -->
				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<!-- end #sidebar -->
		<!-- begin #content -->
		<div id="content" class="content">
			<div>
		        <iframe name="iframe-content" id="iframe-content" src="${base}/jsp/common/welcome.jsp" frameborder="0" width="100%" height="93%"></iframe>
		    </div>
		</div>
		<!-- end #content -->
		
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	<script>
		$(document).ready(function() {
			App.init();
			var height = $(window).height();
			var width = $(window).width();
		});
		// 自适应大小
		window.onresize=function(){
			var height = $(window).height();
			var width = $(window).width();
		}
	</script>

</body>
</html>
