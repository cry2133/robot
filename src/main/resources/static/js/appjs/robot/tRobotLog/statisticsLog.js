
var prefix = "/robot/tRobotLog"
	
var GBvalue = "";

$(function() {
	load();
	getLine();
	getPie();
});

function load() {
	var x = [];
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/statisticsList", // 服务器数据的加载地址
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						singleSelect : false, // 设置为true将禁止多选
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								limit: params.limit,
								offset:params.offset,
								robotName:$('#robotName').val(),
								beginTime:$('#beginTime').val(),
								endTime:$('#endTime').val(),
								groupByValue:GBvalue
							};
						},
						
						columns : [
								{
									field : 'id', 
									title : '错误数量' 
								},
																{
									field : 'robotNo', 
									title : '机械人编号' 
								},
								{
									field : 'robotName', 
									title : '机器人名称' ,
								},
																{
									field : 'level', 
									title : '错误等级' 
									
								},
																{
									field : 'tag', 
									title : '标签' 
								}],
						responseHandler : responseHandler
					});
}
	function reLoad() {
		var v = [];
		if($("#groupByLevel").is(":checked")){//选中  
			v.push("level");
		} 
		if($("#groupByRobotNo").is(":checked")){//选中  
			v.push("robotNo");
		} 
		if($("#groupByTag").is(":checked")){//选中  
			v.push("tag");
		} 
		GBvalue = v.toString();
		$('#exampleTable').bootstrapTable('refresh');
	}

	function responseHandler(res){
		var arrayJson = res.rows;
		for(var p in arrayJson){
			//alert(arrayJson[p].robotName);
		}
		//console.log(res);
		return res;
	}
	

	//获取线图数据
	function getLine(){
		$.ajax({
			  type: 'GET',
			  url: prefix + "/getLine",
			  data: { "date" : "1"},   //date参数根据时间显示（开发扩展）
			  dataType: "json",
			  success: function (data){
				  //填入线图的数据
				  console.log(data.legendData);
				  var legendData = data.legendData;
				  var xData = data.xData;
				  var seriesData = data.seriesData;
				  console.log(legendData);
				  /*
				  var arrayJson = data.rows;
				  //获取到前十的机器人日志
				  for(var p in arrayJson){
						var robotName = arrayJson[p].robotName;
						legendData.push(robotName);
				  }
				  */
				  
				  /*
				  var legendData = ['邮件营销','联盟广告','视频广告','直接访问','搜索引擎'];
				  var xData = ['周一','周二','周三','周四','周五','周六','周日'];
				  var seriesData = [
					  		        {
							            name:'邮件营销',
							            type:'line',
							            stack: '总量',
							            data:[120, 132, 101, 134, 90, 230, 210]
							        },
							        {
							            name:'联盟广告',
							            type:'line',
							            stack: '总量',
							            data:[220, 182, 191, 234, 290, 330, 310]
							        },
							        {
							            name:'视频广告',
							            type:'line',
							            stack: '总量',
							            data:[150, 232, 201, 154, 190, 330, 410]
							        },
							        {
							            name:'直接访问',
							            type:'line',
							            stack: '总量',
							            data:[320, 332, 301, 334, 390, 330, 320]
							        },
							        {
							            name:'搜索引擎',
							            type:'line',
							            stack: '总量',
							            data:[820, 932, 901, 934, 1290, 1330, 1320]
							        }
							    ];
				  */
				  line(legendData,xData,seriesData);
			  },
			  error: 	function (){
					layer.msg("获取线图标签失败！");
			  }
		});
	}
	
	function line(legendData,xData,seriesData){
		var dom = document.getElementById("containerLine");
		var myChart = echarts.init(dom);
		option = null;
		option = {
		    title: {
		        text: '日志统计'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:legendData   //线图标签
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: xData    // X 轴数据
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: seriesData  //线图数据
		};
		;
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		}
	}

	//获取饼图数据
	function getPie(){
		$.ajax({
			  type: 'GET',
			  url: prefix + "/getPie",
			  data: { "date" : "1" },  //date参数根据时间显示（开发扩展）
			  dataType: "json",
			  success: pie,
			  error: 	function (){
					layer.msg("获取饼图数据失败！");
			  }
		});
	}
	
	function pie(data){
		var arrayJson = data.rows;
		var tagArr = [];
		var tagData = [];
		//填入图表数据
		for(var p in arrayJson){
			var tag = arrayJson[p].tag;
			var count = arrayJson[p].id;
			var data = {"value":count,"name":tag};
			tagArr.push(tag);
			tagData.push(data);
		}
		
		//var tag = ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎'];
		/*var tagData = [
		                {value:335, name:'直接访问'},
		                {value:310, name:'邮件营销'},
		                {value:234, name:'联盟广告'},
		                {value:135, name:'视频广告'},
		                {value:1548, name:'搜索引擎'}
		            ];*/
		
		var dom = document.getElementById("containerPie");
		var myChart = echarts.init(dom);
		option = null;
		option = {
		    title : {
		        text: '标签类统计',
		        subtext: '按标签统计',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: tagArr    //饼图标签
		    },
		    series : [
		        {
		            name: '日志标签',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '65%'],
		            data:tagData,  //饼图数据
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		;
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		}
	}
