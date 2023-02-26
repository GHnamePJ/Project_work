// components/select/select.js
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
    //左边下拉框内容高度相应配置
    select: false,
    selectMinute:false,
    selectMinuteHour:false,
    selectHour:false,
    selectDay:false,

    titleLeft: '时间',
    selectLeftTop:"",
    titleRight_minute:"yy-mm-dd",
    titleRight_hour:"yy-mm-dd",
    titleRight_day:"yy-mm-dd / yy-mm-dd",
    titleRight_minute_hour:"h",
    // 显示隐藏
    Mdisplay:"none",
    Hdisplay:"none",
    Ddisplay:"none",

  },

  /**
   * 组件的方法列表
   */
  methods: {
    bindShowMsg() {
      this.setData({
        select: !this.data.select
      })
    },
    bindShowMsgRightMinute(){
      // console.log("点击时间");
      this.setData({
        selectMinute: !this.data.selectMinute
      })
    },
    bindShowMsgRightMinuteHour(){
      this.setData({
        selectMinuteHour: !this.data.selectMinuteHour
      })
    },
    bindShowMsgRightHour(){
      this.setData({
        selectHour: !this.data.selectHour
      })
    },
    bindShowMsgRightDay(){
      this.setData({
        selectDay: !this.data.selectDay
      })
    },

    // 动态赋值
    mySelectMH(e){
      var name = e.currentTarget.dataset.name
      this.setData({
        titleRight_minute_hour: name,
        selectMinuteHour: false
      })
    },
    mySelect(e) {
      var name = e.currentTarget.dataset.name
      this.setData({
        titleLeft: name,
        select: false
      })

      // 右边显示内容
      if (name === "某分钟") {
        this.setData({
          Mdisplay:'',
          Hdisplay:'none',
          Ddisplay:'none'
        })
      }
      else if(name === "某小时"){
        this.setData({
          Mdisplay:'none',
          Hdisplay:'',
          Ddisplay:'none'
        })
      }
      else if(name === "某天"){
        this.setData({
          Mdisplay:'none',
          Hdisplay:'none',
          Ddisplay:''
        })
      }
    }
  },

  lifetimes: {
    attached: function () {
      // 在组件实例进入页面节点树时执行
      let that = this;
      var query = wx.createSelectorQuery();
      query.select('.leftTitle').boundingClientRect(function (rect) {
        console.log(rect);
        that.setData({
          // 获取循环标签的高度
          selectLeftTop: rect.height+"px"
        })
        console.log("当前selectLeftTop的高度:",that.data.selectLeftTop);
      }).exec()
    },
    detached: function () {
      // 在组件实例被从页面节点树移除时执行
    },
  },
})