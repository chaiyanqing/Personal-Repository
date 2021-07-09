<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>数据统计</title>
	<style type="text/css">
		.myrow{
			border-top: 2px solid black;
			padding: 4px;
		}
		.myrow font{
			display: inline-block;
			padding: 10px 20px;
		}
		.mylia{
			text-align: center; 
			padding: 5px !important; 
			font-size: 16px !important;
		}
	</style>
</head>
<%@include file="main.jsp" %>
<script src="${bodyPlus }/assets/js/ajaxfileupload.js"></script>
<body>
	<%@include file="header.jsp" %>
	<script type="text/javascript">
		try{ace.settings.loadState('main-container')}catch(e){}
	</script>
	<%-- 列表 --%>
	<div class="main-content" id="main-content-list">
		<div class="main-content-inner">
			<div class="tabbable">
				<ul class="nav nav-tabs" id="myTab" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black; padding-left: 40%;">
					<li style="width: 100px;" class="active"><a data-toggle="tab" href="#groupTab" class="mylia">群组</a></li>
					<li style="width: 100px;"><a data-toggle="tab" href="#memberTab" class="mylia">成员</a></li>
				</ul>
				<div class="page-content tab-content" style="border: 0;">
						<div class="tab-pane fade in active" id="groupTab">
							<table id="group-table-foot" class="table table-striped1 table-bordered table-hover1" style="color: white">
								<thead>
									<tr>
										<th>序号</th>
										<th>群组名称</th>
										<th>成员数量</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="tab-pane fade" id="memberTab">
							<table id="member-table-foot" class="table table-striped1 table-bordered table-hover1" style="color: white">
								<thead>
									<tr>
										<th>ID</th>
										<th>名称</th>
										<th>性别</th>
										<th>SN</th>
									</tr>
								</thead>
							</table>
						</div>
				</div><!-- /.page-content -->		
			</div>
		</div><!-- /.main-container-inner -->
	</div><!-- /.列表 -->
	
	<%-- 群组统计列表 --%>
	<div class="main-content" id="main-content-statlist" style="display: none">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="returnListBtn"> 返 回 </button>
				<button style="float: left; font-size: 14px;" id="exportBtn"> 导 出 </button>
				<select id="circleSelectBtn" class="myselect" style="float: left;">
					<option value="W">小周期</option>
					<option value="M">中周期</option>
					<option value="Y">大周期</option>
				</select>
				<span class="white" id="groupTitle">群组名称</span>
				<select id="labelSelectBtn" class="myselect" style="float: right;">
					<option value="">全部数据</option>
					<c:forEach var="item" items="${labels}">
						<option value="${item.name }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="page-content">
				<!-- 头部周期部分 -->
				<div class="row myrow center" style="color: white; font-size: 18px;">
					<span style="float: left; font-size: 24px; display: inline-block; width: 40px" id="groupViewLeft">
						<a style="color: white; cursor: pointer; text-decoration: none;">&lt</a></span>
					<span id="groupViewDate">2021年1月1日—2021年2月2日</span>
					<span style="float: right; font-size: 24px; display: inline-block; width: 40px" id="groupViewRight">
						<a style="color: white; cursor: pointer; text-decoration: none;">&gt</a>
					</span>
				</div><!-- /.row -->
				<!-- 群组统计部分 -->
				<div class="row myrow">
					<div class="table-responsive">
						<table id="groupstat-table-foot" class="table table-striped1 table-bordered table-hover1" style="color: white">
							<thead>
								<tr>
									<th>序号</th>
									<th>名称</th>
									<th>训练次数</th>
									<th>训练时长（H）</th>
									<th>受伤风险</th>
									<th>训练负荷</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
								    <th>团队平均</th>
									<th>-</th>
									<th>-</th>
									<th>-</th>
									<th>-</th>
									<th>-</th>
								</tr>
							 </tfoot>
						</table>
					</div><!-- /.table-responsive -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.群组统计列表 -->
	
	<%-- 个人训练视图 --%>
	<div class="main-content" id="main-content-memberview" style="display: block">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="returnStatBtn"> 返 回 </button>
				<span class="white" id="memberTitle">成员名称</span>
				<select id="circleSelectBtnMem" class="myselect" style="float: right;">
					<option value="W">小周期</option>
					<option value="M">中周期</option>
					<option value="Y">大周期</option>
				</select>
			</div>
			<div class="page-content">
				<!-- 头部周期部分 -->
				<div class="row myrow center" style="color: white; font-size: 18px;">
					<span style="float: left; font-size: 24px; display: inline-block; width: 40px" id="memberViewLeft">
						<a style="color: white; cursor: pointer; text-decoration: none;">&lt</a></span>
					<span id="memberViewDate">2021年1月1日—2021年2月2日</span>
					<span style="float: right; font-size: 24px; display: inline-block; width: 40px" id="memberViewRight">
						<a style="color: white; cursor: pointer; text-decoration: none;">&gt</a>
					</span>
				</div><!-- /.row -->
				<!-- 中部汇总部分 -->
				<div class="row myrow center" style="color: white; font-size: 18px;">
					<font>训练次数：<span id="memTranCounts" style="display: inline-block; min-width: 50px; text-align: left;">0</span></font>
					<font>总时长：<span id="memTranTimes" style="display: inline-block; min-width: 50px; text-align: left;">0.0</span>小时</font>
					<font>卡路里：<span id="memTranCalories" style="display: inline-block; min-width: 50px; text-align: left;">0.00</span>kCal</font>
					<font>训练负荷：<span id="memTranLoads" style="display: inline-block; min-width: 50px; text-align: left;">0</span></font>
					<font>受伤风险：<span id="memInjuryRisk" style="display: inline-block; min-width: 50px; text-align: left;">--</span></font>
				</div><!-- /.row -->
				<!-- 下部图形部分 -->
				<div class="row myrow">
					<div class="col-xs-12 col-sm-6 center">
						<div id="memberCharts1" style="height: 300px; border: 1px solid silver;"></div>						
					</div>
					<div class="col-xs-12 col-sm-6 center">
						<div id="memberCharts2" style="height: 300px; border: 1px solid silver;"></div>
					</div>
				</div><!-- /.row -->
				<div class="row myrow" style="border-bottom: 2px solid black;">
					<div class="col-xs-12 col-sm-6 center">
						<div id="memberCharts3" style="height: 300px; border: 1px solid silver;"></div>
					</div>
					<div class="col-xs-12 col-sm-6 center">
						<div id="memberCharts4" style="height: 300px; border: 1px solid silver;"></div>
					</div>
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.个人训练视图 -->
	
	<script type="text/javascript">
		jQuery("#menu_1").addClass('active');
	</script>
	
	<script type="text/javascript">
		var currentGroupName = "";
		var currentMemberId = "";
		var offsetvalue = 0;
		var intVal = function (i) {
            return typeof i === 'string' ? i.replace(/[\$,]/g, '') * 1 : typeof i === 'number' ? i : 0;
        };
        
	 	/**************列表查询**************/
		//群组列表
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
			$ajax("/member/groupSearch", "get", {},function(res){
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
		// 成员列表
		var oTable2Column = [{ "mData": null,
								mRender : function(data, type, row, meta){
									return '<span name="member_idspan" class="member_idspan" style="display: block;">'+row.id+'</span>'+
									'<input type="checkbox" name="member_idinput" class="member_idinput" style="width: 18px; height: 18px; display: none;" value=\''+row.id+'\' onclick="window.event.cancelBubble=true;" />';
					       		} 
							},
							{ "mData": "name" },
							{ "mData": "sex" },
							{ "mData" : null,
						        mRender : function(data, type, row, meta){
					        		return row.sn;
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
			$("#member-table-foot").css("width", "100%");
			$ajax("/member/memberSearch", "get", {groupName: currentGroupName},function(res){
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
		memberListLoad();
		// 群组训练统计
		var oTable3Column = [{ "mData": "id"},
							{ "mData": "name"},
							{ "mData": "counts"},
							{ "mData": "times"},
							{ "mData": "injury"},
							{ "mData": "loads"}
						];
		var groupStatTable = $('#groupstat-table-foot').dataTable({
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
							"aoColumns": oTable3Column,
							"footerCallback": function (row, data, start, end, display ) {
				           		var api = this.api();
				           		var totalMembers = api.column(1).data().count();
				           		var totalCounts = api.column(2).data().reduce(function (a, b) {return intVal(a) + intVal(b);}, 0);
				           		var totalTimes = api.column(3).data().reduce(function (a, b) {return intVal(a) + intVal(b);}, 0);
				           		var totalLoads = api.column(5).data().reduce(function (a, b) {return intVal(a) + intVal(b);}, 0);
				           		
				            	$(api.column(1).footer()).html(totalMembers);
				                $(api.column(2).footer()).html(totalCounts / totalMembers); 
				                $(api.column(3).footer()).html(totalTimes / totalMembers); 
				                $(api.column(5).footer()).html(totalLoads / totalMembers); 
							}
						});
		var groupStatListLoad = function() {
			// 获取参数
			var label = $("#labelSelectBtn").val(); // 标签
			var cycle = $("#circleSelectBtn").val(); // 周期
			var cycleDate = $("#groupViewDate").text(); // 时间周期
			$("#groupstat-table-foot").css("width", "100%");
			$ajax("/member/groupStatSearch", "get", 
				{
					groupName: currentGroupName,
					label: label,
					cycle: cycle,
					cycleDate: cycleDate
				},function(res){
					console.info(res)
					if(res.success) {
						groupStatTable.fnClearTable();
						if(res.data.memberData.length > 0){
							groupStatTable.fnAddData(res.data.memberData);
						}	
					} else {
						alert("查询失败，" + res.msg);
					}
				}
			);
		}	
		/***列表数据加载完成***/
		
		/****图形初始化****/
		var memberCharts1 = echarts.init(document.getElementById('memberCharts1')); 
		var memberCharts2 = echarts.init(document.getElementById('memberCharts2')); 
		var memberCharts3 = echarts.init(document.getElementById('memberCharts3')); 
		var memberCharts4 = echarts.init(document.getElementById('memberCharts4')); 
        
        var option1 = {
            tooltip: {show: false},
            legend: {data:['训练负荷分布']},
            xAxis : [{type : 'category', data : [1, 2, 3, 4, 5]}],
            yAxis : [{type : 'value'}],
            series : [{name:"", type:"bar", data:[0.5, 1.5, 2.5, 3.0, 2.0]}]
        };
        var option2 = {
            tooltip: {show: false},
            legend: {data:['']},
            xAxis : [{type : 'category', data : ["一", "二", "三", "四", "五", "六", "日"]}],
            yAxis : [{type : 'value'}],
            series : [{name:"", type:"bar", data:['0.0', '1.0', '2.0', '3.0', '4.0', '5.0']}]
        };
        memberCharts1.setOption(option1); 
        option2.legend.data[0] = "训练负荷";
        memberCharts2.setOption(option2); 
        option2.legend.data[0] = "卡路里";
        memberCharts3.setOption(option2); 
        option2.legend.data[0] = "训练强度";
        memberCharts4.setOption(option2); 
        $("#main-content-memberview").css("display", "none");
		/****图形初始化****/
		var memberViewStatLoad = function() {
			var cycle = $("#circleSelectBtnMem").val(); // 周期
			var cycleDate = $("#memberViewDate").text(); // 时间周期
			$ajax("/member/memberViewSearch", "get", 
				{
					memberId: currentMemberId,
					cycle: cycle,
					cycleDate: cycleDate
				},function(res){
					console.info(res)
					if(res.success) {
						// 重新渲染数据
						$("#memTranCounts").text(res.data.totalData.counts);
						$("#memTranTimes").text(res.data.totalData.times);
						$("#memTranCalories").text(res.data.totalData.calories);
						$("#memTranLoads").text(res.data.totalData.loads);
						$("#memInjuryRisk").text("-");
						// 表格数据
						option2.legend.data[0] = "训练负荷";
						option2.xAxis[0].data = res.data.xAxis;
						option2.series[0].data = res.data.loadData;
						memberCharts2.setOption(option2, true); 
						option2.legend.data[0] = "卡路里";
						option2.xAxis[0].data = res.data.xAxis;
						option2.series[0].data = res.data.calorieData;
						memberCharts3.setOption(option2, true); 
						option2.legend.data[0] = "训练强度";
						option2.xAxis[0].data = res.data.xAxis;
						option2.series[0].data = res.data.intensityData;
						memberCharts4.setOption(option2, true); 
					} else {
						alert("查询失败，" + res.msg);
					}
				}
			);
		}
		
       	/*** 群组行事件，点击显示群组视图 ***/
		var returnToPage = "groupstat";
		$('#group-table-foot tbody').on( 'mouseover', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "silver");
			$($(t)[0].currentTarget).css("cursor", "pointer");
		});
		$('#group-table-foot tbody').on( 'mouseout', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "gray");
		});
		$('#group-table-foot tbody').on( 'click', 'tr', function(t) {	
			var groupid = $(t)[0].currentTarget.cells[0].children[0].innerText;
			var groupName = $(t)[0].currentTarget.cells[1].innerText;
			currentGroupName = groupName;
			$("#groupTitle").text(groupName);
    		$("#main-content-list").css("display", "none");
			$("#main-content-statlist").css("display", "block");
			$("#groupstat-table-foot").css("width", "100%");
			// 查询组统计数据
			groupStatListLoad();
		});
		// 群组统计行事件，点击显示成员视图
		$('#groupstat-table-foot tbody').on( 'mouseover', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "silver");
			$($(t)[0].currentTarget).css("cursor", "pointer");
		});
		$('#groupstat-table-foot tbody').on( 'mouseout', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "gray");
		});
		$('#groupstat-table-foot tbody').on( 'click', 'tr', function(t) {	
			returnToPage = "groupstatlist";
			offsetvalue = 0;
			var currentMembername = $(t)[0].currentTarget.cells[1].innerText;
			currentMemberId = $(t)[0].currentTarget.cells[0].innerText;
			$("#memberTitle").text(currentMembername);
    		$("#main-content-list").css("display", "none");
    		$("#main-content-statlist").css("display", "none");
			$("#main-content-memberview").css("display", "block");
			// 查询加载成员列表
			memberViewStatLoad();
		});
		// 成员行事件，点击显示成员视图
		$('#member-table-foot tbody').on( 'mouseover', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "silver");
			$($(t)[0].currentTarget).css("cursor", "pointer");
		});
		$('#member-table-foot tbody').on( 'mouseout', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "gray");
		});
		$('#member-table-foot tbody').on( 'click', 'tr', function(t) {
			returnToPage = "list";
			offsetvalue = 0;
			var currentMembername = $(t)[0].currentTarget.cells[1].innerText;
			currentMemberId = $(t)[0].currentTarget.cells[0].innerText;
			$("#memberTitle").text(currentMembername);
    		$("#main-content-list").css("display", "none");
    		$("#main-content-statlist").css("display", "none");
			$("#main-content-memberview").css("display", "block");
			// 查询加载成员列表
			memberViewStatLoad();
		});
		// 返回按钮事件
		$("#returnListBtn").click(function(item){
    		$("#main-content-list").css("display", "block");
    		$("#main-content-statlist").css("display", "none");
    		$("#main-content-memberview").css("display", "none");
    	});
    	// 返回按钮事件
		$("#returnStatBtn").click(function(item){
    		$("#main-content-memberview").css("display", "none");
			if(returnToPage == "groupstatlist"){
				$("#main-content-list").css("display", "none");
	    		$("#main-content-statlist").css("display", "block");
			}else{
				$("#main-content-list").css("display", "block");
	    		$("#main-content-statlist").css("display", "none");
			}
    	});
    	// 导出
    	$("#exportBtn").click(function(item){
			
        });
        /*************************************/
        /**************组界面变化**************/
        /*************************************/
    	// 组统计左右变化
   		var nowDate = new Date();
   		var currYear = nowDate.getFullYear(); // 年
   		var currMonth = nowDate.getMonth() + 1; // 月
   		var currDate = nowDate.getDate(); // 日
   		var currDay = nowDate.getDay(); // 星期几，从0开始，可以计算周一、周日
   		
   		// 显示小周期：周（如：2021年4月5日—2021年4月11日）
   		function showWeakDate(targetShow, offsetvalue){
	    	var dateOf1 = new Date();
	    	var dateOf2 = new Date();
	    	if(currDay == 0) { // 周日为0单独计算
				dateOf1.setDate(dateOf1.getDate() - 6 - (-offsetvalue * 7));
		    	dateOf2.setDate(dateOf2.getDate() + 0 + (+offsetvalue * 7));
			}else {
		    	dateOf1.setDate(dateOf1.getDate() - (currDay - 1) - (-offsetvalue * 7));
		    	dateOf2.setDate(dateOf2.getDate() + (7 - currDay) + (+offsetvalue * 7));
			}
	    	var groupViewDateShow = "";
	    	groupViewDateShow += dateOf1.getFullYear() + "年" + (dateOf1.getMonth() + 1) + "月" + dateOf1.getDate() + "日";
	    	groupViewDateShow += "—";
	    	groupViewDateShow += dateOf2.getFullYear() + "年" + (dateOf2.getMonth() + 1) + "月" + dateOf2.getDate() + "日";
	    	$("#"+targetShow).text(groupViewDateShow);
   		}
   		// 显示中周期：月（如：2021年4月）
   		function showMonthDate(targetShow, offsetvalue){
	    	var dateOf1 = new Date();
	    	dateOf1.setMonth(dateOf1.getMonth() + offsetvalue);
	    	var groupViewDateShow = dateOf1.getFullYear() + "年" + (dateOf1.getMonth() + 1) + "月";
	    	$("#"+targetShow).text(groupViewDateShow);
   		}
   		// 显示大周期：月（如：2021年）
   		function showYearDate(targetShow, offsetvalue){
	    	var dateOf1 = new Date();
	    	dateOf1.setYear(1900 + dateOf1.getYear() + offsetvalue);
	    	var groupViewDateShow = dateOf1.getFullYear() + "年";
	    	$("#"+targetShow).text(groupViewDateShow);
   		}
   		// 默认显示小周期
   		showWeakDate("groupViewDate", 0);
   		showWeakDate("memberViewDate", 0);
   	 	/*************************************/
        /**************组界面变化**************/
        /*************************************/
        // 组统计周期变化
    	$("#circleSelectBtn").change(function(item){
     		// 判断切换时间域显示
    		offsetvalue =0;
			var circle = $("#circleSelectBtn").val();
			if(circle == "W"){
				showWeakDate("groupViewDate", offsetvalue);
			}else if(circle == "M"){
				showMonthDate("groupViewDate", offsetvalue);
			}else if(circle == "Y"){
				showYearDate("groupViewDate", offsetvalue);
			}
     		// 重新加载数据
     		groupStatListLoad();
     	});
    	// 组统计标签变化
    	$("#labelSelectBtn").change(function(item){
     		var circle = $("#labelSelectBtn").val();
     		// 重新加载数据
     		groupStatListLoad();
     	});
    	// 时间域左右切换
		$("#groupViewLeft").click(function(item){
			offsetvalue --;
			var circle = $("#circleSelectBtn").val();
			// 周切换
			if(circle == "W"){
				showWeakDate("groupViewDate", offsetvalue);
			}else if(circle == "M"){
				showMonthDate("groupViewDate", offsetvalue);
			}else if(circle == "Y"){
				showYearDate("groupViewDate", offsetvalue);
			}
			// 重新加载数据
     		groupStatListLoad();
		});
		$("#groupViewRight").click(function(item){
			offsetvalue ++;
			var circle = $("#circleSelectBtn").val();
			// 周切换
			if(circle == "W"){
				showWeakDate("groupViewDate", offsetvalue);
			}else if(circle == "M"){
				showMonthDate("groupViewDate", offsetvalue);
			}else if(circle == "Y"){
				showYearDate("groupViewDate", offsetvalue);
			}
			// 重新加载数据
     		groupStatListLoad();
		});

		/*************************************/
		/**************成员面变化**************/
		/*************************************/
    	// 成员视图周期变化
    	$("#circleSelectBtnMem").change(function(item){
     		// 判断切换时间域显示
    		offsetvalue =0;
     		var circle = $("#circleSelectBtnMem").val();
			if(circle == "W"){
				showWeakDate("memberViewDate", offsetvalue);
			}else if(circle == "M"){
				showMonthDate("memberViewDate", offsetvalue);
			}else if(circle == "Y"){
				showYearDate("memberViewDate", offsetvalue);
			}
     		// 重新加载数据
			memberViewStatLoad();
     	});
		// 成员视图时间域左右变化
		$("#memberViewLeft").click(function(item){
			offsetvalue --;
			var circle = $("#circleSelectBtnMem").val();
			// 周切换
			if(circle == "W"){
				showWeakDate("memberViewDate", offsetvalue);
			}else if(circle == "M"){
				showMonthDate("memberViewDate", offsetvalue);
			}else if(circle == "Y"){
				showYearDate("memberViewDate", offsetvalue);
			}
			// 重新加载数据
			memberViewStatLoad();
		});
		$("#memberViewRight").click(function(item){
			offsetvalue ++;
			var circle = $("#circleSelectBtnMem").val();
			// 周切换
			if(circle == "W"){
				showWeakDate("memberViewDate", offsetvalue);
			}else if(circle == "M"){
				showMonthDate("memberViewDate", offsetvalue);
			}else if(circle == "Y"){
				showYearDate("memberViewDate", offsetvalue);
			}
			// 重新加载数据
			memberViewStatLoad();
		});
		
    </script>
</body>
</html>

