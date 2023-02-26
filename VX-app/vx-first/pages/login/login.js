// pages/login/login.js
Page({
  data: {
    obj:[
      {
        url:"/static/image/login/rhyl.png",
        text:"入会有礼"
      },
      {
        url:"/static/image/login/jfdh.png",
        text:"积分兑换"
      },
      {
        url:"/static/image/login/sjtq.png",
        text:"升级特权"
      },
      {
        url:"/static/image/login/srtq.png",
        text:"生日特权"
      },
      {
        url:"/static/image/login/nxbz.png",
        text:"奈雪宝藏"
      },
    ],
  },
  // 处理登录
    login(){
      wx.getUserProfile({
        desc: '登录',
        // 允许
        success:(result) => {
          console.log("点击允许")
          console.log(result);
          console.log(result.userInfo)
          var token = result.userInfo
          wx.setStorageSync('token',token)
          wx.showToast({
            title: '登录成功',
            mask:true
          }),
          wx.navigateBack({
            delta: 1,
          })
        },
        // 拒绝
        fail:(error) => {
          console.log("点击拒绝")
          wx.showToast({
            title: '登录失败',
            mask:true
          }),
          wx.navigateBack({
            delta: 1,
          })
        },
        // 必定执行
        complete:(both) =>{
          console.log("不管点击什么都会执行该函数",both)
        }
      })
    }
})