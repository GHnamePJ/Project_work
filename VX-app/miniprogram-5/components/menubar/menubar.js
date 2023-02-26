// components/menubar/menubar.js
const app = getApp();
Component({
  properties: {
   
  },
  data: {
    // 菜单是否划出
    show:false,
    navBarHeight: app.globalData.navBarHeight,
    menuRight: app.globalData.menuRight,
    menuTop: app.globalData.menuTop,
    menuHeight: app.globalData.menuHeight,
  },
  methods: {
    toMenu(){
        this.setData({
          show:true
        })
    },
    toSearch(){
      console.log("点击跳转查询");
      wx.navigateTo({
        url: '/pages/search/search',
      })
      this.setData({
        show:false
      })
    },
    toStatistics(){
      console.log("点击跳转统计");
      wx.navigateTo({
        url: '/pages/statistics/statistics',
      })
      this.setData({
        show:false
      })
    },
    toForecast(){
      console.log("点击跳转预测");
      wx.navigateTo({
        url: '/pages/forecast/forecast',
      })
      this.setData({
        show:false
      })
    },
    toAboutwe(){
      console.log("点击跳转关于我们");
      wx.navigateTo({
        url: '/pages/about-us/about-us',
      })
      this.setData({
        show:false
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