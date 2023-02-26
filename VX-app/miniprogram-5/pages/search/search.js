// pages/search/search.js
const app = getApp()
import { getStationsDate, getStatHour, getStatDay, getStatDayRange, getQuery } from "../../api/weatherapi"
import * as echarts from "../../components/ec-canvas/echarts"
import {
  formatTime,
} from "../../utils/util"
// 配置图表
function getLazyOption(type, xData, yData, unit, text, subtext) {
  return {
    grid: {//控制图表位置
      left: '15%',
      bottom: '20%'
    },
    title: [
      {//标题
        left: 'center',
        text: text,
        textStyle: {
          color: '#656565',
          fontWeight: '600',
          fontSize: 18,
        },
      },
      {//副标题
        left: 'center',
        top: '5%',
        subtext: subtext,
        subtextStyle: {
        },
      }
    ],
    tooltip: {
      trigger: 'axis',
      position: 'top'
    },
    xAxis: {
      type: 'category',
      data: xData,
      textStyle: {
        fontSize: 10,
      },
      boundaryGap: false,
    },
    yAxis: {
      type: 'value',
      axisLabel: {
        formatter: '{value}' + unit,
        fontsize: 10
      },
    },
    dataZoom: {
      type: 'slider',
      bottom: '7%',
      height: 10,
      show: true,
      start: 0,
      end: 100,
    },
    series: [
      {
        type: type,
        smooth: true,
        lineStyle: {
          width: 0
        },
        showSymbol: false,
        areaStyle: {
          opacity: 0.5,
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            {
              offset: 0,
              color: 'rgb(0, 0, 290)'
            },
            {
              offset: 1,
              color: 'rgb(0, 180, 280)'
            }
          ])
        },
        data: yData,
      }
    ]
  }
}

Page({
  /**
   * 页面的初始数据
   */
  data: {
    // 是否隐藏图片
    showImg: false,
    //图表
    ec: {
      lazyLoad: true
    },
    //查询下拉框内容
    queryArray: [
      {
        "id": "01",
        "text": "普通查询"
      },
      {
        "id": "01",
        "text": "高级查询"
      },
    ],
    // 时间下拉框内容
    dataArray: [
      {
        "id": "01",
        "text": "分钟"
      },
      {
        "id": "02",
        "text": "小时"
      },
      {
        "id": "03",
        "text": "时间段"
      },
    ],
    //高级查询因素下拉
    factorsArray: [],
    //查询下拉框默认的文字
    defaultText: "查询方式：",
    //因素下拉默认显示
    factorsText: "",
    // 时间下拉默认文字
    dataText: "时间：",
    // 普通查询时间参数
    showText: "",
    showText1: "",
    // 高级查询日期参数
    startText: "YYYY-MM-DD",
    endText: "YYYY-MM-DD",
    //是否显示弹出层
    commonPopup: false,
    advancedPopup: false,
    //多选内容数组
    checkFactors: [],
    items: [{
      value: '温度',
      isChecked: false,
      disabled: false,
      icon: '1'
    },
    {
      value: '湿度',
      isChecked: false,
      disabled: false,
      icon: '2'
    },
    {
      value: '风速',
      isChecked: false,
      disabled: false,
      icon: '3'
    },
    {
      value: '风向',
      isChecked: false,
      disabled: false,
      icon: '4'
    },
    {
      value: '降雨量',
      isChecked: false,
      disabled: false,
      icon: '5'
    },
    {
      value: '光照',
      isChecked: false,
      disabled: false,
      icon: '6'
    },
    {
      value: 'PM2.5',
      isChecked: false,
      disabled: false,
      icon: '7'
    },
    {
      value: 'PM10',
      isChecked: false,
      disabled: false,
      icon: '8'
    },
    ],
    // 页面内容高度
    forecastHeight: app.globalData.contentHeight,
    //是否隐藏页面
    commonPage: true,
    advancedPage: true,
    // 下拉显示选择分钟
    minute: false,
    // 下拉显示选择小时
    hour: false,
    // 下拉显示选择时间段
    time_quantum: false,
    // 根据选择的时间改变picker的类型
    pickerModel: "",
    // 自定义picker类型为selector的内容
    minuteArray: [
    ],
    // 单选框状态
    checked: false,
    // 选中的单选框
    selectChecked: "",
    //普通查询因素数组
    commonFactorArray: ["Temperature", "Humidity", "Speed", "Direction", "Rain", "Sunlight", "PM2.5", "PM10"],
    // 高级查询的参数
    factors: [
      ["start_temperature", ""], ["end_temperature", ""],
      ["start_humidity", ""], ["end_humidity", ""],
      ["start_speed", ""], ["end_speed", ""],
      ["start_direction", ""], ["end_direction", ""],
      ["start_rain", ""], ["end_rain", ""],
      ["start_sunlight", ""], ["end_sunlight", ""],
      ["start_pm25", ""], ["end_pm25", ""],
      ["start_pm10", ""], ["end_pm10", ""]
    ],
    // input
    disabled_states: [
      [true, "", true, ""],
      [true, "", true, ""],
      [true, "", true, ""],
      [true, "", true, ""],
      [true, "", true, ""],
      [true, "", true, ""],
      [true, "", true, ""],
      [true, "", true, ""]
    ],
    // 页面参数
    commonFactor: "",
    maxMinMean: [//气象因素最大、最小、平均
      {
        text: "",
        value: ""
      },
      {
        text: "",
        value: ""
      },
      {
        text: "",
        value: ""
      }
    ],
    factorIcon: "",
    factorUnit: ['℃', '%', 'm/s', '°', 'mm', 'Lux', 'ppm', 'ppm'],
    time: [],
    // 普通查询参数
    startDate: '',//开始日期
    endDate: '',//结束日期
    factorContent: [['最高温度', '最低温度', '平均温度', '气温波动幅度较小,温度的极差为', '气温波动幅度无明显变化,温度的极差为', '气温波动幅度较大,温度的极差高达'],
    ['最大湿度', '最小湿度', '平均湿度', '湿度波动幅度较小,湿度的极差为', '湿度波动幅度无明显变化,湿度的极差为', '湿度波动幅度较大,湿度的极差高达'],
    ['最大风速', '最小风速', '平均风速', '风速波动幅度较小，风速的极差为', '风速波动幅度无明显变化,风速的极差为', '风速波动的幅度较大,风速的极差高达'],
    ['最大风向', '最小风向', '平均风向', '风向波动幅度较小,风向的极差为', '风向波动幅度无明显变化,风向的极差为', '风向波动幅度较大,风向的极差为'],
    ['最大降雨量', '最小降雨量', '平均降雨量', '降雨量波动幅度较小,极差为', '降雨量波动幅度无明显变化,降雨量的极差为', '降雨量波动幅度较大,降雨量的极差为'],
    ['最强光照', '最弱光照', '平均光照', '光照波动幅度较小,极差为', '光照波动幅度无明显变化,光照的极差为', '光照波动幅度较大,光照的极差为'],
    ['最大浓度', '最小浓度', '平均浓度', '浓度波动幅度较小,极差为', '浓度波动幅度无明显变化,浓度的极差为', '浓度波动幅度较大,浓度的极差为'],
    ['最大浓度', '最小浓度', '平均浓度', '浓度波动幅度较小,极差为', '浓度波动幅度无明显变化,浓度的极差为', '浓度波动幅度较大,浓度的极差为']],
    factorScop:[[5,10],[10,15],[1,3],[25,65],[25,50],[50,100],[10,20],[10,20],],//因数数值范围
    // 高级查询数据
    factors_json: [],
    mean: [],
    dateArray: [],
    times: [],
    //提交后台数据
    submit_data: {},
    //后台返回data
    data: [],
  },
  //选择查询方式
  myget(e) {
    var items = this.data.items
    var disabled_states = this.data.disabled_states
    //清空多选状态
    for (var i = 0; i < items.length; i++) {
      items[i].isChecked = false
    }
    // input清空、锁定
    for (var i = 0; i < disabled_states.length; i++) {
      disabled_states[i][0] = true
      disabled_states[i][1] = ""
      disabled_states[i][2] = true
      disabled_states[i][3] = ""
    }
    this.setData({//清空弹框上一次内容,同步视图
      //气象站编号
      // station:wx.getStorageSync('station').station,
      //普通
      showText: "",
      showText1: "",
      dataText: "时间：",
      checked: false,
      selectChecked: "",
      //高级
      startText: "YYYY-MM-DD",
      endText: "YYYY-MM-DD",
      factors_json: [],
      submit_data: {},
      factorsArray: [],
      checkFactors: [],
      //多选状态
      items: items,
      // input内容
      disabled_states: disabled_states,
      times: [],
      dateArray: []
    })
    if (e.detail.id === 0) { // 普通
      this.setData({
        commonPopup: true,
      })
      console.log("点击:", e.detail.text, this.data.startDate, this.data.endDate)
    } else if (e.detail.id === 1) {//高级

      this.setData({
        advancedPopup: true,
      })
      console.log("点击:", e.detail.text)
    }
  },
  //高级查询因素下拉
  myfactors(e) {
    var data = this.data.data
    var factor = e.detail.text
    var factorId = e.detail.id
    var factorsArray = this.data.factorsArray
    var submit_data = this.data.submit_data
    var endText = this.data.endText
    var factorUnit = this.data.factorUnit
    var xData = []
    var yData = []
    var text = factorsArray[factorId]['text']
    var subtext = submit_data['start_date'] + " ~ " + endText
    var unit
    for (var i = 0; i < data.length; i++) {//x轴数据
      var timeIndex = data[i][0].lastIndexOf(":") + 1
      var timeText = data[i][0].substring(timeIndex - 9, timeIndex - 7) + "日" + data[i][0].substring(timeIndex - 6, timeIndex - 4) + "点"
      xData[i] = timeText
      if (factor === "温度") {
        unit = factorUnit[0]
        yData[i] = data[i][1]
      } else if (factor === "湿度") {
        unit = factorUnit[1]
        yData[i] = data[i][2]
      } else if (factor === "风速") {
        unit = factorUnit[2]
        yData[i] = data[i][3]
      } else if (factor === "风向") {
        unit = factorUnit[3]
        yData[i] = data[i][4]
      } else if (factor === "降雨量") {
        unit = factorUnit[4]
        yData[i] = data[i][5]
      } else if (factor === "光照") {
        unit = factorUnit[5]
        yData[i] = data[i][6]
      } else if (factor === "PM2.5") {
        unit = factorUnit[6]
        yData[i] = data[i][7]
      } else if (factor === "PM10") {
        unit = factorUnit[7]
        yData[i] = data[i][8]
      }
    }
    // 获取图表组件
    this.lazyComponent = this.selectComponent('#echart-per')
    // 图表参数
    // console.log("图表：", xData, yData, unit, text)
    setTimeout(() => this.init('line', xData, yData, unit, text, subtext), 500)
  },
  // 时间下拉
  getData(e) {
    // console.log(e.detail)
    if (e.detail.id === 0) { // 分钟
      this.setData({
        pickerModel: "selector",
        showText: "YYYY-MM-DD",
        showText1: "hh",
        dataText: "分钟"
      })
      console.log("点击:", e.detail.text)
    } else if (e.detail.id === 1) {//小时
      this.setData({
        pickerModel: "time",
        showText: "YYYY-MM-DD",
        showText1: "",
        dataText: "小时"
      })
      console.log("点击:", e.detail.text)
    } else if (e.detail.id === 2) {//时间段
      this.setData({
        pickerModel: "date",
        showText: "YYYY-MM-DD",
        showText1: "YYYY-MM-DD",
        dataText: "时间段",
      })
      console.log("点击:", e.detail.text)
    } else {

    }
  },
  // 监听普通查询picker-data选择内容
  bindDateChange(e) {
    console.log("picker选择：", e)
    this.setData({
      showText: e.detail.value
    })
  },
  bindPickerModelChange(e) {
    // console.log("picker选择：", e.detail.value)
    var showText = this.data.showText
    console.log(showText)
    if (e.detail.value.length < 3 && e.detail.value.length > 0) {
      this.setData({
        showText1: e.detail.value + ":00"
      })
    } else {
      this.setData({
        showText1: e.detail.value
      })
    }
    if (showText.indexOf('Y') != -1) {
      wx.showToast({
        title: '请先选择开始日期',
        icon: 'error'
      })
      this.setData({
        showText1: "YYYY-MM-DD"
      })
    } else {

    }
  },
  //高级查询picker-data开始日期
  bindStartText(e) {
    this.setData({
      startText: e.detail.value
    })
  },
  // 高级查询picker-data结束日期
  bindEndText(e) {
    var startText = this.data.startText
    this.setData({
      endText: e.detail.value
    })
    if (startText.indexOf('Y') != -1) {
      wx.showToast({
        title: '请先选择开始日期',
        icon: 'error'
      })
      this.setData({
        endText: "YYYY-MM-DD"
      })
    } else {

    }
  },
  // 普通确认按钮
  commonAffirm() {
    var station = wx.getStorageSync('station').station
    var dataText = this.data.dataText
    var showText = this.data.showText
    var showText1 = this.data.showText1
    var selectChecked = this.data.selectChecked//单选选中的下标
    var commonFactorArray = this.data.commonFactorArray
    var items = this.data.items
    var factorUnit = this.data.factorUnit
    var submit_data = this.data.submit_data//提交后台参数
    if (dataText === "分钟") {//分钟
      if (station != undefined && showText.indexOf('Y') === -1 && showText1.indexOf('h') === -1 && selectChecked.length != 0) {
        if (showText1.length < 6 && showText1.length > 3) {
          //切割时间
          let index = showText1.lastIndexOf(":")
          showText1 = showText1.substring(0, index);
        }
        else { }
        //形成json数据
        submit_data['station'] = station
        submit_data['date'] = showText
        submit_data['hour'] = showText1
        submit_data['which'] = selectChecked
        //发送分钟气象数据请求成功
        console.log("发送的数据：", submit_data)
        getStatHour(submit_data).then(res => {
          if (res.success === "1") {
            // console.log("请求的因素：", items[selectChecked - 1].value)
            var data = res.data
            var factorIcon = items[selectChecked - 1].icon
            var commonFactor = commonFactorArray[selectChecked - 1]
            var unit
            var xData = []//x轴数据
            var yData = []//y轴数据
            var text = items[selectChecked - 1].value //图表标题
            var subtext = submit_data.date + " " + submit_data.hour + ":00"
            console.log("数据", data, items[selectChecked - 1].value)
            if (items[selectChecked - 1].value === "温度") {
              //单位
              unit = factorUnit[0]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "湿度") {
              //单位
              unit = factorUnit[1]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "风速") {
              //单位
              unit = factorUnit[2]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "风向") {
              //单位
              unit = factorUnit[3]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "降雨量") {
              //单位
              unit = factorUnit[4]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "光照") {
              //单位
              unit = factorUnit[5]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "PM2.5") {
              //单位
              unit = factorUnit[6]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "PM10") {
              //单位
              unit = factorUnit[7]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            }
            this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, xData, yData, text, subtext)
          } else {
          }

        }).catch(err => {
          console.log("接口有误", err)
        })
      } else if (station === undefined) {
        wx.showToast({
          title: '未选择气象站',
          icon: 'error',
        })
      } else if (showText.indexOf('Y') != -1 || showText1.indexOf('hh') != -1) {
        wx.showToast({
          title: '请选择时间',
          icon: 'error',
        })
      } else if (selectChecked.length === 0) {
        wx.showToast({
          title: '请选择气象因素',
          icon: 'error',
        })
      }

    } else if (dataText === "小时") {//小时
      if (station != undefined && showText.indexOf('Y') === -1 && selectChecked.length != 0) {
        //形成json数据
        submit_data['station'] = station
        submit_data['date'] = showText
        submit_data['which'] = selectChecked
        // 发送请求成功
        getStatDay(submit_data).then(res => {
          if (res.success === "1") {
            console.log("请求的因素：", items[selectChecked - 1].value)
            var data = res.data
            var factorIcon = items[selectChecked - 1].icon
            var commonFactor = commonFactorArray[selectChecked - 1]
            var unit
            var xData = []//x轴数据
            var yData = []//y轴数据
            var text = items[selectChecked - 1].value //图表标题
            var subtext = submit_data.date
            if (items[selectChecked - 1].value === "温度") {
              //单位
              unit = factorUnit[0]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "湿度") {
              //单位
              unit = factorUnit[1]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "风速") {
              //单位
              unit = factorUnit[2]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "风向") {
              //单位
              unit = factorUnit[3]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "降雨量") {
              //单位
              unit = factorUnit[4]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "光照") {
              //单位
              unit = factorUnit[5]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "PM2.5") {
              //单位
              unit = factorUnit[6]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "PM10") {
              //单位
              unit = factorUnit[7]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = i
                yData[i] = data[i]
              }
            }
            this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, xData, yData, text, subtext)
          } else {
            // wx.showToast({
            //   title: '请求失败',
            //   icon: 'error',
            // })
          }
        }).catch(err => {
          console.log("接口有误", err)
        })
      } else if (station === undefined) {
        wx.showToast({
          title: '未选择气象站',
          icon: 'error',
        })
      } else if (showText.indexOf('Y') != -1) {
        wx.showToast({
          title: '请选择时间',
          icon: 'error',
        })
      } else if (selectChecked.length === 0) {
        wx.showToast({
          title: '请选择气象因素',
          icon: 'error',
        })
      }
    } else if (dataText === "时间段") {//时间段
      if (station != undefined && showText.indexOf('Y') === -1 && showText1.indexOf('Y') === -1 && selectChecked.length != 0) {
        //形成json数据
        submit_data['station'] = station
        submit_data['start_date'] = showText
        submit_data['end_date'] = showText1
        submit_data['which'] = selectChecked
        // 发送请求成功
        getStatDayRange(submit_data).then(res => {
          if (res.success === "1") {
            console.log("请求的因素：", items[selectChecked - 1].value)
            var data = res.data
            var factorIcon = items[selectChecked - 1].icon
            var commonFactor = commonFactorArray[selectChecked - 1]
            var unit
            var xData = []//x轴数据
            var yData = []//y轴数据
            var text = items[selectChecked - 1].value //图表标题
            var subtext = submit_data.start_date + " - " + submit_data.end_date
            var dataArray = []
            dataArray = this.dealDate(showText, showText1)
            dataArray.pop()
            data.pop()
            console.log(dataArray, data)
            var index = dataArray[0].lastIndexOf('-')
            if (items[selectChecked - 1].value === "温度") {
              //单位
              unit = factorUnit[0]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = dataArray[i].substring(index + 1, dataArray[i].length)
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "湿度") {
              //单位
              unit = factorUnit[1]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = dataArray[i].substring(index + 1, dataArray[i].length)
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "风速") {
              //单位
              unit = factorUnit[2]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = dataArray[i].substring(index + 1, dataArray[i].length)
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "风向") {
              //单位
              unit = factorUnit[3]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = dataArray[i].substring(index + 1, dataArray[i].length)
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "降雨量") {
              //单位
              unit = factorUnit[4]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = dataArray[i].substring(index + 1, dataArray[i].length)
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "光照") {
              //单位
              unit = factorUnit[5]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = dataArray[i].substring(index + 1, dataArray[i].length)
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "PM2.5") {
              //单位
              unit = factorUnit[6]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = dataArray[i].substring(index + 1, dataArray[i].length)
                yData[i] = data[i]
              }
            } else if (items[selectChecked - 1].value === "PM10") {
              //单位
              unit = factorUnit[7]
              for (var i = 0; i < data.length; i++) {//图表
                xData[i] = dataArray[i].substring(index + 1, dataArray[i].length)
                yData[i] = data[i]
              }
            }
            this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, xData, yData, text, subtext)
          } else {
          }

        }).catch(err => {
          console.log(err)
        })
        // 关闭弹窗
      } else if (station === undefined) {
        wx.showToast({
          title: '未选择气象站',
          icon: 'error',
        })
      }
      else if (showText.indexOf('Y') != -1 || showText1.indexOf('Y') != -1) {
        wx.showToast({
          title: '请选择时间',
          icon: 'error',
        })
      } else if (selectChecked.length === 0) {
        wx.showToast({
          title: '请选择气象因素',
          icon: 'error',
        })
      }
    } else {
      wx.showToast({
        title: '请选择时间单位',
        icon: 'error',
      })
    }

  },
  // 普通取消按钮
  commonCancel() {
    console.log("点击取消按钮")
    this.setData({
      commonPopup: false,
    })
  },
  // 监听单选因素内容
  EventHandle(e) {
    this.setData({
      selectChecked: e.detail.value
    })
    // console.log(e.detail.value)
  },
  // 高级查询确认按钮
  advancedAffirm(e) {
    var station = wx.getStorageSync('station').station
    //开始、结束日期
    var startText = this.data.startText
    var endText = this.data.endText
    //因素数组参数
    var factors_json = this.data.factors_json
    //提交后台参数
    var submit_data = this.data.submit_data
    //多选选中因素数组
    var checkFactors = this.data.checkFactors
    var items = this.data.items
    var factorUnit = this.data.factorUnit
    var factorsArray = this.data.factorsArray
    var time = this.data.time
    var times = this.data.times
    var dateArray = this.data.dateArray
    // indexOf("x")返回-1，表示x未出现过
    if (station != undefined && startText.indexOf("Y") === -1 && endText.indexOf("Y") === -1 && factors_json.length > 0) {//时间、因素不为空
      //将时间转为上一天的23:59:59
      var newEndText = new Date(endText).getTime() - 1000 * 3600 * 24 //时间戳 
      var endDate = formatTime(new Date(newEndText)).split(' ')[0].replaceAll("/", "-")
      //添加时间参数到json对象
      submit_data['station'] = station
      submit_data['start_date'] = startText
      submit_data['end_date'] = endDate + " 23:59:59"
      // 将因素数组数据转变为json对象
      for (var i = 0; i < factors_json.length; i++) {
        //向json对象添加属性、属性值
        submit_data[factors_json[i][0]] = factors_json[i][1]
      }
      console.log("发送请求，提交数据", submit_data)
      //发送请求成功
      getQuery(submit_data).then(res => {
        if (res.success === "1") {
          if (res.data.length != 0) {
            var data = res.data
            var xData = []//x轴
            var yData = []//数据
            var text
            var subtext
            var unit = factorUnit[checkFactors[0]]
            // console.log("选中：", checkFactors, unit, data)
            var mean = []
            for (var j = 0; j < checkFactors.length; j++) {//获取日期对应的
              // console.log("当前元素：", checkFactors[j])
              var factorsIndex = checkFactors[j]
              var dayData = []
              for (var i = 0; i < data.length; i++) {
                if (data[i][0].substring(0, 10) === data[0][0].substring(0, 10)) {
                  //获取因素对应的值
                  dayData.push(data[i][factorsIndex])
                } else {
                }
              }
              // console.log(dayData)
              var sum = 0
              for (var k = 0; k < dayData.length; k++) {
                var value = parseInt(dayData[k])
                sum = sum + value
              }
              mean.push((sum / dayData.length).toFixed(0))
            }
            // console.log("默认显示的数据：", mean)
            //页面默认显示第一个日期数据
            for (var i = 0; i < checkFactors.length; i++) {//下拉框数据
              var obj = {}
              var index = checkFactors[i]
              obj['id'] = i
              obj['text'] = items[checkFactors[i] - 1].value
              obj['value'] = mean[i] + factorUnit[checkFactors[i] - 1]
              obj['icon'] = index
              factorsArray.push(obj)
              // console.log("选中数据单位：",factorUnit[checkFactors[i]-1])
              // console.log("选中因素列表：", checkFactors, "数据：", data)
              //在时间段内每个因素的平均值
              var sum = 0
              for (var j = 0; j < data.length; j++) {
                sum = sum + parseInt(data[j][index])
              }
              var obj1 = {}
              obj1['id'] = i
              obj1['text'] = items[checkFactors[i] - 1].value
              obj1['value'] = mean[i] + factorUnit[checkFactors[i] - 1]
              obj1['icon'] = index
              dateArray.push(obj)
              // console.log("dateArray:",dateArray)
            }
            for (i = 0; i < data.length; i++) {//x轴数据
              var timeIndex = data[i][0].lastIndexOf(":") + 1
              var timeText = data[i][0].substring(timeIndex - 9, timeIndex - 7) + "日" + data[i][0].substring(timeIndex - 6, timeIndex - 4) + "点"
              xData[i] = timeText
              if (items[checkFactors[0] - 1].value
                === "温度") {
                unit = factorUnit[0]
                yData[i] = data[i][1]
              } else if (items[checkFactors[0] - 1].value
                === "湿度") {
                unit = factorUnit[1]
                yData[i] = data[i][2]
              }
              else if (items[checkFactors[0] - 1].value
                === "风速") {
                unit = factorUnit[2]
                yData[i] = data[i][3]
              } else if (items[checkFactors[0] - 1].value
                === "风向") {
                unit = factorUnit[3]
                yData[i] = data[i][4]
              } else if (items[checkFactors[0] - 1].value
                === "降雨量") {
                unit = factorUnit[4]
                yData[i] = data[i][5]
              } else if (items[checkFactors[0] - 1].value
                === "光照") {
                unit = factorUnit[5]
                yData[i] = data[i][6]
              } else if (items[checkFactors[0] - 1].value
                === "PM2.5") {
                unit = factorUnit[6]
                yData[i] = data[i][7]
              } else if (items[checkFactors[0] - 1].value
                === "PM10") {
                unit = factorUnit[7]
                yData[i] = data[i][8]
              }
            }
            //图表标题
            text = factorsArray[0]['text']
            subtext = submit_data['start_date'] + " ~ " + endText
            // 获取图表组件
            this.lazyComponent = this.selectComponent('#echart-per')
            // 图表参数
            // console.log("图表：", xData, yData, unit, text)
            setTimeout(() => this.init('line', xData, yData, unit, text, subtext), 500)
            //图表下面的数据
            //天数滚动（获取两个时间之间的日期）
            time = this.dealDate(startText, endText)
            time.pop()
            console.log("time:", time)
            for (var i = 0; i < time.length; i++) {
              var obj = {}
              time[i] = time[i].substring(5, 10).replace("-", "/")
              obj['text'] = time[i]
              if (i === 0) {
                obj['color'] = 'black'
                obj['fontSize'] = '32rpx'
              } else {
                obj['color'] = 'gray'
                obj['fontSize'] = '30rpx'
              }
              times.push(obj)
            }
            // 关闭弹窗
            this.setData({
              advancedPopup: false,
              advancedPage: false,
              commonPage: true,
              showImg: true,
              factorsArray: factorsArray,//因素下拉
              factorsText: factorsArray[0]['text'],//默认显示第一个因素
              data: data,
              time: time,
              times: times,
              dateArray: dateArray
            })
          } else {//数据为空
            wx.showToast({
              title: '暂无数据',
              icon: 'error'
            })
          }

        } else {
          console.log(res.success)
        }
      }).catch(err => {
        console.log(err)
      })

    } else if (station === undefined) {
      wx.showToast({
        title: '未选择气象站',
        icon: 'error',
      })
    }
    else if (startText.indexOf("Y") != -1 || endText.indexOf("Y") != -1) {//时间为空
      wx.showToast({
        title: '请选择时间',
        icon: 'error',
      })
    } else if (factors_json.length === 0) {
      wx.showToast({
        title: '请选择气象因素',
        icon: 'error',
      })
    }
  },
  // 复杂查询取消按钮
  advancedCancel() {
    console.log("点击取消按钮")
    this.setData({
      advancedPopup: false
    })
  },
  // 高级查询滚动条选着日期
  bindtapData(e) {
    var data = this.data.data
    var checkFactors = this.data.checkFactors
    var factorsArray = this.data.factorsArray
    var time = this.data.time
    var times = this.data.times
    var factorUnit = this.data.factorUnit
    var id = parseInt(e.target.id.substring(4, e.target.id.length))
    console.log("复杂查询数据：", time, times, id)
    //点击样式时改变样式
    for (var i = 0; i < times.length; i++) {
      if (i === id) {
        times[i].color = 'black'
        times[i].fontSize = '32rpx'
      } else {
        times[i].color = 'gray'
        times[i].fontSize = '30rpx'
      }
    }
    for (var z = 0; z < checkFactors.length; z++) {
      for (var i = 0; i < time.length; i++) {
        if (id === i) {
          for (var j = 0; j < time.length; j++) {
            time[j] = time[j].replace("/", "-")
          }
          var dayData = []
          //筛选当前点击日期的数据，计算平均值
          for (var k = 0; k < data.length; k++) {
            if (data[k][0].substring(5, 10) === time[id]) {
              // console.log("当前的因素:", checkFactors[z],"当前点击日期：",time[id])
              // console.log("找到data的时间：", data[k][0].substring(5, 10))
              //获取因素对应的值
              var index = checkFactors[z]
              dayData.push(data[k][index])
            } else {
            }
          }
          console.log("dayDaat:", dayData)
          //计算平均值
          var sum = 0
          for (var h = 0; h < dayData.length; h++) {
            var value = parseInt(dayData[h])
            sum = sum + value
          }
          var mean = (sum / dayData.length).toFixed(0)
          var unitIndex = checkFactors[z] - 1
          factorsArray[z].value = mean + factorUnit[unitIndex]
          console.log("平均值:", mean)
          console.log("列表内容：", factorsArray)
          this.setData({
            factorsArray: factorsArray,
            times: times
          })
        } else {
        }

      }
    }
  },
  // 监听多选框选中
  onChangeTap(e) {
    // console.log("多选框选中数据:", e);
    // 数组下标
    let index = e.detail.key - 1
    var startIndex = index * 2
    var endIndex = index * 2 + 1
    var factors = this.data.factors
    var factors_json = this.data.factors_json
    //点击次数统计
    //判断下标是否在数组出现过 
    if (this.data.checkFactors.indexOf(index + 1) > -1) {//取消勾选
      // console.log("点击了两次");
      let getLocation = this.data.checkFactors.indexOf(index + 1);
      // 当 splice(i,j) 方法有两个参数时，两个参数必须均为整数。表示从数组中索引为 i 开始删除，一共删除 j 个元素。
      this.data.checkFactors.splice(getLocation, 1);
      const item = 'items[' + index + '].isChecked'
      // console.log("取消勾选：",index+1)
      // 找到factors对应的两个气象因素
      // console.log("将要删除的因素：",factors[startIndex][0],factors[endIndex][0])
      for (var i = 0; i < factors_json.length; i++) {
        if (factors_json[i][0] === factors[startIndex][0] || factors_json[i][0] === factors[endIndex][0]) {//删除取消勾选的因素
          factors_json.splice(i, 1)
          factors_json.splice(i, 1)
          this.setData({
            factors_json: factors_json
          })
        } else {
        }
      }
      // console.log("取消勾选的后的数据：",factors_json)
      this.setData({
        [item]: false,
        // 清空input
        ['disabled_states[' + index + '].[1]']: "",
        ['disabled_states[' + index + '].[3]']: "",
        // 锁定input
        ['disabled_states[' + index + '].[0]']: true,
        ['disabled_states[' + index + '].[2]']: true,
      })
    } else {//选中
      // 向数组添加选中数据
      this.data.checkFactors.push(index + 1)
      // console.log("多选框选中数据:", this.data.checkFactors);
      const item = 'items[' + index + '].isChecked'
      console.log("选中了：", index + 1)
      this.setData({
        [item]: true,
        // 解放input
        ['disabled_states[' + index + '].[0]']: false,
        ['disabled_states[' + index + '].[2]']: false,
        // 给factors赋值
        ['factors[' + startIndex * 2 + '][1]']: "",
        ['factors[' + endIndex + '][1]']: "",
      })
      factors_json.push(this.data.factors[startIndex], this.data.factors[endIndex])
      // console.log("factors_json数据：", factors_json)
    }
  },
  // input失去焦点
  inputBindblur(e) {
    var index = e.target.id.charAt(3)
    console.log("id:", e.target.id, "value:", e.detail.value, "index:", index)
    var startIndex = index * 2
    var endIndex = index * 2 + 1
    var factors = this.data.factors
    var disabled_states = this.data.disabled_states
    var min
    var max
    //不同的因素不同的范围
    if (index === 0) {//温度
      min = -20
      max = 40
    } else if (index === 1) {//湿度
      min = 0
      max = 100
    }
    else if (index === 2) {//风速
      min = 0
      max = 100
    } else if (index === 3) {//风向
      min = 0
      max = 360
    } else if (index === 4) {//降雨量
      min = 0
      max = 250
    } else if (index === 5) {//光照
      min = 0
      max = 100
    } else if (index === 6) {//PM2,5
      min = 0
      max = 100
    } else if (index === 7) {//PM10
      min = 0
      max = 100
    }
    //赋值
    if (e.target.id == "min" + index) {//min
      this.setData({//实时记录input框的内容
        ['factors[' + startIndex + '][1]']: e.detail.value,
      })
      var maxValue = factors[endIndex][1]
      var minValue = factors[startIndex][1]
      this.setData({
        ['disabled_states[' + index + '][1]']: minValue
      })
      console.log("min:", parseInt(minValue), "max:", parseInt(maxValue))
      if (parseInt(minValue) != NaN && parseInt(minValue) >= min && parseInt(minValue) <= max) {
        if (parseInt(maxValue) != NaN) {
          if (parseInt(minValue) > parseInt(maxValue)) {//清空min值
            wx.showToast({
              title: 'min值大于max值',
              icon: 'error'
            })
            //清空当前min值
            this.setData({
              ['disabled_states[' + index + '][1]']: ""
            })
            console.log("min>max:", disabled_states)
          } else if (parseInt(minValue) < parseInt(maxValue)) {
            console.log("min<max:", disabled_states)
          }
        } else if (parseInt(maxValue) === NaN) {//max为空保存min
          console.log("min不空，max空:", disabled_states)
        }
      } else if (parseInt(minValue) === NaN) {
        console.log("min空:", disabled_states)
      } else if (parseInt(minValue) > max) {
        wx.showToast({
          title: 'min值过大',
          icon: 'error'
        })
        this.setData({
          ['disabled_states[' + index + '][1]']: ""
        })
        console.log(disabled_states)
      } else if (parseInt(minValue) < min) {
        wx.showToast({
          title: 'min值过小',
          icon: 'error'
        })
        this.setData({
          ['disabled_states[' + index + '][1]']: ""
        })
        console.log(disabled_states)
      }
    } else if (e.target.id == "max" + index) {//max
      this.setData({//实时记录input框的内容
        ['factors[' + endIndex + '][1]']: e.detail.value,
      })
      var maxValue = factors[endIndex][1]
      var minValue = factors[startIndex][1]
      this.setData({
        ['disabled_states[' + index + '][3]']: maxValue
      })
      console.log("disabled_states:", disabled_states)
      console.log("min:", parseInt(minValue), "max:", parseInt(maxValue))
      if (parseInt(maxValue) != NaN && parseInt(maxValue) >= min && parseInt(maxValue) <= max) {
        if (parseInt(minValue) != NaN) {
          if (parseInt(maxValue) > parseInt(minValue)) {
          } else if (parseInt(maxValue) < parseInt(minValue)) {
            wx.showToast({
              title: 'max值小于min',
              icon: 'error'
            })
            //清空最大值
            this.setData({
              ['disabled_states[' + index + '][3]']: ""
            })
            console.log("max<min:", disabled_states)
          }
        } else if (parseInt(minValue) === NaN) {
          console.log("max不空，min空:", disabled_states)
        }
      } else if (parseInt(maxValue) === NaN) {
        console.log("max空:", disabled_states)
      } else if (parseInt(minValue) > max) {
        wx.showToast({
          title: 'max值过大',
          icon: 'error'
        })
        this.setData({
          ['disabled_states[' + index + '][3]']: ""
        })
        console.log(disabled_states)
      } else if (parseInt(minValue) < min) {
        wx.showToast({
          title: 'max值过小',
          icon: 'error'
        })
        this.setData({
          ['disabled_states[' + index + '][3]']: ""
        })
        console.log(disabled_states)
      }
    }
  },
  // 手动初始化图表
  init(type, xData, yData, unit, text, subtext) {
    this.lazyComponent.init((canvas, width, height, dpr) => {
      let lazyChart = echarts.init(canvas, null, {
        width: width,
        height: height,
        devicePixelRatio: dpr
      })
      let lazyOption = getLazyOption(type, xData, yData, unit, text, subtext)
      lazyChart.setOption(lazyOption)
      this.lazyChart = lazyChart
      return lazyChart
    })
  },
  // 普通查询对选择因素的数据进行统一处理展示
  commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, xData, yData, text, subtext) {
    var maxMinMean = this.data.maxMinMean
    var commonFactorText = this.data.commonFactorText
    var factorContent = this.data.factorContent
    var selectChecked = this.data.selectChecked
    var factorScop = this.data.factorScop
    var max = parseInt(data[0]) //最高
    var min = parseInt(data[0]) //最低
    var mean = 0 //平均
    for (var i = 0; i < data.length; i++) {
      data[i] = parseInt(data[i])
      if (data[i] > max) {//最大
        max = data[i]
      } else {
        max = max
      }
      if (data[i] < min) {//最小
        min = data[i]
      } else {
        min = min
      }
      //平均
      mean = mean + data[i]
    }
    maxMinMean[0]['text'] = factorContent[selectChecked-1][0]
    maxMinMean[1]['text'] = factorContent[selectChecked-1][1]
    maxMinMean[2]['text'] = factorContent[selectChecked-1][2]
    maxMinMean[0]['value'] = max + unit
    maxMinMean[1]['value'] = min + unit
    maxMinMean[2]['value'] = (mean / data.length).toFixed(0) + unit
    if (max - min >= factorScop[selectChecked-1][0] && max - min <= factorScop[selectChecked-1][1]) {
      commonFactorText = factorContent[selectChecked-1][3] + String(max - min) + unit
    } else if (max - min < factorScop[selectChecked-1][0]) {
      commonFactorText = factorContent[selectChecked-1][4] + String(max - min) + unit
    } else if (max - min > factorScop[selectChecked-1][1]) {
      commonFactorText = factorContent[selectChecked-1][5] + String(max - min) + unit
    }
    // 获取图表组件
    this.lazyComponent = this.selectComponent('#commonEchart')
    console.log(unit)
    // 模拟请求
    setTimeout(() => this.init('line', xData, yData, unit, text, subtext), 500)
    //修改页面数据
    this.setData({
      commonFactor: commonFactor,//因素名称
      maxMinMean: maxMinMean,
      commonFactorText: commonFactorText,
      factorIcon: factorIcon//因素图标
    })
    // 关闭弹窗
    this.setData({
      commonPopup: false,
      commonPage: false,
      advancedPage: true,
      showImg: true
    })
    console.log("提交参数:", submit_data)
  },
  //遍历一段时间
  dealDate(startTime, endTime) {
    //格式化日期方法
    Date.prototype.Format = function (fmt) {
      var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
      }
      if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
      for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
      return fmt;
    }
    //存放groupDate;
    var groupDate = []
    //开始时间
    var startTime = new Date(startTime.trim());
    //结束时间
    var endTime = new Date(endTime.trim());
    var distanceDayLength = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24);
    var startDay = startTime.getDate();
    for (var i = 0; i <= distanceDayLength; i++) {
      groupDate.push(new Date(startTime.setDate(startDay + i)).Format("yyyy-MM-dd"));
    }
    return groupDate
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var station = wx.getStorageSync('station').station
    var id = wx.getStorageSync('token')
    var submit_data = this.data.submit_data
    var startDate = this.data.startDate
    var endDate = this.data.endDate
    submit_data['station'] = station
    submit_data['id'] = id
    //获取开始和结束日期
    getStationsDate(submit_data).then(res => {
      if (res.success === "1") {
        console.log("开始结束日期：", res.date[0].substring(0, 10), res.date[1].substring(0, 10))
        startDate = res.date[0].substring(0, 10)
        endDate = res.date[1].substring(0, 10)
        this.setData({
          startDate: startDate,
          endDate: endDate
        })
      } else {

      }
    }).catch(err => {

    })
    // console.log("气象编号：", wx.getStorageSync('station').station)
    // 自定义picker类型为小时的内容
    var minuteArray = this.data.minuteArray
    for (var i = 0; i <= 23; i++) {
      minuteArray[i] = i + ":00"
    }
    this.setData({
      minuteArray: minuteArray
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})