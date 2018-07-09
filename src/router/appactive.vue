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
        <div class="item" @click="kefu()">
           <div ><img src="../public/imgs/faxian/icon_6.jpg" alt=""><span class="title">联系客服</span></div>
           <div class="jump"></div>
       </div>
            <topbar selected="活动"></topbar> 

          <mt-popup
        v-model="popupVisible"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title">提示</div>
           <div class="des">
             拨打客服电话<br/>400-8088-775
           </div>
           <div class="btn">
             <div class="" @click="popupVisible=false">取消</div>
             <a href="tel:400-8088-775" @click="popupVisible=false">确认</a>
           </div>
        </div>
      </mt-popup>
    </div>
</template>
<script>
import topbar from "../components/topbar.vue"
import {  Toast,Popup } from "mint-ui";
export default {
  name: "appactive",
  data() {
    return {
    popupVisible:false,
    };
  },
  components: {
    topbar
  },
  mounted() {
  },
  methods: {
    kefu(){
      this.popupVisible=true;
    },
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
  .msgbox{
    width: 600px;
    background: #fff;
    border-radius: 15px;
    padding: 20px 0px;
    .title{
      font-size: 36px;
      color: #000;
      text-align: center;
    }
    .title_t{ font-size: 36px;
      color: #000;
      text-align: center;
       border-bottom: 1Px solid #f2f2f2;
       padding-bottom: 20px;
      }
    .des{
      font-size: 24px;
      padding: 30px 0px;
      text-align: center;
      border-bottom: 1Px solid #f2f2f2;
    }
    .shouyi{
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 10px 20px;
      .red{color: #fe4c58}
    }
    .btn_t{
      background: #b89a54;
      color: #fff;
      font-size: 32px;
      text-align: center;
      margin-left: 20px;
      margin-right: 20px;
      padding: 20px 0px;
      margin-top: 10px;
    }
    .des_t{
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 20px 15px;
      border-bottom: 1Px solid #f2f2f2;
      img{width: 32px;height: 32px;;}
      .left{
        padding-right: 30px;
        box-sizing: border-box;
        width: 50%;display: flex;justify-content: space-between;align-items: center;border-right: 1Px solid #f2f2f2;
        input{height: 70px;text-align: right;box-sizing: border-box;width: 220px;}
        }
        .right{flex:1;display: flex;
        padding-left: 20px;
        justify-content: space-between;align-items: center;}
    }
    .btn{
      margin-top: 10px;
      display: flex;justify-content: space-between;align-items: center;
      div,a{width: 50%;color: #000;text-decoration: none;};
      div:first-child{
        border-right: 1Px solid #f2f2f2;
        color: #999;
      }
    }
  }
  .mint-popup{
    border-radius: 15px;
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
