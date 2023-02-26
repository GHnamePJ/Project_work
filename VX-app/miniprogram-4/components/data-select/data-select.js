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
    imgsrc:"data:image/png;base64,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"
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
