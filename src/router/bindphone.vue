<template>
<div style="height:100%">
    <div class="page-phone">
         <div class="msg">短信验证码将发送至{{phone|starphone}}</div>
       <div class="input_content">
        <i class="icon_code"></i>
        <input type="text" placeholder="请输入验证码" v-model="checkcode" maxlength="11">
  
        <div v-show="count!==0"  class="btn" >重新发送({{count}})</div>
        <div class="btn"  v-show="count==0"  @click="getmsgCode()">获取验证码</div>
    </div>
    <div class="input_content">
        <i class="icon_card"></i>
        <input type="text" placeholder="请输入身份证末八位" maxlength="8" v-model="identitycard">
    </div>
    <div class="rule">
       为保证您的账号安全，请输入您认证时的身份证号末8位
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
     cardList:[],
      sessionid: this.$store.state.sessionid || "",
      count:0,
      checkcode:"",
      identitycard:"",
      onceToken:"",
      phone:"",
    };
  },
  methods: {
      next(){
          if(this.checkcode!==""&&this.identitycard!==""){
              this.$http.get(this.Interface.changeOldMobileValid,{params:{checkcode:this.checkcode,identitycard:this.identitycard,sessionid:this.sessionid}}).then(res=>{
                  if(res.data.success){
                      this.$router.push({path: '/bindphone_step2'})
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
      getmsgCode(){
          this.$http.get(this.Interface.getRegOnceToken)
          .then(res=>{
              if(res.data.success){
                  this.onceToken=res.data.data.onceToken;
                return this.$http.get(this.Interface.changeOldMobileCodeGet,{params:{
                    onceToken:res.data.data.onceToken,
                    sessionid:this.sessionid,
                }})
              }else{
                Toast({
                  message: res.data.msg || res.data.data.error_msg,
                  position: "center",
                  duration: 5000
                });
              }
          })
          .then(res=>{
              if(res.data.success){
                  this.getCode()
              }else{
                  Toast({
                  message: res.data.msg || res.data.data.error_msg,
                  position: "center",
                  duration: 5000
                }); 
              }
          })
      },
    getCode(){
     const TIME_COUNT = 60;
     if (!this.timer) {
       this.count = TIME_COUNT;
       this.show = false;
       this.timer = setInterval(() => {
       if (this.count > 0 && this.count <= TIME_COUNT) {
         this.count--;
        } else {
         clearInterval(this.timer);
         this.timer = null;
        }
       }, 1000)
      }
   } ,

  },
  created() {
      this.phone=this.$route.query.phone
  }
};
</script>
<style lang="less">
.page-phone{
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
         font-size: 32px;
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
    width: 100%;
    height: 84px;
    -webkit-border-radius: 6px 7px 7px 6px/7px;
    -moz-border-radius: 6px 7px 7px 6px/7px;
    border-radius: 6px 7px 7px 6px/7px;
    background-color: #5261f7;
 }
}

</style>
