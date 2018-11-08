$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/robot/tFaq/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			repositoryName : {
				required : true
			},
			majorName : {
				required : true
			},
			keygroupName : {
				required : true
			},
			question : {
				required : true
			},
			answer : {
				required : true
			}
		},
		messages : {
			repositoryName : {
				required : icon + "请选择知识库"
			},
			majorName : {
				required : icon + "请选择专业"
			},
			keygroupName : {
				required : icon + "请选择关键字"
			},
			question : {
				required : icon + "请输入问题"
			},
			answer : {
				required : icon + "请输入答案"
			}
		}
	})
}

var openRepository = function(){
	layer.open({
		type:2,
		title:"选择知识库",
		area : [ '300px', '70%' ],
		content:"/robot/tRepository/treeView"
	})
};
function loadRepository( repositoryId,repositoryName){
	$("#repositoryId").val(repositoryId);
	$("#repositoryName").val(repositoryName);
}

var openMajor = function(){
	layer.open({
		type:2,
		title:"选择专业",
		area : [ '300px', '70%' ],
		content:"/robot/tMajor/treeView"
	})
};
function loadMajor( majorId, majorName){
	$("#majorId").val(majorId);
	$("#majorName").val(majorName);
}


//var keygroup=[];
//function selectKeyWordPage() {
//	layer.open({
//		type : 2,
//		title : '选择类型',
//		maxmin : true,
//		shadeClose : false, // 点击遮罩关闭层
//		area : [ '350px', '350px' ],
//		content : '/robot/tKeyword/selectKeyWordPage/2', // iframe的url
//		btn: ['确定','关闭'],
//		yes: function(index){
//				 keygroup.splice(0,keygroup.length);//清空数组 
//
//				 var keygroupName="";
//                 //当点击‘确定’按钮的时候，获取弹出层返回的值
//                 var res = window["layui-layer-iframe" + index].add();
//                 if(res==""){
//                	 return ;
//                 }
//                 var json = JSON.parse(res);
//                 
//                 
//                 //添加类型id数组以及类型显示
//                 for(var i=0,l=json.length;i<l;i++){
//                	 keygroup.push(json[i].keywordId);
//                	 if(i==0){
//                		 keygroupName+=json[i].name;
//                	 }else{
//                		 keygroupName+=","+json[i].name;
//                	 }
//            	 }
//                 
//                 //菜单类型显示
//                 $("#keygroupName").val(keygroupName);
//                 $("#keygroup").val(keygroup);
//                 
////                 console.log(keyWordIds);
//                 //最后关闭弹出层
//                 layer.close(index);
//       },
//	});
//}

var keygroup=[];
//取值
$(document).ready(function(){  
	//加载关键字数据
	$.ajax({
        url : '/robot/tKeyword/selectKeyWord',
//         cache : false, 
          async : true,
          type : "POST",
          success : function (data,textStatus){
    	  var json= data.rows;
    	  for(var i=0;i<json.length;i++){
                  $("#selectUnit").append("<option value='"+json[i].keywordId+"'>"+json[i].name+"</option>");
    	  }
    	  $("#selectUnit").trigger('chosen:updated');
        }
    })
    
    
    //chosen初始化  
    $(".chosen-select").chosen({no_results_text : "未找到此选项!"});  
    //多选select 数据同步  
    chose_get_ini('#selectUnit');  
    //change 事件  
    $('#selectUnit').change(function(){  
//        alert(chose_get_value('#selectUnit'));  
    	var keygroupName="";
    	keygroup=chose_get_value('#selectUnit');
    	keygroupName=chose_get_text('#selectUnit');
    	 $("#keygroup").val(keygroup);
    	 $("#keygroupName").val(keygroupName);
//        alert(chose_get_value('#selectUnit') + ' : '+ chose_get_text('#selectUnit'));  
    });  
});  
//select 数据同步  
function chose_get_ini(select){  
    $(select).chosen().change(function(){$(select).trigger("chosen:updated");});  
}  
//select value获取  
function chose_get_value(select){  
    return $(select).val();  
}  
////select text获取，多选时请注意  
function chose_get_text(select){  
	 var selects = document.getElementById("selectUnit");
	    var str = [];
	    for(i=0;i<selects.length;i++){
	        if(selects.options[i].selected){
	            str.push(selects[i].text);
	        }
	    }
	 return str;  
//	 return $(select+" option:selected").text();  
}