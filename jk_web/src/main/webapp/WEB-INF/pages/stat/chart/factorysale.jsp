<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
    <!-- 引入 ECharts 文件 -->
    <script src="${ctx }/js/echarts/echarts.min.js"></script>
</head>


<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 100%;height:500px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    myChart.title = '生产厂家销售统计';

    option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ${xAxisData}, //['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'销售量',
                type:'bar',
                barWidth: '60%',
                data:${seriesData} ,   //[10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
