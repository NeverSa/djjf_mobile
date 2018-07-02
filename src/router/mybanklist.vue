<template>
  
    <div class="page-banklist">
      <div class="top">银行卡管理</div>
      <div class="des_bank">如需更换银行卡，请联系客服</div>
             <div class="bank_bg">
        <div class="tops">
             <div class="name">{{cardList.bankName}} </div>
        </div>
        <div class="center" >
          <div>储蓄卡：{{cardList.cardNumber}}</div>
          <div>持卡人：{{cardList.realName}}</div>
        </div>
        <div class="bottom">
          已绑定
         
        </div>
     </div>
    </div>


</template>

<script>
import {  } from "mint-ui";
export default {
  props: [],
  data() {
    return {
     cardList:[],
      sessionid: this.$store.state.sessionid || "",
              userId:this.$store.state.userId || "",
    };
  },
  methods: {
        // 获取银行卡
      mybankcardList(){
        this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/account/cards?token=${this.sessionid}`).then(res=>{
            if(res.data.resultCode=="0"){
              this.cardList=res.data.resultData[0];
            }
      })
      },

  },
  created() {
   this.mybankcardList();
  }
};
</script>
<style lang="less">
html {
  background: #f4f4f4;
}
.mint-header {
  background: #fff;
  color: #333;
  height: 100px;;
  border-bottom: 1Px solid #e3e1e1;
    .mint-header-title {
    font-weight: 900;
  }
}

.page-banklist {
  .des_bank{
  background: #fff9ef;
  color: #fe4c58;
  padding: 15px 20px;
  }

 .bank_bg {
    display: flex;
    width: 600px;
    height: 250px;
    background-size: contain;
    margin: 0 auto;
    margin-top: 30px;
    justify-content: space-between;
    flex-direction: column;
    text-align: right;
    background: #fff;
    border-radius: 15px;
    .tops {
      display: flex;
      align-items: center;
      background-image: linear-gradient(#16a3fc, #99e1fc);      
     border-top-left-radius: 10px;
     border-top-right-radius: 10px;
     padding: 15px 0px;;
      .name {
        font-size: 28px;
        color: #fff;
        margin-left: 30px;
      }
    }
    .center {
      font-size: 26px;
      text-align: left;
      padding-left: 40px;
      line-height: 40px;
    }
    .bottom {
      padding-bottom: 20px;
     color: #666;padding-right: 30px;
    }
  }
}
</style>
