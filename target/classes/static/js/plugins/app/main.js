window.onload = function() {
//用户验证预约按钮
    $('#verifyDIV').show();
    $('#appoinmentDIV').hide();

    $('#verifyButton').click(function () {
        console.log('---------用户验证预约按钮------------')
        var identityID = $('#identityID').val();
        console.log(identityID)
        if (!identityID) {
            alert('身份证id不能为空！');
            return false;
        }
        $.ajax({
            type: "get",
            url: "verifyID",
            data: {"identityID": identityID},
            success: function (returnData) {
                console.log('--------验证用户-----------')
                console.log(returnData);
                if (returnData.status > 0) {
                    alert(returnData.msg);
                } else {
                    if(returnData.data=="请先进行实名认证！")
                    {
                        mui.alert( '还没有您的身份认证信息，请先进行实名认证！','温馨提示：', function() {
                        });
                        return;
                    }

                    for(i=0;i<returnData.data.length;i++) {
                        var companyRow ;
                        if(i==0) {
                            $("#name").val(returnData.data[i].name);
                            $("#sex").val(returnData.data[i].sex);
                            $("#identityID1").val(returnData.data[i].identityID);
                            companyRow ='<div class="mui-input-row"> <label>'+returnData.data[i].companyName+'</label> <input name="radio1" type="radio" style="margin-top: 12px;"> </div>';
                            //$("#companyListDIV").val(companyRow);
                            $('#companyListDIV').append(companyRow);
                            //$("#companyItem0").val(returnData.data[i].companyName);
                        }
                        else
                        {
                            companyRow ='<div class="mui-input-row"> <label>'+returnData.data[i].companyName+'</label> <input name="radio1" type="radio" style="margin-top: 12px;"> </div>';
                            $('#companyListDIV').append(companyRow);
                        }


                    }
                    openAppointment();
                }
            }
        })
    });

    $('#appointmentButton').click(function () {
        console.log('---------用户预约按钮------------')
        var identityID = $('#identityID').val();
        var appointmentTime = $('#appointmentTime').text();
        var type =  $('#businessType').val();
        var company=$('input:radio[name="radio1"]:checked').val();
        console.log(identityID)
        if (!identityID) {
            alert('身份证id不能为空！');
            return false;
        }
        if (!company){
            alert('预约公司不能为空');
            return false;
        }
        if (!appointmentTime) {
            alert('预约时间不能为空！');
            return false;
        }
        if (!type) {
            alert('预约类型不能为空！');
            return false;
        }
        $.ajax({
            type: "get",
            url: "appointment",
            data: {"identityID": identityID,"appointmentTime":appointmentTime,"type":type},
            success: function (returnData) {
                console.log('--------用户预约-----------')
                console.log(returnData);
                if (returnData.status > 0) {
                    alert(returnData.msg);
                } else {
                    mui.alert(returnData.data, '温馨提示', function() {
                        location.reload([false]);
                    });
                    openVerify();
                }
            }
        })
    })
}

function openAppointment() {
    $('#verifyDIV').hide();
    $('#appoinmentDIV').show();
}

function openVerify() {
    $('#verifyDIV').show();
    document.getElementById("identityID").value="";
    $('#appoinmentDIV').hide();
}