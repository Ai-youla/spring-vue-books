<template>
  <div>
    <el-menu
      style="width: 200px; min-height: calc(100vh - 50px);"
      default-active="path"
      router
      class="el-menu-vertical-demo">
      <el-sub-menu index="1">
            <template #title>
            <el-icon><location /></el-icon>
            <span>系统管理</span>
            </template>
            <el-menu-item-group >
            <el-menu-item index="/user">用户管理</el-menu-item>
            </el-menu-item-group>
      </el-sub-menu>
      <el-menu-item index="/books">
        <el-icon><Menu /></el-icon>
        <span>书籍管理</span>
      </el-menu-item>
      <el-menu-item index="/news">
        <el-icon><Briefcase /></el-icon>
        <span>新闻管理</span>
      </el-menu-item>
      <el-menu-item index="3">
        <el-icon><setting /></el-icon>
        <span>设置</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default{
  name:"Aside",
  data() {
    return {
      path:"",
    };
  },
  created(){
    this.path=this.$route.path
  }
}
</script>

<style>

</style>