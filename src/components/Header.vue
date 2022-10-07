<template>
    <div style="height: 50px; line-height: 50px; border-bottom:1px solid #ccc; display: flex;">
        <div style="width: 200px;padding-left: 30px;font-weight: bold;color: pink;">后台管理</div>
        <div style="flex: 1;">t</div>
        <div style="width: 100px;padding-top: 8px;">
            <el-dropdown>
                <el-button type="primary">
                    {{username}}<el-icon class="el-icon--right"><arrow-down /></el-icon>
                </el-button>
                <template #dropdown>
                    <el-dropdown-menu>
                    <el-dropdown-item @click='person'>个人信息</el-dropdown-item>
                    <el-dropdown-item>修改头像</el-dropdown-item>
                    <el-dropdown-item @click='logout'>退出</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script>

export default{
    name:"Header",
    data() {
        return {
            username:"心海"
        };
    },
    methods:{
        logout(){
            this.$router.push("/login")
        },
        person(){
            this.$router.push("/person")
        }
    },
    mounted(){
        let content = JSON.parse(sessionStorage.getItem("user"))
        if(!content){
            this.$router.push("/login")
        }else{
            this.username=content.username;
        }

    },
}
</script>

<style >

</style>