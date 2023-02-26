// pages/mdata/mdata.js
const app = getApp()
import {
  hasToken
} from '../../utils/token'
import {
  getStations,
  getStatHour,
  getStatDay,
  getStatDayRange
} from "../../api/weatherapi"
import {
  formatTime,
  FactorNumToName,
  FactorNumToEnglish,
  FactorNumToCompany,
  isFuture
} from "../../utils/util"
const {
  key
} = require("../../api/env").Tokenkey
const {
  station
} = require("../../api/env").StationKey

Page({
  data: {
    // 内容高度
    forecastHeight: app.globalData.indexcontentHeight,
    // 切换气象站
    changeStation: "切换气象站",
    // 弹框显示
    isShow: false,
    // 气象站数据赋值
    stationdata: [],
    //  今天日期
    today: "",
    //  当月
    month: "",
    // 每分钟的温度
    templement: "",
    isDisplay:true,
    swiperCurrent: 0, //小时轮播正处在哪个索引位置
    hourList: [], //时间存储列表
    scrollWidth: "", //scroll宽度
    canvasLen: "",
    temp: [20, 21, 26, 30, 35, 15, 38, 16, 14, 20, 20, 21, 26, 30, 35, 15, 38, 16, 14, 20, 14, 19, 20, 16], //温度数据
    data: [], //8个气象数据显示
  },
  /** ----------------------------- 主页面事件 ------------------------ */
  /**点击切换气象站 */
  onChangeStation() {
    this.setData({
      isShow: true
    })
  },

  //可获取日历点击事件
  mydata(e) {
    let data = e.detail.data
    console.log("日历点击的时间", data)
     //  后一天时间
     let tomorrow = new Date(data).getTime() + 1000 * 3600 * 24 //时间戳
     let tomorrowdate = formatTime(new Date(tomorrow)).split(' ')[0] //2022/11/17
     var reg = /(\d{4})\/(\d{2})\/(\d{2})/;
     // 转换格式
     let tomorrowtime = tomorrowdate.replace(reg, "$1-$2-$3")
     console.log("选择日期的下一天",tomorrowtime);
    // 💡 两种情况
    let dataresult = []
    console.log("时间判断:", isFuture(data) ? "true--未来时间" : "flase--过去时间");
    //1. 过去时间
    if (!isFuture(data)) {
      let datatoday = data
      let dateOldjson = {}
      dateOldjson.station = wx.getStorageSync(station).station
      dateOldjson.start_date = datatoday
      dateOldjson.end_date = tomorrowtime
      for (let i = 1; i <= 8; i++) {
        dateOldjson.which = i
        // 发送请求
        /**----------------------------- 后期数据修改 ------------------------------ */
        getStatDayRange(dateOldjson).then(res => {
          // console.log(FactorNumToName(i),res.data[FactorNumToEnglish(i)]);
          let dataObject = {}
          dataObject['name'] = FactorNumToName(i)
          dataObject['value'] = res.data[FactorNumToEnglish(i)]
          dataObject['company'] = FactorNumToCompany(i)
          dataObject['icon'] = i
          dataresult.push(dataObject)
          this.setData({
            data: dataresult
          })
          // console.log("选择了过去时间");
        }).catch()
      }
    }
    // 2.预期时间
    else {
      let datatoday = data
      let dateNewjson = {}
      dateNewjson.station = wx.getStorageSync(station).station
      dateNewjson.start_date = datatoday
      dateNewjson.end_date = tomorrowtime
      for (let i = 1; i <= 8; i++) {
        dateNewjson.which = i
        // 发送请求
        /**----------------------------- 后期数据修改 ------------------------------ */
        getStatDayRange(dateNewjson).then(res => {
          // console.log(FactorNumToName(i),res.data[FactorNumToEnglish(i)]);
          let dataObject = {}
          dataObject['name'] = FactorNumToName(i)
          dataObject['value'] = res.data[FactorNumToEnglish(i)]
          dataObject['company'] = FactorNumToCompany(i)
          dataObject['icon'] = i
          dataresult.push(dataObject)
          this.setData({
            data: dataresult
          })
        }).catch()
      }
    }
  },

  scrollCanvas: function (e) {
    // console.log(e);
    var canvasLen = e.detail.scrollLeft;
    this.setData({
      canvasLen: canvasLen
    })
  },

  /** ---------------------------- 弹窗事件 ------------------------- */
  // 选择气象站
  onClickStation(e) {
    // console.log("点击的index",e.currentTarget.dataset.index);
    let id = e.currentTarget.dataset.index
    // console.log("获取点击的数据",this.data.stationdata[id]);
    let stationData = this.data.stationdata[id]
    // 将选择的气象站数据放入本地存储
    wx.setStorageSync(station, stationData)
    let stationname = wx.getStorageSync(station).name
    // console.log(stationname === undefined);
    if (stationname !== undefined || stationname !== null) {
      this.setData({
        station: stationname,
        isShow: false
      })
    }
  },

  /** ------------------------ 生命周期 ------------------------------------ */
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let that = this
    hasToken();
    /**默认给定气象站 */
    // 1.发送气象站请求
    let data = {
      "id": wx.getStorageSync(key)
    }
    getStations(data).then(res => {
      // 2.获取第一个气象站
      // console.log(res.data.stations[0]);
      // 保存在本地存储里面
      wx.setStorageSync(station, res.data.stations[0])
      // 3.设为默认的气象站
      that.setData({
        changeStation: wx.getStorageSync(station).name,
        stationdata: res.data.stations
      })
    }).catch()
    /** 今日日期 */
    // console.log(formatTime(new Date()).split(" ")[0]);
    let today = formatTime(new Date()).split(" ")[0]
    let month = today.split("/")[0] + "/" + today.split("/")[1]
    that.setData({
      today: today,
      month: month
    })
    // /**每分钟获取的 温度 */ (setTimeout)
    // 参数json
    let hour = formatTime(new Date()).split(" ")[1].split(":")[0]
    // console.log(hour);
    var reg = /(\d{4})\/(\d{2})\/(\d{2})/;
    let datatoday = today.replace(reg, "$1-$2-$3")

    let json = {}
    json.station = wx.getStorageSync(station).station
    json.data = datatoday
    json.hour = hour
    json.which = String(1)
    // 发送请求
    getStatHour(json).then(res => {
      that.setData({
        templement: "20"
      })
    }).catch()
    // console.log(month);

    /** 时间温度 (滑动)*/
    let hours = []
    let datajson = {}
    datajson.station = wx.getStorageSync(station).station
    datajson.date = datatoday
    datajson.which = String(1)
    // console.log(datajson);
    /** 发送网络请求 获取任一天的小时气象信息 (一天)*/
    getStatDay(datajson).then(res => {
      /** -------------------------------- 后期修改（数据格式有误） --------------------------------- */
      console.log("获取一天的温度气象信息", res.data);
      let templements = []
      res.data.map((w) => {
        // console.log("温度",w["temperature"]);
        templements.push(Number(w["temperature"]))
      })
      // console.log(templements);
      that.setData({
        // temp:templements
      })
    }).catch()
    for (let i = 0; i < 24; i++) {
      // console.log(i);
      let hour = i + ":00"
      hours.push(hour)
    }
    // console.log("时间列表",hours);
    that.setData({
      hourList: hours
    })
    // 获取 scroll-view 的总宽度
    wx.createSelectorQuery()
      .select('.hours')
      .boundingClientRect(rect => {
        this.setData({
          scrollWidth: rect.right - rect.left,
        });
      })
      .exec();

    /** 气象因素 获取任意时间段以天为单位的气象数据*/
    // 💡 两种情况
    let dataresult = []
    // 今天日期的数据
    let dateOldjson = {}
    // console.log("今天日期", datatoday);
    //  后一天时间
    let tomorrow = new Date().getTime() + 1000 * 3600 * 24 //时间戳
    let tomorrowdate = formatTime(new Date(tomorrow)).split(' ')[0] //2022/11/17
    // console.log("明天:", tomorrowdate);
    // 转换格式
    let tomorrowtime = tomorrowdate.replace(reg, "$1-$2-$3")
    // console.log(tomorrowtime);
    dateOldjson.station = wx.getStorageSync(station).station
    dateOldjson.start_date = datatoday
    dateOldjson.end_date = tomorrowtime
    for (let i = 1; i <= 8; i++) {
      dateOldjson.which = i
      // 发送请求
      /**----------------------------- 后期数据修改 ------------------------------ */
      getStatDayRange(dateOldjson).then(res => {
        // console.log(FactorNumToName(i),res.data[FactorNumToEnglish(i)]);
        let dataObject = {}
        dataObject['name'] = FactorNumToName(i)
        dataObject['value'] = res.data[FactorNumToEnglish(i)]
        dataObject['company'] = FactorNumToCompany(i)
        dataObject['icon'] = i
        dataresult.push(dataObject)
        that.setData({
          data: dataresult
        })
      }).catch()
    }
    setTimeout(()=>{
      that.setData({
        isDisplay:false
      })
    },200)
  }
})