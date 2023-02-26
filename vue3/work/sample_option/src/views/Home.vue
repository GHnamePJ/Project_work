<template>
  <div id="home">
    <!-- 子传父在子组件定义函数 -->
    <Nav @get_nav="nav" />
    <!-- 父传子在子组件定义属性 -->
    <Content :get_content="text" />
  </div>
</template>

<script>
import Nav from "@/components/Nav.vue";
import Content from "@/components/Content.vue";

export default {
  name: "Home",
  components: {
    Nav,
    Content,
  },

  methods: {
    // 在子组件上定义函数,父组件接收子组件传递的信息
    nav(response) {
      console.log("子传父,接收这是:" + response);
      if(response === "首页"){
        this.text = "这是："+response;
        console.log("父传子,传递内容:" + this.text);
      }else if(response === "新闻"){
        this.text = "这是：新闻专栏";
        console.log("父传子,传递内容:" + this.text);
      }else{
        this.text = "这是：体育专栏";
        console.log("父传子,传递内容:" + this.text);
      }
    },
  },
  data() {
    return {
      text: "",
    };
  },
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