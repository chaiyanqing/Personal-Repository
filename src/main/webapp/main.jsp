<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object userName = request.getSession().getAttribute("userName");
%>
<c:set var="bodyPlus" value="${pageContext.request.contextPath}" />
<c:set var="userName" value="<%=userName %>" />

<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>飞行员生命体征监测与搜救一体化信息处理系统</title>
	<meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
	<meta name="description" content="JS代码网提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	
	<!-- basic styles -->
	<link rel="stylesheet" href="${bodyPlus }/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${bodyPlus }/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

	<!-- text fonts -->
	<link rel="stylesheet" href="${bodyPlus }/assets/css/fonts.googleapis.com.css" />

	<!-- ace styles -->
	<link rel="stylesheet" href="${bodyPlus }/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
	
	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${bodyPlus }/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
	<![endif]-->
	<link rel="stylesheet" href="${bodyPlus }/assets/css/ace-skins.min.css" />
	<link rel="stylesheet" href="${bodyPlus }/assets/css/ace-rtl.min.css" />

	<!--[if lte IE 9]>
	  <link rel="stylesheet" href="${bodyPlus}/assets/css/ace-ie.min.css" />
	<![endif]-->

	<!-- ace settings handler -->
	<script src="${bodyPlus }/assets/js/ace-extra.min.js"></script>
	

	<link rel="stylesheet" href="${bodyPlus }/assets/css/chosen.min.css" />
	<!-- ace styles -->
	<link rel="stylesheet" href="${bodyPlus }/assets/css/jquery-ui.custom.min.css" />
	<link rel="stylesheet" href="${bodyPlus }/assets/css/daterangepicker.min.css" />
	<link rel="stylesheet" href="${bodyPlus }/assets/css/dropzone.min.css" />
	<link rel="stylesheet" href="${bodyPlus }/assets/css/jquery.gritter.min.css" />
	<link rel="stylesheet" href="${bodyPlus }/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${bodyPlus }/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="${bodyPlus }/assets/css/bootstrap-datetimepicker.min.css"/>
	<link rel="stylesheet" href="${bodyPlus }/assets/css/bootstrap-timepicker.min.css" />
	<!--[if lte IE 8]>
	  <link rel="stylesheet" href="${bodyPlus }/assets/css/ace-ie.min.css" />
	<![endif]-->

	<!--  表格表头冻结引入 -->
	<link rel="stylesheet" href="${bodyPlus }/assets/css/bootstrap-duallistbox.min.css" />
		
	<link rel="stylesheet" href="${bodyPlus }/assets/css/select2.min.css" type="text/css" />
	
	
	<!--[if lte IE 8]>
	  <script src="${bodyPlus }/assets/js/html5shiv.min.js"></script>
	  <script src="${bodyPlus }/assets/js/respond.min.js"></script>
	<![endif]-->
	
	<!--[if lt IE 9]>
	<script src="${bodyPlus }/assets/js/html5shiv.js"></script>
	<script src="${bodyPlus }/assets/js/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		.modal-size{
			width: 800px;
			height: 400px;
		}
		.mene-font{
			/* font-size: 16px; */
		}
		.row-new1{
		    width: 100%;
		    height: 48px;
			padding-top: 10px;
		    padding-bottom: 8px;
   			padding-left: 10px;		
		    background-color: #eff3f8;
		}
		.row-new2{
		    height: 48px;
		    margin-top: -48px;
		    padding-top: 10px;
		    padding-bottom: 8px;
			padding-right: 10px;
		    background-color: #eff3f8;
		}
		.glyphicon-flag {
		    color: red;
		}
		.menu-icon{
			display: inline !important;
			font-size: 16px;
		}
		.main-content{
			padding: 0 200px !important;
			
		}
		.menu-text{
			font-size: 16px !important;
		}
		.page-content{
			background-color: gray !important;
		}
		.login-layout{
			background-color: #464444 !important;
		}
		.myselect{
			width: 100px; 
			font-size: 14px; 
			height: 27px; 
			border-radius: 3px;
		}
		
		.new-nav{
			padding-left: 0;
    		list-style: none;
		}
		.new-nav li{
			display: block;
    		position: relative;
		}
		.new-nav li a{
			display: block;
   			position: relative;
			padding: 15px 15px;
			text-decoration: none;
			cursor: pointer;
		}
		/* .new-nav li a:hover{
			background-color: silver;
			font-weight: bold;
		} */
		.new-nav li:hover{
			/* background-color: silver; */
			font-weight: bold;
		}
	</style>
</head>

<body class="login-layout">
<!-- basic scripts -->
<!--[if !IE]> -->

		<!--[if !IE]> -->
		<script src="${bodyPlus }/assets/js/jquery-2.1.4.min.js"></script>
		<!-- <![endif]-->
		<!--[if IE]>
		  <script src="${bodyPlus }/assets/js/jquery-1.11.3.min.js"></script>
		<![endif]-->

		<!--[if !IE]> -->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${bodyPlus}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${bodyPlus }/assets/js/bootstrap.min.js"></script>

		<script src="${bodyPlus }/assets/js/ace-elements.min.js"></script>
		<script src="${bodyPlus }/assets/js/ace.min.js"></script>

		<script src="${bodyPlus }/assets/js/jquery-typeahead.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${bodyPlus }/assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="${bodyPlus }/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${bodyPlus }/assets/js/jquery.easypiechart.min.js"></script>
		<script src="${bodyPlus }/assets/js/jquery.gritter.min.js"></script>
		<!-- <script src="${bodyPlus }/assets/js/spin.min.js"></script> -->

		<script src="${bodyPlus }/assets/js/chosen.jquery.min.js"></script>
		<script src="${bodyPlus }/assets/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="${bodyPlus }/assets/js/moment.min.js"></script>
		<script src="${bodyPlus }/assets/js/bootstrap-datetimepicker.min.js"></script>
		<script src="${bodyPlus }/assets/js/bootstrap-timepicker.min.js"></script>
		<script src="${bodyPlus }/assets/js/moment.min.js"></script>
		<script src="${bodyPlus }/assets/js/daterangepicker.min.js"></script>
		<script src="${bodyPlus }/assets/js/bootstrap-colorpicker.min.js"></script>
		
		
		
		<script src="${bodyPlus }/assets/js/jquery.validate.min.js"></script>
		<script src="${bodyPlus }/assets/js/jquery-additional-methods.min.js"></script>
		<script src="${bodyPlus }/assets/js/bootbox.js"></script>
		<script src="${bodyPlus }/assets/js/jquery.maskedinput.min.js"></script>
		<script src="${bodyPlus }/assets/js/autosize.min.js"></script>
		<script src="${bodyPlus }/assets/js/jquery.inputlimiter.min.js"></script>
		<script src="${bodyPlus }/assets/js/bootstrap-tag.min.js"></script>
		<script src="${bodyPlus }/assets/js/jquery.knob.min.js"></script>
		<!-- ace scripts -->
		<!-- excel-js -->
		<script src="${bodyPlus }/assets/js/js-xlsx/FileSaver.min.js"></script>
		<script src="${bodyPlus }/assets/js/js-xlsx/xlsx.full.min.js"></script>
		
		<%-- <script src="${bodyPlus }/assets/js/jquery.dataTables.min.js"></script>
		<script src="${bodyPlus }/assets/js/jquery.dataTables.bootstrap.js"></script>  --%>
		<!--  表格表头冻结引入替换 -->
		<script src="${bodyPlus }/assets/js/jquery.dataTables.min.js"></script>
		<script src="${bodyPlus }/assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<%-- 
		<script src="${bodyPlus }/js/dateFormat.js"></script>
		<script src="${bodyPlus }/js/excel.js"></script>

        <script src="${bodyPlus }/js/calculate.js"></script> --%>
		<!-- inline scripts related to this page -->
		<script src="${bodyPlus }/js/ajax.js"></script>
		
		<script src="${bodyPlus }/assets/js/select2.min.js"></script>
		<script src="${bodyPlus }/assets/echarts-all.js"></script>
</body>

</html>

