<template>
  <div class="page_newslist">
    <div class="top">媒体资讯</div>
     <div class="news_list" v-for="item in newslist" @click="godetail(item.id)">
       <img :src="item.coverImage" alt="" class="left_img" >
       <div class="right_content">
         <div class="title">{{item.title}}</div>
         <div class="des">{{item.subTitle}}</div>
         <div class="time">{{item.updatedTime |newDate}}</div>
       </div>
     </div>
     <div class="noresult">没用更多了</div>
</div>
</template>
<script>

export default {
  name: "",
  data() {
    return {
      newslist:[],
    };
  },
   components: {
  },
    computed: {
    
    },
    created() {
      this.getNewsList();
     
    },
  mounted() {
   
  },
  methods: {
    godetail(id){
       this.$router.push({path: '/medialistdetail', query: {id:id}})
    },
  //获取公告
  getNewsList(){
     this.$http.get(`${this.Interface.NodeServer}/news/list?limit=9&page=1`).then(res=>{
      if(res.data.resultCode=="0"){
        this.newslist=res.data.resultData;
        for(let i=0;i++;i<this.newslist.length){
          this.newslist[i].updatedTime=moment(this.newslist[i].updatedTime).format("YYYY-MM-DD")
        }
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
  display: flex;
  justify-content: space-between;
  padding: 20px 30px;
  border-bottom: 1Px solid #f2f2f2;
  background: #fff;
  .left_img{
    width: 280px;
    height: 200px;
  }
  .right_content{
    flex: 1;
    margin-left: 30px;
    .title{
      font-size: 28px;
      color: #323333;
      font-weight: 600;
      line-height: 40px;
      overflow: hidden;
      height: 70px;
    }
    .des{
      font-size: 24px;
      color: #666666;
      margin-top: 10px;
       line-height: 40px;
        overflow: hidden;
        height: 70px;
    }
    .time{
       margin-top: 10px;
      color: #999999;
      font-size: 24px;
      text-align: right;
    }
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
