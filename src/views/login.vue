<template>
  <div style="width: 100%;height: 100vh;background-color: aqua;overflow: hidden;">
    <div style="width: 400px;margin: 150px auto;">
        <div style="color: pink;font-size: 40px;font-weight: 900;text-align: center;">欢迎登陆</div>
        <el-form ref="form" :model="form" size="normal" style="margin-top: 30px;">
            <el-form-item>
                <el-input v-model="form.username" clearable prefix-icon="User"><el-icon><User /></el-icon></el-input>
            </el-form-item>
            <el-form-item>
                <el-input v-model="form.password" clearable show-password prefix-icon="Lock"> </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="success" style="width: 100%;" @click='login'>登录</el-button>
            </el-form-item>

        </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name:"Login",
  components: {},
  props: {},
  data() {
    return {
        form:{}
    };
  },
  created(){
      sessionStorage.removeItem("user")
  },
  methods:{
    login(){
        this.$http({
            url:"/user/userLogin",
            method:"post",
            data:this.form
            // params:params
        }).then(({data})=>{
          if(data&&data.code===0){
            // this.$bus.emit('username',data.data.username),
            // this.$bus.emit('user',data.data),
            this.$message({
            type:"success",
            message:"登录成功！！！"
          }),
          sessionStorage.setItem("user",JSON.stringify(data.data))
          this.$router.push("/")
          }else{
            this.$message({
            type:"error",
            message:data.msg
          })
          }
        },)
    }
  }

};
</script>
