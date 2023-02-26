// pages/search/search.js
const app = getApp()
import { getStatHour, getStatDay, getStatDayRange, getQuery } from "../../api/weatherapi"
import * as echarts from "../../components/ec-canvas/echarts"
import number from "../../miniprogram_npm/lin-ui/common/async-validator/validator/number"
import formValidation from "../../utils/formValidation"
// 配置图表
function getLazyOption(type, xData, yData, unit, text) {
  return {
    grid: {
      left: '15%',
      bottom: '22%'
    },
    title: {
      left: 'left',
      top: '5%',
      text: text,
      textStyle: {
        color: '#656565',
        fontWeight: '500',
        fontSize: 14,
      }
    },
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
    },
    yAxis: {
      type: 'value',
      axisLabel: {
        formatter: '{value}' + unit,
        fontsize: 10
      },
    },
    dataZoom: [
      {
        bottom: 0,
        height: 0,
        show: false,
      },
      {
        type: 'inside',
        start: 0,
        end: 10
      },
      {
        start: 0,
        end: 10
      },

    ],
    series: [
      {
        data: yData,
        type: type,
        showBackground: true,
        backgroundStyle: {
          color: 'rgba(180, 180, 180, 0.2)'
        },
        smooth: true
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
        "text": "复杂查询"
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
    //复杂查询因素下拉
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
    // 复杂查询日期参数
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
    // 复杂查询的参数
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
    // input是否禁用
    disabled_states: [
      [true, ""],
      [true, ""],
      [true, ""],
      [true, ""],
      [true, ""],
      [true, ""],
      [true, ""],
      [true, ""]
    ],
    // 页面参数
    commonFactor: "",
    maxMinMean: [
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
    factorUnit: ['℃', '%', 'm/s', '', 'mm', 'lx', '%', '%'],
    time: [],
    // 复杂查询数据
    factors_json: [],
    //提交后台数据
    submit_data: {},
    //后台返回data
    data: [],
    //input规则
    inputArray: [],

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
      //复杂
      startText: "YYYY-MM-DD",
      endText: "YYYY-MM-DD",
      factors_json: [],
      submit_data: {},
      factorsArray: [],
      checkFactors: [],
      //多选状态
      items: items,
      // input内容
      disabled_states: disabled_states
    })
    if (e.detail.id === 0) { // 普通
      this.setData({
        commonPopup: true,
      })
      console.log("点击:", e.detail.text)
    } else if (e.detail.id === 1) {//复杂
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
    var xData = []
    var yData = []
    var text = factorsArray[factorId]['text'] + submit_data['start_date'] + " ~ " + submit_data['end_date']
    var unit
    var index
    // console.log(data)
    for (var i = 0; i < data.length; i++) {//x轴数据
      xData[i] = i + 1
      if (factor === "温度") {
        unit = "℃"
        index = data[i][1].lastIndexOf("℃")
        yData[i] = parseInt(data[i][1].substring(0, index))
      } else if (factor === "湿度") {
        unit = "%"
        index = data[i][2].lastIndexOf("%")
        yData[i] = parseInt(data[i][2].substring(0, index))
      } else if (factor === "风速") {
        unit = "m/s"
        index = data[i][3].lastIndexOf("m/s")
        yData[i] = parseInt(data[i][3].substring(0, index))
      } else if (factor === "风向") {
        // unit = ""
        // index = data[i][1].lastIndexOf("")
        // yData[i] = parseInt(data[i][4].substring(0, index))
      } else if (factor === "降雨量") {
        unit = "mm"
        index = data[i][5].lastIndexOf(unit)
        yData[i] = parseInt(data[i][5].substring(0, index))
      } else if (factor === "光照") {
        unit = "lx"
        index = data[i][6].lastIndexOf("lx")
        yData[i] = parseInt(data[i][6].substring(0, index))
      } else if (factor === "PM2.5") {
        unit = "%"
        index = data[i][7].lastIndexOf("%")
        yData[i] = parseInt(data[i][7].substring(0, index))
      } else if (factor === "PM10") {
        unit = "%"
        index = data[i][8].lastIndexOf("%")
        yData[i] = parseInt(data[i][8].substring(0, index))
      }
    }
    //更新视图
    this.setData({

    })
    // 获取图表组件
    this.lazyComponent = this.selectComponent('#echart-per')
    // 图表参数
    console.log("图表：", xData, yData, unit, text)
    setTimeout(() => this.init('line', xData, yData, unit, text), 500)
  },
  // 普通查询时间下拉
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
  // 普通查询picker-data选择内容
  bindDateChange(e) {
    // console.log("picker选择：", e)
    this.setData({
      showText: e.detail.value
    })
  },
  bindPickerModelChange(e) {
    // console.log("picker选择：", e.detail.value)
    if (e.detail.value.length < 3 && e.detail.value.length > 0) {
      this.setData({
        showText1: e.detail.value + ":00"
      })
    } else {
      this.setData({
        showText1: e.detail.value
      })
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
    this.setData({
      endText: e.detail.value
    })
  },


  // 监听单选因素内容
  EventHandle(e) {
    this.setData({
      selectChecked: e.detail.value
    })
    // console.log(e.detail.value)
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
    var submit_data = this.data.submit_data//提交后台参数
    // console.log("点击确认按钮气象编号：", station)
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
        getStatHour(submit_data).then(res => {
          if (res.success === 1) {
            // console.log("请求的因素：", items[selectChecked - 1].value)
            var data = res.data
            var factorIcon = items[selectChecked - 1].icon
            var commonFactor = commonFactorArray[selectChecked - 1]
            var unit
            var factorArray = []
            var summary = []
            var summaryScope = []
            var xData = []//x轴数据
            var yData = []//y轴数据
            var text = items[selectChecked - 1].value + "-" + submit_data.date + " " + submit_data.hour + ":00"//图表标题
            console.log(items[selectChecked - 1].value)
            if (items[selectChecked - 1].value === "温度") {
              //单位
              unit = "℃"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大湿度"
              factorArray[1] = "最小湿度"
              factorArray[2] = "平均湿度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)

            } else if (items[selectChecked - 1].value === "湿度") {
              //单位
              unit = "%"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大湿度"
              factorArray[1] = "最小湿度"
              factorArray[2] = "平均湿度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "风速") {
              //单位
              unit = "m/s"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最高风速"
              factorArray[1] = "最低风速"
              factorArray[2] = "平均风速"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 3
              summaryScope[1] = 4
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "风向") {
              //单位
              unit = ""
              //min、max、mean的文字、范围
              factorArray[0] = ""
              factorArray[1] = ""
              factorArray[2] = ""
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] =
                summaryScope[1] =
                this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScopexData, yData, text)
            } else if (items[selectChecked - 1].value === "降雨量") {
              //单位
              unit = "mm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大降雨量"
              factorArray[1] = "最小降雨量"
              factorArray[2] = "平均降雨量"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "光照") {
              //单位
              unit = "lx"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最强光照"
              factorArray[1] = "最弱光照"
              factorArray[2] = "平均光照"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "PM2.5") {
              //单位
              unit = "μm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大浓度"
              factorArray[1] = "最小浓度"
              factorArray[2] = "平均浓度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "PM10") {
              //单位
              unit = "μm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大浓度"
              factorArray[1] = "最小浓度"
              factorArray[2] = "平均浓度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            }
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
          if (res.success === 1) {
            console.log("请求的因素：", items[selectChecked - 1].value)
            var data = res.data
            var factorIcon = items[selectChecked - 1].icon
            var commonFactor = commonFactorArray[selectChecked - 1]
            var unit
            var factorArray = []
            var summary = []
            var summaryScope = []
            var xData = []//x轴数据
            var yData = []//y轴数据
            var text = items[selectChecked - 1].value + "-" + submit_data.date//图表标题
            if (items[selectChecked - 1].value === "温度") {
              //单位
              unit = "℃"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最高温度"
              factorArray[1] = "最低温度"
              factorArray[2] = "平均温度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 5
              summaryScope[1] = 10
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "湿度") {
              //单位
              unit = "%"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大湿度"
              factorArray[1] = "最小湿度"
              factorArray[2] = "平均湿度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "风速") {
              //单位
              unit = "m/s"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最高风速"
              factorArray[1] = "最低风速"
              factorArray[2] = "平均风速"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 3
              summaryScope[1] = 4
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "风向") {
              //单位
              unit = ""
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = ""
              factorArray[1] = ""
              factorArray[2] = ""
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] =
                summaryScope[1] =
                this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "降雨量") {
              //单位
              unit = "mm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大降雨量"
              factorArray[1] = "最小降雨量"
              factorArray[2] = "平均降雨量"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "光照") {
              //单位
              unit = "lx"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最强光照"
              factorArray[1] = "最弱光照"
              factorArray[2] = "平均光照"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "PM2.5") {
              //单位
              unit = "μm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大浓度"
              factorArray[1] = "最小浓度"
              factorArray[2] = "平均浓度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "PM10") {
              //单位
              unit = "μm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大浓度"
              factorArray[1] = "最小浓度"
              factorArray[2] = "平均浓度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            }
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
          if (res.success === 1) {
            console.log("请求的因素：", items[selectChecked - 1].value)
            var data = res.data
            var factorIcon = items[selectChecked - 1].icon
            var commonFactor = commonFactorArray[selectChecked - 1]
            var unit
            var factorArray = []
            var summary = []
            var summaryScope = []
            var xData = []//x轴数据
            var yData = []//y轴数据
            var text = items[selectChecked - 1].value + "-" + submit_data.start_date + " - " + submit_data.end_date//图表标题
            if (items[selectChecked - 1].value === "温度") {
              //单位
              unit = "℃"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最高温度"
              factorArray[1] = "最低温度"
              factorArray[2] = "平均温度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 5
              summaryScope[1] = 10
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "湿度") {
              //单位
              unit = "%"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大湿度"
              factorArray[1] = "最小湿度"
              factorArray[2] = "平均湿度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "风速") {
              //单位
              unit = "m/s"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最高风速"
              factorArray[1] = "最低风速"
              factorArray[2] = "平均风速"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 3
              summaryScope[1] = 4
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "风向") {
              //单位
              unit = ""
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = ""
              factorArray[1] = ""
              factorArray[2] = ""
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] =
                summaryScope[1] =
                this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "降雨量") {
              //单位
              unit = "mm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大降雨量"
              factorArray[1] = "最小降雨量"
              factorArray[2] = "平均降雨量"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "光照") {
              //单位
              unit = "lx"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最强光照"
              factorArray[1] = "最弱光照"
              factorArray[2] = "平均光照"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "PM2.5") {
              //单位
              unit = "μm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大浓度"
              factorArray[1] = "最小浓度"
              factorArray[2] = "平均浓度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            } else if (items[selectChecked - 1].value === "PM10") {
              //单位
              unit = "μm"
              for (var i = 0; i < data.length; i++) {//图表
                //去除数据单位
                var index = data[i].lastIndexOf(unit)
                data[i] = parseInt(data[i].substring(0, index))
                xData[i] = i
                yData[i] = data[i]
              }
              //min、max、mean的文字、范围
              factorArray[0] = "最大浓度"
              factorArray[1] = "最小浓度"
              factorArray[2] = "平均浓度"
              //总结文字
              summary[0] = "在时间段内,正常波动,最大、最小差值为"
              summary[1] = "在时间段内,变化起伏较小,最大、最小差值为"
              summary[2] = "在时间段内,变化起伏较大,最大、最小差值为"
              //总结评定范围
              summaryScope[0] = 30
              summaryScope[1] = 70
              this.commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text)
            }
          } else {
            // wx.showToast({
            //   title: '请求失败',
            //   icon: 'error',
            // })
          }

        }).catch(err => {
          console.log("接口有误")
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
    // indexOf("x")返回-1，表示x未出现过
    if (station != undefined && startText.indexOf("Y") === -1 && endText.indexOf("Y") === -1 && factors_json.length > 0) {//时间、因素不为空
      //添加时间参数到json对象
      submit_data['station'] = station
      submit_data['start_date'] = startText
      submit_data['end_date'] = endText
      // 将因素数组数据转变为json对象
      for (var i = 0; i < factors_json.length; i++) {
        //向json对象添加属性、属性值
        submit_data[factors_json[i][0]] = factors_json[i][1]
      }
      console.log("发送请求，提交数据", submit_data)
      //发送请求成功
      getQuery(submit_data).then(res => {
        if (res.success === 1) {
          var data = res.data
          var xData = []//x轴
          var yData = []//数据
          var text = "因素"
          var unit = factorUnit[checkFactors[0] - 1]
          var time = this.data.time
          // console.log("选中：", checkFactors, unit, data)
          //页面默认显示第一个数据
          for (var i = 0; i < checkFactors.length; i++) {//下拉框数据
            var obj = {}
            var index = checkFactors[i]
            obj['id'] = i
            obj['text'] = items[checkFactors[i] - 1].value
            obj['value'] = data[0][index]
            obj['icon'] = index
            factorsArray.push(obj)
          }
          for (i = 0; i < data.length; i++) {//x轴数据
            xData[i] = i + 1
            var timeIndex = data[i][0].lastIndexOf("-") + 1
            // console.log(timeIndex,data[i][0].length)
            var timeText = data[i][0].substring(timeIndex, data[i][0].length)
            time[i] = timeText
            if (items[checkFactors[0] - 1].value
              === "温度") {
              //找出对应数据,去除单位放进yData
              let index = data[i][1].lastIndexOf("℃")
              //去除双引号""
              yData[i] = parseInt(data[i][1].substring(0, index));
            } else if (items[checkFactors[0] - 1].value
              === "湿度") {
              let index = data[i][2].lastIndexOf("%")
              yData[i] = parseInt(data[i][1].substring(0, index));
            }
            else if (items[checkFactors[0] - 1].value
              === "风速") {
              let index = data[i][3].lastIndexOf("m/s")
              yData[i] = parseInt(data[i][1].substring(0, index));
            } else if (items[checkFactors[0] - 1].value
              === "风向") {
              // let index = data[i][1].lastIndexOf("℃")
              // yData[i] = parseInt(data[i][1].substring(0, index));
            } else if (items[checkFactors[0] - 1].value
              === "降雨量") {
              let index = data[i][5].lastIndexOf("mm")
              yData[i] = parseInt(data[i][1].substring(0, index));
            } else if (items[checkFactors[0] - 1].value
              === "光照") {
              let index = data[i][6].lastIndexOf("lx")
              yData[i] = parseInt(data[i][1].substring(0, index));
            } else if (items[checkFactors[0] - 1].value
              === "PM2.5") {
              let index = data[i][7].lastIndexOf("%")
              yData[i] = parseInt(data[i][1].substring(0, index));
            } else if (items[checkFactors[0] - 1].value
              === "PM10") {
              let index = data[i][8].lastIndexOf("%")
              yData[i] = parseInt(data[i][1].substring(0, index));
            }
          }
          //图表标题
          text = factorsArray[0]['text'] + submit_data['start_date'] + " ~ " + submit_data['end_date']
          // 获取图表组件
          this.lazyComponent = this.selectComponent('#echart-per')
          // 图表参数
          console.log("图表：", xData, yData, unit, text)
          setTimeout(() => this.init('line', xData, yData, unit, text), 500)
          //图表下面的数据
          var factorText = []
          for (var i = 0; i < factorsArray.length; i++) {
            factorText[i] = data[checkFactors[i] + 1]
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
            time: time
          })
        } else {
          console.log(res.success)
        }
      }).catch(err => {
        // console.log("接口有误")
      })

    } else if (station.length === 0) {
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
  // 高级查询取消按钮
  advancedCancel() {
    console.log("点击取消按钮")
    this.setData({
      advancedPopup: false
    })
  },
  // 高级查询选着日期
  bindtapData(e) {
    var data = this.data.data
    var checkFactors = this.data.checkFactors
    var factorsArray = this.data.factorsArray
    console.log("复杂查询数据：", e.target.id, data, checkFactors, factorsArray)
    for (var i = 0; i < data.length; i++) {
      if (e.target.id, data === i) {
        data[i] = factorsArray[i].value
      } else {
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
        // 锁定input
        ['disabled_states[' + index + '].[0]']: true,
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
        // 给factors赋值
        ['factors[' + startIndex * 2 + '][1]']: "",
        ['factors[' + endIndex + '][1]']: "",
      })
      factors_json.push(this.data.factors[startIndex], this.data.factors[endIndex])
      console.log("factors_json数据：", factors_json)
    }
  },
  // input失去焦点,判断值是否合法，并将值放入数组
  inputBindblur(e) {
    var index = e.target.id.charAt(3)
    var id = e.target.id
    var value = e.detail.value
    console.log("id:", e.target.id, "value:", e.detail.value, "index:", index)
    var startIndex = index * 2
    var endIndex = index * 2 + 1
    var factors_json = this.data.factors_json
    var inputArray = this.data.inputArray
    //赋值
    if (e.target.id == "min" + index) {//min
      var obj = {}
      obj.id = "min" + index
      obj.value = e.detail.value
      inputArray.push(obj)
      for (var i = 0; i < inputArray.length; i++) {
        if (obj.id === inputArray[i]['id']) {
          // inputArray = inputArray.splice(i, 1)
          // inputArray.push(obj)
          // i = i - 1
          console.log(inputArray)
        } else {
          console.log(inputArray)
        }
      }
      this.setData({
        ['factors[' + startIndex + '][1]']: e.detail.value,
      })
      // console.log("变动因素：",factors[startIndex][0],"变动内容：",factors[startIndex][1])
      // console.log("factors_json数组内容：",factors_json)
    } else if (e.target.id == "max" + index) {//max
      var obj = {}
      obj.id = "max" + index
      obj.value = e.detail.value
      for (var i = 0; i < 5; i++) {
        if (obj.id === inputArray[i]['id']) {
          inputArray = inputArray.splice(i, 1)
        }
      }
      inputArray.push(obj)
      this.setData({
        ['factors[' + endIndex + '][1]']: e.detail.value,
      })
      console.log("factors_json数组内容：", factors_json)

    }
    //判断值是否合法
    if (id === "min0") {
      console.log(inputArray)
      if (typeof value === Number) {
        if (value < 40 && value > -20) {
          //添加数据

        } else {
          wx.showToast({
            title: '数值异常',
          })
        }
      } else {
        wx.showToast({
          title: '请输入数字',
          icon: 'none'
        })
      }
    } else if (id === "max0") {
      console.log(inputArray)
      // if()
    }

  },
  // 手动初始化图表
  init(type, xData, yData, unit, text) {
    this.lazyComponent.init((canvas, width, height, dpr) => {
      let lazyChart = echarts.init(canvas, null, {
        width: width,
        height: height,
        devicePixelRatio: dpr
      })
      let lazyOption = getLazyOption(type, xData, yData, unit, text)
      lazyChart.setOption(lazyOption)
      this.lazyChart = lazyChart
      return lazyChart
    })
  },
  // 普通查询对选择因素的数据进行处理展示
  commonSelectFactor(submit_data, data, commonFactor, factorIcon, unit, factorArray, summary, summaryScope, xData, yData, text) {//
    var maxMinMean = this.data.maxMinMean
    var commonFactorText = this.data.commonFactorText
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
    maxMinMean[0]['text'] = factorArray[0]
    maxMinMean[1]['text'] = factorArray[1]
    maxMinMean[2]['text'] = factorArray[2]
    maxMinMean[0]['value'] = max + unit
    maxMinMean[1]['value'] = min + unit
    maxMinMean[2]['value'] = (mean / data.length).toFixed(2) + unit
    if (max - min >= summaryScope[0] && max - min <= summaryScope[1]) {
      commonFactorText = summary[0] + String(max - min)
    } else if (max - min < summaryScope[0]) {
      commonFactorText = summary[1] + String(max - min)
    } else if (max - min > summaryScope[1]) {
      commonFactorText = summary[2] + String(max - min)
    }
    // 获取图表组件
    this.lazyComponent = this.selectComponent('#commonEchart')
    console.log(unit)
    // 模拟请求
    setTimeout(() => this.init('line', xData, yData, unit, text), 500)
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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // console.log("气象编号：", wx.getStorageSync('station').station)
    // 自定义picker类型为小时的内容
    var minuteArray = this.data.minuteArray
    for (var i = 0; i <= 23; i++) {
      minuteArray[i] = i + ":00"
    }
    this.setData({
      minuteArray: minuteArray
    })
    //表单验证
    this.initValidate()
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