// components/head/head.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    // 菜单距离顶部距离
    paddingTopNum: wx.getSystemInfoSync().statusBarHeight + 7,
    //这里为啥要+7，是因为小程序的胶囊按钮距离手机浏览器之间还有7个像素的间距，所以是为了让导航能够和胶囊按钮齐平
    // 菜单距离左侧距离
    paddingLeftNum: "10px"
  },
  /**
   * 组件的方法列表
   */

  methods: {

  },
  lifetimes: {
    attached: function () {
      // 在组件实例进入页面节点树时执行
      wx.setNavigationBarTitle({
        title: "首页"
    })
    },
    detached: function () {
      // 在组件实例被从页面节点树移除时执行
    },
  }

})
