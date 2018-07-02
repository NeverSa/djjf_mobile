<template>
   <div class="page_wis">
     <div class="top">提现</div>
       <div class="item">
        <div>
           <div>{{bank.bankName}}</div><br/>
           <span class="card"> {{bank.cardNumber}}</span>
        </div>
     </div>
     <div class="grat" style="padding:0.2rem 0.2rem">可提现金额：<span style="color:red">{{(user.money/100).toFixed(2)}}</span> 元</div>
     <div class="item">
       <label for="">提现金额</label>
       <input type="text" placeholder="请输入提现金额" v-model="money">
       <span class="yuan">元</span>
     </div>
      <div class="item item_wi">
       <label for="">手续费</label>
       <span>{{(count.money/100).toFixed(2)}}元</span>
     </div>
     <div class="item item_wi">
       <label for="">实际到账</label>
       <span><span class="red">{{money-(count.money/100).toFixed(2)}}</span>元</span>
     </div>
     <div class="error">
         每个月免费提现次数3次，本月剩余 {{count.count}}次
     </div>
    <div class="submit" @click="withdrawal" style="margin-top:0.4rem">
      提交
    </div>
    <div class="noresult" @click="jump()">提现规则</div>


      <mt-popup
        v-model="popupVisible"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title">提示</div>
           <div class="des"><input type="password" placeholder="请输入交易密码" v-model="tradePassword"></div>
           <div class="btn">
             <div @click="sumitwithdrawal()">确认</div>
           </div>
        </div>
      </mt-popup>
  </div>

  
</template>
<script>

import { Toast,Popup} from "mint-ui";
export default {
  name: "",
  data() {
    return {
         sessionid: this.$store.state.sessionid || "",
        userId:this.$store.state.userId || "",
        count:{},
        bank:"",
        money:"",
        user:"",
        tradePassword:"",
        popupVisible:false,
    };
  },
watch:{
},
  created() {
      this.mybankcardList()
      this.getwithdrawcount()
      this.getuser()
  },
  mounted() {},
  methods: {
    jump(){
      this.$router.push("withdrawexplain");
    },
    // 获取银行卡
      mybankcardList(){
        this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/account/cards?from=android&version=100&token=${this.sessionid}`).then(res=>{
            if(res.data.resultCode=="0"){
              this.bank=res.data.resultData[0];
            }
      })
      },
        
     getuser(){
        this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/accountInfo/?token=${this.sessionid}`).then(res=>{
            if(res.data.resultCode=="0"){
              this.user=res.data.resultData;
          
            }
      })
      },
      getwithdrawcount(){
               this.$http.get(`${this.Interface.NodeServer}/user/account/${this.userId}/withdraw/count?token=${this.sessionid}`).then(res=>{
            if(res.data.resultCode=="0"){
              this.count=res.data.resultData;
           }
             })
         
      },
      sumitwithdrawal(){
              this.$http.post(`${this.Interface.NodeServer}/user/account/${this.userId}/withdraw?money=${this.money*100}&tradePassword=${this.tradePassword}&token=${this.sessionid}`).then(res=>{
                      if(res.resultCode=="0"){
                           Toast("提现申请成功");
                           this.popupVisible=false;
                           this.$router.push("personalcenter")
                      }else{
                          Toast({
                          message: res.resultMsg,
                          position: "center",
                          duration: 5000
                        });
                      }
                })
      },
    //   预充值
      withdrawal(){
          if(parseInt(this.money)==""){
           Toast("提现金额不能为空")
          }else if(this.money>this.user.money/100){
              Toast("提现金额不能超过余额")
            }else{
              this.popupVisible=true
          }
      },


  }
};
</script>
<style lang="less">
html,
body {
  height: 100%;
  background: #f4f4f4;
}

.page_wis{
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
      padding: 0px 50px;
      margin-top: 30px;
      input{width: 100%;text-align: center;height: 50px;}
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
      div{width: 100%;color: #000};
      div:first-child{
        border-right: 1Px solid #f2f2f2;
        color: #999;
      }
    }

  }
  .mint-popup{
    border-radius: 15px;
  }
  .error{
    padding:20px;
  }
  .item{
  background: #fff;
  padding: 30px 20px;
  display: flex;
  align-items: center;
  position: relative;
  border-bottom: 1Px solid #f2f2f2;
  .card{padding-top: 15px;}
  label{width: 160px;font-size: 28px;color: #323333;
  }
  input{background: transparent;width: 350px};
  .yuan{
    position: absolute;
    right: 30px;
    top:50%;
    transform: translateY(-50%)
  }
  }
  .item_wi{
    justify-content: space-between;
    .red{color:red;margin-right: 15px;}
  }
}
</style>
