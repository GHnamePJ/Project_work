<template>
  <div id="content">
    <p>{{get_content}}</p>
  </div>
</template>

<script>
export default {
  name: 'Content',
  // 接收父组件传递的参数
  props:{
      get_content:String,
  }
};
</script>

<style>
    #content{
        width: 100%;
        height: 600px;
        margin-top: 20px;
        background: rgb(224, 237, 177);
    }
    #content>p{
        text-align: center;
    }
</style>