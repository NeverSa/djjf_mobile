<template>
   <div class="page_recharge">
     <div class="top">充值</div>
       <div class="item">
        <div>
           <div>{{bank.bankName}}</div><br/>
           <span class="card"> {{bank.cardNumber}}</span>
        </div>
     </div>
     <div class="item">
       <label for="">充值金额</label>
       <input type="text" placeholder="请输入充值金额(100元起)" v-model="money">
       <span class="yuan">元</span>
     </div>
      <div class="item">
       <label for="">交易密码</label>
       <input type="password"  placeholder="请输入交易密码" v-model="tradePassword">
     </div>
     
     <div class="error">
       单笔最高{{bank.currentLimit/1000000}}万，每日限额{{bank.dayLimit/1000000}}万
     </div>
    <div class="submit" @click="recharge" >
      确定
    </div>
    					

                    
         <mt-popup
        v-model="popupVisible"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title">提示</div>
           <div class="des">确认充值</div>
           <div></div>
           <div class="btn">
             <div class="" @click="popupVisible=false">取消</div>
             	<form class="form-inline"  :action="payBody.url" method="post" name="E_FORM" ref="form_s">
								<input type="hidden" name="ENCTP" :value="payBody.ENCTP">
								<input type="hidden" name="FM" :value="payBody.FM">
								<input type="hidden" name="LOGOTP" :value="payBody.LOGOTP">
								<input type="hidden" name="MCHNTCD" :value="payBody.MCHNTCD">
								<input type="hidden" name="VERSION" :value="payBody.VERSION">
								<!-- <input type="hidden" name="card_no" :value="payBody.card_no">
								<input type="hidden" name="page_notify_url" :value="payBody.page_notify_url">
								<input type="hidden" name="back_notify_url" :value="payBody.back_notify_url">
								<input type="hidden" name="cert_type" :value="payBody.cert_type">
								<input type="hidden" name="user_id" :value="payBody.user_id">
								<input type="hidden" name="cardholder_name" :value="payBody.cardholder_name">
								<input type="hidden" name="cert_no" :value="payBody.cert_no"> -->
                   <button  type="submit" >确认充值</button>
							</form>
            
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
        bank:"",
        money:"",
        tradePassword:"",
        payBody:{},
        popupVisible:false,
    };
  },
watch:{
},
  created() {
      this.mybankcardList()
  },
  mounted() {},
  methods: {
    // 获取银行卡
      mybankcardList(){
        this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/account/cards?from=android&version=100&token=${this.sessionid}`).then(res=>{
            if(res.data.resultCode=="0"){
              this.bank=res.data.resultData[0];
          
            }
      })
      },
    //   预充值
      recharge(){
          if(this.moneyPrepay!==""){
            this.$http.post(`${this.Interface.NodeServer}/user/${this.userId}/account/recharge/h5?money=${this.money*100}&tradePassword=${this.tradePassword}&token=${this.sessionid}`).then(res=>{
                    if(res.data.resultCode=="0"){
                      this.payBody=res.data.resultData;
                      this.popupVisible=true
                    }else{
                        Toast({
                        message: res.data.resultMsg,
                        position: "center",
                        duration: 5000
                      });
                    }
              })
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

.page_recharge{
     .msgbox{
    width: 600px;
    background: #fff;
    border-radius: 15px;
    padding: 20px 0px;
    form{width: 50%}
    button{background: #fff;width: 100%;text-align: center;}
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
  .error{
    color: #fe4c58;
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
}
</style>
