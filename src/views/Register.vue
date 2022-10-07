<template>
  <div style="width: 100%;height: 100vh;background-color: aqua;overflow: hidden;">
    <div style="width: 400px;margin: 150px auto;">
        <div style="color: pink;font-size: 40px;font-weight: 900;text-align: center;">欢迎注册</div>
        <el-form ref="form" :rules="rules" :model="form" size="normal" style="margin-top: 30px;">
            <el-form-item prop="username">
                <el-input v-model="form.username" clearable prefix-icon="User"><el-icon><User /></el-icon></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="form.password" clearable show-password prefix-icon="Lock"> </el-input>
            </el-form-item>
            <el-form-item prop="confirm">
                <el-input v-model="form.confirm" clearable show-password prefix-icon="Lock"> </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="success" style="width: 100%;" @click='register'>注册</el-button>
            </el-form-item>

        </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name:"Register",
  components: {},
  props: {},
  data() {
    return {
        form:{},
        rules:{
          username:[
                { required: true, message: '请输入用户名', trigger: 'blur' },
          ],
          password:[
            { required: true, message: '请输入密码', trigger: 'blur' },
          ],
          confirm:[
            { required: true, message: '请输入确认密码', trigger: 'blur' },
          ],
        }
    };
  },
  methods:{
    register(){
      if(this.form.password!==this.form.confirm){
        this.$message({
            type:"error",
            message:"2次密码输入不一致!!!"
          })
          this.form.confirm=""
          return
      }
      this.$refs["form"].validate((valid)=>{
        if(valid){
          this.$http({
              url:"/user/Register",
              method:"post",
              data:this.form
              // params:params
          }).then(({data})=>{
            if(data&&data.code===0){
              this.$message({
              type:"success",
              message:"注册成功！！！"
            }),
            this.$router.push("/login")
            }else{
              this.$message({
              type:"error",
              message:data.msg
            })
            }
          })
        }
      })


    }
  }

};
</script>
