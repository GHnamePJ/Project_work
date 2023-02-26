// components/select/select.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    dataArray: {
      type: Array,
    },
    dataText:{
      type:String,
      value:""
    }
  },
  /**
   * 组件的初始数据
   */
  data: {
    selectShow: false, //初始option是否显示下拉框
    dataText: "", //初始option内容
    animationData: {}, //点击动画
    imgsrc:"http://a1.qpic.cn/psc?/V54NOYW73dqLX62u51390hfTtC1B0M27/ruAMsa53pVQWN7FLK88i5oUFSSqWp2dcmsbiSyr0GzK3dNWmg*ukzWjV5ep58qRLwmILMaHRe.Em2exWTBegJFwvMiJuCM9cxHzrkdX03Ds!/b&ek=1&kp=1&pt=0&bo=yADIAAAAAAADFzI!&tl=1&vuin=3095499045&tm=1670245200&dis_t=1670245518&dis_k=4ed89fa526aeaa7127bad64c4c913142&sce=60-1-1&rf=viewer_4"
  },
  /**
   * 组件的方法列表
   */
  methods: {
    //option的显示与否
    selectToggle: function () {
      var nowShow = this.data.selectShow; //获取当前option显示的状态
      //创建动画
      var animation = wx.createAnimation({
        timingFunction: "ease"
      })
      //设置动画
      this.animation = animation;
      //下拉触发动画
      if (nowShow) {
        //从原点顺时针旋转0度(还原)
        animation.rotate(0).step();
        this.setData({
        //导出动画队列
        //export 方法每次调用后会清掉之前的动画操作。
          animationData: animation.export()
        })
      } else {//下拉触发动画
        //从原点顺时针旋转180度
        animation.rotate(180).step();
        this.setData({
          animationData: animation.export()
        })
      }
      this.setData({
        selectShow: !nowShow
      })
    },
    //设置显示内容
    setText: function (e) {
      var nowData = this.properties.dataArray; //当前option的数据是引入组件的页面传过来的，所以这里获取数据只有通过this.properties
      // var nowData = this.data.queryArray;
      var nowIdx = e.target.dataset.index; //当前点击的索引
      // console.log("点击索引：",nowIdx)
      var dataText = nowData[nowIdx].text; //当前点击的内容
      // console.log("点击内容：",nowText)
      //子组件触发事件
      var nowDate = {
        id: nowIdx,
        text: dataText
      }
      // 
      this.triggerEvent('myevent', nowDate)
      //再次执行动画，注意这里一定，一定，一定是this.animation来使用动画
      //选择内容后收回下拉框
      this.animation.rotate(0).step();
      this.setData({
        selectShow: false,
        dataText: dataText,
        animationData: this.animation.export()
      })
    }
  },
  lifetimes: {
    attached: function () {
      this.setData({
        dataText:this.properties.dataText
      })
    },
  }
})
