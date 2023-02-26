// app.js
//保存token的键的名
const { key } = require("./api/env").Tokenkey;
App({
  onLaunch() {
    const that = this;
    // 获取系统信息
    const systemInfo = wx.getSystemInfoSync();
    // console.log(systemInfo);
    // 胶囊按钮位置信息
    const menuButtonInfo = wx.getMenuButtonBoundingClientRect();
    // 导航栏高度 = 状态栏高度 + 44
    that.globalData.navBarHeight = systemInfo.statusBarHeight + 44;
    that.globalData.menuRight = systemInfo.screenWidth - menuButtonInfo.right;
    that.globalData.menuTop= menuButtonInfo.top;
    that.globalData.menuHeight = menuButtonInfo.height;

    // 获取内容高度
    // that.globalData.contentHeight = systemInfo.windowHeight -  that.globalData.navBarHeight
    that.globalData.contentHeight = systemInfo.windowHeight - systemInfo.statusBarHeight 
    that.globalData.indexcontentHeight = systemInfo.windowHeight - systemInfo.statusBarHeight - 44



    // // 展示本地存储能力
    // const logs = wx.getStorageSync("logs") || [];
    // logs.unshift(Date.now());
    // wx.setStorageSync("logs", logs);
  },

  // 全局
  globalData: {
    userInfo: null,
    navBarHeight: 0, // 导航栏高度
    menuRight: 0, // 胶囊距右方间距（方保持左、右间距一致）
    menuTop: 0, // 胶囊距顶部间距
    menuHeight: 0, // 胶囊高度（自定义内容可与胶囊高度保证一致）
    contentHeight:0,//内容高度,
    indexcontentHeight:0,//首页的内容高度
    changeStation:wx.getStorageSync("station")
  },
  onHide(){
    // 关闭程序 跳转到登录页
    // console.log("点击关闭");
    // wx.removeStorageSync(key);
    // wx.reLaunch({
    //   url: '/pages/login/login',
    // })
  }
});
