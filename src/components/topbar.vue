<template>
  <div class="topbar">
     <div class="link" :class="{'active':selected=='首页'}" @click="jump('home')">
        <img src="../public/imgs/icon_home.png" alt="" v-show="!(selected=='首页')">
        <img src="../public/imgs/icon_home_active.png" alt="" v-show="selected=='首页'">
        <span >首页</span>
      </div>
       <div class="link" @click="jump('productlist')" :class="{'active':selected=='理财'}">
          <img src="../public/imgs/icon_licai.png" alt="" v-show="!(selected=='理财')">
           <img src="../public/imgs/icon_licai_active.png" alt="" v-show="selected=='理财'">
        <span to="/login">投资</span>
      </div>
       <div class="link" @click="jump('appactive')" :class="{'active':selected=='活动'}">
          <img src="../public/imgs/icon_active.png" alt="" v-show="!(selected=='活动')">
          <img src="../public/imgs/icon_active_active.png" alt="" v-show="selected=='活动'">
        <span to="/productlist">发现</span>
      </div>
       <div class="link" @click="jump('personalcenter')" :class="{'active':selected=='我的'}">
          <img src="../public/imgs/icon_my.png" alt=""  v-show="!(selected=='我的')">
          <img src="../public/imgs/icon_my_active.png" alt="" v-show="selected=='我的'">
        <span to="/productdetail">我的</span>
      </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
     
    };
  },
  props: ["selected"],
  mounted() {
  },
  methods: {
    jump(url){
      this.$router.push(url)
    }
  },
     created() {
     console.log(this.selected)
   },
};
</script>
<style lang="less" scoped>

.topbar{
    background: #fff;
    display:flex;
    padding:10px 55px;
    bottom: 0px;
    position: fixed;
    left: 0;
    width: 100%;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
     z-index: 999;
    .link{
      display: flex;
      justify-content: space-between;
      align-items: center;
      flex-direction: column;
      height: 80px;
     
      img{
        width: 40px;
        height: 40px;;
      }
    }
    span{
      text-decoration:none;
      color: #999999;
      font-size: 20px;
    }
    .active span{
        color: #b89a54 !important;
      }
}
</style>
