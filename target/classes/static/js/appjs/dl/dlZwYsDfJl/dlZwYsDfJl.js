
var prefix = "/dl/dlZwYsDfJl"
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
									field : 'yszwlsh', 
									title : '应收账务流水号' 
								},
																{
									field : 'jldbh', 
									title : '计量点编号' 
								},
																{
									field : 'jldxh', 
									title : ' 计量点序号 ' 
								},
																{
									field : 'gzdbh', 
									title : '工作单编号' 
								},
																{
									field : 'cbqdbh', 
									title : '抄表区段编号' 
								},
																{
									field : 'yhbh', 
									title : '用户编号' 
								},
																{
									field : 'jshh', 
									title : '结算户号' 
								},
																{
									field : 'cbjhbh', 
									title : '抄表计划编号' 
								},
																{
									field : 'dfny', 
									title : '电费年月' 
								},
																{
									field : 'bqcbcs', 
									title : '本期抄表次数' 
								},
																{
									field : 'ycbcs', 
									title : '应抄表次数' 
								},
																{
									field : 'jslxdm', 
									title : '结算类型代码' 
								},
																{
									field : 'czcs', 
									title : '冲正次数' 
								},
																{
									field : 'czny', 
									title : '冲正年月' 
								},
																{
									field : 'yxxbz', 
									title : '有效性标志' 
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
									field : 'wyjrq', 
									title : ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 违约金日期 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; '
								},
																{
									field : 'hyfldm', 
									title : '行业分类代码' 
								},
																{
									field : 'yhlbdm', 
									title : '用户类别代码' 
								},
																{
									field : 'pjlxdm', 
									title : '票据类型代码' 
								},
																{
									field : 'cxdm', 
									title : '城乡代码' 
								},
																{
									field : 'ydlbdm', 
									title : '用电类别代码' 
								},
																{
									field : 'djdm', 
									title : '电价代码' 
								},
																{
									field : 'dj', 
									title : '电价(主电价)'
								},
																{
									field : 'fsjfbz', 
									title : '分时计费标志' 
								},
																{
									field : 'cctbdl', 
									title : '差错退补电量' 
								},
																{
									field : 'jfdl', 
									title : '计费电量' 
								},
																{
									field : 'dddf', 
									title : '电度电费' 
								},
																{
									field : 'jbdf', 
									title : '基本电费'
								},
																{
									field : 'ltdf', 
									title : '力调电费' 
								},
																{
									field : 'ysdf', 
									title : '应收电费' 
								},
																{
									field : 'fjfhj', 
									title : '附加费合计' 
								},
																{
									field : 'cctbdf', 
									title : '差错退补电费' 
								},
																{
									field : 'zctbdf', 
									title : '政策性退补电费' 
								},
																{
									field : 'jfyxj', 
									title : '缴费优先级' 
								},
																{
									field : 'pjdyxxbs', 
									title : '票据流水号'  
								},
																{
									field : 'sfsczdbz', 
									title : '是否生成账单数据的标志' 
								},
																{
									field : 'zDscsj', 
									title : '账单生成时间' 
								},
																{
									field : 'ywlbdm', 
									title : '应收业务类别代码' 
								},
																{
									field : 'sjlx', 
									title : '数据类型（电费）' 
								},
																{
									field : 'cdzxldf', 
									title : '超过需量定值电费' 
								},
																{
									field : 'jtlx', 
									title : '阶梯类型' 
								},
																{
									field : 'jbDfjsfsdm', 
									title : '基本电费计算方式代码' 
								},
																{
									field : 'mfdl', 
									title : '免费电量' 
								},
																{
									field : 'fgzs', 
									title : '峰谷增收' 
								},
																{
									field : 'fxrbs', 
									title : '发行人标识' 
								},
																{
									field : 'fxsj', 
									title : ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 发行时间 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' 
								},
																{
									field : 'gddwbm', 
									title : '供电单位编码' 
								},
																{
									field : 'dwbm', 
									title : '单位编码' 
								},
																{
									field : 'dqbm', 
									title : '地区编码' 
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
									field : 'djbbbh', 
									title : '参数版本唯一标识' 
								},
																{
									field : 'jshmc', 
									title : ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 结 算 户 名 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' 
								},
																{
									field : 'jshdz', 
									title : ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 结 算 户 地 址 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' 
								},
																{
									field : 'fpdyfs', 
									title : '发票打印方式' 
								},
																{
									field : 'fphqfs', 
									title : '发票获取方式' 
								},
																{
									field : 'xlxdbs', 
									title : '线路线段标识' 
								},
																{
									field : 'tqbs', 
									title : '台区标识' 
								},
																{
									field : 'nljdl', 
									title : '年累计电量' 
								},
																{
									field : 'jhzdpsrq', 
									title : '计划账单派送日期' 
								},								{
									field : 'jhhkrq', 
									title : '计划划扣日期' 
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