<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>硬件管理</title>
</head>
<%@include file="main.jsp" %>
<script src="${bodyPlus }/assets/js/ajaxfileupload.js"></script>
<body>
	<%@include file="header.jsp" %>
	<script type="text/javascript">
		try{ace.settings.loadState('main-container')}catch(e){}
	</script>
	<%-- 硬件编组列表 --%>
	<div class="main-content" id="main-content-group">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="editGroupBtn"> 编 辑 </button>
				<button style="float: left; font-size: 14px; display: none;" id="cancelGroupBtn"> 取 消 </button>
				<span class="white" id="groupTitle">硬 件 编 组 列 表</span>
				<button style="float: right; font-size: 14px;" id="addGroupBtn" href="#groupadd-modal-table" data-backdrop="static" data-keyboard="false" data-toggle="modal"> 添 加 </button>
				<button style="float: right; font-size: 14px; display: none; padding: 0 10px;" id="deleteGroupBtn" class="btn btn-sm btn-danger"> 删 除 </button>
			</div>
			<div class="page-content">
				<div class="row">
					<div class="table-responsive">
						<table id="group-table-foot" class="table table-striped1 table-bordered table-hover1" style="color: white">
							<thead>
								<tr>
									<th>序号</th>
									<th>编组名称</th>
									<th>设备数量</th>
									<th>修改</th>
								</tr>
							</thead>
						</table>
					</div><!-- /.table-responsive -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.硬件编组列表 -->
	
	<%-- 成员列表 --%>
	<div class="main-content" id="main-content-member" style="display: none;">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="returnBtn"> 返 回 </button>
				<button style="float: left; font-size: 14px;" id="editMemberBtn"> 编 辑 </button>
				<button style="float: left; font-size: 14px; display: none;" id="cancelMemberBtn"> 取 消 </button>
				<span class="white" id="memberTitle">添加/修改</span>
				<button style="float: right; font-size: 14px; display: none; padding: 0 10px;" id="deleteMemberBtn" class="btn btn-sm btn-danger"> 删 除 </button>
				<a style="float: right; font-size: 14px; padding: 0 6px; border-radius: 3px;" class="btn btn-sm" id="dowloadFile" href="${bodyPlus}/files/hardware_20210401.xlsx" download ="硬件编组批量导入模板" title="点击下载模板">下 载 </a>
				<button style="float: right; font-size: 14px;" id="importProductSimple" onclick="javascript:importMemberBtn.click()">
					<input id="importMemberBtn" enctype="multipart/form-data" class="picRelationUpload" type="file" name="file" onchange="execFileupload('importMemberBtn');" style="position: absolute;opacity: 0;width: 0px;height:0px;padding:0px;margin:0px;z-index: 1;margin-left: -10px;"/> 
					<i class="icon-upload align-top bigger-100"></i> 导 入
				</button>
				<button style="float: right; font-size: 14px;" id="addMemberBtn" href="#memberadd-modal-table" onclick="toAddMember();" data-backdrop="static" data-keyboard="false" data-toggle="modal"> 添 加 </button>
			</div>
			<div class="page-content">
				<div class="row">
					<div class="table-responsive">
						<table id="member-table-foot" class="table table-striped1 table-bordered table-hover1" style="color: white">
							<thead>
								<tr>
									<th>ID</th>
									<th>SN</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div><!-- /.table-responsive -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.成员列表 -->
	
	<!-- 群组添加 start -->
	<div id="groupadd-modal-table" class="modal fade" tabindex="-1">
		<div class="modal-dialog modal-size" style="width: 400px;">
			<div class="modal-content" style="width: 400px; border: 3px solid white; margin-top: 120px;">
				<div class="modal-header no-padding">
					<div id="g_title" class="table-header" style="text-align: center;">添 加 群 组</div>
				</div>				
				<div class="">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12 col-sm-12">
								<div class="profile-group-info">
									<form class="form-horizontal" id="validation-form">
										<input type="hidden" name="uid" id="uid" />
										<div class="space-4"></div>
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="uaccount"> 编组名称: <span style="color: red">*</span></label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="hidden" id="groupId" name="groupId" value="" class="col-xs-12 col-sm-12" />
													<input type="text" id="groupName" name="groupName" value="" class="col-xs-12 col-sm-12" />
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div><!-- /.row -->
					</div><!-- /.page-content -->	
				</div><!-- /.main-content -->
				
				<div class="modal-footer no-margin-top center">
					<button class="btn btn-sm btn-cancel" onclick="toCancelSaveGroup();" data-dismiss="modal"><i class="icon-remove"></i> 取 消 </button>
					<button class="btn btn-sm btn-primary"  onclick="toSaveGroup();"><i class="icon-save"></i> 确 认 </button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- PAGE CONTENT ENDS -->
	<!-- 群组添加 end -->
	
	
	<!-- 硬件添加 start -->
	<div id="memberadd-modal-table" class="modal fade" tabindex="-1">
		<div class="modal-dialog modal-size" style="width: 400px;">
			<div class="modal-content" style="width: 400px; border: 3px solid white; margin-top: 120px;">
				<div class="modal-header no-padding">
					<div id="m_title" class="table-header" style="text-align: center;">添加新硬件</div>
				</div>				
				<div class="">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12 col-sm-12">
								<div class="profile-group-info">
									<form class="form-horizontal" id="validation-form-addmember">
										<input type="hidden" name="uid" id="uid" />
										<div class="space-4"></div>
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="uaccount"> 序号/SN: <span style="color: red">*</span></label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="hidden" id="memId" name="memId" value="" />
													<input type="hidden" id="memGroup" name="memGroup" value="" />
													<input type="text" id="memSn" name="memSn" value="" class="col-xs-12 col-sm-12" />
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div><!-- /.row -->
					</div><!-- /.page-content -->	
				</div><!-- /.main-content -->
				
				<div class="modal-footer no-margin-top center">
					<button class="btn btn-sm btn-cancel" data-dismiss="modal"><i class="icon-remove"></i> 取 消 </button>
					<button class="btn btn-sm btn-primary" id="saveAddMemberBtn"><i class="icon-save"></i> 确 认 </button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- PAGE CONTENT ENDS -->
	<!-- 硬件添加 end -->
	
	<script type="text/javascript">
		jQuery("#menu_1").addClass('active');
	</script>
	
	<script type="text/javascript">

	 	/**************群组管理**************/
		//控制分页
		var oTable1Column = [{ "mData": null,
								mRender : function(data, type, row, meta){
									return '<span name="group_idspan" class="group_idspan" style="display: block;">'+row.id+'</span>'
									+'<input type="checkbox" name="group_idinput" class="group_idinput" style="width: 18px; height: 18px; display: none;" value=\''+row.id+'\' onclick="window.event.cancelBubble=true;" />';
					       		} 
							 },
							{ "mData": "name" },
							{ "mData": "counts" },
							{ "mData" : null,
						        mRender : function(data, type, row, meta){
					        		return '<a type="button" role="button" class="btn btn-minier btn-primary" href="#groupadd-modal-table" data-backdrop="static" data-keyboard="false" data-toggle="modal" onclick="toEditGroup(' + row.id + ', \'' + row.name + '\')" > 修改 </a>&nbsp;';
					       		}  
							}];
		var groupTable = $('#group-table-foot').dataTable({
			"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "抱歉， 没有找到",
				"sInfo": "", //"从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty": "",
				"sZeroRecords": "没有检索到数据",
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
				"sSearch":"搜索：",
				"oPaginate": {
					"sFirst": "首页",
					"sPrevious": "前一页",
					"sNext": "后一页",
					"sLast": "尾页"
				}
			},
            "paging": false, // 禁止分页
            "aoColumns": oTable1Column
        });

		var memberGroupListLoad = function() {
			$ajax("/hardware/groupSearch", "get", {},function(res){
				if(res.success) {
					groupTable.fnClearTable();
					if(res.data.length > 0){
    					groupTable.fnAddData(res.data);
					}
				} else {
					alert("查询失败，" + res.msg);
				}
			});
		}
		memberGroupListLoad();
        $("#editGroupBtn").click(function(item){
    		$("#editGroupBtn").css("display", "none");
    		$("#addGroupBtn").css("display", "none");
    		$("#cancelGroupBtn").css("display", "block");
    		$("#deleteGroupBtn").css("display", "block");
    		$(".group_idspan").css("display", "none");
    		$(".group_idinput").css("display", "block");
    	});

 		$("#cancelGroupBtn").click(function(item){
 			$("#editGroupBtn").css("display", "block");
    		$("#addGroupBtn").css("display", "block");
    		$("#cancelGroupBtn").css("display", "none");
    		$("#deleteGroupBtn").css("display", "none");
    		$(".group_idspan").css("display", "block");
    		$(".group_idinput").css("display", "none");
    	});
 		$("#deleteGroupBtn").click(function(item){
 			var obj = $('input:checkbox[name="group_idinput"]:checked');
			if(!obj || obj.length == 0){
				bootbox.alert("请选择记录.");
			}else{
				bootbox.confirm("确定删除选中的"+obj.length+"个编组？", function (result) {
					if(result){
						var selectedIds = [];
						$('input:checkbox[name="group_idinput"]:checked').each(function(){
				            selectedIds.push($(this).val());
			            });
						var ids = selectedIds.join(",");
						console.info(ids.toString())
						$post("${bodyPlus}/hardware/deleteGroup", {"groupIds" : ids.toString()}, function(res){
							bootbox.alert("操作完成：" + res.msg, function(){
								memberGroupListLoad();
								$("#cancelGroupBtn").hit();
							});
						});
					}
				});
			}
 		});
 		$("#addGroupBtn").click(function(item){
			$("#g_title").text("添加新编组");
 			$('#groupName').val("");
 			$('#groupId').val("");
    	});
    	var isCancel = false;
 		function toEditGroup(id, name){
 			isCancel = true;
			$("#g_title").text("修改编组名称");
			$("#groupId").val(id);
			$("#groupName").val(name);
		}
 		// 保存编组
		function toSaveGroup(){
			isCancel = false;
			var obj = $('#validation-form').serializeObject();
			console.info(obj);
			var groupId = $('#groupId').val();
			var groupName = $('#groupName').val();
			if(groupId == '' && groupName){ // 添加，判断名称是否存在
				$get("${bodyPlus}/hardware/groupSearch", {groupName: groupName}, function(res){
					if(res.data.length > 0){
						bootbox.alert("编组名称已存在。");
						$("#groupadd-modal-table").modal('hide');
					}else{
						$post("${bodyPlus}/hardware/addOrUpdateGroup", {groupId: groupId, groupName: groupName}, function(res){
							if(res.success) {
								bootbox.alert("操作完成：" + res.msg, function(){
									$("#groupadd-modal-table").modal('hide');
									memberGroupListLoad();
								});
							} else {
								bootbox.alert("操作完成：" + res.msg);
							}
						});
					}
				});
			} else if(groupName){ // 修改
				$post("${bodyPlus}/hardware/addOrUpdateGroup", {groupId: groupId, groupName: groupName}, function(res){
					if(res.success) {
						bootbox.alert("操作完成：" + res.msg, function(){
							$("#groupadd-modal-table").modal('hide');
							memberGroupListLoad();
						});
					} else {
						bootbox.alert("操作完成：" + res.msg);
					}
				});
			}
		}
		function toCancelSaveGroup(){
			isCancel = false;
		}


		/**************成员管理**************/
		//控制分页
		var oTable2Column = [{ "mData": null,
								mRender : function(data, type, row, meta){
									return '<span name="member_idspan" class="member_idspan" style="display: block;">'+row.id+'</span>'+
									'<input type="checkbox" name="member_idinput" class="member_idinput" style="width: 18px; height: 18px; display: none;" value=\''+row.id+'\' onclick="window.event.cancelBubble=true;" />';
					       		} 
							},
							{ "mData": "sn" },
							{ "mData" : null,
						        mRender : function(data, type, row, meta){
					        		return '<a type="button" role="button" class="btn btn-minier btn-primary" href="#memberadd-modal-table" data-backdrop="static" data-keyboard="false" data-toggle="modal" onclick="toEditMember(' + row.id + ', \'' + row.sn + '\', \'' + row.group + '\')" > 修改 </a>&nbsp;';
					       		}  
							}];
		var memberTable = $('#member-table-foot').dataTable({
			"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "抱歉， 没有找到",
				"sInfo": "", //"从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty": "",
				"sZeroRecords": "没有检索到数据",
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
				"sSearch":"搜索：",
				"oPaginate": {
					"sFirst": "首页",
					"sPrevious": "前一页",
					"sNext": "后一页",
					"sLast": "尾页"
				}
			},
            "paging": false, // 禁止分页
            "aoColumns": oTable2Column
        });
		var memberListLoad = function() {
			$ajax("/hardware/hardwareSearch", "get", {groupName: currentGroupName},function(res){
				console.info(res)
				if(res.success) {
					memberTable.fnClearTable();
					if(res.data.length > 0){
	    				memberTable.fnAddData(res.data);
					}	
				} else {
					alert("查询失败，" + res.msg);
				}
			});
		}
		// 添加行事件
		$('#group-table-foot tbody').on( 'mouseover', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "silver");
			$($(t)[0].currentTarget).css("cursor", "pointer");
		});
		$('#group-table-foot tbody').on( 'mouseout', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "gray");
		});
		var currentGroupName = "";
		$('#group-table-foot tbody').on( 'click', 'tr', function(t) {
			if(isCancel){
				return;
			}
			var groupid = $(t)[0].currentTarget.cells[0].children[0].innerText;
			var groupName = $(t)[0].currentTarget.cells[1].innerText;
			currentGroupName = groupName;
			$("#memberTitle").text(groupName);
    		$("#main-content-group").css("display", "none");
			$("#main-content-member").css("display", "block");
			$("#member-table-foot").css("width", "100%");
			// 查询加载成员列表
			memberListLoad();
		});
		$("#returnBtn").click(function(item){
    		$("#main-content-member").css("display", "none");
    		$("#main-content-group").css("display", "block");
    		$("#editMemberBtn").css("display", "block");
    		$("#cancelMemberBtn").css("display", "none");
    		$("#addMemberBtn").css("display", "block");
    		$("#importMemberBtn").css("display", "block");
    		$("#deleteMemberBtn").css("display", "none");
    	});
		$("#editMemberBtn").click(function(item){
    		$("#editMemberBtn").css("display", "none");
    		$("#cancelMemberBtn").css("display", "block");
    		$("#addMemberBtn").css("display", "none");
    		$("#importMemberBtn").css("display", "none");
    		$("#deleteMemberBtn").css("display", "block");
    		$(".member_idspan").css("display", "none");
    		$(".member_idinput").css("display", "block");
    	});
 		$("#cancelMemberBtn").click(function(item){
 			$("#editMemberBtn").css("display", "block");
    		$("#cancelMemberBtn").css("display", "none");
    		$("#addMemberBtn").css("display", "block");
    		$("#importMemberBtn").css("display", "block");
    		$("#deleteMemberBtn").css("display", "none");
    		$(".member_idspan").css("display", "block");
    		$(".member_idinput").css("display", "none");
    	});
 		$("#addMemberBtn").click(function(item){
			$('#validation-form-addmember')[0].reset();
			$("#membId").val("");
    	});
 		function execFileupload(fileElementId){
    		ajaxFileUpload('${bodyPlus}/import/excelImportHardware?groupName='+currentGroupName, fileElementId);
        }
 		$("#deleteMemberBtn").click(function(item){
 			var obj = $('input:checkbox[name="member_idinput"]:checked');
			if(!obj || obj.length == 0){
				bootbox.alert("请选择记录.");
			}else{
				bootbox.confirm("确认删除已选择的"+obj.length+"个硬件？", function (result) {
					if(result){
						var selectedIds = [];
						$('input:checkbox[name="member_idinput"]:checked').each(function(){
				            selectedIds.push($(this).val());
			            });
						var ids = selectedIds.join(",");
						console.info(ids.toString())
						$post("${bodyPlus}/hardware/deleteHardware", {"memberIds" : ids.toString()}, function(res){
							bootbox.alert("操作完成：" + res.msg, function(){
								memberListLoad();
							});
						});
					}
				});
			}
 		});
 		function toAddMember(){
 			$("#m_title").text("添加新硬件");
 			$("#memGroup").val(currentGroupName);
 	 	}
 		function toEditMember(mid, msn, mgroup){
 	 		$("#m_title").text("编辑硬件SN");
 	 		$("#memId").val(mid);
 	 		$("#memSn").val(msn);
 	 		$("#memGroup").val(mgroup);
 	 	}
		$("#saveAddMemberBtn").click(function(item){
			var memId = $('#memId').val();
			var memSn = $('#memSn').val();
			if(memSn && memSn != ""){
				var obj = $('#validation-form-addmember').serializeObject(); 
				console.info(obj);
				$post("${bodyPlus}/hardware/addOrUpdateHardware", obj, function(res){
					if(res.success) {
						bootbox.alert("操作完成：" + res.msg, function(){
							memberListLoad();
							$("#memberadd-modal-table").modal('hide');
						});
					} else {
						bootbox.alert("操作完成：" + res.msg);
					}
					// 修改完成，重新验证复选框是否显示
					if(memId != ""){
						console.info($("#editMemberBtn").css("display") == "none")
						console.info(memId)
						if($("#editMemberBtn").css("display") == "none"){
				    		$(".member_idspan").css("display", "none");
				    		$(".member_idinput").css("display", "block");
						}else{
				    		$(".member_idspan").css("display", "block");
				    		$(".member_idinput").css("display", "none");
						}
					}
				});
			}else{
				bootbox.alert("SN不能为空。");
				return;
			}
    	});
    </script>
</body>
</html>

