<template>
  <div>
    <el-card style="width: 40%;margin: 10px;">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%;"/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" show-password style="width: 80%;"/>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width: 80%;"/>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%;"/>
          </el-form-item>
          <el-form-item label="性别" style="width: 60%;">
            <el-select v-model="form.sex"  placeholder="请选择性别">
              <el-option label="男" value="男" />
              <el-option label="女" value="女" />
              <el-option label="保密" value="保密" />
            </el-select>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-show="false" v-model="form.address" style="width: 80%;"/>
            <Address></Address>
          </el-form-item>
        </el-form>
        <div style="text-align: center;">
            <el-button type="danger" plain @click='save'>保存</el-button>
        </div>
    </el-card>
  </div>
</template>

<script>
import Address  from '@/components/Address/Address.vue';
export default {
  name:"Person",
  data() {
    return {
        form:{}
    };
  },
  components:{
    Address
  },
  methods:{
    save(){
        this.$http({
            url:"/user/userUpata",
            method:"post",
            data:this.form
        }).then(({data})=>{
          if(data&&data.code===0){
            // this.dialogVisible=false;
            this.$message({
            type:"success",
            message:"更新成功！！！"
          }),
            this.load()
          }else{
            this.$message({
            type:"success",
            message:"更新失败！！！"
          })
        }
      })
    }
  },
  // created(){
  //   this.$bus.on("user",(data)=>{
  //      this.form=data;
  //       //   console.log(data.address+"老婆已到达!!!");
  //     })
  // }
  mounted(){
        let content = JSON.parse(sessionStorage.getItem("user"))
        if(!content){
            this.$router.push("/login")
        }else{
            this.form=content;
            // alert(content.address)
            if(content.address!==null){
                let fet={
                  fet1:content.address.substring(0,3),
                  fet2:content.address.substring(4,7),
                  fet3:content.address.substring(8,11),
                };
                this.$bus.emit('pname12',fet);
            }
        }

    },

};
</script>
