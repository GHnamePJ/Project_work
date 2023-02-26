// pages/mdata/mdata.js
// import { hasToken } from '../../utils/token'

Page({
  data: {
    tabWidth: "",
    containerWidth: "556px",
  },
  //监听Tab页切换，并根据页面内容改变每个tab页高度
  changeTabs(e) {
    var that = this
    var activeKey = e.detail.activeKey
    var query = wx.createSelectorQuery();
    console.log(e)
    if (activeKey === "one") {
      query.select('#content').boundingClientRect(function (rect) {
        var height = rect.height
        console.log("高：" + height)
        that.setData({
          containerWidth: height + "px"
        })
      }).exec();
    } else if (e.detail.activeKey === "two") {
      query.select('#content1').boundingClientRect(function (rect) {
        var height = rect.height
        console.log("高：" + height)
        that.setData({
          containerWidth: height + "px"
        })
      }).exec();
    } else if (e.detail.activeKey === "three") {
      query.select('#content2').boundingClientRect(function (rect) {
        var height = rect.height
        console.log("高：" + height)
        that.setData({
          containerWidth: height + "px"
        })
      }).exec();
    } else if (e.detail.activeKey === "four") {
      query.select('#content3').boundingClientRect(function (rect) {
        var height = rect.height
        console.log("高：" + height)
        that.setData({
          containerWidth: height + "px"
        })
      }).exec();
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this
    // hasToken();
    console.log("m监听页面加载");
    //屏幕宽度
    var screenwidth = wx.getSystemInfoSync().screenWidth
    // console.log(screenwidth);
    that.setData({
      tabWidth: screenwidth + "px"
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    //初始化页面内容高度
    var that = this
    var query = wx.createSelectorQuery();
    query.select('#query-data').boundingClientRect(function (rect) {
      var height = rect.height
      console.log("查询数据页面初次渲染完成高:" + height)
      that.setData({
        containerWidth: height + 42 + "px"
      })
    }).exec();
    // console.log("m监听页面初次渲染完成");
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    // console.log("m监听页面显示");
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {
    hasToken();
    // console.log("m监听页面隐藏");
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