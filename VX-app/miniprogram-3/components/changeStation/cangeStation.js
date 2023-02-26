// components/changeStation/cangeStation.js
const app = getApp()
import {
  getStations
} from "../../api/weatherapi"
import { isEmptyObject } from "../../miniprogram_npm/lin-ui/common/async-validator/util"
// 键
const {
  key
} = require('../../api/env').Tokenkey
const {
  station
} = require('../../api/env').StationKey
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    // 气象站编号改变
    station: app.globalData.changeStation,
    // 弹框显示
    isShow: false,
    // 气象站数据赋值
    stationdata: []
  },

  /**
   * 组件的方法列表
   */
  methods: {
    /* ----------------------------- 主页面事件 ------------------------------ */
    // 点击弹窗
    onChangeStation() {
      this.setData({
        isShow: true
      })
    },

    /* ---------------------------- 弹窗事件 ------------------------- */
    // 选择气象站
    onClickStation(e) {
      // console.log("点击的index",e.currentTarget.dataset.index);
      let id = e.currentTarget.dataset.index
      // console.log("获取点击的数据",this.data.stationdata[id]);
      let stationData = this.data.stationdata[id]
      // 将选择的气象站数据放入本地存储
      wx.setStorageSync(station, stationData)
      let stationname = wx.getStorageSync(station).name
      // console.log(isEmptyObject(stationname));
      if(!isEmptyObject(stationname)){
        this.setData({
          station: stationname,
          isShow: false
        })
      }
    }
  },



  /** ------------------------ 生命周期 --------------------------- */
  lifetimes: {
    attached: function () {
      // 在组件实例进入页面节点树时执行
      let that = this;
      // 发送网络请求 显示气象站数据
      // 获取本地存储的id
      let token = wx.getStorageSync(key)
      let tokenJson = {
        "id": token
      }
      // console.log(tokenJson);
      getStations(tokenJson).then(res => {
        // console.log("获取气象站数据:",res.data.stations);
        // 赋值
        that.setData({
          stationdata: res.station
        })
      }).catch()

      // 气象站信息展示
      // 判断存储里面是否有station
      let stationdata = wx.getStorageSync(station) 
      // console.log(isEmptyObject(stationdata),"气象站");
      if(!isEmptyObject(stationdata)){//不为空
        that.setData({
          station: stationdata.name,
        })
      }
      else{
        const toast = this.selectComponent('#toast');
          // 显示 Toast
      toast.linShow({
        icon: "error",
        title: '请选择气象站'
      });
      // 隐藏 Toast
      setTimeout(() => toast.linHide(), 2000)
      }
    },
    detached: function () {
      // 在组件实例被从页面节点树移除时执行
    },
  }

})