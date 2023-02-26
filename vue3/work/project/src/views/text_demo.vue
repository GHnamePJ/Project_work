<!-- 文本插值与表达式 -->
<template>
  <div id="app">
    <p>变量：{{ num }}</p>
    <p>表达式：{{ 5 + 10 }}</p>
    <p>{{ 3 < 5 ? '早上好' : '晚上好' }}</p>
  </div>
</template>
<script>
import { ref } from "vue";
export default {
  name: "text_demo",
  setup() {
    const num = ref("2");
    return {
      num,
    };
  },
};
</script>