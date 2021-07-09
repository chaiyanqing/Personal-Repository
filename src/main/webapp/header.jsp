<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!-- <div id="navbar" class="navbar navbar-default navbar-collapse h-navbar ace-save-state"> -->
<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse ace-save-state" style="margin-top: 0px; background-color: black;">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand" style="color: white;">
					飞行员生命体征监测与搜救一体化信息处理系统
			</a>
		</div>

		<div class="navbar-buttons navbar-header pull-right  collapse navbar-collapse" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue dropdown-modal user-min">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<i class="ace-icon fa fa-cog bigger-130"></i>
					</a>

					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close" style="left: auto; padding-top: 0px !important;">
						<li>
							<a href='#profile-modal-table' data-backdrop="static" data-keyboard="false" data-toggle="modal">
								<i class="icon-user"></i>
								个人信息
							</a>
						</li>
						<li>
							<a onclick="toModifyPassword()">
								<i class="icon-key"></i>
								修改密码
							</a>
						</li>
						<li class="divider"></li>
	
						<li>
							<a href="${bodyPlus }/logout.htm">
								<i class="icon-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>

		<nav role="navigation" class="navbar-menu pull-left collapse navbar-collapse">
			<ul class="new-nav navbar-nav">
				<li class="" id="menu_1">
					<a href="${bodyPlus }/abody/desktop.html" style="color: white;"><i class="menu-icon fa fa-tachometer"></i><span class="menu-text"> 首页 </span></a>
				</li>
				<li class="" id="menu_2">
					<a href="${bodyPlus }/abody/train.html" style="color: white;"><i class="menu-icon fa fa-calendar"></i><span class="menu-text">训练管理</span></a>
				</li>
				<%-- <li class="" id="menu_2">
					<a href="${bodyPlus }/abody/monitor.html" style="color: white;"><i class="menu-icon fa fa-calendar"></i><span class="menu-text">训练监测</span></a>
				</li> --%>
				<li class="" id="menu_3">
					<a href="${bodyPlus }/abody/user.html" style="color: white;"><i class="menu-icon fa fa-desktop"></i><span class="menu-text">成员管理</span></a>
				</li>
				<li class="" id="menu_4">
					<a href="${bodyPlus }/abody/equipment.html" style="color: white;"><i class="menu-icon fa fa-list"></i><span class="menu-text">硬件管理</span></a>
				</li>				
				<li class="" id="menu_5">
					<a href="${bodyPlus }/abody/dataview.html" style="color: white;"><i class="menu-icon fa fa-pencil-square-o"></i><span class="menu-text">数据统计</span></a>
				</li>
			</ul>
		</nav>
</div>
<!-- 个人信息 -->
<div id="profile-modal-table" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-size">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
					</button>
					个人信息
				</div>
			</div>
			
			<div class="">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name"> 姓名 </div>
									<div class="profile-info-value">
										<span>${user.name }</span>
									</div>
								</div>
								
								<div class="profile-info-row">
									<div class="profile-info-name"> 账号 </div>
									<div class="profile-info-value">
										<input type="hidden" id="account" value="${user.account }" />
										<span>${user.account }</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> 电话 </div>
									<div class="profile-info-value">
										<span>${user.phone }</span>
									</div>
								</div>
							</div>
						</div>
					</div><!-- /.row -->
				</div><!-- /.page-content -->	
			</div><!-- /.main-content -->
			
			<div class="modal-footer no-margin-top">
				<button class="btn btn-sm btn-danger center" data-dismiss="modal"><i class="icon-remove"></i>关闭</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- PAGE CONTENT ENDS -->
<!-- 修改密码 -->
<div id="password-modal-table" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-size">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
					</button>
					修改密码
				</div>
			</div>
			
			<div class="">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							<form class="form-horizontal" id="validation-form-header">
								<input type="hidden" name="user_account" id="user_account" value="${user.account }" />
								<div class="space-4"></div>
								<div class="form-group">
									<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="user_oldPassword"> 原密码: <span style="color: red">*</span></label>
									<div class="col-xs-12 col-sm-9">
										<div class="clearfix">
											<input type="password" id="user_oldPassword" name="user_oldPassword" class="col-xs-12 col-sm-6" />
										</div>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="user_newPassword"> 新密码: <span style="color: red">*</span></label>
									<div class="col-xs-12 col-sm-9">
										<div class="clearfix">
											<input type="password" name="user_newPassword" id="user_newPassword" class="col-xs-12 col-sm-6" />
										</div>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="user_confirmPassword"> 确认密码: <span style="color: red">*</span></label>
									<div class="col-xs-12 col-sm-9">
										<div class="clearfix">
											<input type="password" name="user_confirmPassword" id="user_confirmPassword" class="col-xs-12 col-sm-6" />
										</div>
									</div>
								</div>
							</form>
						</div>
					</div><!-- /.row -->
				</div><!-- /.page-content -->	
			</div><!-- /.main-content -->
			
			<div class="modal-footer no-margin-top center">
				<button class="btn btn-sm btn-primary"  onclick="toModifySave();"><i class="icon-save"></i> 保存 </button>
				<button class="btn btn-sm btn-cancel" data-dismiss="modal"><i class="icon-remove"></i> 取消 </button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- PAGE CONTENT ENDS -->

<!-- <button class="btn btn-success" id="gritter-without-image">Notice</button> -->

<script type="text/javascript">
	jQuery(function($) {
		// 页面刷新自动获取站内信息
		console.info($("#account").val())
		
		
	});
	
	var validate = $('#validation-form-header').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: false,
		rules: {
			user_oldPassword: {
				required: true
			},
			user_newPassword: {
				required: true,
				minlength: 6,
				maxlength: 18
			},
			user_confirmPassword: {
				required: true,
				minlength: 6,
				maxlength: 18
			}
		},

		messages: {
			user_oldPassword: {
				required: "请输入原密码."
			},
			user_newPassword: {
				required: "请输入新密码.",
				minlength: "密码长度不能低于6位.",
				maxlength: "密码长度不能超过18位."
			},
			user_confirmPassword: {
				required: "请输入确认密码.",
				minlength: "密码长度不能低于6位.",
				maxlength: "密码长度不能超过18位."
			}
		},

		invalidHandler: function (event, validator) { //display error alert on form submit   
			$('.alert-danger', $('.login-form')).show();
		},

		highlight: function (e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},

		success: function (e) {
			$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
			$(e).remove();
		},

		errorPlacement: function (error, element) {
			if(element.is(':checkbox') || element.is(':radio')) {
				var controls = element.closest('div[class*="col-"]');
				if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
				else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
			}
			else if(element.is('.select2')) {
				error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
			}
			else if(element.is('.chosen-select')) {
				error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
			}
			else error.insertAfter(element.parent());
		},

		submitHandler: function (form) {
		},
		invalidHandler: function (form) {
		}
	});

	function toModifyPassword(){
		$('#validation-form-header')[0].reset() 
		validate.resetForm();
		$("#password-modal-table").modal("show");
	}

	//  保存更新密码
	function toModifySave(){
		if(!$('#validation-form-header').valid()){
			return false;
		} 
		else {
			var obj = $('#validation-form-header').serializeObject();
			console.info(obj); 
			if(obj.user_oldPassword == obj.user_newPassword){
				bootbox.alert("新密码与原密码不能相同。");
				return false;
			}
			if(obj.user_newPassword != obj.user_confirmPassword){
				bootbox.alert("两次输入密码不一致。");
				return false;
			}
			$post("${bodyPlus }/system/updatePassword", obj, function(res){
				if(res.success) {
					bootbox.alert("密码修改成功。", function(){
						$("#password-modal-table").modal('hide');
					});
				} else {
					bootbox.alert(res.msg);
				}
			});
		}
	}
</script>