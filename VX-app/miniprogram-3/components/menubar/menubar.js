// components/menubar/menubar.js
const app = getApp();
Component({
  properties: {
   
  },
  data: {
    // 菜单是否划出
    show:false,
    // 点击次数
    menuClickNum:0,
    navBarHeight: app.globalData.navBarHeight,
    menuRight: app.globalData.menuRight,
    menuTop: app.globalData.menuTop,
    menuHeight: app.globalData.menuHeight,
  },
  methods: {
    toMenu(){
      console.log("点击菜单",this.data.menuClickNum);
      if(this.data.menuClickNum === 0){
        this.setData({
          show:true,
          menuClickNum:1
        })
      }
      else if(this.data.menuClickNum === 1){
        this.setData({
          show:false,
          menuClickNum:0
        })
      }
    },
    toSearch(){
      console.log("点击跳转查询");
      wx.navigateTo({
        url: '/pages/search/search',
      })
      this.setData({
        show:false,
        menuClickNum:0
      })
    },
    toStatistics(){
      console.log("点击跳转统计");
      wx.navigateTo({
        url: '/pages/statistics/statistics',
      })
      this.setData({
        show:false,
        menuClickNum:0
      })
    },
    toForecast(){
      console.log("点击跳转预测");
      wx.navigateTo({
        url: '/pages/forecast/forecast',
      })
      this.setData({
        show:false,
        menuClickNum:0
      })
    },
    toAboutwe(){
      console.log("点击跳转关于我们");
      wx.navigateTo({
        url: '/pages/about-us/about-us',
      })
      this.setData({
        show:false,
        menuClickNum:0
      })
    }

  },
  lifetimes:{
    attached: function() {
      // 在组件实例进入页面节点树时执行
      console.log(wx.getSystemInfoSync());
    },
    detached: function() {
      // 在组件实例被从页面节点树移除时执行
    },
  }

})