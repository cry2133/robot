$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function update() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/robot/tEntranceFaq/update",
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
}
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
}
function loadMajor( majorId, majorName){
    $("#majorId").val(majorId);
    $("#majorName").val(majorName);
}

function count(keygroups){
    var num = 0;
    var arr = keygroups.split(",");
    if(arr){
        num = arr.length;
    }
    return num;
}

var keygroup=[];
//取值
$(document).ready(function(){
    //加载关键字数据
    var groups=$("#keygroup").val();
    var countSize=count(groups);	//检测有多少个数组
    var keygroups = groups.split(",");
    $.ajax({
        url : '/robot/tKeyword/selectKeyWord',
        async : false,
        type : "POST",
        success : function (data,textStatus){
            var json= data.rows;
            var flag=false;
            for(var i=0;i<json.length;i++){

                for(var j=0;j<countSize;j++){
                    if(keygroups[j]==json[i].keywordId){
                        flag=true;
                    }
                }
                if(flag){
                    $("#selectUnit").append("<option selected value='"+json[i].keywordId+"'>"+json[i].name+"</option>");
                    flag=false;
                }else{
                    $("#selectUnit").append("<option value='"+json[i].keywordId+"'>"+json[i].name+"</option>");
                }
                //你在这里写初始化chosen 的那些代码
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
//    return $(select+" option:selected").text();
}