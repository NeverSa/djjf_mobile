<template>
  <div class="page_productdetail">
      <div class="top">产品详情</div> 
      <div class="top_nav">
        <div class="product_name">{{detail.name}}</div>
      </div>
      <div>
        <div class="red_bg"></div>
       <div class="pro_detail">
          <div class="t_1"><span class="pre">{{((detail.rasingRate+detail.rate)/10).toFixed(1)}}<span class="small">%</span></span> <span class="tag">{{detail.typeName}}</span></div>
          <div class="t_2">预期年化收益</div>
          <div class="t_3">
            <div><span class="st" v-if="detail.stagingUnit=='day'">{{detail.staging}}天</span><span class="st" v-if="detail.stagingUnit=='month'">{{detail.staging}}月</span><span class="second">收益期限</span></div>
             <div><span class="st">{{(detail.amount)/100 |NumFormat}}</span><span class="second">总额(元)</span></div>
              <div><span class="st"> 100</span><span class="second">起投金额(元)</span></div>
          </div>
       </div>
       <div class="pro_des">
         <div class="title">产品介绍</div>
         <div class="item">剩余额度：<span style="color:#fe4c58">{{(detail.amount-detail.nowSum)/100}}元</span></div>
         <div class="item">计息方式：<span>{{detail.interestType}}</span></div>
         <div class="item">还款时间：<span>{{detail.repayTime}}</span></div>
         <div class="item">还款方式：<span>{{detail.repayType}}</span></div>
       </div>

      <div class="project">
        <div class="item" @click="jumpprojectdetail()"><span>项目详情</span> <div class="jump"></div></div>
        <div class="item"  @click="jumpprojectimg()"><span>相关资料</span> <div class="jump"></div></div>
        <div class="item"  @click="jumpinvestlist()"><span>投资记录</span> <div class="jump"></div></div>
      </div>
     <div class="empt_d"></div>
      <div class="invest">
        <img src="../public/imgs/investment/calculator.png" alt="" @click="calculate()">
        <div class="btn" @click="buy()" v-if="detail.state==0">立即购买</div>
         <div class="btn btn_cs" v-if="detail.state==1">还款中</div>
      </div>
      </div>
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
 
 
      <mt-popup
        v-model="popupVisible2"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title_t">计算收益</div>
           <div class="des_t">
             <div class="left">
               <img src="../public/imgs/investment/icon_money.png" alt="">
               <input type="text" placeholder="请输入金额（元）" v-model="money">
             </div>
             <div class="right">
                <img src="../public/imgs/investment/icon_data.png" alt="">
               <span v-show="detail.stagingUnit=='day'">{{detail.staging}}天</span>
                <span v-show="detail.stagingUnit=='month'">{{detail.staging*30}}天</span>
             </div>
           </div>
           <div class="shouyi"><div>投资预期收益（元）</div><div class="red">{{amont.toFixed(2)}}</div></div>
           <div class="btn_t" @click="add()">
             计算
           </div>
        </div>
      </mt-popup>

  </div>
</template>
<script>
import { Popup } from 'mint-ui';
export default {
  name: "",
   components: {
  },
  data() {
    return {
      sessionid: localStorage.getItem("sessionid")|| "",
    userId:localStorage.getItem("userId")|| "",
           hasBankCard: localStorage.getItem("hasBankCard")|| "",
       hasTradePassword:localStorage.getItem("hasTradePassword")|| "",
      detail:"",
      popupVisible:false,
      popupVisible1:false,
      popupVisible2:false,
      money:"",
      amont:0.00,
    };
  },
  created() {
    this.init();
   
  },
  computed: {},
  mounted() {
  
  },

  methods: {
    calculate(){
      this.popupVisible2=true;
    },
    gorealname(){
      this.$router.push("realname")
    },
       gosetingpsd(){
        this.$router.push("changetradingpassword_new")
    },
    buy(){
      if(this.sessionid==""){
        this.$router.push("login")
      }else{
       if(this.hasBankCard=="true"){
         if(this.hasTradePassword=="true"){
            this.$router.push({path: '/investmentconform', query: {productid:this.$route.query.productid}})
         }else{
            this.popupVisible1=true;
         }
       }else{
           this.popupVisible=true;
       }
      }

    },
    add(){
     if(this.money!==""){
      if(this.detail.stagingUnit=='day'){
      
        this.amont=((this.detail.rasingRate+this.detail.rate)/100*this.detail.staging*this.money)/365/10
      }else{
         this.amont=((this.detail.rasingRate+this.detail.rate)/100*this.detail.staging*30*this.money)/365/10
      }
     }
    },
    init(){
       this.$http.get(`${this.Interface.NodeServer}/plan/${this.$route.query.productid}`).then(res=>{
        if(res.data.resultCode=="0"){
          this.detail=res.data.resultData;
        }
      })
    },
 
    jumpprojectdetail(){
           this.$router.push({path: '/projectdetail', query: {productid:this.$route.query.productid}})
    },
    jumpprojectimg(){
       this.$router.push({path: '/projectimg', query: {productid:this.$route.query.productid}})
    },
      jumpinvestlist(){
       this.$router.push({path: '/investlist', query: {productid:this.$route.query.productid}})
    }
  }
};
</script>
<style lang="less">

.page_productdetail {
  background: #f4f4f4;
      .btn_cs{background: #999999 !important}
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
  .empt_d{height: 200px;background: #fff;}
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
   .red_bg{
     background: #fff6ec;
     height: 240px;;
   }
   .top_nav{
     height: 220px;;
     background: #b89953;
     .product_name{
       font-size: 32px;
       color: #fff;
       text-align: center;
       padding-top: 30px;
     }
   }
   .pro_detail{
     background: #fff;
     width: 680px;
     padding: 0px 30px;
     box-sizing: border-box;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    top:200px;
     .t_1{
       display: flex;justify-content: space-between;align-items: center;
       .pre{
         font-size: 64px;
         color: #fe4c58;
         font-family: "DinPro";
         .small{font-size: 48px;}
       }
       .tag{font-size: 24px;color: #fff;background: #fe4c58;padding: 3px 50px; border-radius: 20px;}
     }
     .t_2{ color: #fe4c58;margin-top: 5px;}
     .t_3{
       display: flex;
       justify-content: space-between;
       align-items: center;
       text-align: center;
       padding: 30px 50px;
       div{
         display: flex;flex-direction: column;
         .st{font-size: 32px;color: #323333;}
         .second{font-size: 20px;color: #999999;margin-top: 30px}
       }
     }
   }
   .pro_des{
     background: #fff;
     margin-bottom: 15px;
     .title{
       margin: 0px 30px;
       padding: 20px 0px;
       border-bottom: 1PX solid #f2f2f2;
       color: #000;
       font-size: 28px;
     }
     .item{padding: 15px 35px;span{margin-left: 10px;}}
   }
   .project{
     background: #fff;
     .item{
       padding: 25px 35px;border-bottom: 1Px solid #f2f2f2;display: flex;justify-content: space-between;align-items: center;
       span{font-size: 28px;color: #323333;}
         .jump{
          width: 34px;
          height: 54px;
          background: url(../public/imgs/faxian/icon_right_jump.jpg) no-repeat ;
          background-size: cover;
        }
       }
   }
   .invest{position: fixed;
   bottom: 0;
   left: 0;
   right: 0;
   display: flex;
   justify-content:space-between;
   align-items: center;
   padding: 5px;
   img{width: 70px}
   .btn{background: #b89a54;color: #fff;text-align: center;flex: 1;height: 78px;line-height: 78px;margin-left: 10px;font-size: 32px;}
   }
}
</style>
