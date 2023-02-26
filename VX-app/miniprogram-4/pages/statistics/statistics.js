// pages/statistics/statistics.js
import * as echarts from '../../components/ec-canvas/echarts';
import {
  getStationsDate,
  getCorrelation
} from "../../api/weatherapi"
import {
  formatTime,
  FactorNumToName,
  // getMaxIndex,
  // getMinIndex
} from "../../utils/util"
import { isEmptyObject } from '../../miniprogram_npm/lin-ui/common/async-validator/util';
const {
  key
} = require('../../api/env').Tokenkey
const {
  station
} = require('../../api/env').StationKey
const app = getApp();

/** ------------------------------- 图表 ----------------------------------- */
function setOption(chart, type, xdata, ydata) {
  const option = {
    xAxis: {
      type: 'category',
      data: xdata
    },
    yAxis: {
      type: 'value'
    },
    series: [{
      data: ydata,
      type: type
    }]
  };
  chart.setOption(option)
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 内容高度
    scrollHeight: app.globalData.contentHeight,
    // 下拉
    select: false,
    // 下拉框的宽
    rightContentWidth: 0,
    rightContentleft: 0,
    rightContentTop: 0,
    // 选择下拉框的内容显示
    rightContent: "因素选择",
    // 图表
    ec: {
      lazyLoad: true
    },
    // 类型选择切换
    typeselect: false,
    // 统计条件弹窗
    popupSelect: false,
    //时间
    startTime: "yyyy-mm-dd",
    endTime: "yyyy-mm-dd",
    // 时间范围
    start: "",
    end: "",
    // 复选框相关配置
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
    // 数据显示
    statistics: [
      //   {
      //   factorname: "温度",
      //   matrixValue: 0.7
      // }
    ],
    matrixValue: [],
    // 选择因素的index
    index: 0,
    indexName: '',
    statisticsInfo: [],
    // 最大（除它本身） 最小
    min: '',
    max: '',
    // 大小排序
    sizeSort:[],
    xiaoyu:"<"
  },



  /* --------------------------------- 弹窗事件 --------------------------- */

  //时间选择器
  bindStartDateChange(e) {
    let starttime = e.detail.value
    // 开始时间后一天
    let tomorrow = new Date(starttime).getTime() + 1000 * 3600 * 24 //时间戳 
    let tomorrowdate = formatTime(new Date(tomorrow)).split(' ')[0] //2022/11/17
    var reg = /(\d{4})\/(\d{2})\/(\d{2})/;
    let time = tomorrowdate.replace(reg, "$1-$2-$3")
    this.setData({
      startTime: e.detail.value,
      start: time
    })
  },
  bindDateChange: function (e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      endTime: e.detail.value
    })
  },

  // 多选框选中
  onChangeTap(e) {
    // console.log("多选框选中数据:", e);
    let index = e.detail.key - 1
    // console.log("index",index);
    // console.log("checkid",this.data.checkid);

    //点击次数统计  
    if (this.data.checkFactors.indexOf(index + 1) > -1) {
      // console.log("点击了两次");
      let getLocation = this.data.checkFactors.indexOf(index + 1);
      this.data.checkFactors.splice(getLocation, 1);
      console.log(this.data.checkFactors);
      const item = 'items[' + index + '].isChecked'
      // 取消勾选
      this.setData({
        [item]: false
      })
    } else {
      this.data.checkFactors.push(index + 1)
      // console.log("多选框选中数据:", index + 1);
      const item = 'items[' + index + '].isChecked'
      // 选中打勾
      this.setData({
        [item]: true
      })
    }
  },


  // 点击确认按钮
  confirm() {
    // 1.封装请求参数
    let total = {}
    total.station = wx.getStorageSync(station).station
    total.start_date = this.data.startTime
    total.end_date = this.data.endTime
    total.correlation = this.data.checkFactors
    console.log(total);
    // 2.判断数据参数是否为空
    const toast = this.selectComponent('#my-toast');
    // console.log(total.correlation.length,typeof(total.correlation.length));
    console.log(total.station === undefined,"返回");
    if (total.station === undefined || total.start_date === "yyyy-mm-dd" || total.end_date === "yyyy-mm-dd" || total.correlation.length === 0) {
      console.log("请求参数为空");
      // 显示 Toast
      toast.linShow({
        icon: "error",
        title: '请求参数为空'
      });
      // 隐藏 Toast
      setTimeout(() => toast.linHide(), 1000)
    }
    // 3.参数不为空
    else {
      //4. 查出选择哪些气象因素
      var statisticsShow = []
      // let statistic = {}
      let factorname = []
      for (let x in total.correlation) {
        console.log("选择那些因素:", FactorNumToName(total.correlation[x]));
        factorname.push(FactorNumToName(total.correlation[x]))
        // statisticsShow[x] = {"factorname":factorname[y]}
      }
      statisticsShow = factorname
      // statisticsShow = factorname.map(f => ({
      //   factorname: f
      // }))
      // console.log(factorname);
      // console.log("statisticsShow", statisticsShow);



      // 6.关闭弹窗
      this.setData({
        popupSelect: false,
        rightContent: statisticsShow[0],
        statistics: statisticsShow,
      })

      // 7.因素下拉框数据
      let right = wx.createSelectorQuery().select('#right');
      right.boundingClientRect(rect => {
        console.log(rect);
        this.setData({
          rightContentWidth: rect.width * 3.5 / 4,
          rightContentleft: rect.left,
          rightContentTop: rect.top * 1.4 / 4
        })
      }).exec()


      // 获取组件
      this.oneComponent = this.selectComponent('#mychart-dom')
      // 发送网络请求(预测)
      getCorrelation(total).then(res => {
        // console.log("预测 相关矩阵", res.data);
        let matrixValue = res.data
        this.setData({
          matrixValue: matrixValue,
          indexName: this.data.statistics[0]
        })
        console.log(matrixValue, "数据");
        // 模拟请求
        setTimeout(()=>{
          this.init('bar', factorname, this.data.matrixValue[0])
        },500)
       
      }).catch()


      setTimeout(() => {
        let info = []
        // info.factorname = this.data.statistics
        // info.matrixValue = this.data.matrixValue
        for (let i in this.data.statistics) {
          info[i] = {
            "factorname": this.data.statistics[i],
            "matrixValue": this.data.matrixValue[i]
          }
        }
        console.log("数据相关性", info);
        this.setData({
          statisticsInfo: info
        })
      }, 500)
    }


    // 判断获取的数据大小
    setTimeout(() => {
      // console.log('当前因素数据:', this.data.statisticsInfo[0]);
      let arr = this.data.statisticsInfo[0].matrixValue
      let changearr = this.data.statisticsInfo[0].matrixValue
      changearr.sort(function (a, b) {
        return a - b
      })
      let sorts = []
      for(let i in changearr){
        console.log(changearr[i]);
        console.log(this.data.statistics[arr.indexOf(changearr[i])]);
        sorts.push(this.data.statistics[arr.indexOf(changearr[i])])
      }
      // sorts = changearr.map(item =>{
      //   console.log(item);
      //   console.log(this.data.statistics[arr.indexOf(item)])
        
      // })
      let min = this.data.statistics[arr.indexOf(changearr[0])]
      let max = this.data.statistics[arr.indexOf(changearr[(changearr.length - 1) - 1])]
      this.setData({
        min: min,
        max: max,
        sizeSort:sorts
      })
    }, 500)
  },



  // 点击取消按钮
  close() {
    this.setData({
      popupSelect: false
    })
  },


  /* ---------------------------------- 主页面事件 ------------------------------ */

  // 点击统计按钮
  onClickStation() {
    console.log("统计");
    this.setData({
      statistics: [],
      matrixValue: [],
      statisticsInfo: [],
      checkFactors: [],
      popupSelect: true,
      // 重置数据
      startTime: "yyyy-mm-dd",
      endTime: "yyyy-mm-dd",
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
      ]
    })
  },
  // 点击下拉事件
  bindShowFactor() {
    this.setData({
      select: !this.data.select
    })
  },

  // 回显下拉选中数据
  mySelect(e) {
    // console.log("下拉",e);
    var name = e.currentTarget.dataset.name
    this.setData({
      rightContent: name,
      select: false
    })
    // 选择下拉框后切换图
    let index = e.currentTarget.dataset.index
    this.setData({
      index: index
    })
    // console.log()
    // 获取组件
    this.oneComponent = this.selectComponent('#mychart-dom')
    // this.init('bar', this.data.statistics, this.data.matrixValue[index])
    if (!this.data.typeselect) {
      this.init('bar', this.data.statistics, this.data.matrixValue[index])
    } else {
      this.init('line', this.data.statistics, this.data.matrixValue[index])
    }

    // 相关性数据显示
    this.setData({
      indexName: this.data.statistics[index]
    })

    // 判断获取的数据大小(从小到大)
    console.log('当前因素数据:', this.data.statisticsInfo[index]);
    let arr = this.data.statisticsInfo[index].matrixValue
    let changearr = this.data.statisticsInfo[index].matrixValue
    changearr.sort(function (a, b) {
      return a - b
    })
    let sorts = []
    for(let i in changearr){
      console.log(changearr[i]);
      console.log(this.data.statistics[arr.indexOf(changearr[i])]);
      sorts.push(this.data.statistics[arr.indexOf(changearr[i])])
    }
    console.log("相关性大小", changearr, (changearr.length - 1) - 1);
    // max min
    console.log("最大值（除它本身）:", changearr[(changearr.length - 1) - 1], this.data.statistics[arr.indexOf(changearr[(changearr.length - 1) - 1])]);
    console.log("最小值:", changearr[0], arr.indexOf(changearr[0])); 
    let min = this.data.statistics[arr.indexOf(changearr[0])]
    let max = this.data.statistics[arr.indexOf(changearr[(changearr.length - 1) - 1])]
    this.setData({
      min: min,
      max: max,
      sizeSort:sorts
    })

  },
  // 图表切换（bar）
  onClickBar() {
    this.setData({
      typeselect: false
    })
    this.oneComponent = this.selectComponent('#mychart-dom')
    this.init('bar', this.data.statistics, this.data.matrixValue[this.data.index])
  },
  // 图表切换（line）
  onClickLine() {
    this.setData({
      typeselect: true
    })
    this.oneComponent = this.selectComponent('#mychart-dom')
    this.init('line', this.data.statistics, this.data.matrixValue[this.data.index])
  },

  /**   -------------------------------生命周期-------------------------------------------- */

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

    let that = this
    // 获取指定气象站的数据日期范围
    let json = {}
    json.id = wx.getStorageSync(key)
    json.station = wx.getStorageSync(station).station
    console.log(json);
    // 获取有效时间
    getStationsDate(json).then(res => {
      console.log(typeof (res.data.date[0]));
      // console.log(typeof(new Date("2022/11/02")));
      let array = res.data.date
      let arrayDate = []
      array.forEach((element, index) => {
        // console.log(element,index);
        arrayDate[index] = new Date(element)
        //  console.log(typeof(arrayDate[index]));
      });
      // 转换格式
      var reg = /(\d{4})\/(\d{2})\/(\d{2})/;
      let Max = new Date(Math.max.apply(null, arrayDate))
      let Maxtime = formatTime(Max).split(" ")[0]
      let Maxtimes = Maxtime.replace(reg, "$1-$2-$3")
      console.log("最大时间:", Maxtimes);
      let Min = new Date(Math.min.apply(null, arrayDate))
      let Mintime = formatTime(Min).split(" ")[0]
      let Mintimes = Mintime.replace(reg, "$1-$2-$3")
      console.log("最小时间:", Mintimes);
      that.setData({
        start: Mintimes,
        end: Maxtimes
      })
    }).catch()

  },
  init(type, xdata, ydata) {
    this.oneComponent.init((canvas, width, height) => {
      const chart = echarts.init(canvas, null, {
        width: width,
        height: height
      });
      setOption(chart, type, xdata, ydata)
      this.chart = chart;
      return chart;
    });
  },
})