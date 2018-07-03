<template>
  <div class="page-my-setting">
       
           <div @click="bindphone()">
            <mt-cell id="cell_1"
                    title="实名认证"
                    is-link
                    :value="hasBankCard" >
            </mt-cell>
           </div>

      <mt-cell style="border-top:none"
            title="绑定手机"
            :value="user.name"
           >
     </mt-cell>

     <div @click="changebank()">
        <mt-cell
              title="银行卡管理"
              is-link
              :value="hasCardId">
        </mt-cell>
    </div>
    <div @click="changeloginpassword()">
        <mt-cell
              title="登录密码"
              is-link
              value="修改">
        </mt-cell>
    </div>

    <div @click="changetradingpassword()">
        <mt-cell
              title="交易密码"
              is-link
              value="修改">
        </mt-cell>
    </div>

      <div @click="logout()">
        <mt-cell
              title="清除缓存"
               is-link
              >
        </mt-cell>
    </div>
     <div class="logout" @click="logout()">
       <img src="../public/imgs/login/icon_logout.png" alt="">  安全退出
     </div>
  </div>
</template>
<script>

import { Toast,Cell  } from "mint-ui";
export default {
  name: "",
  data() {
    return {
      sessionid:this.$store.state.sessionid||"",
      user:[],
      changetrading:"",
      userId:this.$store.state.userId||"",
      hasBankCard:"未认证",
      hasCardId:"未绑定",
    };
  },
  created() {
      this.getuser()
  },
  mounted() {},
  methods: {
    changetradingpassword(){
      this.$router.push("changetradingpassword_new")
    },
    changebank(){
     if(!this.hasBankCard){
        this.$router.push("realname")
      }else{
        this.$router.push("mybanklist")
      }
    },
    bindphone(){
      if(!this.hasBankCard){
        this.$router.push("realname")
      }
    },
    changeloginpassword(){
      this.$router.push("changeloginpassword")
    },
   logout(){
    this.$store.commit('logout');
    sessionStorage.setItem("form","personalcenter")
    this.$router.push("login")
   },
   getuser(){
      this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/userInfo?token=${this.sessionid}`).then(res=>{
        if(res.data.resultCode=="0"){
         this.user=res.data.resultData;
         if(this.user.hasBankCard){
           this.hasBankCard="已认证";
           this.hasCardId="已绑定"
         }
        }
      })
   }
  }
};
</script>
<style lang="less">
html,
body {
  height: 100%;
  background: #f4f4f4;
}
.mint-cell-text{
  font-size: 28px;
}
.page-my-setting{
   height: 100%;
  background: #f4f4f4;
 .empt{height: 110px;}
 .mint-cell{
     border-bottom: 1Px solid #efefef;
     min-height: 100px;;
 }
 .logout{
     margin-top:10px;
     background: #fff;
     color: #5261f7;
     width: 100%;
     padding: 20px;
     font-size: 30px;
     text-align: center;
     box-sizing: border-box;
     display: flex;
     justify-content: center;
     align-items: center;
     img{width:26px;margin-right: 20px; }
 }
}
</style>
