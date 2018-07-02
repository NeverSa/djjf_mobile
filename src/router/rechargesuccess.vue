
<template>
      <div class="page-recharge-success">
        <div class="success_of">
          <i class="success" v-if="list.statusOrder==1"></i>
          <i class="inprogress" v-if="list.statusOrder==0||list.statusOrder==2 "></i>
          <i class="fail"  v-if="list.statusOrder==-1||list.statusOrder==3||list.statusOrder==4"></i>
          <p class="des">{{getstatusOrder(list.statusOrder)}}</p>
          <h3><span>{{list.moneyPrepay}}</span>元</h3>
        </div>
          <div class="payinfo flex ai-center jc-space-between">
            <p style="color: #333;">付款信息</p>
          <p  style="color: #636363;"><span>{{list.bankName}}（{{list.cardNo}}）</span></p>
        </div>
        <div class="forbanner">
          <a href="javascript:void(0)" v-bind:key="item.link" v-for="item in ad" @click="jump(item.link)">
            <img :src="item.url" alt="">
          </a>
        </div>
        
      <div class="wancheng" @click="close">
                  完成
              </div>
      </div>
</template>
<script>
import { Toast, Header } from "mint-ui";
export default {
  props: [],
  data() {
    return {
      list: {},
       ad: [],
      islist:true,
      sessionid: this.$store.state.sessionid || ""
    };
  },
  methods: {
      jump(url){
    if(url!==""){
     window.location.href=url
    }
}, 
     close(){
      this.$router.push("personalcenter")
    },
    getClass(status) {
      if (status == -1 || status == 3 || status == 4) {
        return "red";
      } else if (status == 1) {
        return "green";
      } else if (status == 0 || status == 2) {
        return "yellow";
      }
    },
    getstatusOrder(status) {
      if (status == -1) {
        return "关闭";
      } else if (status == 0) {
        return "代付款";
      } else if (status == 1) {
        return "充值成功";
      } else if (status == 2) {
        return "处理中 ";
      } else if (status == 3) {
        return "退款";
      } else if (status == 4) {
        return "失败";
      }
    },
    getOrder() {
      this.$http
        .get(this.Interface.getOrderPay, {
          params: {
            sessionid: this.sessionid,
            noOrder: this.$route.query.noorder
          }
        })
        .then(res => {
          if (res.data.success == true) {
            this.list = res.data.data;
          } else {
            Toast({
              message: res.data.msg || res.data.data.error_msg,
              position: "center",
              duration: 5000
            });
          }
        });
    }
  },
  created() {
    if(this.$route.query.islist!=="undefined"&&this.$route.query.islist=="false"){
      this.islist=false;
    }
        //广告列表
    this.$http
      .get(this.Interface.getAdList, { params: { adspaceId: 28 } })
      .then(res => {
        if (res.data.success == true) {
          this.ad = res.data.data.list;
        }
      });
    //获取订单状态
    this.getOrder();
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
html {
 background: #fff;
}
.page-recharge-success {
  height: 100%;
  background: #fff;
  .payinfo {
    background: #ffffff;
    height: 96px;
    position: relative;
    font-size: 28px;
    padding: 20px 0px;
    border-top: 1Px solid #efefef;
    margin: 0 40px;
  }
.forbanner{
  padding: 0px 40px;
}
  .red {
    color: #f76867;
  }

  .green {
    color: #92c84b;
  }

  .yellow {
    color: #feac73;
  }

  .success_of {
    background: #fff;
    padding: 56px 0 72px;
  }
.des{
  font-family: "苹方";
  color: #666;
  font-size: 28px;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 60px;
}
  .success_of i {
    display: block;
    float: none;
    width: 111px;
    height: 111px;
    margin: 0 auto;
    background-size: contain;
  }
  .success_of .success {
    background-image: url("../public/imgs/icon_resuccess.png");
  }
  .success_of .inprogress {
    background-image: url("../public/imgs/progress.png");
  }
  .success_of .fail {
    background-image: url("../public/imgs/fail.png");
  }

  // .success_of p {
  //   text-align: center;
  //   font-size: 34px;
  //   color: #92c84b;
  //   margin: 32px 0 24px;
  //   line-height: 40px;
    
  // }

  .success_of h3 {
    text-align: center;
    font-size: 34px;
    color: #333333;
    font-weight: 600;
    font-family: "苹方";
  
  }

  .success_of h3 span {
    font-size: 54px;
    margin-right: 10px;
    font-family: "DinPro";
    font-weight: 300;
  }
}
</style>
