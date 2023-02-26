// pages/mine/mine.js
// import { hasToken } from '../../utils/token'
const { key } = require('../../api/env.js').Tokenkey

Page({
  //页面的初始数据
  data: {
    photo: "https://profile.csdnimg.cn/9/F/0/1_weixin_61396453"
  },
  // 关于我们
  about() {
    console.log("关于")
    wx.navigateTo({
      url: "/pages/about-us/about-us"
    })
  },
  // 退出登录
  exit() {
    console.log("退出登录")
    wx.removeStorageSync(key);
    wx.removeStorage({
      key: 'photo',
    })
    wx.reLaunch({
      url: '/pages/login/login',
    })
  },
  
  //生命周期函数--监听页面加载
  onLoad(options) {
  },
  //生命周期函数--监听页面初次渲染完成
  onReady() {
  },
  //生命周期函数--监听页面显示
  onShow() {
  },
  // 生命周期函数--监听页面隐藏
  onHide() {
  },
  // 生命周期函数--监听页面卸载
  onUnload() {
  },
  //页面相关事件处理函数--监听用户下拉动作
  onPullDownRefresh() {
  },
  //页面上拉触底事件的处理函数
  onReachBottom() {
  },
  //用户点击右上角分享
  onShareAppMessage() {
  }
})