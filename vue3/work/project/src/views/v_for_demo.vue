<!-- v-for渲染 -->
<template>
  <div id="app">
    <div class="post" v-for="(item, index) in posts" :key="index">
      <h1 v-if="item.isStar === true" style="color: pink">{{ item.title }}</h1>
      <h1 v-if="item.isStar === false">{{ item.title }}</h1>
      <div>{{ item.datel }}</div>
      <br />
      <button class="star" v-if="item.isStar === false" @click="addStar(item)">
        收藏
      </button>
      <button class="star" v-if="item.isStar === true" @click="addStar(item)">
        取消收藏
      </button>
    </div>
  </div>
</template>
<script>
import { reactive, toRefs } from "vue";
export default {
  name: "v_for_demo",
  setup() {
    let data = reactive({
      posts: [
        { title: "HTML", datel: "Something about HTML", isStar: false },
        {
          title: "JavaScript",
          datel: "Something about JavaScript",
          isStar: true,
        },
        { title: "Css", datel: "Something about Css", isStar: false },
      ],
    });
    let addStar = (item) => {
      console.log(item.isStar);
      if (item.isStar === false) {
        item.isStar = true;
      } else {
        item.isStar = false;
      }
    };
    return {
      ...toRefs(data),
      addStar,
    };
  },
};
</script>
<style>
.post {
  width: 300px;
  height: 180px;
  margin: auto;
  text-align: center;
  margin-bottom: 20px;
  border: 1px solid black;
  border-radius: 10px;
}
.star {
  width: 100px;
}
</style>