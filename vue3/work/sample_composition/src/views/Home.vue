<template>
  <div id="home">
    <Nav @get_nav="nav" />
    <Content :get_content="text" />
  </div>
</template>

<script>
import Nav from "@/components/Nav.vue";
import Content from "@/components/Content.vue";
import {ref} from 'vue'

export default {
   name: "Home",
   components: {
    Nav,
    Content,
    },
  setup(props,context){
    // 响应式文本
    let text = ref("")
    let nav = (response) => {
      console.log("子传父,接收内容:" + response);
      if(response === "首页"){
        text.value = "这是："+response;
        console.log("父传子,传递内容:" + text.value);
      }else if(response === "新闻"){
        text.value = "这是：新闻专栏";
        console.log("父传子,传递内容:" + text.value);
      }else{
        text.value = "这是：体育专栏";
        console.log("父传子,传递内容:" + text.value);
      }
    }
    // 把定义的内容暴露出去
    return{
      nav,
      text
    }
  }
};
</script>

<style scoped>
#home {
  width: 92%;
  height: 100%;
  padding-left: 4%;
  padding-right: 4%;
  background: rgb(202, 227, 244);
}
</style>