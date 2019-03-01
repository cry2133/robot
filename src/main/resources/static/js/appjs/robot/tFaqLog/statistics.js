
var prefix = "/robot/tFaqLog";


$(function() {
    var beginTime = $('#beginTime').val();
    var endTime = $('#endTime').val();
    getBar(beginTime,endTime);
});


function reLoad() {
    var beginTime = $('#beginTime').val();
    var endTime = $('#endTime').val();
    getBar(beginTime,endTime);
}

//获取线图数据
function getBar(beginTime,endTime){
    $.ajax({
        type: 'GET',
        url: prefix + "/statisticsList",
        data: { "beginTime" : beginTime, "endTime" : endTime},   //date参数根据时间显示（开发扩展）
        dataType: "json",
        success: function (data){
            //填入线图的数据
            console.log(data.legendData);
            var xData = data.xData;
            var seriesData = data.seriesData;
            var dom = document.getElementById("containerLine");

            bar(dom,xData,seriesData);
        },
        error: 	function (){
            layer.msg("获取线图标签失败！");
        }
    });
}

function bar(dom,xData,seriesData) {
    var myChart = echarts.init(dom);
    var app = {};
    var option = null;

    app.config = {
        rotate: 90,
        align: 'left',
        verticalAlign: 'middle',
        position: 'insideBottom',
        distance: 15,
        onChange: function () {
            var labelOption = {
                normal: {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                }
            };
            myChart.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
        }
    };


    var labelOption = {
        normal: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 16,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };


    for(var i in seriesData){
        seriesData[0].barGap = 0;
        seriesData[i].label = labelOption;
    }

    option = {
        color: ['#003366', '#006699', '#4cabce', '#e5323e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['默认', '单轮应答', '多轮应答', '闲聊']
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                data: xData    // X 轴数据
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series:seriesData
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}