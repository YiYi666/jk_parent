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

    option = {
        title: {
            text: '一天24小时系统使用分布',
            subtext: ''
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        toolbox: {
            show: true,
            feature: {
                saveAsImage: {}
            }
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data: ${xAxisData}/*['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00','18:00','19:00','20:00', '21:00','22:00','23:00']*/
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} 人'
            },
            axisPointer: {
                snap: true
            }
        },
        visualMap: {
            show: false,
            dimension: 0,
            pieces: [ {
                gt: 0,
                lte: 24,
                color: 'green'
            }]
        },
        series: [
            {
                name:'访问人数',
                type:'line',
                smooth: true,
                data: ${seriesData},//[300, 280, 250, 260, 270, 300, 550, 500, 400, 390, 380, 390, 400, 500, 600, 750, 800, 700, 600, 400, 400, 400, 400, 400],
            }
        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
