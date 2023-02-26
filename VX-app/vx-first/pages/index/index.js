Page({
  data:{
    swiperHei:"",
    image:["/static/image/lunbo/1.jpg","/static/image/lunbo/2.jpg","/static/image/lunbo/3.jpg","/static/image/lunbo/4.jpg"],
    obj:[
      {
        id:"1",
        url:"/static/image/lunbo/9.jpg"
      },
      {
        id:"2",
        url:"/static/image/lunbo/9.jpg"
      },
      {
        id:"3",
        url:"/static/image/lunbo/9.jpg"
      },
      {
        id:"4",
        url:"/static/image/lunbo/9.jpg"
      },
    ]
  },
  // 处理轮播图在不同机型下的显示
  // img(event){
  //   console.log(event);
  //   // 原图宽/高 = 不同机型宽/高
  //   var width = event.detail.width;
  //   var height = event.detail.height;
  //   console.log(width,height);
  //   var pmwidth = wx.getSystemInfoSync().screenWidth;
  //   console.log(pmwidth);
  //   var newheight = Math.ceil(pmwidth/(width/height)) + "px";
  //   console.log(newheight);
  //   this.setData({
  //     swiperHei:newheight
  //   })
  // },
  // 外卖
  waimai(){
    var token = wx.getStorageSync('token')
    console.log(!token)
    if(!token){
      wx.navigateTo({
        url: '/pages/login/login',
      })
     
    }else{
      wx.navigateTo({
        url: '/pages/addres/addres',
      })
    }
  },
  // 会员操作
  vip(){
  var token = wx.getStorageSync('token')
  console.log(!token)
    if(!token){
      wx.navigateTo({
        url: '/pages/login/login',
    })
    }else{
      wx.navigateTo({
        url: '/pages/vip/vip',
      })
    }
  },
  // 券包
  quanbao(){
    var token = wx.getStorageSync('token')
    console.log(!token)
    if(!token){
      wx.navigateTo({
        url: '/pages/login/login',
      })
    }else{
      wx.navigateTo({
        url: '/pages/quanbao/quanbao',
      })
    }
  },
  // 买茶送包
  teabar(){
    var token = wx.getStorageSync('token')
    console.log(!token)
    if(!token){
      wx.navigateTo({
        url: '/pages/login/login',
      })
    }else{
      wx.navigateTo({
        url: '/pages/teabar/teabar',
      })
    }
  }
})
