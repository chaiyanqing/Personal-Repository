<%@page import="com.zs.entity.User"%>
<%@page import="com.zs.util.JdbcUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>训练管理</title>
</head>
<%@include file="main.jsp" %>
<body>
	<%@include file="header.jsp" %>
	<script type="text/javascript">
		try{ace.settings.loadState('main-container')}catch(e){}
	</script>
	<%-- <%@include file="menu.jsp" %> --%>
	<div class="main-content" id="main-content-task">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="refreshBtn"> 刷 新 </button>
				<span class="white">训 练 管 理</span>
				<button style="float: right; font-size: 14px;" href="#tranadd-modal-table" id="addBtn" data-backdrop="static" data-keyboard="false" data-toggle="modal"> 添 加 </button>
			</div>
			<div class="page-content">
				<!-- 训练 -->
				<div class="row">
					<div class="table-responsive">
						<table id="train-table-foot" class="table table-striped1 table-bordered table-hover1" style="color: white">
							<thead>
								<tr>
									<th>序号</th>
									<th>训练名称</th>
									<th>地点</th>
									<th>团队</th>
									<th>训练模式</th>
									<th>状态</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>训练时长(分钟)</th>
									<th>成员数量</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div><!-- /.table-responsive -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.main-content -->
	
	<%-- 训练成员列表 --%>
	<div class="main-content" id="main-content-member" style="display: none;">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px; margin: 0 4px;" id="returnBtn"> 返 回 </button>
				<button style="float: left; font-size: 14px; margin: 0 4px;" id="memberRefreshBtn"> 刷 新 </button>
				<span class="white" id="trainTitle"></span>
				<button style="float: right; font-size: 14px; display: none;" id="startTrainBtn"> 开 始 </button>
				<button style="float: right; font-size: 14px; display: none;" id="endTrainBtn"> 结 束 </button>
				<span style="float: right; font-size: 14px; padding: 3px 6px 0 10px; color: white; border-radius: 3px; margin-right: 4px;" id="trainStatus"></span>
				<a style="float: right; font-size: 14px; display: none; padding: 3px 6px 0 10px; color: white; text-decoration: underline; cursor: pointer;" id="trainMonitorBtn"> 实时监测 </a>
			</div>
			<div class="page-content">
				<div class="row">
					<div class="table-responsive">
						<table id="member-table-foot" class="table table-striped1 table-bordered table-hover1" style="color: white;">
							<thead>
								<tr>
									<th>ID</th>
									<th>成员名称</th>
									<th>SN</th>
									<th>最大心率</th>
									<th>受伤风险</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div><!-- /.table-responsive -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.训练成员列表 -->
	
	<!-- 创建训练 start -->
	<div id="tranadd-modal-table" class="modal fade" tabindex="-1">
		<div class="modal-dialog modal-size" style="width: 800px;">
			<div class="modal-content" style="width: 800px; border: 3px solid white; margin-top: 120px;">
				<div class="modal-header no-padding">
					<div id="g_title" class="table-header" style="text-align: center;">创 建 训 练</div>
				</div>				
				<div class="">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12 col-sm-12">
								<div class="profile-group-info">
									<form class="form-horizontal" id="validation-form">
										<div class="form-group">
											<div class="space-4"></div>
											<div class="row">
												<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="traName"> 训练名称: <span style="color: red">*</span></label>
												<div class="col-xs-12 col-sm-4">
													<input type="text" id="traName" name="traName" value="" class="col-xs-12 col-sm-12" />
												</div>
												
												<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="traAddress"> 训练地点: <span style="color: red">*</span></label>
												<div class="col-xs-12 col-sm-4">
													<input type="text" id="traAddress" name="traAddress" value="" class="col-xs-12 col-sm-12" />
												</div>
											</div>
										
											<div class="space-4"></div>
											<div class="row">
												<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="traTeam"> 训练团队: <span style="color: red">*</span></label>
												<div class="col-xs-12 col-sm-4">
													<select id="userGroupSelectBtn" name="traTeam" class="myselect" style="float: left; width: 645px;">
														<c:forEach var="item" items="${groups}">
															<option value="${item.name }">${item.name }</option>
														</c:forEach>
													</select>
												</div>
											</div>
											
											<div class="space-4"></div>
											<div class="row">
												<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="traModel"> 训练模式: <span style="color: red">*</span></label>
												<div class="col-xs-12 col-sm-4">
													<input type="radio" name="traModel" value="1" style="width: 20px;height: 20px;" />在线训练
													<input type="radio" name="traModel" value="2" style="width: 20px;height: 20px;" />离线训练
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
					<button class="btn btn-sm btn-primary"  onclick="toSave();"><i class="icon-save"></i> 确 认 </button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- PAGE CONTENT ENDS -->
	<!-- 创建训练 end -->
	
	<script type="text/javascript">
		jQuery("#menu_2").addClass('active');
	</script>
	
	<script type="text/javascript">
     	//控制分页
		var oTable1Column = [{ "mData": "id",},
							{ "mData": "name" },
							{ "mData": "address" },
							{ "mData": "team" },
							{ "mData": "model", mRender: function(data, type, row, meta){
								return row.model == 1 ? "在线训练" : "离线训练";  // 1在线 2离线
							}},
							{ "mData": null, mRender: function(data, type, row, meta){
								if(row.status1 == "已结束"){
									return '<span style="color: skyblue;">'+row.status1+'</span>';
								}else if(row.status1 == "进行中"){
									return '<span style="color: red;">'+row.status1+'</span>';
								}else{
									return row.status1;
								}
							}},
							{ "mData": "start_time" },
							{ "mData": "end_time" },
							{ "mData": "timeDiff" },
							{ "mData": "memCount" },
							{ "mData" : null,
								mRender : function(data, type, row, meta){
					        		return "训练详情";
					       		}  
				       		}];
		var trainTable = $('#train-table-foot').dataTable({
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

		var trainDataReload = function() {
			$ajax("/train/search", "get", null,function(res){
				if(res.success) {
					trainTable.fnClearTable();
					if(res.data.length > 0){
						trainTable.fnAddData(res.data);
					}
				} else {
					alert("查询失败，" + res.msg);
				}
			});
		}
		trainDataReload();

		$("#refreshBtn").click(function(item){
			trainDataReload();
		});
		
		// 训练成员管理
		var currentTranId = "";
		var currentTranName = "";
		/**************成员管理**************/
		//控制分页
		var oTable2Column = [{ "mData": "id"},
							{ "mData": "name" },
							{ "mData": "sn" },
							{ "mData" : "heartrate"},
							{ "mData" : null, mRender: function(data, type, row, meta){
								return '受伤风险-';
							}},
							{ "mData" : null, mRender: function(){
								return '-';
							}}
							];

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
		var tranMemberLoad = function() {
			$ajax("/train/trainMember", "get", {trainId: currentTranId},function(res){
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
		$("#memberRefreshBtn").click(function(item){
			tranMemberLoad();
		});
		// 添加行事件
		$('#train-table-foot tbody').on( 'mouseover', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "silver");
			$($(t)[0].currentTarget).css("cursor", "pointer");
		});
		$('#train-table-foot tbody').on( 'mouseout', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "gray");
		});
		$('#train-table-foot tbody').on( 'click', 'tr', function(t) {
			var trainId = $(t)[0].currentTarget.cells[0].innerText;
			var trainName = $(t)[0].currentTarget.cells[1].innerText;
			currentTranId = trainId;
			currentTranName = trainName;
			var trainStatus = $(t)[0].currentTarget.cells[5].innerText;
			$("#trainTitle").text("训练任务："+currentTranName);
			$("#trainStatus").text("训练"+trainStatus);
			if(trainStatus == "进行中"){
				$("#startTrainBtn").css("display", "none");
				$("#endTrainBtn").css("display", "block");
				$("#trainMonitorBtn").css("display", "block");
			}else if(trainStatus == "未开始"){
				$("#startTrainBtn").css("display", "block");
				$("#endTrainBtn").css("display", "none");
				$("#trainMonitorBtn").css("display", "none");
			}else if(trainStatus == "已结束"){
				$("#startTrainBtn").css("display", "none");
				$("#endTrainBtn").css("display", "none");
				$("#trainMonitorBtn").css("display", "block");
			}else{
				$("#startTrainBtn").css("display", "none");
				$("#endTrainBtn").css("display", "none");
				$("#trainMonitorBtn").css("display", "block");
			}
    		$("#main-content-task").css("display", "none");
			$("#main-content-member").css("display", "block");
			$("#member-table-foot").css("width", "100%");
			// 查询加载成员列表
			tranMemberLoad();
		});
		
		// 保存训练
		function toSave(){
			var obj = $('#validation-form').serializeObject();
			$post("${bodyPlus}/train/addTrain", obj, function(res){
				if(res.success) {
					bootbox.alert("添加完成：" + res.msg, function(){
						trainDataReload();
						$("#tranadd-modal-table").modal('hide');
					});
				} else {
					bootbox.alert("添加完成：" + res.msg);
				}
			});
		}

		$("#returnBtn").click(function(item){
    		$("#main-content-member").css("display", "none");
    		$("#main-content-task").css("display", "block");
    	});	

		$("#trainMonitorBtn").click(function(item){
			window.location.href="${bodyPlus}/train/monitor?trainId=" + currentTranId + "&trainName=" + currentTranName;
		});
		
		$("#startTrainBtn").click(function(item){
			bootbox.confirm("训练是否开始？", function(res){
				if(res){
					$post("${bodyPlus}/train/startTrain", {trainId: currentTranId}, function(res){
						if(res.success) {
							bootbox.alert("训练开始：" + res.msg, function(){
								trainDataReload();
								$("#main-content-member").css("display", "none");
					    		$("#main-content-task").css("display", "block");
							});
						} else {
							bootbox.alert("训练开始：" + res.msg);
						}
					});
				}
			});
    	});

		$("#endTrainBtn").click(function(item){
			bootbox.confirm("训练是否已结束？", function(res){
				if(res){
					$post("${bodyPlus}/train/endTrain", {trainId: currentTranId}, function(res){
						if(res.success) {
							bootbox.alert("训练结束：" + res.msg, function(){
								trainDataReload();
								$("#main-content-member").css("display", "none");
					    		$("#main-content-task").css("display", "block");
							});
						} else {
							bootbox.alert("训练结束：" + res.msg);
						}
					});
				}
			});
    	});
		
    </script>
</body>
</html>

