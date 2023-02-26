// pages/vip/vip.js
Page({
  data: {
    name:"微信用户",
    url:"/static/image/vip/Photo.jpg"
  },
  photo(){
    var userInfo = wx.getStorageSync("token")
    var name = userInfo.nickName
    var url = userInfo.avatarUrl
    console.log("点击头像")
    this.setData({
      name:name,
      url:url
    })
  }
  
})