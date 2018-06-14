
var prefix = "/robot/tFaq"
var repositoryId = $("#s_RepositoryId").val();
var majorId = $("#s_MajorId").val();
$(function() {
	getTreeData();
	load(majorId,repositoryId);
});

function load(majorId,repositoryId) {
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
								offset:params.offset,
								majorId:majorId,
								repositoryId:repositoryId,
								question:"%"+$('#searchQuestion').val()+"%"
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
									checkbox : true
								},
																{
									field : 'faqId', 
									title : '问答编号' 
								},
																{
									field : 'repositoryName', 
									title : '知识库名称' 
								},
								{
									field : 'majorName', 
									title : '专业名称' 
								},
																{
									field : 'question', 
									width:	'20%',
									title : '问题' 
								},
																{
									field : 'answer', 
									width:	'25%',
									title : '答案' 
								},
																{
									field : 'keygroupName', 
									title : '关键字组合' 
								},
																{
									field : 'keygroupPriority', 
									title : '关键字优先级' 
								},
								{
									field : 'flowIdentify', 
									title : '流程标识' 
								},
																{
									field : 'isFlow', 
									title : '是否流程' 
								},
																{
									field : 'flowLevel', 
									title : '流程级别' 
								},
																{
									field : 'createtime', 
									width:	'6%',
									title : '创建时间' 
								},
								{
									field : 'amount', 
									title : '回答次数' 
								},
																{
									title : '操作',
									width:	'15%',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.faqId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.faqId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="子问题"  mce_href="#" onclick="openNext(\''
												+ row.faqId
												+ '\')"><i class="fa  fa-level-down"></i></a> ';
										return e + f + d  ;
									}
								} ]
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
		area : [ '800px', '90%' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '90%' ],
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
				'faqId' : id
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

function openNext(id) {
	layer.open({
		type : 2,
		title : '子问题',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '90%' ],
		content : prefix + '/tFaqnext?faqId=' + id, // iframe的url
		cancel: function(){ 
			reLoad();
		  }
	});
	
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
			ids[i] = row['faqId'];
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

function reLoadTree() {
	$('#jstree').data('jstree', false).empty();
	$.ajax({
		type : "GET",
//		url : "/robot/tRepository/tree",
		url : "/robot/tMajor/tree",
		success : function(tree) {
			$('#jstree').jstree({
				'core' : {
					'data' : tree
				},
				"plugins" : [ "search" ]
			});
			$('#jstree').jstree().open_all();
		}
	});
	
}

function getTreeData() {
	$.ajax({
		type : "GET",
//		url : "/robot/tRepository/tree",
		url : "/robot/tMajor/tree",
		success : function(tree) {
			loadTree(tree);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	}).on('select_node.jstree', function (e, data) {
    }).on("loaded.jstree", function (event, data) {
        //这两句化是在loaded所有的树节点后，然后做的选中操作，这点是需要注意的，loaded.jstree 这个函数
        //取消选中，然后选中某一个节点
        $("#jstree").jstree("deselect_all",true);
//        $('#jstree').jstree('select_node',repositoryId,true);
        $('#jstree').jstree('select_node',majorId,true);
    });
	$('#jstree').jstree().open_all();
}

$('#jstree').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				repositoryId : '',
				majorId : '',
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
//				repositoryId : data.selected[0],
				repositoryId : repositoryId,
				majorId : data.selected[0],
			}
		}
//		$("#s_RepositoryId").val(data.selected[0]);
		$("#s_MajorId").val(data.selected[0]);
		majorId =data.selected[0];
		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});