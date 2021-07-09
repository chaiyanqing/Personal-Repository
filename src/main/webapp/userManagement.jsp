<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>成员管理</title>
</head>
<%@include file="main.jsp" %>
<script src="${bodyPlus }/assets/js/ajaxfileupload.js"></script>
<body>
	<%@include file="header.jsp" %>
	<script type="text/javascript">
		try{ace.settings.loadState('main-container')}catch(e){}
	</script>
	<%-- 群组列表 --%>
	<div class="main-content" id="main-content-group">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="editGroupBtn"> 编 辑 </button>
				<button style="float: left; font-size: 14px; display: none;" id="cancelGroupBtn"> 取 消 </button>
				<span class="white" id="groupTitle">群 组 管 理</span>
				<button style="float: right; font-size: 14px;" href="#groupadd-modal-table" id="addGroupBtn" data-backdrop="static" data-keyboard="false" data-toggle="modal"> 添 加 </button>
				<button style="float: right; font-size: 14px; display: none; padding: 0 10px;" id="deleteGroupBtn" class="btn btn-sm btn-danger"> 删 除 </button>
			</div>
			<div class="page-content">
				<div class="row">
					<div class="table-responsive">
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
					</div><!-- /.table-responsive -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.群组列表 -->
	
	<%-- 成员列表 --%>
	<div class="main-content" id="main-content-member" style="display: none;">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="returnBtn"> 返 回 </button>
				<button style="float: left; font-size: 14px;" id="editMemberBtn"> 编 辑 </button>
				<button style="float: left; font-size: 14px; display: none;" id="cancelMemberBtn"> 取 消 </button>
				<span class="white" id="memberTitle">添加/修改</span>
				<button style="float: right; font-size: 14px; display: none; padding: 0 10px;" id="deleteMemberBtn" class="btn btn-sm btn-danger"> 删 除 </button>
				<a style="float: right; font-size: 14px; padding: 0 6px; border-radius: 3px;" class="btn btn-sm" id="dowloadFile" href="${bodyPlus}/files/member_20210401.xlsx" download ="训练组成员批量导入模板" title="点击下载模板">下 载 </a>
				<button style="float: right; font-size: 14px;" id="importProductSimple" onclick="javascript:importMemberBtn.click()">
					<input id="importMemberBtn" enctype="multipart/form-data" class="picRelationUpload" type="file" name="file" onchange="execFileupload('importMemberBtn');" style="position: absolute;opacity: 0;width: 0px;height:0px;padding:0px;margin:0px;z-index: 1;margin-left: -10px;"/> 
					<i class="icon-upload align-top bigger-100"></i> 导 入
				</button>
				<button style="float: right; font-size: 14px;" id="addMemberBtn" data-backdrop="static" data-keyboard="false" data-toggle="modal"> 添 加 </button>
			</div>
			<div class="page-content">
				<div class="row">
					<div class="table-responsive">
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
					</div><!-- /.table-responsive -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.成员列表 -->
	
	<%-- 成员添加/修改 --%>
	<div class="main-content" id="main-content-addmember" style="display: none;">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="cancelAddMemberBtn"> 取 消 </button>
				<span class="white" id="addOrUpdatememberTitle">新 建 成 员</span>
				<button style="float: right; font-size: 14px; padding: 0 10px;" id="saveAddMemberBtn" class="btn btn-sm btn-danger"> 完 成 </button>
			</div>
			<div class="page-content">
				<div class="row">
					<div id="user-profile-1" class="user-profile row">
						<form class="form-horizontal" id="validation-form-addmember">
							<input type="hidden" id="memberId" name="memberId" value="" />
							<div class="col-xs-12 col-sm-3 center">
								<div class="space-10"></div>
								<div class="space-10"></div>
								<div>
									<span class="profile-picture">
										<img id="avatar" class="editable img-responsive" alt="Alex's Avatar" src="${bodyPlus }/assets/images/avatars/profile-pic.jpg" />
									</span>
									<div class="space-4"></div>
								</div>
								<div class="space-10"></div>
		
								<div class="profile-user-info">
									<div class="form-group">
										<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="memId"> ID</label>
										<div class="col-xs-12 col-sm-9">
											<div class="input-group">
												<input type="text" id="memId" name="memId" class="col-xs-12 col-sm-12"/>
											</div>
										</div>
									</div>
									<div class="space-10"></div>
									<div class="space-10"></div>
									<div class="form-group">
										<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="memSn"> SN</label>
										<div class="col-xs-12 col-sm-9">
											<div class="input-group">
												<input type="text" id="memSn" name="memSn" class="col-xs-12 col-sm-6"/>
												<button class="btn btn-sm btn-primary"  onclick="searchSn();"><i class="icon-save"></i> 搜索 </button>
											</div>
										</div>
									</div>
								</div>
							</div>
		
							<div class="col-xs-12 col-sm-9">
								<div class="profile-user-info">
									<div class="space-10"></div>
									<div class="space-10"></div>
									<div class="form-group">
										<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memName"> 姓名</label>
										<div class="col-xs-12 col-sm-4">
											<div class="clearfix">
												<input type="text" id="memName" name="memName" class="col-xs-12 col-sm-12" />
											</div>
										</div>
										<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memHeight"> 身高</label>
										<div class="col-xs-12 col-sm-4">
											<div class="clearfix">
												<input type="text" id="memHeight" name="memHeight" class="col-xs-12 col-sm-12" />
											</div>
										</div>
									</div>
								
									<div class="space-10"></div>
									<div class="space-10"></div>
									<div class="form-group">
										<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memSex"> 性别</label>
										<div class="col-xs-12 col-sm-4">
											<div class="clearfix">
												<select id="memSex" name="memSex" class="col-xs-12 col-sm-12">
													<option value="男">男</option>
													<option value="女">女</option>
												</select>
											</div>
										</div>
										<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memWeight"> 体重(KG)</label>
										<div class="col-xs-12 col-sm-4">
											<div class="clearfix">
												<input type="text" id="memWeight" name="memWeight" class="col-xs-12 col-sm-12" />
											</div>
										</div>
									</div>
									
									<div class="space-10"></div>
									<div class="space-10"></div>
									<div class="form-group">
										<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memGroup"> 群组</label>
										<div class="col-xs-12 col-sm-4">
											<div class="clearfix">
												<select id="memGroup" name="memGroup" class="col-xs-12 col-sm-12">
													<c:forEach var="item" items="${groups}">
														<option value="${item.name }">${item.name }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memSeq"> 组内序号</label>
										<div class="col-xs-12 col-sm-4">
											<div class="clearfix">
												<input type="text" id="memSeq" name="memSeq" class="col-xs-12 col-sm-12" />
											</div>
										</div>
									</div>
									
									<div class="space-10"></div>
									<div class="space-10"></div>
									<div class="form-group">
										<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memBirth"> 生日</label>
										<div class="col-xs-12 col-sm-10">
											<div class="clearfix">
												<select id="memBirthYear" name="memBirthYear" class="col-xs-12 col-sm-4">
													<option value="1922">1922</option>
													<option value="1923">1923</option>
													<option value="1924">1924</option>
													<option value="1925">1925</option>
													<option value="1926">1926</option>
													<option value="1927">1927</option>
													<option value="1928">1928</option>
													<option value="1929">1929</option>
													<option value="1930">1930</option>
													<option value="1931">1931</option>
													<option value="1932">1932</option>
													<option value="1933">1933</option>
													<option value="1934">1934</option>
													<option value="1935">1935</option>
													<option value="1936">1936</option>
													<option value="1937">1937</option>
													<option value="1938">1938</option>
													<option value="1939">1939</option>
													<option value="1940">1940</option>
													<option value="1941">1941</option>
													<option value="1942">1942</option>
													<option value="1943">1943</option>
													<option value="1944">1944</option>
													<option value="1945">1945</option>
													<option value="1946">1946</option>
													<option value="1947">1947</option>
													<option value="1948">1948</option>
													<option value="1949">1949</option>
													<option value="1950">1950</option>
													<option value="1951">1951</option>
													<option value="1952">1952</option>
													<option value="1953">1953</option>
													<option value="1954">1954</option>
													<option value="1955">1955</option>
													<option value="1956">1956</option>
													<option value="1957">1957</option>
													<option value="1958">1958</option>
													<option value="1959">1959</option>
													<option value="1960">1960</option>
													<option value="1961">1961</option>
													<option value="1962">1962</option>
													<option value="1963">1963</option>
													<option value="1964">1964</option>
													<option value="1965">1965</option>
													<option value="1966">1966</option>
													<option value="1967">1967</option>
													<option value="1968">1968</option>
													<option value="1969">1969</option>
													<option value="1970">1970</option>
													<option value="1971">1971</option>
													<option value="1972">1972</option>
													<option value="1973">1973</option>
													<option value="1974">1974</option>
													<option value="1975">1975</option>
													<option value="1976">1976</option>
													<option value="1977">1977</option>
													<option value="1978">1978</option>
													<option value="1979">1979</option>
													<option value="1980">1980</option>
													<option value="1981">1981</option>
													<option value="1982">1982</option>
													<option value="1983">1983</option>
													<option value="1984">1984</option>
													<option value="1985">1985</option>
													<option value="1986">1986</option>
													<option value="1987">1987</option>
													<option value="1988">1988</option>
													<option value="1989">1989</option>
													<option value="1990">1990</option>
													<option value="1991">1991</option>
													<option value="1992">1992</option>
													<option value="1993">1993</option>
													<option value="1994">1994</option>
													<option value="1995">1995</option>
													<option value="1996">1996</option>
													<option value="1997">1997</option>
													<option value="1998">1998</option>
													<option value="1999">1999</option>
													<option value="2000">2000</option>
													<option value="2001">2001</option>
													<option value="2002">2002</option>
													<option value="2003">2003</option>
													<option value="2004">2004</option>
													<option value="2005">2005</option>
													<option value="2006">2006</option>
													<option value="2007">2007</option>
													<option value="2008">2008</option>
													<option value="2009">2009</option>
													<option value="2010">2010</option>
													<option value="2011">2011</option>
													<option value="2012">2012</option>
													<option value="2013">2013</option>
													<option value="2014">2014</option>
													<option value="2015">2015</option>
													<option value="2016">2016</option>
													<option value="2017">2017</option>
													<option value="2018">2018</option>
													<option value="2019">2019</option>
													<option value="2020">2020</option>
													<option value="2021">2021</option>
													<option value="2022">2022</option>
													<option value="2023">2023</option>
													<option value="2024">2024</option>
													<option value="2025">2025</option>
													<option value="2026">2026</option>
													<option value="2027">2027</option>
												</select>
												<select id="memBirthMonth" name="memBirthMonth" class="col-xs-12 col-sm-4">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
												</select>
												<select id="memBirthDay" name="memBirthDay" class="col-xs-12 col-sm-4">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
													<option value="17">17</option>
													<option value="18">18</option>
													<option value="19">19</option>
													<option value="20">20</option>
													<option value="21">21</option>
													<option value="22">22</option>
													<option value="23">23</option>
													<option value="24">24</option>
													<option value="25">25</option>
													<option value="26">26</option>
													<option value="27">27</option>
													<option value="28">28</option>
													<option value="29">29</option>
													<option value="30">30</option>
													<option value="31">31</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="space-10"></div>
									<div class="space-10"></div>
									<div class="form-group">
										<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memHart"> 最大心率</label>
										<div class="col-xs-12 col-sm-4">
											<div class="clearfix">
												<input type="text" id="memHart" name="memHart" class="col-xs-12 col-sm-12" />
											</div>
										</div>
									</div>
									
								</div>
							</div>
						</form>
					</div>
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.成员添加/修改-->
	
	<%-- 成员详情 --%>
	<div class="main-content" id="main-content-detailmember" style="display: none;">
		<div class="main-content-inner">
			<div class="center" style="font-size: 18px; padding: 10px; margin: 10px; border-bottom: 2px solid black;">
				<button style="float: left; font-size: 14px;" id="cancelDetailMemberBtn"> 返 回 </button>
				<span class="white" id="memberDetailTitle">成 员 详 情</span>
				<button style="float: right; font-size: 14px; padding: 0 10px;" id="toEditMember" onclick="toEditMember()" class="btn btn-sm btn-danger"> 编 辑 </button>
			</div>
			<div class="page-content">
				<div class="row">
					<div id="user-profile-1" class="user-profile row">
						<div class="col-xs-12 col-sm-3 center">
							<div class="space-10"></div>
							<div>
								<span class="profile-picture">
									<img id="avatar" class="editable img-responsive" alt="Alex's Avatar" src="${bodyPlus }/assets/images/avatars/profile-pic.jpg" />
								</span>
								<div class="space-4"></div>
							</div>
						</div>
	
						<div class="col-xs-12 col-sm-9">
							<div class="profile-user-info">
								<div class="space-10"></div>
								<div class="form-group">
									<label class="control-label col-xs-12 col-sm-2 no-padding-right"> ID</label>
									<div class="col-xs-12 col-sm-4">
										<div class="clearfix" id="memDetailId"></div>
									</div>
									<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memHeight"> SN</label>
									<div class="col-xs-12 col-sm-4">
										<div class="clearfix" id="memDetailSn"></div>
									</div>
								</div>

								<div class="space-10"></div>
								<div class="space-10"></div>
								<div class="space-10"></div>
								<div class="space-10"></div>
								<div class="form-group">
									<label class="control-label col-xs-12 col-sm-2 no-padding-right"> 姓名</label>
									<div class="col-xs-12 col-sm-4">
										<div class="clearfix" id="memDetailName"></div>
									</div>
									<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memHeight"> 性别</label>
									<div class="col-xs-12 col-sm-4">
										<div class="clearfix" id="memDetailSex"></div>
									</div>
								</div>
							
								<div class="space-10"></div>
								<div class="space-10"></div>
								<div class="space-10"></div>
								<div class="space-10"></div>
								<div class="form-group">
									<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memHeight"> 身高</label>
									<div class="col-xs-12 col-sm-4">
										<div class="clearfix" id="memDetailHeight"></div>
									</div>
									<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memHart"> 最大心率</label>
									<div class="col-xs-12 col-sm-4">
										<div class="clearfix" id="memDetailHart"></div>
									</div>
								</div>
								
								<div class="space-10"></div>
								<div class="space-10"></div> 
								<div class="space-10"></div> 
								<div class="space-10"></div> 
								<div class="form-group">
									<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memWeight"> 体重(KG)</label>
									<div class="col-xs-12 col-sm-4">
										<div class="clearfix" id="memDetailWeight"></div>
									</div>
									<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="memGroup"> 群组</label>
									<div class="col-xs-12 col-sm-4">
										<div class="clearfix" id="memDetailGroup"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div><!-- /.row -->
			</div><!-- /.page-content -->		
		</div><!-- /.main-container-inner -->
	</div><!-- /.成员详情-->
	
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
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="uaccount"> 群组名称: <span style="color: red">*</span></label>
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
					<button class="btn btn-sm btn-cancel" data-dismiss="modal"><i class="icon-remove"></i> 取 消 </button>
					<button class="btn btn-sm btn-primary"  onclick="toSaveGroup();"><i class="icon-save"></i> 确 认 </button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- PAGE CONTENT ENDS -->
	<!-- 群组添加 end -->
	
	
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
				bootbox.confirm("该操作将彻底删除群组内所有成员及其训练数据，请谨慎操作！", function (result) {
					if(result){
						var selectedIds = [];
						$('input:checkbox[name="group_idinput"]:checked').each(function(){
				            selectedIds.push($(this).val());
			            });
						var ids = selectedIds.join(",");
						console.info(ids.toString())
						$post("${bodyPlus}/member/deleteGroup", {"groupIds" : ids.toString()}, function(res){
							bootbox.alert("操作完成：" + res.msg, function(){
								window.location.reload();
							});
						});
					}
				});
			}
 		});
 		$("#addGroupBtn").click(function(item){
			$("#g_title").text("添 加 群 组");
 			$('#groupName').val("");
 			$('#groupId').val("");
    	});
 		var isCancel = false;
 		function toEditGroup(id, name){
 			isCancel = true;
			$("#g_title").text("修 改 群 组");
			$("#groupId").val(id);
			$("#groupName").val(name);
		}
 		// 保存群组
		function toSaveGroup(){
			isCancel = false;
			var obj = $('#validation-form').serializeObject();
			console.info(obj);
			var groupId = $('#groupId').val();
			var groupName = $('#groupName').val();
			if(groupId == '' && groupName){ // 添加，判断名称是否存在
				$get("${bodyPlus}/member/groupSearch", {groupName: groupName}, function(res){
					if(res.data.length > 0){
						bootbox.alert("群组名称已存在。");
						$("#groupadd-modal-table").modal('hide');
					}else{
						$post("${bodyPlus}/member/addOrUpdateGroup", {groupId: groupId, groupName: groupName}, function(res){
							if(res.success) {
								bootbox.alert("操作完成：" + res.msg, function(){
									window.location.reload();
									$("#groupadd-modal-table").modal('hide');
								});
							} else {
								bootbox.alert("操作完成：" + res.msg);
							}
						});
					}
				});
			} else if(groupName){
				$post("${bodyPlus}/member/addOrUpdateGroup", {groupId: groupId, groupName: groupName}, function(res){
					if(res.success) {
						bootbox.alert("操作完成：" + res.msg, function(){
							window.location.reload();
							$("#groupadd-modal-table").modal('hide');
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
							{ "mData": "name" },
							{ "mData": "sex" },
							{ "mData" : null,
						        mRender : function(data, type, row, meta){
					        		//return '<a type="button" role="button" class="btn btn-minier btn-primary" href="#memberadd-modal-table" data-backdrop="static" data-keyboard="false" data-toggle="modal" onclick="toEditMember(' + row.id + ')" > 修改 </a>&nbsp;';
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
		// 添加行事件
		$('#group-table-foot tbody').on( 'mouseover', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "silver");
			$($(t)[0].currentTarget).css("cursor", "pointer");
		});
		$('#group-table-foot tbody').on( 'mouseout', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "gray");
		});
		var currentMemberId = "";
 		var returnToPage = "";
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
			$("#main-content-addmember").css("display", "block");
			$("#main-content-member").css("display", "none");
			$("#addOrUpdatememberTitle").text("新 建 成 员");
			$('#validation-form-addmember')[0].reset();
			$("#memberId").val("");
			$("#memGroup").val(currentGroupName);
			currentMemberId = "";
	 		returnToPage = "";
    	});
 		$("#cancelAddMemberBtn").click(function(item){
			$("#main-content-addmember").css("display", "none");
 	 		if(returnToPage == "list"){
				$("#main-content-member").css("display", "block");
 	 	 	}else{
				$("#main-content-detailmember").css("display", "block");
 	 	 	}
    	});
    	function execFileupload(fileElementId){
    		ajaxFileUpload('${bodyPlus}/import/excelImportMember?groupName='+currentGroupName, fileElementId);
        }
 		$("#deleteMemberBtn").click(function(item){
 			var obj = $('input:checkbox[name="member_idinput"]:checked');
			if(!obj || obj.length == 0){
				bootbox.alert("请选择记录.");
			}else{
				bootbox.confirm("该操作将彻底所选成员的信息及训练数据，请谨慎操作！", function (result) {
					if(result){
						var selectedIds = [];
						$('input:checkbox[name="member_idinput"]:checked').each(function(){
				            selectedIds.push($(this).val());
			            });
						var ids = selectedIds.join(",");
						console.info(ids.toString())
						$post("${bodyPlus}/member/deleteMember", {"memberIds" : ids.toString()}, function(res){
							bootbox.alert("操作完成：" + res.msg, function(){
								memberListLoad();
							});
						});
					}
				});
			}
 		});
 		function toEditMember(id){
 	 		$("#addOrUpdatememberTitle").text("修 改 成 员");
 			$("#main-content-addmember").css("display", "block");
			$("#main-content-member").css("display", "none");
			$("#main-content-detailmember").css("display", "none");
			// 实时查询成员信息
			var memid = "";
			if(currentMemberId != ""){
				memid = currentMemberId;
				returnToPage = "detail";
 			}else{
				memid = id;
				returnToPage = "list";
			}
			$ajax("/member/get", "get", {id: memid},function(res){
				console.info(res)
				// 赋值
				$("#memberId").val(res.data.id);
				$("#memName").val(res.data.name);
				$("#memHeight").val(res.data.height);
				$("#memSex").val(res.data.sex);
				$("#memWeight").val(res.data.weight);
				$("#memGroup").val(res.data.group);
				$("#memSeq").val(res.data.groupseq);
				$("#memBirthYear").val(res.data.birthyear);
				$("#memBirthMonth").val(res.data.birthmonth);
				$("#memBirthDay").val(res.data.birthday);
				$("#memHart").val(res.data.heartrate);
				$("#memId").val(res.data.memberid);
				$("#memSn").val(res.data.sn);
			});
 	 	}
 	 	$("#cancelDetailMemberBtn").click(function(item){
			$("#main-content-detailmember").css("display", "none");
			$("#main-content-member").css("display", "block");
 	 	});
		$("#saveAddMemberBtn").click(function(item){
			var memId = $('#memId').val();
			var memName = $('#memName').val();
			if(memId && memName && memId != "" && memName != ""){
				var obj = $('#validation-form-addmember').serializeObject(); 
				console.info(obj);
				$post("${bodyPlus}/member/addOrUpdateMember", obj, function(res){
					if(res.success) {
						bootbox.alert("操作完成：" + res.msg, function(){
							memberListLoad();
							$("#main-content-addmember").css("display", "none");
							$("#main-content-member").css("display", "block");
						});
					} else {
						bootbox.alert("操作完成：" + res.msg);
					}
				});
			}else{
				bootbox.alert("ID和姓名不能为空。");
				return;
			}
    	});
		// 添加行事件
		$('#member-table-foot tbody').on( 'mouseover', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "silver");
			$($(t)[0].currentTarget).css("cursor", "pointer");
		});
		$('#member-table-foot tbody').on( 'mouseout', 'tr', function(t) {
			$($(t)[0].currentTarget).css("background-color", "gray");
		});
		$('#member-table-foot tbody').on( 'click', 'tr', function(t) {
			currentMemberId = $(t)[0].currentTarget.cells[0].children[0].innerText;
    		$("#main-content-member").css("display", "none");
			$("#main-content-detailmember").css("display", "block");
			// 查询加载成员列表
			$ajax("/member/get", "get", {id: currentMemberId},function(res){
				$("#memDetailName").text(res.data.name);
				$("#memDetailSex").text(res.data.sex);
				$("#memDetailHeight").text(res.data.height);
				$("#memDetailHart").text(res.data.heartrate);
				$("#memDetailWeight").text(res.data.weight);
				$("#memDetailGroup").text(res.data.group);
				$("#memDetailId").text(res.data.memberid);
				$("#memDetailSn").text(res.data.sn);
			});
		});
    </script>
</body>
</html>

