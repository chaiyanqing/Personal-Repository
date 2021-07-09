<!DOCTYPE html>

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@include file="../main.jsp" %>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>飞行员生命体征监测与搜救一体化信息处理系统</title>
		<meta name="keywords" content="BodyPlus"/>
		<meta name="description" content="训练监测分析系统" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<%-- <link href="${bodyPlus }/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${bodyPlus }/assets/css/font-awesome.min.css" /> --%>

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${bodyPlus }/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
		<!-- page specific plugin styles -->
		<!-- fonts -->
		<!-- ace styles -->

		<%-- <link rel="stylesheet" href="${bodyPlus }/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${bodyPlus }/assets/css/ace-rtl.min.css" /> --%>

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${bodyPlus }/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${bodyPlus }/assets/js/html5shiv.js"></script>
		<script src="${bodyPlus }/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<br/><br/><br/>
						<div class="login-container" style="width: 430px;">
							<div class="center" style="margin: 10px; border-bottom: 2px solid black;">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">飞行员生命体征监测与搜救一体化信息处理系统</span>
									<span class="white"></span>
								</h1>
							</div>
							<h2></h2>
							<div class="space-6"></div>

							<div class="position-relative" style="margin-left: 35px; width: 360px;">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												请输入您的用户名密码
											</h4>
											<div class="space-6"></div>
                                            <input type="hidden" value="<%=session.getAttribute("userName") %>" id="userId">
											<form method="post" action="${bodyPlus }/login.htm">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" id="i_username" name="i_username" class="form-control" placeholder="Username" value="" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id="i_password" name="i_password" class="form-control" placeholder="admin" value=""/>
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>
													<!-- error msg start -->
													<div id="error_div_info1" class="alert alert-danger" style="display: none;">
														<button type="button" class="close">
															<i class="icon-remove"></i>
														</button>
														<span id="error_span_info1"></span>
														<br />
													</div>
													<!-- error msg end -->

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住密码</span>
														</label>

														<button type="button" id="btnLogin" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="icon-key"></i>
															登 录
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										
									</div><!-- /widget-body -->
								</div><!-- /login-box -->
								
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

	</body>
	
	<script type="text/javascript">
		var flag = false;
	    $(document).ready(function() {
	    	 var userId=$("#userId").val();
	    	 if(userId!=''&& userId!=null && userId!="null"){
	    		 window.location.href = "${bodyPlus }/abody/desktop";
	         }
	    });

		jQuery(function($) {
			$("#btnLogin").on(ace.click_event, function() {
				if(!$("#i_username").val() || !$("#i_password").val()){
					$("#error_div_info1").css("display","block");
					$("#error_span_info1")[0].innerText = ("请输入用户名或密码.");
					return false;
				}
				var rdata = {i_username:$("#i_username").val(), i_password:$("#i_password").val()};
				$.post("${bodyPlus }/login",rdata,function(result){
					console.info(result);
					if(result.success) {
						window.location.href = "${bodyPlus }/abody/desktop";
					}else{
						$("#error_div_info1").css("display","block");
						$("#error_span_info1")[0].innerText = result.msg;
					}
				});
			});
		});
		
	</script>
</html>
