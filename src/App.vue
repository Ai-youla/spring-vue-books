<template>
  <div>
    <!-- 头部 -->
    <Header/>
    <!-- 主体 -->
    <div style="display: flex;">
        <!-- 侧边栏 -->
        <Aside/>
        <!-- 内容区 -->
        <router-view style="flex: 1"/>
    </div>
  </div>


</template>
<script>

export default {
    name:"App",
}
</script>
