<template>
  <div style="padding: 10px;">
    <!-- 功能区域 -->
    <div style="margin: 10px 0;">
      <el-button type="primary" @click='add'>添加</el-button>
      <el-button type="danger">导入</el-button>
      <el-button type="success">导出</el-button>

    </div>
    <!-- 搜索区域 -->
    <div style="margin: 10px 0;">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%;" clearable/>
      <el-button type="info" style="margin-left: 5px;" @click='load'>查询</el-button>
    </div>

    <el-table :data="tableData"
        border
        stripe
        style="width: 100%">
    <!-- <el-table-column prop="id" label="用户ID"/> -->
    <el-table-column prop="username" label="姓名"/>
    <el-table-column prop="password" label="密码"/>
    <el-table-column prop="nickName" label="昵称"/>
    <el-table-column prop="age" label="年龄"/>
    <el-table-column prop="sex" label="性别"/>
    <el-table-column prop="address" label="地址"/>

    <el-table-column fixed="right" label="操作" width="120">
      <template #default="scoped">
        <el-button link type="primary" size="small" @click="handleEdit(scoped.row)">
          <el-icon color="blue"><Edit /></el-icon>
        </el-button>
        <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scoped.row.id)">
          <template #reference>
            <el-button link type="primary" size="small">
              <el-icon color="red"><Delete /></el-icon>
            </el-button>
          </template>
        </el-popconfirm>

      </template>
    </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div style="margin: 10px 0;">
      <div class="demo-pagination-block">
        <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20, 50]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
      <!-- 添加弹窗 -->
      <el-dialog v-model="dialogVisible" style="text-align: center;display:block;" title="添加用户" width="30%" >
        <!-- 表单 -->
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%;"/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" style="width: 80%;"/>
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

          <!-- <h1  >{{info}}</h1> -->
        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false" >取消</el-button>
            <el-button type="primary" @click="save">确认</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import Address from '@/components/Address/Address';

  export default{
    name:"User",
    data() {
      return {
        data1:"",
        data2:"",
        data3:"",


        form:{},

        //查询
        search:"",

        //分页
        total:2,//总条数
        currentPage:1,
        pageSize:10,

        //跳出添加
        dialogVisible:false,

        tableData :[]
      };
    },
    components:{
      Address
    },
    methods:{
      // 查询
      load(){
        let params={
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
        this.$http({
            url:"/user/findPage",
            method:"post",
            data:this.form,
            params:params,
        }).then(({data})=>{
          // var str = JSON.stringify(data);
          // alert(str);
          this.tableData=data.data.records
          this.total=data.data.total
        },
        error=>{
          alert("查询失败！！！")
        })
      },
      add(){
        this.dialogVisible=true
        // 清空表单

          this.form={}
          this.form.address="";
          let pname11="";
          this.$bus.emit('pname1',pname11)

      },
      save(){
        if(this.form.id){
          this.$http({
            url:"/user/userUpata",
            method:"post",
            data:this.form
        }).then(({data})=>{
          if(data&&data.code===0){
            this.dialogVisible=false;
            this.$message({
            type:"success",
            message:"更新成功！！！"
          }),
            this.load()
          }else{
            this.$message({
            type:"success",
            message:"更新失败！！！"
          })}})}else{
          this.$http({
            url:"/user/userAdd",
            method:"post",
            data:this.form
        }).then(({data})=>{
          // var str = JSON.stringify(data);
          // alert(str);
          this.dialogVisible=false
          this.$message({
            type:"success",
            message:"添加成功！！！"
          }),
          this.load()

        },
        error=>{
          this.$message({
            type:"error",
            message:"添加失败！！！"
          })
        })
        }

      },

      handleEdit(row){
        this.form=JSON.parse(JSON.stringify(row));
        if(this.form.address!==null){
          let fet={
          fet1:this.form.address.substring(0,3),
          fet2:this.form.address.substring(4,7),
          fet3:this.form.address.substring(8,11),
        };
        this.$bus.emit('pname12',fet);
        }

        this.dialogVisible=true;
      },
      handleDelete(id){
        this.$http({
            url:"/user/userDelete",
            method:"post",
            params:{id:id}
        }).then(({data})=>{
          this.$message({
            type:"success",

          })
            this.load()
        },
        error=>{
          alert("删除失败！！！")
        })
      },
      handleSizeChange(){
        //改变当前每页的个数触发
        this.load();
      },
      handleCurrentChange(){
        //改变当前页面触发
        this.load();
      },
    },
    //页面加载时调用
    created(){
      this.load()
    },
    mounted(){
      this.$bus.on("name",(data)=>{
        this.form.address=data.data1+"-"+data.data2+"-"+data.data3;
          console.log(this.form.address+"老婆已到达!!!");
      })

    },
    beforeDestroy(){
          this.$bus.$off("name")
    },
  }

</script>
<style scoped>

</style>