<template>
  <div class="page_newslist_detail">
    <div class="top">公告</div>
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
     console.log(this.$route.query.id)
    },
  mounted() {
   
  },
  methods: {

    getdetail(){
      this.$http.get(`${this.Interface.NodeServer}/notice/${this.$route.query.id}`).then(res=>{
        if(res.data.resultCode=="0"){
          this.title=res.data.resultData[1].title;
          this.time=res.data.resultData[1].updatedTime;
          this.content=res.data.resultData[1].content;
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
   img{width: 90%}
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
 
   .top{
     color: #e6ca92;
     text-align: center;
     display: flex;
     justify-content: center;
     align-items: center;
     font-size: 30px;
     padding: 30px 0px;
     background: #fff;
     border-bottom: 1Px solid #f2f2f2;
   }

} 
</style>
