<template>
  <div class="page_newslist_detail">
    <div class="top">资讯详情</div>
    <div class="title">{{title}}</div>
    <div class="time">{{time | newsdata}}</div>
    <div class="content" v-html="content"></div>
</div>
</template>
<script>
import topbar from "../components/topbar.vue"
export default {
  name: "",
  data() {
    return {
      title:"",
      time:"",
      content:"",
    };
  },
   components: {
    topbar,
  },
    computed: {
    
    },
    created() {
      this.getdetail();
    },
  mounted() {
   
  },
  methods: {
    getdetail(){
      this.$http.get(`${this.Interface.NodeServer}/news/details/${this.$route.query.id}`).then(res=>{
        if(res.data.resultCode=="0"){
          this.title=res.data.resultData.title;
          this.time=res.data.resultData.createdTime;
          this.content=res.data.resultData.content;
        }
      })
    },

 
  },
   
};
</script>
<style lang="less">

.page_newslist_detail{
  background: #fff;
  padding: 0px 20px;
  height: 100%;
      .title{
      color: #000;
      font-size: 30px;
      margin-top: 30px;
    }
    .time{
      margin-top: 10px;
      color: #999;
      font-size: 26px;
    }
  img{width: 90%}


} 
</style>
