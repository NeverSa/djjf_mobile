<template>
<div class="page-ding-psw">
   <div class="top">设置交易密码</div>
   <div class="form_item">
       <label>交易密码</label>
       <input type="password" placeholder="请输入6位数字交易密码" v-model="pwd">
   </div>
     <div class="form_item">
       <label>确认交易密码</label>
       <input type="password" placeholder="请再次输入您的交易密码" v-model="pwd1">
   </div>

     <div class="form_item">
       <input type="text" placeholder="请输入短信验证码" v-model="code">
       <div class="codebtn" v-show="count==0" @click="rderPayResendSms()">获取验证码</div>
         <div class="codebtn" v-show="count!==0">{{count}}s</div>
   </div>
    <div class="submit" @click="next()">
     确认
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
      userId:this.$store.state.userId || "",
        count:0,
        defaultcont:60,
        pwd:"",
        pwd1:"",
        code:"",
    };
  },
  methods: {
           getCode(){
     let TIME_COUNT = this.defaultcont;
     if (!this.timer) {
        // this.recharge()
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
   
      rderPayResendSms(){
      this.$http
        .get(
          `${this.Interface.NodeServer}/sms/trade?token=${this.sessionid}`,
        ).then(res=>{
          if(res.data.resultCode == "0"){
            this.getCode()
          }else{
            Toast({
              message: res.data.resultMsg,
              position: "center",
              duration: 5000
            });
          }
         
        })
    },
      next(){
          if(this.pwd!==""&&this.code!==""&&this.pwd1!==""){
              if(this.pwd!==this.pwd1){
                    Toast({
                    message:"二次密码不一致",
                    position: "center",
                    duration: 5000
                    });
              }else{
              this.$http.post(`${this.Interface.NodeServer}/user/${this.userId}/account/tradepwd?pwd=${this.pwd1}&code=${this.code}&token=${this.sessionid}`).then(res=>{
                  if(res.data.resultCode == "0"){
                        Toast({
                        message: "交易密码设置成功",
                        position: "center",
                        duration: 2000
                        }); 
                      this.$store.commit('logout');
                      sessionStorage.setItem("form","personalcenter")
                      this.$router.push("login")
                  }else{
                        Toast({
                        message: res.data.resultMsg,
                        position: "center",
                        duration: 5000
                        });         
                  }
              })
              }
          
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
 background: #f2f2f2;
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
        .form_item{
        position: relative;
         display: flex;
         padding: 25px 35px;
         background: #fff;
         border-bottom: 1Px solid #f2f2f2;
         label{width: 200px;}
         .codebtn{    position: absolute;
      top: 50%;
      transform: translateY(-50%);
      right: 30px;
      width: 160px;
      height: 50px;
      color: #f7ad2b;
      border:1Px solid #f7ad2b;
      border-radius: 10px;
      line-height: 50px;
      text-align: center;}
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
}

</style>
