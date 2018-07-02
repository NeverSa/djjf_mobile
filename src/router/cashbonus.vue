<template>
<div style="height:100%" class="page_cashbonus">
    <div class="page-mycouponlist_c">
      <div class="noresult" v-show="list.length==0">
     <img src="../public/imgs/no_coupon.png" alt="">
     <div class="des">暂无可用红包</div>
      </div>
        <div class="couponlist" v-for="item in list" v-show="list.length!==0" @click.once="collect(item)">
           <div class="left_c">
                <img src="../public/imgs/red_packet_item_use.png" alt="">
            </div>
            <div class="center_c">
                <div class="title_c">
                    {{item.name}}
                </div>
                <div class="des_c"> {{item.money}}元 </div>
                 <div class="des_c"> 有效期至{{item.expireTime}}</div>
            </div>
            <div class="right_c">领取</div>
        </div>
    </div>
    <mt-popup
  v-model="popupVisible"
  popup-transition="popup-fade">
    <div class="name">
     {{selectname}}
    </div>
    <div class="money">
      {{selectmoney}} <span>元</span>
    </div>
    <div class="des">
      已兑换至您的账户余额
    </div>
    <div class="cancel_btn" @click="popupVisible=false" :closeOnClickModal="false">
     好的
    </div>
</mt-popup>
</div>

</template>

<script>
import { Popup, Toast } from "mint-ui";
export default {
  props: [],
  data() {
    return {
      sessionid: this.$store.state.sessionid || "",
      list: [],
      overlist: [],
      popupVisible: false,
      selectname: "",
      selectmoney: ""
    };
  },
  methods: {
    mypassbookList() {
      this.$http
        .get(this.Interface.mypassbookList, {
          params: {
            pageNo: 1,
            pageSize: 100,
            sessionid: this.sessionid,
            type: 4
          }
        })
        .then(res => {
          if (res.data.success) {
            this.list=[]
            res.data.data.list.map(item => {
              if (item.status == 1 || item.status == 0) {
                this.list.push(item);
              } 
              console.log(item);
            });
          }
        });
    },
    collect(item) {
      this.$http
        .post(this.Interface.redpackageExchange, {
          sessionid: this.sessionid,
          id: item.id
        })
        .then(res => {
          if (res.data.success) {
            this.selectname = item.name;
            this.selectmoney = item.money.toFixed(2);
            setTimeout(()=>{
               this.mypassbookList()
               this.popupVisible = true;
            },1000)
           
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
    this.mypassbookList();
  }
};
</script>
<style lang="less">
html,body{
  background: #f4f4f4;
}
.page_cashbonus{
  .mint-popup {
  width: 570px;
  border-radius: 15px;
  text-align: center;
  .name {
    font-size: 34px;
    color: #333;
    margin-top: 50px;
  }
  .money {
    font-size: 54px;
    color: #f2616d;
    margin-top: 140px;
    span {
      font-size: 34px;
    }
  }
  .des {
    font-size: 24px;
    color: #999;
    margin-top: 40px;
  }
  .cancel_btn {
    border-top: 1px solid #e0e0e0;
    font-size: 34px;
    color: #333;
    margin: 120px 45px 0 45px;
    padding: 35px 0px;
  }
}
.mint-header {
  background: #fff;
  color: #333;
  height: 100px;
  border-bottom: 1px solid #e3e1e1;
  .mint-header-title {
    font-weight: 900;
  }
}

.page-mycouponlist_c {
  background: #f4f4f4;
  padding-top: 140px;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  .noresult {
    padding-top: 320px;
    margin: 0 auto;
    background: #f4f4f4;
    width: 100%;
    text-align: center;
    img {
      width: 266px;
      height: 116px;
    }
    .des {
      font-size: 28px;
      color: #999;
      margin-top: 100px;
      text-align: center;
    }
  }
  .couponlist {
    width: 670px;
    background: #f2616d;
    border-radius: 10px;
    box-sizing: border-box;
    margin: 0 auto;
    margin-bottom: 30px;
    display: flex;
    padding: 40px 35px 40px 0px;
    justify-content: space-between;
    align-items: center;
    color: #fff;
    &:first-child {
      margin-top: 20px;
    }
    .left_c {
      border-right: 1px solid #fff;
      width: 180px;
      text-align: center;
      img {
        width: 94px;
        height: 96px;
      }
    }
    .center_c {
      margin-left: 30px;
      flex: 1;
      .title_c {
        font-size: 30px;
      }
      .des_c {
        font-size: 22px;
        margin-top: 15px;
        padding-left: 7px;
      }
    }
    .right_c {
      height: 45px;
      width: 85px;
      line-height: 45px;
      text-align: center;
      color: #f2616d;
      background: #fff;
      border-radius: 10px;
      font-size: 24px;
    }
  }
}
}

</style>
