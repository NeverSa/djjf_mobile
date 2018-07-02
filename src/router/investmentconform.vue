<template>
  <div class="page_invest">
      <div class="top">投资确认</div>
      <div class="back_bg">
        <div class="item yello_b">
          <span>剩余可投</span>
          <span class="money"><span>{{(detail.amount-detail.nowSum)/100}} </span>元</span>
        </div>
          <div class="item ">
          <span>账户余额</span>
        <span class="money"><span>{{user.money}}</span>元</span>
        </div>
      </div>

      <div class="gray_bg">
        <div class="item ">
          <span>输入购买金额</span>
        </div>
      </div>

     <div class="back_bg">
        <div class="item input_item">
         <div class="">购买金额 <input type="text" :placeholder="detail.minAmount/100+'元起投'" v-model="money"></div>
         <div class="btn" @click="all()">全投</div>
        </div>
      </div>

      <div class="gray_bg">
        <div class="item input_item">
          <span>投资区间：<span style="margin-left:0.1rem"><span style="color:#fe4c58">{{detail.minAmount/100}}元</span>起投</span></span>
            <span>每位用户限投：<span style="color:#fe4c58">{{detail.maxAmount/100}}元</span></span>
        </div>
      </div>

       <div class="back_bg">
        <div class="item input_item">
         <div class="">账户余额 </div>
         <div class="red">{{user.money}}元</div>
        </div>
      </div>

       <div class="back_bg" @click="selectpakege()">
        <div class="item input_item">
         <div class="">使用红包 </div>
         <div class="flex_d"><span v-show="defaultamont!==''">{{defaultamont}}元</span><span class="jump"></span></div>
        </div>
      </div>

      <div class="back_bg">
        <div class="item input_item">
         <div class="">使用加息券 </div>
         <div class="flex_d">0.12% <span class="jump"></span></div>
        </div>
      </div>

      
    <div class="submit" @click="submit" >
      确认
    </div>

         <mt-popup
        v-model="popupVisible"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title">提示</div>
           <div class="des">账户余额不足，去充值</div>
           <div class="btn">
             <div class="" @click="popupVisible=false">取消</div>
             <div @click="gorechange()">确认</div>
           </div>
        </div>
      </mt-popup>

      
         <mt-popup
        v-model="popupVisible2"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title">提示</div>
           <div class="des">确认投资</div>
           <div></div>
           <div class="btn">
             <div class="" @click="popupVisible2=false">取消</div>
             <div @click="submitbuy()">确认</div>
           </div>
        </div>
      </mt-popup>
  </div>
</template>
<script>
import {  Toast,Popup } from "mint-ui";
export default {
  name: "",
  data() {
    return {
      detail:"",
        sessionid: this.$store.state.sessionid || "",
        userId:this.$store.state.userId || "",
        user:"",
        money:"",
        defaultamont:"",
        popupVisible:false,
        popupVisible2:false,
    }
  },
  created() {
    this.init()
    this.getwalletBalanceList()
    this.defaultamont=this.$route.query.amount;
    this.money=this.$route.query.money||"";
   this.pocketId=this.$route.query.pocketId||"";
   this.raisingId=this.$route.query.raisingId||"";
  },
  computed: {

  },
  mounted() {

  },

  methods: {
    gorechange(){
       this.$router.push("recharge")
    },
    selectpakege(){
      if(this.money<this.detail.minAmount/100){
        Toast("投资金额不能小于起投金额")
      }else{
         this.$router.push({path: '/selectpackets', query: {productid:this.$route.query.productid,amount:this.money}})
      }
    },
    all(){
      this.money=this.user.money
    },
    submit(){
      if(this.money==""){
        Toast("请输入投资金额")
      }
      else if(this.money>this.user.money){
         this.popupVisible=true;
      }else{
           this.popupVisible2=true;
      }
    },
       init(){
       this.$http.get(`${this.Interface.NodeServer}/plan/${this.$route.query.productid}`).then(res=>{
        if(res.data.resultCode=="0"){
          this.detail=res.data.resultData;
        }
      })
    },
 //确认购买
 submitbuy(){
    this.$http.post(`${this.Interface.NodeServer}/plan/${this.$route.query.productid}/join?userId=${this.userId}&money=${this.money*100-this.defaultamont*100}&pocketId=${this.pocketId}&raisingId=${this.raisingId}&token=${this.sessionid}`).then(res=>{
       if(res.data.resultCode=="0"){
          Toast("购买成功");
          this.$router.push("productlist")
        }else{
           Toast(res.data.resultMsg)
        }
      })
 },

    // 获取账户余额
    getwalletBalanceList() {
      this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/accountInfo?token=${this.sessionid}`).then(res=>{
          if(res.data.resultCode=="0"){
          this.user=res.data.resultData;
        }
      })

    },
     
  



  //    getCode(){
  //    let TIME_COUNT = this.defaultcont;
  //    if (!this.timer) {
  //       // this.recharge()
  //      this.count = TIME_COUNT;
  //      this.show = false;
  //      this.timer = setInterval(() => {
  //      if (this.count > 0 && this.count <= TIME_COUNT) {
  //        this.count--;
  //       } else {
  //        clearInterval(this.timer);
  //        this.timer = null;
  //       }
  //      }, 1000)
  //     }
  //  } ,
 
  }
};
</script>
<style lang="less">

.page_invest {
  height: 100%;
  background: #F2F2F2;
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
    .jump{
     width: 34px;
     height: 54px;
     background: url(../public/imgs/faxian/icon_right_jump.jpg) no-repeat ;
     background-size: cover;
     display: inline-block;
     margin-left: 10px;
   }
     .submit {
    font-size: 36px;
    color: #fff;
    text-align: center;
    line-height: 84px;
    margin: 0 auto;
    width: 638px;
    height: 84px;
    -webkit-border-radius: 6px 7px 7px 6px/7px;
    -moz-border-radius: 6px 7px 7px 6px/7px;
    border-radius: 6px 7px 7px 6px/7px;
    background-color: #b89a54;
    margin-top: 100px;
  }
   .top {
    color: #e6ca92;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 30px;
    padding: 30px 0px;
    background: #fff;
    border-bottom: 1px solid #f2f2f2;
  }
  .back_bg{
    background: #fff;
    padding: 0px 25px;
  }
  .gray_bg{
    background: #f5f5f5;
    padding: 0px 25px;
    .item{
      padding: 20px 0px;font-size: 26px;color: #666666  
    }
  }
  .yello_b{border-bottom: 1Px solid #f7ad2b}
  .item{padding: 30px 0px;font-size: 26px;color: #323333;
   .money{margin-left: 110px;span{color: #fe4c58}}
   .flex_d{display: flex;align-items: center;justify-content: center;}
  }
  .input_item{display: flex;align-items: center;justify-content: space-between;
  .btn{width: 100px;height: 50px;text-align: center;border:1Px solid #b89a54;border-radius: 10px;line-height: 50px;color: #b89a54;}
   input{margin-left: 20px;}
  }
  .red{color:#fe4c58 }
}
</style>
