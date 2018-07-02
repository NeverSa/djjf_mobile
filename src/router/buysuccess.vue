
<template>
    <div class="content">
    
      <div class="containt-box">
        <div class="success_step">
          <p class="step">
            <span>付款成功（投标中）</span>
          </p>
          <p class="time">{{buy_list.dealTime}}</p>
        </div>
        <div class="success_step">
          <p class="step">投标成功</p>
          <p class="time">预计1个工作日内</p>
        </div>
       
      </div>
     <div v-if="adlist.length!==0">
       <a href="javascript:void(0)" class="success_banner" v-bind:key="item.url"  v-for="item in adlist" @click="jump(item.link)">
         <img v-lazy="item.url" />
       </a>
     </div>
      <div class="wancheng" @click="close">
            完成
        </div>
    </div>
</template>

<script>

export default {
  props: [],
  data() {
    return {
      sessionid: this.$store.state.sessionid || "",
      money: "",
      starttime: "",
      endtime: "",
      adlist: [],
      buy_list: {}
    };
  },
  methods: {
  close(){
    this.$router.push("productlist")
  },
  jump(url){
    if(url!==""){
     window.location.href=url
    }
},
  },

  created() {
    //我的投资详情
      this.$http
        .get(this.Interface.getMyinvestDetail, {
          params: {
            partId: this.$route.query.partid,
            sessionid: this.sessionid
          }
        })
        .then(res => {
          if (res.data.success == true) {
            this.buy_list = res.data.data;
          }
        });

           //广告列表
    this.$http
      .get(this.Interface.getAdList, { params: { adspaceId: 20 } })
      .then(res => {
        if (res.data.success == true) {
          this.adlist = res.data.data.list;
        }
      });
  }
};
</script>


<style lang="less">
.wancheng{
  width: 80%;
  margin: 0 auto;
  background: #5261f7;
  font-size: 28px;
  color: #fff;
  text-align: center;
  padding: 20px 0px;
  border-radius: 15px;
  margin-top: 30px;
}
.content{
  height: 100%;
  background: #fff
}
.containt-box {
  padding: 50px 0 50px 30px;
  margin-bottom: 34px;
  background-color: #fff;
}

.containt-box .success_step {
  padding-left: 60px;
  background: url("../public/imgs/busuccess_one2.0.png") left top no-repeat;
  background-size: 57px 116px;
  height:  116px;
}
.success_step p{
  margin-left: 20px;  
}
.containt-box .success_step .step {
  font-size: 32px;
  color: #333333 !important;
  text-align: left;
  line-height: 32px;
  font-family: "苹方"
}

.containt-box .success_step .time {
  padding-top: 20px;
  font-size: 20px;
  color: #999;
  text-align: left;
}

.containt-box .success_step:nth-child(2) {
  background: url("../public/imgs/busuccess_two2.0.png") left top no-repeat;
  background-size: 57px 116px;
  height: auto;
}

.containt-box .success_step:nth-child(2) .step {
  padding-top: 46px;
  color: #999999;
}

.success_banner {
  display: block;
  width: 100%;
  padding: 0px 30px;
  box-sizing: border-box;
}

.success_banner img {
  width: 100%;
}
</style>
