$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
        //forbid();
		save();
	}
});



function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/robot/tNewFaq/save",
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

/**
 * 强制保存
 */
function forceSave() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/robot/tNewFaq/forceSave",
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


/**
 * 禁止重复提交
 */
function forbid() {
    var btn = $("#force-submit-btn");
    btn.attr("disabled", true);  //按钮禁止点击
}
