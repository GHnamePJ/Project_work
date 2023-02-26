// pages/lunbotu/lunbotu.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    screenheight:"",
    images:[
      {
        url:"/static/images/1.jpg"
      },
      {
        url:"/static/images/2.jpg"
      },
      {
        url:"/static/images/3.jpg"
      },
      {
        url:"/static/images/4.jpg"
      },
      {
        url:"/static/images/5.jpg"
      },
      {
        url:"/static/images/6.jpg"
      }
    ]
  },
  // 轮播图自适应屏幕
  refreshImg(event){
    //宽
    var imgwidth = event.detail.width
    //高
    var imgheight = event.detail.height
    //屏幕的宽
    var screenwidth = wx.getSystemInfoSync().screenWidth
    //屏幕的高=屏幕宽/（imgwidth/imghieght）
    var screenheight = screenwidth/(imgwidth/imgheight)
    this.setData({
      screenheight:screenheight
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    
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