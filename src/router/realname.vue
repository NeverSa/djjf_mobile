<template>

   <div class="page_realname">
     <div class="top">实名认证/绑定银行卡</div>
     <div class="garyitem">身份证</div>
     <div class="backitem bot">
       <label>姓名</label>
       <input type="text"  placeholder="请输入姓名" v-model="realName">
     </div>

      <div class="backitem ">
       <label>身份证号</label>
       <input type="text"  placeholder="请输入身份证号码" v-model="idCard">
     </div>

      <div class="garyitem">
        <div class="red">请填写本人真实信息，核实后无法更改</div>
         银行卡
      </div>
       <div class="backitem bor" @click="jumpbanklist()">
       <label>银行</label>
       <div class="bank"><span class="bule">{{bankname}}</span><span class="jump"></span></div>
     </div>
        <div class="backitem ">
       <label>银行卡号</label>
       <input type="text"  placeholder="请输入银行卡号" v-model="cardNumber">
     </div>
     
      <div class="submit" @click="submit" >
          确认
        </div>

           <mt-popup
        v-model="popupVisible"
        popup-transition="popup-fade">
        <div class="msgbox">
           <div class="title">提示</div>
           <div class="des">您还没有设置交易密码，设置</div>
           <div class="btn">
             <div class="" @click="popupVisible=false">先逛逛</div>
             <div @click="gosetingpsd()">确认</div>
           </div>
        </div>
      </mt-popup>
   </div>
</template>
<script>
const keys = () => [1, 2, 3, 4, 5, 6, 7, 8, 9, "", 0];
import { Toast } from "mint-ui";
export default {
  name: "",
  data() {
    return {
      sessionid:this.$store.state.sessionid||"",
      userId:this.$store.state.userId||"",
      bankCode:"",
      bankname:"选择银行",
      realName:"",
      idCard:"",
      cardNumber:"",
      popupVisible:false,
    };
  },
watch:{

},
  created() {
   try {
     if(this.$route.query.bankCode){
      this.bankCode=this.$route.query.bankCode;
     this.bankname=this.$route.query.bankname;
     this.realName=this.$route.query.realName;
     this.idCard=this.$route.query.idCard;
     }
     
   } catch (error) {
     
   }
  },
  mounted() {},
  methods: {
       gosetingpsd(){
        this.$router.push("changetradingpassword_new")
    },
    jumpbanklist(){
         this.$router.push({path: '/banklist', query: {realName:this.realName,idCard:this.idCard}})
    },
      submit(){
      this.$http
        .post(
          `${this.Interface.NodeServer}/user/${this.userId}/account/card/binding?realName=${this.realName}&idCard=${this.idCard}&cardNumber=${this.cardNumber}&bankCode=${this.bankCode}`).then(res=>{
           if(res.data.resultCode=="0"){
           this.popupVisible=true;
           }else{
              Toast({
              message: res.data.resultMsg,
              position: "center",
              duration: 5000
            });
           }
          })
    },

  
  }
};
</script>
<style lang="less">
html,
body {
  height: 100%;
  background: #f2f2f2;;
}
.page_realname{
    height: 100%;
  background: #f2f2f2;;
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
    .des{
      font-size: 24px;
      padding: 30px 0px;
      text-align: center;
      border-bottom: 1Px solid #f2f2f2;
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

  background: #f2f2f2;
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
   .garyitem{
     background: #f6f6f6;
     padding: 30px 35px;
     font-size: 28px;
     color: #666;
     .red{color: #fe4c58;font-size: 24px;margin-top: -5px;margin-bottom: 10px;}
   }
   .bor{
     border-bottom: 1Px solid #f2f2f2;
   }
   .backitem{
    padding: 30px 35px;
     font-size: 28px;
     background: #fff;
     display: flex;
     justify-content: space-between;
     align-items: center;
     label{width: 180px;font-size: 28px;color: #323333}
     input{flex: 1};
     .bank{
       display: flex;
       align-items: center;
       justify-content: space-between;
       flex: 1;
       .bule{
         color: #4cacfe;
         font-size: 24px;
       }
         .jump{
        width: 34px;
        height: 54px;
        background: url(../public/imgs/faxian/icon_right_jump.jpg) no-repeat ;
        background-size: cover;
      }
     }
   }
   .bot{
     margin-bottom: 5px;
   }
}
</style>
