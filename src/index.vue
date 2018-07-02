<template>
    <div class="page-index">
      <div class="link">
        <router-link to="/home">首页</router-link>
      </div>
       <div class="link">
        <router-link to="/login">登录</router-link>
      </div>
       <div class="link">
        <router-link to="/productlist">产品列表</router-link>
      </div>
       <div class="link">
        <router-link to="/productdetail">产品详情</router-link>
      </div>
       <div class="link">
        <router-link to="/personalcenter">我的</router-link>
      </div>
       <div class="link">
        <router-link to="/realname">实名认证</router-link>
      </div>
       <div class="link">
        <router-link to="/bindcard">绑卡</router-link>
      </div>
       <div class="link">
        <router-link to="/investmentconform">投资确认</router-link>
      </div>
       <div class="link">
        <router-link to="/investmentconform_t">投资确认(体验金)</router-link>
      </div>
       <div class="link">
        <router-link to="/recharge">充值</router-link>
      </div>
      <div class="link">
        <router-link to="/rechargesuccess">充值成功</router-link>
      </div>
      <div class="link">
        <router-link to="/withdrawal">提现</router-link>
      </div>   
      <div class="link">
        <router-link to="/mybanklist">我的银行卡</router-link>
      </div>
      <div class="link">
        <router-link to="/nobanklist">我的银行卡未绑定</router-link>
      </div> 
       <div class="link">
        <router-link to="/mycouponlist">我的优惠券</router-link>
      </div> 
       <div class="link">
        <router-link to="/cashbonus">现金红包</router-link>
      </div> 
      <div class="link">
        <router-link to="/buysuccess">购买成功</router-link>
      </div> 
        <div class="link">
        <router-link to="/seting">设置</router-link>
      </div>                        
    </div>
    
</template>

<script>
// import { Cell } from 'mint-ui';

export default {
  data() {
    return {};
  }
};
</script>

<style lang="less">
  .link{
    height: 80px;
    box-sizing: border-box;
    padding:20px 30px;
    border-bottom: 1px solid #ececec;
    font-size: 28px;
    
    a{
      color:#3986FF;
      text-decoration: none;
    }
  }
</style>
