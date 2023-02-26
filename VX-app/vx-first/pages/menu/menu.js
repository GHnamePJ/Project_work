// pages/memu/menu.js
Page({

  data: {
    addres: "卓悦中心ONE AVENU店",
    peisong: "距离您 896m",
    "breakfast": [
      "新品推荐", "招牌热卖", "软欧包", "手作烘焙", "芝士茗优茶", "无糖茶铺", "咖啡", "保温加购", "共同防疫", "企业团餐"
    ]
  },
  waimai() {
    var token = wx.getStorageSync('token')
    console.log(!token)
    if (!token) {
      wx.navigateTo({
        url: '/pages/login/login',
      })
    } else {
      wx.navigateTo({
        url: '/pages/addres/addres',
      })
    }
  },
  ziqu() {
    this.setData({
      addres: "卓悦中心ONE AVENU店",
    })
  },
  onLoad(options) {
    var addres = wx.getStorageSync('addres')
    // print(addres)
    if (!addres) {
      this.setData({
        addres: "卓悦中心ONE AVENU店",
      })
    } else {
      this.setData({
        addres: addres,
      })
    }
  },
})