<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>首页</title>
</head>
<%@include file="main.jsp" %>
<body>
	<%@include file="header.jsp" %>
	<script type="text/javascript">
		try{ace.settings.loadState('main-container')}catch(e){}
	</script>
	<%-- <%@include file="menu.jsp" %> --%>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<span class="white">本 周 训 练 负 荷</span>
				<select id="userGroupSlt" class="myselect" style="float: right;">
					<option value="">所有群组</option>
					<c:forEach var="item" items="${groups}">
						<option value="${item.name }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="page-content">
				<div class="row">
					<div id="mainCharts" style="height :500px;"></div>
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.main-content -->

	
	<script type="text/javascript">
		jQuery("#menu_1").addClass('active');
	</script>
	
	<script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('mainCharts')); 
        
        var option = {
            tooltip: {show: false},
            // legend: {data:['负荷']},
            xAxis : [{type : 'category', data : ["一","二","三","四","五","六","日"]}],
            yAxis : [{type : 'value'}],
            series : [{name: "", type: "line", data: []}]
        };

		function loadWeakTrainData(){
			var groupName = $("#userGroupSlt").val();
    		$ajax("/abody/trainLoadSearch", "get", {groupName: groupName},function(res){
   				console.info(res)
  				if(res.success) {
					// 重新渲染数据
					option.series[0].data = res.data;
					myChart.setOption(option); 
				} else {
					alert("查询失败，" + res.msg);
				}
   			});
		}
		loadWeakTrainData();
        $("#userGroupSlt").change(function(item){
        	loadWeakTrainData();
    	});
    </script>
</body>
</html>

