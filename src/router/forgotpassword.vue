<template>
<div style="height:100%">
    <div class="page-forgotpsw">
       <div class="input_content">
        <input type="text" placeholder="请输入您的手机号" maxlength="11" v-model="mobile">
    </div>

    <div class="input_content bor_b">
        <i class="icon_phone"></i>
        <input type="text" placeholder="请输入验证码" maxlength="6" v-model="checkcode">
        <div v-show="count!==0"  class="btn" >重新发送({{count}})</div>
        <div class="btn" :class="{'active':count==0&&mobile.length==11}" v-show="count==0"  @click="getmsgCode()">获取验证码</div>
    </div>
   
    
    <div class="btn_next"  @click.stop="next()">
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
      onceToken:"",
       mobile:"",
       count:0,
       checkcode:""
    };
  },
  methods: {
 checkcodemsg(){
  this.$http.get(this.Interface.findPwdValidCode,{params:{mobile:this.mobile,checkcode:this.checkcode}}).then(res=>{
      if(res.data.success){
        this.$router.push({path: '/forgotpassword_step2', query: {mobile: this.mobile,checkcode:this.checkcode}})
      }else{
         Toast({
                  message: res.data.msg || res.data.data.error_msg,
                  position: "center",
                  duration: 5000
                });              
      }
  })
 },
   getOncetoken(){
       this.$http.get(this.Interface.getRegOnceToken).then(res=>{
           if(res.data.success){
               this.onceToken=res.data.data.onceToken;
               this.$http.get(this.Interface.findPwdCodeGet,{params:{mobile:this.mobile,onceToken:this.onceToken}}).then(res=>{
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
           }
       })
   },
   getmsgCode(){
       if(this.mobile.length!==11){
           return 
       }
       this.getOncetoken()
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
        next(){
            if(this.mobile.length!==11&&this.checkcode==""){
                return 
            }
              this.checkcodemsg()
        }
  },
  created() {
  }
};
</script>
<style lang="less">
.page-forgotpsw{
 height: 100%;
 background: #fff;
 padding-left: 40px;
 padding-right: 40px;
  .msg{
  padding: 40px 0px;
  color: #999;
  font-size: 34px;
 }
 .bor_b{
     border-bottom: 1Px solid #efefef;
 }
 .input_content{
     position: relative;
     padding: 40px 0;
     input{
         color: #ccc;
         font-size: 32px;
     };
    
     .icon_phone{ background: url("../public/imgs/icon_password.png") no-repeat;
     width: 28px;
      height: 28px;
      background-size: 100%;
      left: 0px;;
      top:50%;
      transform: translateY(-50%)
     }
      .btn{
         position: absolute;
         width: 205px;
         height: 60px;
         text-align: center;
         line-height: 60px;
         color: #a3a3a3;
         background: #cccccc;
         border-radius: 10px;
         right: 0px;
         top:50%;
         transform: translateY(-50%)
     }
     .active{
         background: #5261f7;
         color: #fff;
     }
      
 }

 .btn_next{
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
    background-color: #5261f7;
    margin-top: 40px;
    width: 100%;
 }
}

</style>
