
var prefix = "/dl/dlKhYdkh"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								
								{
									field : 'yhbh', 
									title : '用户编号' 
								},
																{
									field : 'khbh', 
									title : '唯一标识' 
								},
																{
									field : 'yhmc', 
									title : ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 用 户 名 称 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' 
								},
																{
									field : 'yddz', 
									title : ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 用 电 地 址 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' 
								},
																{
									field : 'xyfz', 
									title : '信誉分数' 
								},
																{
									field : 'ydlbdm', 
									title : '用电类别代码' 
								},
																{
									field : 'dydjdm', 
									title : '电压等级代码' 
								},
																{
									field : 'hyfldm', 
									title : '行业分类代码' 
								},
																{
									field : 'jlfsdm', 
									title : '主计量方式' 
								},
																{
									field : 'yhlbdm', 
									title : '用户分类方式' 
								},
																{
									field : 'gddwbm', 
									title : '供电单位编码' 
								},
																{
									field : 'cbqdbh', 
									title : '抄表区段' 
								},
																{
									field : 'zdycxh', 
									title : '客户提供标识码' 
								},
																{
									field : 'yyhbh', 
									title : '原用户编号' 
								},
																{
									field : 'htrl', 
									title : '合同约定的本用户的容量' 
								},
																{
									field : 'yxrl', 
									title : '合同容量' 
								},
																{
									field : 'scbcdm', 
									title : '用电客户的生产班次分类'
								},
																{
									field : 'fhxzdm', 
									title : '负荷的重要程度分类'
								},
																{
									field : 'ghnhylbdm', 
									title : '依据国家最新的高耗能行业划分' 
								},
																{
									field : 'lhrq', 
									title : '电子用户档案的首次建立日期' 
								},
																{
									field : 'sdrq', 
									title : '用户的首次送电日期' 
								},
																{
									field : 'xhrq', 
									title : '销户业务信息归档的日期' 
								},
																{
									field : 'lsyddqrq', 
									title : '临时用电客户约定的用电到期日期' 
								},
																{
									field : 'lsydbz', 
									title : '是否是临时用电客户' 
								},
																{
									field : 'yhztdm', 
									title : '用电客户的状态说明'
								},
																{
									field : 'ydjczq', 
									title : '检查周期' 
								},
																{
									field : 'scjcrq', 
									title : '用电检查上次周期检查日期' 
								},
																{
									field : 'jcqdbs', 
									title : '检查区段标识(增加)' 
								},
																{
									field : 'tdbz', 
									title : '停电标志' 
								},
																{
									field : 'zglxdm', 
									title : '标识客户是否是转供相关客户'
								},
																{
									field : 'dqbm', 
									title : '用户所在的地区编码，用于分区。' 
								},
																{
									field : 'xzqydm', 
									title : '行政区域(所属村用该字段区分)' 
								},
																{
									field : 'cxdm', 
									title : '城乡标志' 
								},
																{
									field : 'szlc', 
									title : '所在楼层' 
								},
																{
									field : 'yfflxdm', 
									title : '预付费类型' 
								},
																{
									field : 'lsjfgxh', 
									title : '方便收费操作' 
								},
																{
									field : 'jcrybs', 
									title : '分管检查人员标识'  
								},
																{
									field : 'cbsxh', 
									title : '用户抄表顺序' 
								},
																{
									field : 'dwtydz', 
									title : ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 单 位 通 邮 地 址 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' 
								},
																{
									field : 'yzbm', 
									title : '邮政编码' 
								},
																{
									field : 'czhm', 
									title : '传真号码' 
								},
																{
									field : 'khsfdm', 
									title : '客户身份(业主、租户)' 
								},
																{
									field : 'khfqbz', 
									title : '客服使用，对该用户进行分群' 
								},
																{
									field : 'khjlbs', 
									title : '客服使用，记录用户对口服务的客户经理' 
								},
																{
									field : 'sfyzbdc', 
									title : '是否有自备电厂' 
								},
																{
									field : 'zbdcrl', 
									title : '自备电厂容量' 
								},
																{
									field : 'bzfbz', 
									title : '保障房标志' 
								},
																{
									field : 'bzfzhs', 
									title : '保障房总户数' 
								},
																{
									field : 'bzfzmj', 
									title : '保障房总面积' 
								},
																{
									field : 'bzfzrl', 
									title : '保障房总容量' 
								},
																{
									field : 'cjsj', 
									title : '数据创建时间' 
								},
																{
									field : 'czsj', 
									title : '数据最近一次变更时间' 
								},
																{
									field : 'cbzq', 
									title : '抄表周期' 
								},
																{
									field : 'jtlx', 
									title : '阶梯类型' 
								},
																{
									field : 'cdm', 
									title : '村：农村电费公布榜需要' 
								},
																{
									field : 'szxmbz', 
									title : '三重项目标志（是、否）' 
								},
																{
									field : 'sjzybgsj', 
									title : '数据资源管理平台变更时间' 
								},
																{
									field : 'ydjcsczxjcrq', 
									title : '用电检查上次专项检查日期' 
								},
																{
									field : 'ghrl', 
									title : '规划容量' 
								}
												
								/**
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.yhbh
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.yhbh
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.yhbh
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								}
								*/
								 ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'yhbh' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['yhbh'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}