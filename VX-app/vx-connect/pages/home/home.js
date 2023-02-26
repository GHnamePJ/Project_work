import * as echarts from '../../ec-canvas/echarts'
function initChart(canvas, width, height, dpr) {
  // 自定义图标宽高、清晰度
  const chart = echarts.init(canvas, null, {
    width: width,
    height: height,
    devicePixelRatio: dpr
  });
  canvas.setChart(chart);
  // 样式设置
  var option = {
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [120, 170, 150, 80, 70, 110, 130],
        type: 'bar',
        showBackground: true,
        backgroundStyle: {
          color: 'rgba(180, 180, 180, 0.2)'
        }
      }
    ]
  };
  chart.setOption(option);
  return chart;
}
Page({
  data: {
    ec: {
      onInit: initChart
    }
  },
  onLoad(option){
    // wx.request({
    //   url: 'http://192.168.218.1:8099/weatherInfo',
    //   success:(res)=>{
    //     console.log(res.data[0].date)
    //     for(var i=0;i<res.data.length;i++){
          
    //       console.log(res.data[i].date)
    //     }
    //   }
    // })
  }
})

