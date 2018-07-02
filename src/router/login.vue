<template>
  <div class="page-my-customer">
    <div class="top">登录/注册</div>
    <div class="form_item">
      <label>手机号码</label>
      <input type="text" placeholder="请输入您的手机号" v-model="phone">
    </div>

    <div class="submit" @click="submit" :class="{'enable':!enable}">
      登录/注册
    </div>
  </div>
</template>
<script>
import { Toast } from "mint-ui";
export default {
  name: "",
  data() {
    return {
      phone: "",
      enable:false,
    };
  },

  created() {},
  watch: {
    phone(curVal, oldVal) {
        if(!(/(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/.test(curVal))){ 
         this.enable=false;
        }else{
          this.enable=true;
        } 
    }
  },
  mounted() {},
  methods: {
    close() {},
    submit() {
      if(!this.enable){
       return false;
      }
      this.$http
        .post(
          `${this.Interface.NodeServer}/user/check?phone=${
            this.phone
          }&from=pc&version=100`,
          {}
        )
        .then(res => {
          if (res.data.resultCode == "0" ) {
            if(res.data.resultData.registered){
               this.$router.push({path: '/login1', query: {phone:this.phone}})
            }else{
               this.$router.push({path: '/register', query: {phone:this.phone}})
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
.page-my-customer {
  .top {
    color: #e6ca92;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 30px;
    padding: 30px 0px;
    background: #fff;
    border-bottom: 1px solid #f2f2f2;
  }

  height: 100%;
  background: #fff;
  box-sizing: border-box;
  position: relative;

  .form_item {
    margin: 0 auto;
    width: 100%;
    display: flex;
    margin-bottom: 50px;
    justify-content: space-between;
    align-items: center;
    input {
      width: 570px;
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
