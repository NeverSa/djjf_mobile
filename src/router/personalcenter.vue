<template>
  <div class="page-my-center">
       <div class="top_banner">
         <div class="top_S">
           <img src="../public/imgs/user/icon_user.png" alt="" class="icon_user">
           <img src="../public/imgs/user/icon_setting.png" alt="" class="icon_setting" @click="GoDetails('seting')">
         </div>
         <div class="ceter">
            <div> 总资产(元)</div> 
            <div class="number">{{((user.repaying+user.money+user.freezingMoney)/100).toFixed(2)||0.00}}</div>
         </div>
         <div class="bottom">
            <div>可用余额<br/>{{(user.money/100).toFixed(2)||0.00}}</div>
            <div>冻结余额<br/>{{(user.freezingMoney/100).toFixed(2)||0.00}}</div>
         </div>
       </div>

       <div class="nav_bar">
         <div @click="gochongzhi('recharge')"><img src="../public/imgs/user/icon_1.png" alt="" class="icon_1"> <br/>充值</div>
         <div @click="gochongzhi('withdrawal')"><img src="../public/imgs/user/icon_2.png" alt="" class="icon_2"><br/>提现</div>
       </div>

       <div class="item" @click="GoDetails('investlist_p')">
         <div> <img class="icon_3" src="../public/imgs/user/icon_3.png"  alt="" /><span class="title">投资记录</span></div>
          <div class="jump"></div>
       </div>
      <div class="item" @click="GoDetails('financialdetails')">
         <div> <img class="icon_4" src="../public/imgs/user/icon_4.png" alt=""><span class="title">资金明细</span></div>
          <div class="jump"></div>
       </div>
         <div class="item nav_top" @click="GoDetails('accumulated')">
         <div> <img class="icon_5" src="../public/imgs/user/icon_5.png" alt=""><span class="title">回款计划</span></div>
          <div class="jump"></div>
       </div>
         <div class="item" @click="GoDetails('mycouponlist')">
         <div> <img class="icon_6" src="../public/imgs/user/icon_6.png" alt=""><span class="title">我的红包</span></div>
          <div class="jump"></div>
       </div>
         <div class="item" @click="GoDetails('myrates')">
         <div> <img class="icon_7" src="../public/imgs/user/icon_7.png" alt=""><span class="title">加息卷</span></div>
          <div class="jump"></div>
       </div>
       <!-- <div class="item">
         <div> <img class="icon_8" src="../public/imgs/user/icon_8.png" alt=""><span class="title">邀请有礼</span></div>
          <div class="jump"></div>
       </div> -->
             <mt-popup
        v-model="popupVisible"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title">提示</div>
           <div class="des">您还没有实名认证，去认证</div>
           <div class="btn">
             <div class="" @click="popupVisible=false">先逛逛</div>
             <div @click="gorealname()">确认</div>
           </div>
        </div>
      </mt-popup>

      <mt-popup
        v-model="popupVisible1"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title">提示</div>
           <div class="des">您还没有设置交易密码，设置</div>
           <div class="btn">
             <div class="" @click="popupVisible1=false">先逛逛</div>
             <div @click="gosetingpsd()">确认</div>
           </div>
        </div>
      </mt-popup>
       <div class="empt"></div>
       <topbar selected="我的"></topbar> 
  </div>
</template>
<script>
import topbar from "../components/topbar.vue";
import { Popup } from 'mint-ui';
export default {
  components: {
    topbar
  },
  name: "",
  data() {
    return {
      sessionid: this.$store.state.sessionid || "",
      userId:this.$store.state.userId || "",
           hasBankCard: this.$store.state.hasBankCard || "",
       hasTradePassword:this.$store.state.hasTradePassword || "",
            popupVisible:false,
      popupVisible1:false,
      user:{money:0,freezingMoney:0,repaying:0},
    };
  },

  created() {
   this.getuser()
    // this.getmoney()
  },
  mounted() {
  
  },
  methods: {
     gosetingpsd(){
        this.$router.push("changetradingpassword_new")
    },
  gorealname(){
    this.$router.push("realname")
  },
   gochongzhi(url){
      if(this.sessionid==""){
        this.$router.push("login");
      }else{
       if(this.hasBankCard=="true"){
         if(this.hasTradePassword=="true"){
            this.$router.push(url)
         }else{
            this.popupVisible1=true;
         }
       }else{
           this.popupVisible=true;
       }
      }
   },
    GoDetails(url){
      if(this.sessionid==""){
           this.$router.push("login")
      }else{
           this.$router.push(url)
      }
    
    },
    getuser(){
      this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/accountInfo?token=${this.sessionid}`).then(res=>{
        if(res.data.resultCode=="0"){
         this.user=res.data.resultData
        }
      })
    },

    getmoney(){
          this.$http.get(`${this.Interface.NodeServer}/notice/${this.$route.query.id}`).then(res=>{
        if(res.data.resultCode=="0"){
          this.title=res.data.resultData[1].title;
          this.time=res.data.resultData[1].updatedTime;
          this.content=res.data.resultData[1].content;
        }
      })
    }
  }
};
</script>
<style lang="less">
html,
body {
  height: 100%;
  background: #f4f4f4;
}
.empt{height: 100px;;}
.page-my-center {
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
      div{width: 50%;color: #000};
      div:first-child{
        border-right: 1Px solid #f2f2f2;
        color: #999;
      }
    }

  }
  .mint-popup{
    border-radius: 15px;
  }
    height: 100%;
  background: #f4f4f4;
  .icon_1{width: 42px;}
   .icon_2{width: 40px;}
    .icon_3{width: 36px;}
   .icon_4{width: 39px;}
    .icon_5{width: 38px;}
   .icon_6{width: 35px;}
    .icon_7{width: 34px;}
   .icon_8{width: 39px;}
     .nav_top{margin-bottom: 15px;}
  .top_banner{
    background: #b99b55;
    padding-bottom: 30px;
    .top_S{
      display: flex;
      justify-content: space-between;
      padding: 20px 15px 50px 15px;
      .icon_user{width: 50px ;height: 50px;}
      .icon_setting{width: 44px ;height: 42px;;}
    }
  
    .ceter{
      font-size: 28px;
      margin: 0 auto;
      text-align: center;
      color: #fff;
      .number{font-weight: 400;
       font-family: "DinPro";
       font-size: 40px;
       margin-top: 30px;
       margin-bottom: 60px;
      }
    }
    .bottom{
      display: flex;
      justify-content: space-between;
      align-content: center;
      padding: 0px 100px;
      text-align: center;
      line-height: 50px;
      color: #fff;
      font-size: 26px;
    }
  }
  .nav_bar{
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 30px 165px;
    font-size: 30px;
    color: #323333;
    text-align: center;
    background: #fff;
    img{padding-bottom: 25px;}
    margin-bottom: 15px;
  }
  .item{
   background: #fff;
   padding: 20px 28px;
   border-bottom: 1Px solid #f2f2f2;
   display: flex;
   justify-content: space-between;
   align-items: center;
   div{display: flex;
    justify-content: center;
    align-items: center;
   }
   .title{font-size: 30px;color: #323333;margin-left: 10px;}
    .jump{
     width: 34px;
     height: 54px;
     background: url(../public/imgs/faxian/icon_right_jump.jpg) no-repeat ;
     background-size: cover;
   }
}
}
</style>
                                           