<template>
  <div class="page-my-resgis">
      <div class="top_login">
          <div class="top">
            <div></div>
              <div>注册</div>
              <div ></div>
          </div>
          <img src="../public/imgs/login/icon_app.png" alt="" class="app">
      </div>
    <div class="form_item">
      <img src="../public/imgs/login/icon_phone.png" alt="">
      <input type="text" placeholder="请输入手机号码" v-model="name">
    </div>

    <div class="form_item">
        <img src="../public/imgs/login/icon_code.png" alt="">
        <input type="text" placeholder="获取手机验证码" v-model="code" maxlength="6">
        <div class="code_btn" v-show="count==0" @click="rderPayResendSms()">获取验证码</div>
         <div class="code_btn" v-show="count!==0">{{count}}s</div>
        </div>

        
    <div class="form_item">
        <img src="../public/imgs/login/icon_password.png" alt="">
        <input type="password" placeholder="请设置登录密码" v-model="password" >
        </div>

    <div class="form_item">
        <img src="../public/imgs/login/icon_yaoqing.png" alt="">
        <input type="text" placeholder="请输入邀请人手机号" v-model="recommendCode">
        </div>

    <div class="submit" @click="submit" >
      注册
    </div>
  </div>
</template>
<script>
import { Toast } from "mint-ui";
export default {
  name: "",
  data() {
    return {
      name: "",
      password:"",
      code:"",
      recommendCode:"",
      count:0,
      defaultcont:60,
    };
  },

  created() {
      this.name=this.$route.query.phone;
  },

  mounted() {},
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
    close() {
        this.$router.push("home")
    },
    rderPayResendSms(){
      this.$http
        .post(
          `${this.Interface.NodeServer}/sms/register?phone=${this.name}&from=android`,
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
    submit() {
      this.$http
        .post(
          `${this.Interface.NodeServer}/user/register?phone=${this.name}&password=${this.password}&code=${this.code}&recommendCode=${this.recommendCode}`,
        )
        .then(res => {
          if (res.data.resultCode == "0" ) {
             this.$router.push("home")
          } else {
            Toast({
              message: res.data.resultMsg,
              position: "center",
              duration: 5000
            });
          }
        });
    }
  }
};
</script>
<style lang="less">
html,
body {
  height: 100%;
  background: #fff;
}
.page-my-resgis {
.top_login{
    background: #b89a54;
    height: 324px;
    font-size: 32px;
    text-align: center;
    margin-bottom: 15px;
    .app{width: 120px;height: 120px;;margin-top: 50px}
    .top{display: flex;justify-content: space-between;align-self: center;color: #fff;padding-top: 20px;img{width: 26px;height: 26px;margin-left: 20px;}}
}

  height: 100%;
  background: #fff;
  box-sizing: border-box;
  position: relative;

  .form_item {
    margin: 0 auto;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 0px; 
    box-sizing: border-box;
    position: relative;
    img{width: 28px;height: 34px;margin-left: 20px;}
    .code_btn{
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      right: 30px;
      width: 160px;
      height: 50px;
      color: #f7ad2b;
      border:1Px solid #f7ad2b;
      border-radius: 10px;
      line-height: 50px;
      text-align: center;
    }
    input {
      flex: 1;
      height: 85px;
      background-color: #fff !important;
      border-bottom: 1px solid #e3e1e1;
      margin-left:30px;
    }
  }
  label {
    font-size: 28px;
    padding-left: 30px;
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
  .enable{background-color: #a0a09d}
  .forgetpsw {
    margin-top: 40px;
    text-align: right;
    font-size: 28px;
    padding-right: 50px;
    color: #999;
  }
}
</style>
