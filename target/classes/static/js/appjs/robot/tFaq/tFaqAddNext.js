$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var parentId=$("#parentId").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/robot/tFaq/saveNext",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				window.location.href='/robot/tFaq/tFaqnext?faqId=' + parentId;
//				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
//				parent.layer.close(index);

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

function back(id){
	window.location.href='/robot/tFaq/tFaqnext?faqId=' + id;
}


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