<%@page import="com.zs.entity.User"%>
<%@page import="com.zs.util.JdbcUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>训练监测</title>
	
	<style type="text/css">
		.person1{
			border: 1px solid silver;
			border-radius: 10px;
			font-size: 16px;
			color: white;
			/* background-color: midnightblue; */
			width: 20% !important;
			cursor: pointer;
		}
		.pn-row1{
			
		}
		.pn-row1 span{
			display: block;
			height: 40px;
			padding: 10px;
		}
		.pn-row1 .big1{
			font-size: 20px;
		}
		.pn-row2{
		
		}
		.pn-row2 span{
			display: block;
			height: 78px;
			padding: 6px;
		}
		.pn-row2 .big2{
			font-size: 28px;
		}
		.pn-row3{
			font-size: 12px;
		}
		.pn-row3 span{
			display: block;
			height: 40px;
		}
		.navs{
			text-align: center;
			margin: 10px 6px;
		}
		.navs span{
			display: inline-block;
			width: 100px;
			padding: 6px;
			margin: 10px 0;
			color: white;
		}
		.myitem{
			padding: 4px; 
			cursor: pointer;
		}
	</style>
</head>
<%@include file="main.jsp" %>
<body>
	<%@include file="header.jsp" %>
	<script type="text/javascript">
		try{ace.settings.loadState('main-container')}catch(e){}
	</script>
	<%-- <%@include file="menu.jsp" %> --%>
	<div class="main-content" style="padding: 0 10px !important;">
		<div class="main-content-inner">
			<input type="hidden" value="${trainId}" name="trainId" id="trainId" />
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<span style="text-decoration: underline; color: aliceblue; float: left; font-size: 14px; margin: 0 3px; cursor: pointer;" onclick="javascript: window.history.back();">返 回</span>
				<span class="white">${trainName}</span>
				<select id="circleSelectBtn" class="myselect" style="float: right; margin-left: 5px;">
					<option value="BH1">编号↑</option>
					<option value="BH2">编号↓</option>
					<option value="TW1">体温↑</option>
					<option value="TW2">体温↓</option>
					<option value="XL1">心率↑</option>
					<option value="XL2">心率↓</option>
				</select>
				<button style="float: right; font-size: 14px; display: block;" id="endTrainBtn"> 结束训练 </button>
				<span id="view_list" style="text-decoration: underline; color: aliceblue; float: right; font-size: 14px; margin: 0 5px; cursor: pointer;">监测列表</span>
				<span id="view_panel" style="text-decoration: underline; color: aliceblue; float: right; font-size: 14px; margin: 0 5px; cursor: pointer;">监测面板</span>
			</div>
			<div class="page-content row" id="view_panel_div" style="display: block;">
				<div class="row">
					<c:forEach items="${data }" var="item">
						<div class="col-xs-12 col-sm-3 center person1" style="background-color: 
							<c:if test="${item.hrpercent >= 91 }">darkred</c:if>
							<c:if test="${item.hrpercent >= 81 and item.hrpercent < 91 }">orange</c:if>
							<c:if test="${item.hrpercent >= 71 and item.hrpercent < 81 }">green</c:if>
							<c:if test="${item.hrpercent < 71 }">#b7b4b4</c:if>" 
							onclick="showDetailInfo('${item.memId}','${item.memSn}','${item.memName}','${item.heartrate}','${item.hrval}','${item.hrvalmax}','${item.tempval}','${item.breatheval}','${item.caloryval}')">
							<div class="row pn-row1">
								<span class="col-xs-12 col-sm-8" style="text-align: left;">ID:${item.memId }，SN:${item.memSn }</span>
								<span class="col-xs-12 col-sm-4 big1">${item.memName }</span>
							</div>
							<div class="row pn-row2">
								<span class="col-xs-12 col-sm-4">体温:<br/>${item.tempval }</span>
								<span class="col-xs-12 col-sm-4 big2"><i class="fa fa-heart"></i>${item.hrval }</span><!-- 心率 -->
								<span class="col-xs-12 col-sm-4">呼吸：<br/>${item.breatheval }</span>
							</div>
							<div class="row pn-row3">
								<span class="col-xs-12 col-sm-4">心率比：${item.hrpercent }</span>
								<span class="col-xs-12 col-sm-4"><i class="fa fa-fire"></i>：${item.caloryval }</span><!-- 耗能 -->
								<span class="col-xs-12 col-sm-4">负荷：-</span>
							</div>
						</div>
					</c:forEach>
				</div>
				<p class="navs row">
					<span style="background-color: darkred">高危</span>
					<span style="background-color: orange">非正常</span>
					<span style="background-color: green">正常</span>
					<span style="background-color: #b7b4b4">离线</span>
				</p>
			</div><!-- /.page-content -->
			<div class="page-content" id="view_list_div" style="display: none; margin: 0 200px;">
				<div class="row">
					<div class="table-responsive">
						<table id="train-table-foot" class="table table-striped1 table-bordered table-hover1" style="color: white">
							<thead>
								<tr>
									<th>ID</th>
									<th>成员名称</th>
									<th>最大心率</th>
									<th>心率比</th>
									<th>身高</th>
									<th>体重</th>
									<th>设备SN</th>
									<th>心率</th>
									<th>体温</th>
									<th>呼吸</th>
									<th>训练强度</th>
									<th>卡路里</th>
									<th>训练负荷</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${data }">
									<tr onclick="showDetailInfo('${item.memId}','${item.memSn}','${item.memName}','${item.heartrate}','${item.hrval}','${item.hrvalmax}','${item.tempval}','${item.breatheval}','${item.caloryval}')">
										<td>${item.memId }</td>
										<td>${item.memName }</td>
										<td>${item.heartrate }</td>
										<td>${item.hrpercent }</td>
										<td>${item.height }</td>
										<td>${item.weight }</td>
										<td>${item.memSn }</td>
										<td>${item.hrval }</td>
										<td>${item.tempval }</td>
										<td>${item.breatheval }</td>
										<td>&nbsp;</td>
										<td>${item.caloryval }</td>
										<td>&nbsp;</td>
										<td>-</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div><!-- /.table-responsive -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->	
			<div class="page-content row" id="view_detail_div" style="display: block; color: white; margin: 0 200px;">
				<div class="col-xs-12 col-sm-2 center" style="height: 500px; background-color: dimgray;">
					<div style="padding: 10px; font-weight: bold;"><span class="fa fa-users"></span>团队平均<br/>${dataSize}</div>
					<c:forEach items="${data }" var="item" varStatus="seq">
						<p title="点击刷新" id="teamItem" class="myitem" onclick="showDetailInfo('${item.memId}','${item.memSn}','${item.memName}','${item.heartrate}','${item.hrval}','${item.hrvalmax}','${item.tempval}','${item.breatheval}','${item.caloryval}')">
							<span class="badge badge-info">${seq.index + 1}</span>  ${item.memName }<br/>ID: ${item.memId }
						</p>
					</c:forEach>
				</div>
				<div class="col-xs-12 col-sm-10">
					<div class="row" style="padding-left: 5px;">
						<div class="col-xs-12 col-sm-6" style="background-color: dimgray; border-right: 4px solid gray;">	
							<p style="text-align: center; padding: 10px; margin: 0;"><span id="memName"></span>（<span id="memId"></span>）</p>	
							<div class="profile-group-info">
								<div class="form-group">
									<div class="space-4"></div>
									<div class="row">
										<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span id="trainTime"></span><br/>训练时间</label>
										<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span id="trainHravg"></span>（<span id="trainHravgPercent"></span>%）<br/>平均心率</label>
										<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span id="trainLoad">-</span><br/>训练负荷</label>
										<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span id="trainCalory"></span>(千k)<br/>卡路里</label>
									</div>
								
									<div class="space-4"></div>
									<div class="row">
										<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span id="trainHrmax"></span>（<span id="trainHrmaxPercent"></span>%）<br/>最大心率</label>
										<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span id="trainHravg">-</span><br/>训练强度</label>
										<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span id="trainLoad">-</span><br/>受伤风险</label>
										<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span id="trainCalory"></span><br/>-</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6" style="background-color: dimgray; min-height: 176px;">
							<p style="text-align: center; padding: 10px; margin: 0;">心率区间</p>
							
						</div>
					</div>
					<div class="row">
						<div id="memberCharts1" style="height: 319px; border: 1px solid silver; padding: 5px; margin: 5px 0 0 5px;"></div>
					</div>
					
				</div>
				<div class="center">
					<button class="btn btn-sm btn-cancel" id="view_detail" style="margin-top: 10px;">  取 消  </button>
				</div>
			</div>
		</div><!-- /.main-container-inner -->
	</div><!-- /.main-content -->
	
	
	<script type="text/javascript">
		jQuery("#menu_2").addClass('active');
	</script>
	
	<script type="text/javascript">
   		var currnav = 0; // 0面板，2列表
		$("#view_panel").click(function(item){
			$("#view_panel_div").css("display", "block");
			$("#view_list_div").css("display", "none");
			$("#view_detail_div").css("display", "none");
			currnav = 0;
		});
		$("#view_list").click(function(item){
			$("#view_panel_div").css("display", "none");
			$("#view_list_div").css("display", "block");
			$("#view_detail_div").css("display", "none");
			currnav = 1;
		});
		$("#view_detail").click(function(item){
			if(currnav == 0){
				$("#view_panel_div").css("display", "block");
			}else if(currnav == 1){
				$("#view_list_div").css("display", "block");
			}
			$("#view_detail_div").css("display", "none");
		});
		$("#endTrainBtn").click(function(item){
			var currentTranId = $("#trainId").val();
			bootbox.confirm("训练是否已结束？", function(res){
				if(res){
					$post("${bodyPlus}/train/endTrain", {trainId: currentTranId}, function(res){
						if(res.success) {
							bootbox.alert("训练结束：" + res.msg, function(){
								window.location.href="${bodyPlus}/abody/train.html";
							});
						} else {
							bootbox.alert("训练结束：" + res.msg);
							window.location.href="${bodyPlus}/abody/train.html";
						}
					});
				}
			});
    	});

		// 添加行事件
		$('#train-table-foot tbody').on( 'mouseover', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "silver");
			$($(t)[0].currentTarget).css("cursor", "pointer");
		});
		$('#train-table-foot tbody').on( 'mouseout', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "gray");
		});

		// echarts图表
		var option1 = {
				title: {text:'成员训练-心率走势'},
	            tooltip: {show: true},
	            legend: {data:['心率走势图']},
	            xAxis : [{type : 'category', data : [1, 2, 3, 4, 5]}],
	            yAxis : [{type : 'value',  
		            	  axisLabel : {
			                formatter: '{value} bpm'
			             }}
	            ],
	            series : [{name:"", type:"line", data:[0.5, 1.5, 2.5, 3.0, 2.0]}]
	    };

		var memberCharts1 = echarts.init(document.getElementById('memberCharts1'));
        
		function showDetailInfo(memId, memSn, memName, heartrate, hrval, hrvalmax, tempval, breatheval, caloryval){
			$("#view_panel_div").css("display", "none");
			$("#view_list_div").css("display", "none");
			$("#view_detail_div").css("display", "block");
			$("#memName").text(memName);
			$("#memId").text(memId);
			$("#trainTime").text("-");
			$("#trainHravg").text(hrval);
			$("#trainHravgPercent").text((hrval/heartrate*100).toFixed(0));
			$("#trainHrmax").text(hrvalmax);
			$("#trainHrmaxPercent").text((hrvalmax/heartrate*100).toFixed(0));
			$("#trainCalory").text(caloryval);
			var trainId = $("#trainId").val();

			
			
			// 调用接口获取心率
			$ajax("/train/memHrList", "get", {sn: memSn, trainId: trainId},function(res){
				console.info(res)
				if(res.success) {
					// 表格数据
					option1.xAxis[0].data = res.data.xdata;
					option1.series[0].data = res.data.ydata;
					memberCharts1.setOption(option1, true); 
				} else {
					alert("查询失败，" + res.msg);
				}
			});
		}

		$("#view_detail_div").css("display", "none");
		
    </script>
</body>
</html>

