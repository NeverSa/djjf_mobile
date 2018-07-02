<template>
<div style="height:100%">
    <div class="page-ding-psw">
         <div class="msg">为保护您的账户安全，请验证身份</div>
       <div class="input_content">
        <i class="icon_code"></i>
        <input type="password" placeholder="请输入旧交易密码" v-model="payPassword" >
    </div>
    <div class="input_content">
        <i class="icon_card"></i>
        <input type="text" placeholder="请输入身份证号末八位"  v-model="identitycard">
    </div>
    <div class="btn_next" @click="next()">
     下一步
    </div>
    </div>
</div>

</template>

<script>
import { Toast } from "mint-ui";
export default {
  props: [],
  data() {
    return {
      sessionid: this.$store.state.sessionid || "",
      payPassword:"",
      identitycard:"",
    };
  },
  methods: {
      next(){
          if(this.payPassword!==""&&this.identitycard!==""){
              this.$http.post(this.Interface.changePaypwdValidate,{payPassword:this.payPassword,identitycard:this.identitycard,sessionid:this.sessionid}).then(res=>{
                  if(res.data.success){
                      this.$router.push({path: '/changetradingpassword_step2'})
                  }else{
                 Toast({
                  message: res.data.msg || res.data.data.error_msg,
                  position: "center",
                  duration: 5000
                }); 
                  }
              })
          }
      },
  },
  created() {
  }
};
</script>
<style lang="less">
.page-ding-psw{
 height: 100%;
 background: #fff;
 padding-left: 40px;
 padding-right: 40px;
 .msg{
  padding: 40px 0px;
  color: #999;
  font-size: 34px;
 }
 .input_content{
     border-bottom: 1Px solid #efefef;
     position: relative;
     padding: 40px 0;
     input{
         color: #ccc;
         font-size: 30px;
         text-indent: 2em;
         width: 100%;
     };
     i{
         position: absolute;
     }
     .icon_code{ background: url("../public/imgs/icon_password.png") no-repeat;
      width: 28px;
      height: 28px;
      background-size: 100%;
      left: 0px;;
      top:45px;
     }
     .icon_card{
        background: url("../public/imgs/realname_card.png") no-repeat;
      width: 36px;
      height: 28px;
      background-size: 100%;
      left: 0px;; 
       top:42px;
     }
     .btn{
         position: absolute;
         width: 205px;
         height: 60px;
         text-align: center;
         line-height: 60px;
         color: #fff;
         background: #e16a70;
         border-radius: 10px;
         right: 0px;
         top:50%;
         transform: translateY(-50%)
     }
 }
 .rule{
     margin: 40px 0px;
     color: #999;
 }
 .btn_next{
    font-size: 36px;
    color: #fff;
    text-align: center;
    line-height: 84px;
    margin: 0 auto;
    height: 84px;
    -webkit-border-radius: 6px 7px 7px 6px/7px;
    -moz-border-radius: 6px 7px 7px 6px/7px;
    border-radius: 6px 7px 7px 6px/7px;
    background-color: #5261f7;
    width: 100%;
    margin-top: 40px;
 }
}

</style>
