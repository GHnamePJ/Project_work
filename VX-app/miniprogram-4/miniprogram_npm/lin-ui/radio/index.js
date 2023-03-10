Component({
  externalClasses: ["l-class", "l-disabled-class"],
  behaviors: ["wx://form-field"],
  relations: {
    "../radio-group/index": {
      type: "parent"
    }
  },
  properties: {
    key: String,
    cell: Object,
    size: {
      type: String,
      value: "38rpx"
    },
    disabled: {
      type: Boolean
    },
    custom: Boolean,
    color: {
      type: String,
      value: "#ccc"
    },
    selectColor: {
      type: String,
      value: "#3963BC"
    },
    disabledColor: {
      type: String,
      value: "#ccc"
    },
    placement: {
      type: String,
      value: "left"
    },
    transition: {
      type: Boolean,
      value: !0
    }
  },
  data: {
    checked: !1
  },
  methods: {
    setChecked(e) {
      this.setData({
        checked: e
      })
    },
    onRadioChangeTap() {
      if (this.properties.disabled) return;
      const e = this.getRelationNodes("../radio-group/index")[0],
        t = e.properties.noneChecked;
      let r = !0;
      if (this.isCurrentSelectedKey(e) && (r = !1, !t)) return;
      const i = !this.data.checked;
      this.data.checked = i;
      const s = {
        checked: i,
        key: this.properties.key,
        cell: this.properties.cell
      };
      e && e.onEmitEventHandle(s, r)
    },
    isCurrentSelectedKey(e) {
      return e.properties.current === this.properties.key
    }
  }
});