Page({
  data: {
    src:"",
    name:"",
    num:"",
    money:"",
    quan:[]
  },
  onLoad(options) {
    // 去本地储存获取券包详情
    var product = wx.getStorageSync('product');
    var name = product.text ;
    var src = product.src;
    var num = product.num;
    var money = product.money;
    var quan = product.buyquanbao.card_obj;
    // 加载数据到当前页面
    this.setData({
      src:src,
      name:name,
      num:num,
      quan:quan,
      money:money
    })
  },
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