// pages/mine/mine.js
const {PostRequest } = require('../../api/request')
Page({
  //页面的初始数据
  data: {
    photo: "https://profile.csdnimg.cn/9/F/0/1_weixin_61396453"
  },
  //修改头像
  photo() {
    console.log("修改头像")
    var that = this
    // 选取图片
    wx.chooseMessageFile({
      count: 1,
      type: 'image',
      success(res) {
        const tempFilePaths = res.tempFiles
        // console.log(tempFilePaths[0].path)
        //将文件转为二进制
        wx.getFileSystemManager().readFile({
          filePath: tempFilePaths[0].path, //要读取的文件的路径 (本地路径)
          encoding: "base64", //默认ArrayBuffer
          success(res) {
            var user = { "photo": "data:image/jpeg;base64," + res.data }
            // 发送请求
            // request('POST','/photo',user).then(res=>{
            PostRequest('/photo', user).then(res => {
              // wx.setStorageSync("photo", "");
              //存放到浏览器储存
              wx.setStorageSync("photo", user.photo);
              that.setData({
                photo: user.photo
              })
              wx.showToast({
                title: '更换头像成功',
                icon: 'none',
                duration: 2000
              })
            }).catch(err => {
              console.log(err)
            })
          }
        })
      }
    })
  },
  // 修改密码
  password() {
    console.log("修改密码")
    wx.navigateTo({
      url: "/pages/change-password/change-password"
    })
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
    wx.reLaunch({
      url: "/pages/index/index"
    })
  },
  //生命周期函数--监听页面加载
  onLoad(options) {
    var that = this
    wx.getStorage({
      key: 'photo',
      success(res) {
        // console.log(res.data)
        that.setData({
          photo: res.data
        })
      }
    })
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