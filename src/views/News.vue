<template>
  <div style="padding: 10px;">
    <!-- 功能区域 -->
    <div style="margin: 10px 0;">
      <el-button type="primary" @click='add'>添加</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="danger">导出</el-button>
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
    <el-table-column prop="title" label="标题"/>
    <el-table-column prop="content" label="内容"/>
    <el-table-column prop="author" label="作者"/>
    <el-table-column prop="newTime" label="发布时间"/>
    <el-table-column prop="cover" label="封面">
      <template #default="scope">
        <el-image
          style="width: 100px; height: 100px"
          :src="scope.row.cover"
          :preview-src-list="[scope.row.cover]">
        </el-image>
      </template>

    </el-table-column>
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
      <el-dialog v-model="dialogVisible" style="text-align: center;display:block;" title="添加书籍" width="30%" >
        <!-- 表单 -->
        <el-form :model="form" label-width="120px">
          <el-form-item label="标题">
            <el-input v-model="form.booksname" style="width: 80%;"/>
          </el-form-item>
          <el-form-item label="内容">
            <el-input type="textarea" v-model="form.desc"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="form.author" style="width: 80%;"/>
          </el-form-item>
          <el-form-item label="发布日期">
            <el-date-picker style="width: 80%;cursor: pointer;"
                v-model="form.createTime"
                type="date"
                placeholder="Pick a day"
                :size="size"
              />
          </el-form-item>
          <el-form-item label="封面">
            <el-upload ref="upload" action="http://localhost:8848/file/upload"
                       limit="1"
                       name="file"
                       :on-success="filesUploadSuccess">
                <el-button type="primary">上传封面</el-button>
            </el-upload>
          </el-form-item>
          <!-- <el-form-item  label="添加客户Id">
            <el-input v-model="form.userid" style="width: 80%;"/>
          </el-form-item> -->
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
    name:"News",
    data() {
      return {

        form:{},

        //查询
        search:"",

        //分页
        total:2,//总条数
        currentPage:1,
        pageSize:10,

        //跳出添加
        dialogVisible:false,

        tableData :[],


      };
    },
    components:{
      Address
    },
    methods:{
      filesUploadSuccess(res){

        this.form.cover=res.data;
      },

      // 查询
      load(){
        let params={
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
        this.$http({
            url:"/news/findPage",
            method:"post",
            data:this.form,
            params:params,
        }).then(({data})=>{
          // var str = JSON.stringify(data);
          // alert(str);
          this.$message({
            type:"success",
            message:"查询成功！！！"
          }),
          this.tableData=data.data.records
          this.total=data.data.total
        },
        error=>{
          this.$message({
            type:"error",
            message:"查询失败！！！"
          })
        })
      },
      add(){
        this.dialogVisible=true;
        // 清空表单
        this.form={};
        this.$refs["upload"].clearFiles(); //清空历史上传
        let content = JSON.parse(sessionStorage.getItem("user"));
        this.form.userid=content.id;
      },
      save(){
        if(this.form.id){

          this.$http({
            url:"/news/newsUpdate",
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
          })}})
        }else{
          this.$http({
            url:"/news/newsAdd",
            method:"post",
            data:this.form
        }).then(({data})=>{
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

        this.dialogVisible=true;
        this.$nextTick(()=>{
          this.$refs["upload"].clearFiles();//清空历史上传
        })
      },
      handleDelete(id){
        this.$http({
            url:"/news/newsDelete",
            method:"post",
            params:{id:id}
        }).then(({data})=>{
          if(data&&data.code===0){
              this.$message({
              type:"success",
              message:"删除成功！！！"
              })
                this.load()
          }else{
            this.$message({
              type:"error",
              message:"删除失败！！！"
              })
          }

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
      this.load();
      // let content = JSON.parse(sessionStorage.getItem("user"));
      // this.form.userid=content.id;
    },
  }

</script>
<style scoped>

</style>