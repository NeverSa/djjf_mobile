<template>
  <div class="page-my-login">
      <div class="top_login">
          <div class="top_login_S">
              <img src="../public/imgs/login/icon_close.png" alt="" @click="close()">
              <div>登录</div>
              <div ></div>
          </div>
          <img src="../public/imgs/login/icon_app.png" alt="" class="app">
      </div>
    <div class="form_item">
      <img src="../public/imgs/login/icon_user.png" alt="">
      <input type="text" placeholder="请输入手机号" v-model="name">
    </div>

    <div class="form_item">
        <img src="../public/imgs/login/icon_password.png" alt="">
        <input type="password" placeholder="请输入登录密码" v-model="password">
        </div>
    <div class="submit" @click="submit" >
      登录
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
    };
  },

  created() {
      this.name=this.$route.query.phone;
  },

  mounted() {},
  methods: {
    close() {
        this.$router.push("home")
    },
    submit() {
      this.$http
        .post(
          `${this.Interface.NodeServer}/user/login?name=${this.name}&password=${this.password}&from=pc`,
        )
        .then(res => {
          if (res.data.resultCode == "0" ) {
            this.$store.commit("login", res.data.resultData);
            if(sessionStorage.getItem("form")){
              this.$router.push(sessionStorage.getItem("form"))
            }else{
                this.$router.go(-2)
            }
          
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
.page-my-login {
.top_login{
    background: #b89a54;
    height: 324px;
    font-size: 32px;
    text-align: center;
    margin-bottom: 50px;
    div{margin-right: 10px;}
    .app{width: 120px;height: 120px;;margin-top: 50px}
    .top_login_S{display: flex;justify-content: space-between;align-self: center;color: #fff;padding-top: 20px;img{width: 26px;height: 26px;margin-left: 20px;}}
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
    padding: 10px 25px; 
    box-sizing: border-box;
    img{width: 28px;height: 34px;margin-left: 15px;}
    input {
      width: 630px;
      height: 85px;
      background-color: #fff !important;
      border-bottom: 1px solid #e3e1e1;
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
