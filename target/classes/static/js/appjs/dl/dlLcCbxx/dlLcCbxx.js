
var prefix = "/dl/dlLcCbxx"
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
									field : 'gzdbh', 
									title : '工作单编号' 
								},
																{
									field : 'jldbh', 
									title : '计量点编号' 
								},
																{
									field : 'yxdnbbs', 
									title : '运行电能表标识' 
								},
																{
									field : 'sslxdm', 
									title : '示数类型代码' 
								},
																{
									field : 'ywlbdm', 
									title : '抄表业务类别代码' 
								},
																{
									field : 'jldxh', 
									title : '计量点序号' 
								},
																{
									field : 'sdlx', 
									title : '时段代码' 
								},
																{
									field : 'cbjhbh', 
									title : '抄表计划编号' 
								},
																{
									field : 'cbqdbh', 
									title : '抄表区段编号' 
								},
																{
									field : 'jldcbsxh', 
									title : '计量点抄表顺序号' 
								},
																{
									field : 'cbsxh', 
									title : '抄表顺序号' 
								},
																{
									field : 'zfbbz', 
									title : '主副表标志' 
								},
																{
									field : 'dfny', 
									title : '电费年月' 
								},
																{
									field : 'bqcbcs', 
									title : '本月抄表次数' 
								},
																{
									field : 'ycbcs', 
									title : '应抄表次数' 
								},
																{
									field : 'czcs', 
									title : '冲正次数' 
								},
																{
									field : 'yhbh', 
									title : '用户编号' 
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
									field : 'jldytdm', 
									title : '计量点用途代码' 
								},
																{
									field : 'zcbh', 
									title : '资产编号' 
								},
																{
									field : 'ccbh', 
									title : '出厂编号' 
								},
																{
									field : 'sblxdm', 
									title : '设备类型代码' 
								},
																{
									field : 'scbss', 
									title : '上次表示数' 
								},
																{
									field : 'bcbss', 
									title : '本次表示数' 
								},
																{
									field : 'bssce', 
									title : '表示数差额' 
								},
																{
									field : 'bmws', 
									title : '表码位数' 
								},
																{
									field : 'xw', 
									title : '相位' 
								},
																{
									field : 'zhbl', 
									title : '综合倍率' 
								},
																{
									field : 'jbdl', 
									title : '旧表电量' 
								},
																{
									field : 'jjdl', 
									title : '加减电量' 
								},
																{
									field : 'ysdl', 
									title : '预收电量' 
								},
																{
									field : 'tbdl', 
									title : '退补电量' 
								},
																{
									field : 'scysdl', 
									title : '上次预收电量' 
								},
																{
									field : 'bjdl', 
									title : '表计电量' 
								},
																{
									field : 'sccbrq', 
									title : '上次抄表日期' 
								},
																{
									field : 'scbjdl', 
									title : '上次表计电量' 
								},
																{
									field : 'pjbjdl', 
									title : '上上次表计电量' 
								},
																{
									field : 'cbbzdm', 
									title : '抄表标志代码(0_未抄,1_已抄)' 
								},
																{
									field : 'yhlbdm', 
									title : '用户类别代码' 
								},
																{
									field : 'sjcbfsdm', 
									title : '实际抄表方式代码' 
								},
																{
									field : 'cbztdm', 
									title : '抄表状态代码' 
								},
																{
									field : 'cbycdm', 
									title : '抄表异常代码' 
								},
																{
									field : 'gddwbm', 
									title : '供电单位编码' 
								},
																{
									field : 'cbrbs', 
									title : '抄表人标识' 
								},
																{
									field : 'cbsj', 
									title : '抄表时间' 
								},
																{
									field : 'djsj', 
									title : '冻结时间' 
								},
																{
									field : 'scgzdbh', 
									title : '上次工作单编号' 
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
									field : 'czny', 
									title : '冲正年月' 
								},
																{
									field : 'sjlx', 
									title : '数据类型' 
								},
																{
									field : 'yxbz', 
									title : '有效标志' 
								},
																{
									field : 'xzpdabz', 
									title : '下载PDA标志' 
								},
																{
									field : 'ycbh', 
									title : '原抄表号,用于记录电能表上的抄表号' 
								},
																{
									field : 'zwwcbbz', 
									title : '转外委抄表标志' 
								},
																{
									field : 'xxdm', 
									title : '相线，包括：单相、三相三线、三相四线' 
								},
																{
									field : 'jldcbfsdm', 
									title : '计量点抄表方式代码' 
								},
																{
									field : 'zdcbgxsj', 
									title : '自动抄表更新时间' 
								},
																{
									field : 'yccbbz', 
									title : '远程抄表标识' 
								},
																{
									field : 'jlqrldbm', 
									title : '计量前日0点表码' 
								},
																{
									field : 'jldrldbm', 
									title : '计量当日0点表码' 
								},
																{
									field : 'jldresdbm', 
									title : '计量当日24点表码' 
								},
																{
									field : 'jlcresdbm', 
									title : '计量次日24点表码' 
								},
																{
									field : 'sjcbrq', 
									title : '实际抄表日期' 
								},
																{
									field : 'ycbcbz', 
									title : '远程自动化抄表存在补抄的情况' 
								},
								/**
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.gzdbh
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.gzdbh
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.gzdbh
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
				'gzdbh' : id
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
			ids[i] = row['gzdbh'];
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