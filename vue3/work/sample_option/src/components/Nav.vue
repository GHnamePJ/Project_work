<template>
  <ul>
    <li @click="home" :text="info">首页</li>
    <li @click="news" :text="info">新闻</li>
    <li @click="sports" :text="info">体育</li>
  </ul>
</template>

<script>
export default {
  name: "Nav",
  methods: {
    home() {
      //给子组件传递信息
      this.info = "首页";
      this.$emit('get_nav',this.info)
    },
    news() {
      this.info = "新闻";
      this.$emit('get_nav',this.info)
    },
    sports() {
      this.info = "体育";
      this.$emit('get_nav',this.info)
    },
  },
};
</script>

<style scoped>
ul {
  margin: 0%;
  padding: 0%;
  padding-left: 10%;
  list-style: none;
  width: 90%;
  height: 100px;
  background: rgb(197, 238, 197);
}
ul > li {
  float: left;
  line-height: 100px;
  margin-left: 70px;
  /* background: lightblue; */
}
</style>