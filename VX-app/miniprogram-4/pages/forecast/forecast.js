// pages/forecast/forecast.js
import * as echarts from "../../components/ec-canvas/echarts"
const app = getApp()
const {
  station
} = require("../../api/env").StationKey
import {
  formatTime,
  FactorEnglishToChinese,
  FactorNameToNum,
  FactorNumToEnglish,
  FactorNumToCompany,
  FactorNumToName,
  FactorNameToCompany,
  FactorChineseToTip
} from "../../utils/util"
import {
  getPredict
} from "../../api/weatherapi"


/** ------------------------------------ 图表 ------------------------------------ */
function setOption(chart,total,value) {
  const option = {
    series: [
      {
      type: 'pie', //指定类型为饼状图
      clockWise: true,//刻度增长是否按顺时针，默认顺时针。
      radius: ['70%', '75%'], //指定半径，注意不建议直接指定px，不利于自适应。
      itemStyle: {
        normal: {
          label: {
            show: false
          },
          labelLine: {
            show: false
          }
                }
      },
      hoverAnimation: false,//是否开启 hover 在 box 上的动画效果
      data: [{
        value: value,
        name: 'completed',
        itemStyle: {
          normal: {
            borderWidth: 5,
            borderColor: {
              colorStops: [{
                offset: 0,
                color: '#5d7fdf' // 0% 处的颜色
              }, {
                offset: 1,
                color: '#b097d8' // 100% 处的颜色
              }]
            },

            label: {
              show: false
            },
            labelLine: {
              show: false
            }
          }
        }
      },
       {
        name: 'gap',
        value: total - value,
        itemStyle: {
          normal: {
            label: {
              show: false
            },
            labelLine: {
              show: false
            },
            color: 'white',
            borderColor: 'rgba(0, 0, 0, 0)',
            borderWidth: 0
          }
        }
      }]
    }]
  };
  chart.setOption(option)
}

/** --------------------------------- 提示的图表 ------------------------------ */
// function setOptionTip(chart,data) {
//   const option = {
//     series: [
//       {
//         name: 'Nightingale Chart',
//         type: 'pie',
//         radius: [50, 70],
//         center: ['50%', '50%'],
//         data: data
//         // [
//         //   { value: 40, name: '低湿 40%以下' },
//         //   { value: 60, name: '最适合湿度 60%左右' },
//         //   { value: 80, name: '高湿 80%以上' }
//         // ]
//       }
//     ]
//   };
//   chart.setOption(option)
// }

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 内容高度
    forecastHeight: app.globalData.contentHeight,
    // 气象站名称
    station: wx.getStorageSync(station).name,
    // 弹窗出现
    popupSelect: false,
    // 时间
    forecastTime: "yyyy-mm-dd",
    startTime: "",
    // 单选
    radio: '0',
    // 单选数据
    radioItems: [{
        value: '湿度',
        isChecked: false,
        company: "%",
        icon: '2'
      },
      {
        value: '风速',
        isChecked: false,
        company: "m/s",
        icon: '3'
      },
      {
        value: '风向',
        isChecked: false,
        company: " ",
        icon: '4'
      },
      {
        value: '降雨量',
        isChecked: false,
        company: "mm",
        icon: '5'
      },
      {
        value: '光照',
        isChecked: false,
        company: "Lux",
        icon: '6'
      },
      {
        value: 'PM2.5',
        isChecked: false,
        company: " ",
        icon: '7'
      },
      {
        value: 'PM10',
        isChecked: false,
        company: " ",
        icon: '8'
      },
    ],
    // 预测数据
    predict: [],
    factor: 4,
    // 圆形图
    ec: {
      lazyLoad: true
    },
    ectip: {
      lazyLoad: true
    }
  },

  /* ------------------------弹窗---------------------------- */
  //时间选择器
  bindStartDateChange(e) {
    this.setData({
      forecastTime: e.detail.value
    })
  },
  // 气象因素选择
  radioChange(e) {
    let count = e.detail.value
    let num = Number(count) + 1
    console.log("选择的单选", num);
    this.setData({
      radio: num
    })
  },
  // 点击确认按钮
  confirm() {
    const toast = this.selectComponent('#my-toast');
    // 参数
    let total = {}
    total.station = wx.getStorageSync(station).station
    total.date = this.data.forecastTime
    total.which = this.data.radio
    console.log("发送请求参数", total);
    // 判断数据参数是否为空
    if (total.station === undefined || total.date === "yyyy-mm-dd" || total.which === "0") {
      console.log("请求参数为空");
      // 显示 Toast
      toast.linShow({
        icon: "error",
        title: '请求参数为空'
      });
      // 隐藏 Toast
      setTimeout(() => toast.linHide(), 1000)
    } else {
      this.setData({
        popupSelect: false
      })
      // 发送请求
      getPredict(total).then(res => {
        // 气温数据
        let listSelect = []
        let jsonTemplement = {}
        // console.log(FactorNumToEnglish(1));
        jsonTemplement.icon = 1
        jsonTemplement.name = FactorNumToName(1)
        jsonTemplement.values = res.data[FactorNumToEnglish(1)]
        jsonTemplement.company = FactorNumToCompany(1)
        listSelect.push(jsonTemplement)
        let jsonTemplement1 = {}
        jsonTemplement1.icon = total.which
        jsonTemplement1.name = FactorNumToName(total.which)
        jsonTemplement1.values = res.data[FactorNumToEnglish(total.which)]
        jsonTemplement1.company = FactorNumToCompany(total.which)
        listSelect.push(jsonTemplement1)
        // 将请求的需求放入数组
        this.setData({
          predict: listSelect,
          factor: 1
        })
        console.log("选择的气象因素:", FactorNumToEnglish(total.which));
        console.log("指定预测时间数据:", res.data[FactorNumToEnglish(total.which)]);
        this.oneComponent = this.selectComponent('#mychart-dom')
        this.tipComponent = this.selectComponent('#mychart-dom-tip')
        // console.log(res.data[FactorNumToEnglish(total.which)]);
        // console.log(FactorChineseToTip(FactorNumToName(total.which)));
        // PM2.5 PM10
        if(total.which===7 || total.which===8){
          this.init(250,res.data[FactorNumToEnglish(total.which)])
        }
        // 湿度
        else if(total.which===2){
          this.init(100,res.data[FactorNumToEnglish(total.which)])
          // this.initTip(FactorChineseToTip(FactorNumToName(total.which)))
        }
       
      
      }).catch()
    }
  },
  // 点击取消按钮
  close() {
    this.setData({
      popupSelect: false
    })
  },
  /* ----------------------- 主页面 ------------------------ */
  // 点击预测
  onClickForecast() {
    console.log("预测");
    this.setData({
      popupSelect: true,
      // 重置数据
      forecastTime: "yyyy-mm-dd",
      radioItems: [
        //   {
        //   value: '温度',
        //   isChecked: false,
        //   icon: '1'
        // },
        {
          value: '湿度',
          isChecked: false,
          icon: '2'
        },
        {
          value: '风速',
          isChecked: false,
          icon: '3'
        },
        {
          value: '风向',
          isChecked: false,
          icon: '4'
        },
        {
          value: '降雨量',
          isChecked: false,
          icon: '5'
        },
        {
          value: '光照',
          isChecked: false,
          icon: '6'
        },
        {
          value: 'PM2.5',
          isChecked: false,
          icon: '7'
        },
        {
          value: 'PM10',
          isChecked: false,
          icon: '8'
        },
      ]
    })
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // console.log(app.globalData.contentHeight);
    let that = this
    //  后一天时间
    let tomorrow = new Date().getTime() + 1000 * 3600 * 24 //时间戳
    let tomorrowdate = formatTime(new Date(tomorrow)).split(' ')[0] //2022/11/17
    console.log("明天:", tomorrowdate);
    // 转换格式
    var reg = /(\d{4})\/(\d{2})\/(\d{2})/;
    let tomorrowtime = tomorrowdate.replace(reg, "$1-$2-$3")
    that.setData({
      // 时间开始定义
      startTime: tomorrowtime,
    })

    //默认显示数据 
    let jsonpredict = {}
    jsonpredict.station = wx.getStorageSync(station).station
    jsonpredict.date = that.data.startTime
    jsonpredict.which = 1
    console.log(jsonpredict);
    // if( jsonpredict.station===undefined){

    // }else{
    getPredict(jsonpredict).then(res => {
      // console.log(res.data);
      let object = res.data
      // console.log("对象长度:",Object.keys(object).length);
      let list = []
      for (let i = 0; i < Object.keys(object).length; i++) {
        let factorResults = {}
        let oldname = Object.keys(object)[i]
        factorResults.name = FactorEnglishToChinese(oldname)
        factorResults.values = Object.values(object)[i]
        factorResults.icon = FactorNameToNum(FactorEnglishToChinese(oldname))
        factorResults.company = FactorNameToCompany(FactorEnglishToChinese(oldname))
        // console.log(FactorEnglishToChinese(oldname));
        // console.log(Object.values(object)[i]);
        list.push(factorResults)
        // console.log(factorResults);
      }
      console.log("明天预测数据", list);
      that.setData({
        predict: list
      })
    }).catch()
    // }
  },

  /** 图表初始化init */
  init(total,value) {
    this.oneComponent.init((canvas, width, height) => {
      const chart = echarts.init(canvas, null, {
        width: width,
        height: height
      });
      setOption(chart,total,value)
      this.chart = chart;
      return chart;
    });
  }
})



