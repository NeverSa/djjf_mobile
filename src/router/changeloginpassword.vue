<template>
<div class="page-ding-psw">
   <div class="top">设置登录密码</div>
    <div class="form_item">
       <label>原登录密码</label>
       <input type="password" placeholder="请输入你的原登录密码" v-model="password">
   </div>
   <div class="form_item">
       <label>新登录密码</label>
       <input type="password" placeholder="请输入6~16位字母和数字登录密码" v-model="password1">
   </div>
     <div class="form_item">
       <label>确认登录密码</label>
       <input type="password" placeholder="请再次输入您新的登录密码" v-model="passwordNew">
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
        password:"",
        password1:"",
        passwordNew:"",
    };
  },
  methods: {
      next(){
          if(this.password!==""&&this.password1!==""&&this.passwordNew!==""){
              if(this.passwordNew!==this.password1){
                    Toast({
                    message:"二次密码不一致",
                    position: "center",
                    duration: 5000
                    });
              }else{
              this.$http.post(`${this.Interface.NodeServer}/user/${this.userId}/updatePassword/?password=${this.password}&passwordNew=${this.passwordNew}&token=${this.sessionid}`).then(res=>{
                  if(res.data.resultCode == "0"){
                        Toast({
                        message: "登录密码设置成功",
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
