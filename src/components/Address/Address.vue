<template>
  <div style="width: 350px;display: inline-block;">
    <el-select style="width:100px;" v-model="pname" @change="choseProvince" placeholder="省级地区">
      <el-option
        v-for="(item,$index) in province"
        :key="$index"
        :label="item.value"
        :value="item.id"
      ></el-option>
    </el-select>

    <el-select v-model="cname" style="width: 100px;padding-left: 5px;" @change="choseCity" placeholder="市级地区">
      <el-option v-for="(item,$index) in city" :key="$index" :label="item.value" :value="item.id"></el-option>
    </el-select>

    <el-select v-model="bname" style="width: 100px;padding-left: 5px;" @change="choseBlock" placeholder="区级地区">
      <el-option
        v-for="(item,$index) in block"
        :key="$index"
        :label="item.value"
        :value="item.value"
      ></el-option>
    </el-select>
  </div>
</template>

<script>
import ChineseDistricts from "@/assets/js/area";

export default {
  data() {
    return {
      ChineseDistricts: ChineseDistricts,
      province: [],
      shi1: [],
      qu1: [],
      city: [],
      block: [],
      pname: "", //省的名字
      cname: "", //市的名字
      bname: "", //区的名字
      str:{data1:"",data2:"",data3:""}
    };
  },

  methods: {

    // 加载china地点数据，三级
    getCityData() {
      let that = this;
      that.ChineseDistricts.forEach(function(item, index) {

        //省级数据
        that.province.push({
          id: item.code,
          value: item.name,
          children: item.cityList,

        });
      });
    },

    // 选省
    choseProvince(e) {
      let that = this;
      that.city = [];
      that.block = [];
      that.cname = "";
      that.bname = "";
      for (var index2 in that.province) {
        if (e === that.province[index2].id) {
          that.shi1 = that.province[index2].children;
          that.pname = that.province[index2].value;

          that.shi1.forEach(function(citem, cindex) {
            that.city.push({
              id: citem.code,
              value: citem.name,
              children: citem.areaList
            });
          });
        }
      }

      console.log(that.pname);
      this.str.data1=that.pname
      // this.$bus.emit('pname',that.pname)

    },

    // 选市
    choseCity(e) {
      let that = this;
      that.block = [];

      for (var index3 in that.city) {
        if (e === that.city[index3].id) {
          that.qu1 = that.city[index3].children;

          that.cname = that.city[index3].value;

          that.E = that.qu1[0].id;

          that.qu1.forEach(function(bitem, bindex) {
            that.block.push({
              id: bitem.code,
              value: bitem.name,
              children: []
            });
          });
        }
      }
      console.log(that.cname);
      this.str.data2=that.cname;
    },

    // 选区
    choseBlock(e) {
      this.bname = e;
      console.log(this.bname);
      this.str.data3=this.bname;
      this.$bus.emit('name',this.str)
    }
  },
  mounted(){
      this.$bus.on("pname1",(data)=>{
          this.pname=this.cname=this.bname=data
      }),
      this.$bus.on("pname12",(data)=>{
          // var str = JSON.stringify(data);
          // alert(str)
          this.pname=data.fet1;
          this.cname=data.fet2;
          this.bname=data.fet3;
      })
    },
    beforeDestroy(){
          this.$bus.$off('pname1',"pname12")
    },

  created() {
    this.getCityData();
  }
};
</script>
