<template>
  <ul>
    <li @click="home" :text="info">首页</li>
    <li @click="news" :text="info">新闻</li>
    <li @click="sports" :text="info">体育</li>
  </ul>
</template>

<script>
import {ref} from 'vue'
export default {
  name: "Nav",
  setup(props,context){
    let info = ref("")
    let home = () => {
      //给父组件传递信息
      info = "首页"
      context.emit('get_nav',info)
    }
    let news = () => {
      info = "新闻"
      context.emit('get_nav',info)
    }
    let sports = () => {
      info = "体育"
      context.emit('get_nav',info)
    }
    return{
      home,
      news,
      sports
    }
  }
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