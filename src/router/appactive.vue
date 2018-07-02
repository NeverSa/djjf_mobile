<template>
    <div id="appactive">
      <header>
        <h4>发现</h4>
      </header>
      <div>
        <img src="../public/imgs/faxian/faxian_banner.jpg" alt="">
      </div>

       <div class="item" @click="GoDetails('newslist')">
           <div ><img src="../public/imgs/faxian/icon_1.jpg" alt=""><span class="title">平台公告</span></div>
           <div class="jump"></div>
       </div>
          <div class="item" @click="GoDetails('medialist')">
           <div ><img src="../public/imgs/faxian/icon_2.jpg" alt=""><span class="title">媒体资讯</span></div>
           <div class="jump"></div>
       </div>
        <div class="item"  @click="GoDetails('question')">
           <div ><img src="../public/imgs/faxian/icon_3.jpg" alt=""><span class="title">常见问题</span></div>
           <div class="jump"></div>
       </div>
        <div class="item" @click="GoDetails('aboutus')">
           <div ><img src="../public/imgs/faxian/icon_5.jpg" alt=""><span class="title">关于我们</span></div>
           <div class="jump"></div>
       </div>
        <!-- <div class="item">
           <div ><img src="../public/imgs/faxian/icon_6.jpg" alt=""><span class="title">联系客服</span></div>
           <div class="jump"></div>
       </div> -->
            <topbar selected="活动"></topbar> 
    </div>
</template>
<script>
import topbar from "../components/topbar.vue"
export default {
  name: "appactive",
  data() {
    return {
    
    };
  },
  components: {
    topbar
  },
  mounted() {
  },
  methods: {
    // 查看活动详情
    GoDetails(url) {
     this.$router.push(url)
    }
  }
};
</script>
<style lang="less" scoped>
.empt{
    height: 100px;;
}
 .item{
   display: flex;
   padding: 30px ;
   border-bottom: 1Px solid #f3f3f3;
   background: #fff;
   justify-content: space-between;
   align-items: center;
   img{width: 59px;}
   .title{color:#000;font-size: 26px; margin-left: 5px;}
   .jump{
     width: 34px;
     height: 54px;
     background: url(../public/imgs/faxian/icon_right_jump.jpg) no-repeat ;
     background-size: cover;
   }
 }
header{
  background: #fff;
  padding: 20px 0px;
  h4{
    color: #ecd58e;
    font-size: 30px;
    text-align: center;
  }

}
</style>
