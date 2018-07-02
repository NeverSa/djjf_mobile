<template>
  <div class="page_newslist">
    <div class="top">平台公告</div>
    <div class="news_list" v-for="item in list" @click="godetail(item.id)">
      <div class="title">{{item.title}}</div>
      <div class="time">{{item.updatedTime |newsdata}}</div>
    </div>
    
</div>
</template>
<script>
import topbar from "../components/topbar.vue"
export default {
  name: "",
  data() {
    return {
      list:[],
    };
  },
   components: {
    topbar,
  },
    computed: {
    
    },
    created() {
      this.getnewList();
     
    },
  mounted() {
   
  },
  methods: {
    godetail(id){
       this.$router.push({path: '/newslistdetail', query: {id:id}})
    },
    getnewList(){
      this.$http.get(`${this.Interface.NodeServer}/notice/list?limit=100&page=1`).then(res=>{
        if(res.data.resultCode=="0"){
          this.list=res.data.resultData;
        }
      })
    },

 
  },
   
};
</script>
<style lang="less">

.page_newslist{
  background: #f7f7f7;
  height: 100%;
  .news_list{
    padding: 30px 20px;
    border-bottom: 1Px solid #f2f2f2;
    background:#fff;
    .title{
      color: #000;
      font-size: 30px;
    }
    .time{
      margin-top: 10px;
      color: #999;
      font-size: 26px;
    }
  
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
