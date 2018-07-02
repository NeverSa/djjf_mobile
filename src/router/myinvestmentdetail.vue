<template>
    <div id="invest_detail">
      <div class="header">
         <div class="title">
           {{detail.productName}}
         </div>
        <div class="secend_title">
          <div  class="mar_right">预期年化<span style="margin-left:5px;">{{detail.yearIncome}}%</span></div>
          <div class="tree">|</div>
          <div>投资期限{{detail.interestLimit}}<span style="color:#999">天</span></div>
        </div>
      </div>
      <div class="invest_money" v-show="detail.totalPrincipal!==detail.moneyOrder">
        <div>
          <span class="blank_color">{{detail.totalPrincipal}}元</span><br/>
          <span class="gray_color">投资金额</span>
        </div>
        <div class="blank_color">
           -
         </div>
        <div>
          <span class="blank_color">{{detail.totalPrincipal-detail.moneyOrder}}元</span><br/>
          <span class="gray_color">抵扣金额</span>
        </div>
        <div class="blank_color">
          =
        </div>
        <div>
          <span class="red_color">{{detail.moneyOrder}}元</span><br/>
          <span class="gray_color">实付金额</span>
        </div>
      </div>

      <div class="invest_money invest_money_none" v-show="detail.totalPrincipal==detail.moneyOrder">
        <div>
          <span class="gray_color">实付金额</span>
        </div>

        <div>
          <span class="red_color">{{detail.moneyOrder}}元</span><br/>
        </div>
      </div>


      <div class="item_card">
        <div>
          <span class="blank_color">还款方式</span>
          <span class="gray_color">{{detail.incomeWayName}}</span>
        </div>
      </div>
      <div class="item_card">
        <div class="first_card">
        <span class="blank_color">项目收益</span>
        <span class="red_color">{{detail.interest}}元</span>
      </div>
        <div class="font_size_small gray_color" v-if="detail.productInterest!=='0.00'">
          <span >基础收益</span>
          <span >{{detail.productInterest}}元</span>
        </div>
        <div class="font_size_small gray_color" v-if="detail.memberInterest!=='0.00'">
          <span >会员收益</span>
          <span >{{detail.memberInterest}}元</span>
        </div>
        <div class="font_size_small gray_color" v-if="detail.activityInterest!=='0.00'">
          <span >活动加息收益</span>
          <span >{{detail.activityInterest}}元</span>
        </div>
        <div class="font_size_small gray_color" v-if="detail.addInterest!=='0.00'">
          <span >加息券收益</span>
          <span >{{detail.addInterest}}元</span>
        </div>
      </div>
        <div class="item_card">
        <div>
          <span class="blank_color">实际已收本息</span>
          <span class="blank_color" v-show="detail.productType==5">{{parseFloat(detail.interest)}}元</span>
          <span class="blank_color" v-show="!(detail.productType==5)">{{parseFloat(detail.interest)+parseFloat(detail.totalPrincipal)}}元</span>
        </div>
      </div>
      <div class="invest_time">
        <ul>
          <li style="border:none;height:0px;" v-show="detail.statusName=='申购中'"> <i class="round" style="top:0.5rem"></i></li>
          <li >
            <i class="round" v-show="detail.statusName=='计息中'"></i>
             <div class="gray" ></div>
            <div class="bule" v-show="detail.statusName=='计息中'||detail.statusName=='已回款'"></div>
            <span class="left_item" :class="{'bulue_color':detail.statusName=='申购中','blank_color':detail.statusName=='计息中','blank_color':detail.statusName=='申购中'}">投资时间</span>
            <span class="" :class="{'bulue_color':detail.statusName=='申购中','blank_color':detail.statusName=='计息中','blank_color':detail.statusName=='申购中'}">{{detail.dealTime}}</span>
          </li>
          <li >
            <i class="round" v-show="detail.statusName=='已回款'"></i>
            <div class="gray" ></div>
            <div class="bule" v-show="detail.statusName=='已回款'"></div>
            <span class="blank_color left_item" :class="{'bulue_color':detail.statusName=='计息中','blank_color':detail.statusName=='已回款','gray_color':detail.statusName=='申购中'}">开始计息日期</span>
            <span v-show="detail.statusName!=='申购中'" class="full" :class="{'bulue_color':detail.statusName=='计息中','blank_color':detail.statusName=='已回款','gray_color':detail.statusName=='申购中'}"><span><div style="display:inline-block" v-show="!(getActiveClass(detail.startInterestTime))">预计：</div>{{detail.startInterestTime}}</span></span>
             <span v-show="detail.statusName=='申购中'" class="full" :class="{'bulue_color':detail.statusName=='计息中','blank_color':detail.statusName=='已回款','gray_color':detail.statusName=='申购中'}"><span><div style="display:inline-block" ></div>募集成功次日可查看</span></span>
          </li>
          <li style="border-bottom: none" >
            <span class=" left_item gray_color" :class="{'bulue_color':detail.statusName=='已回款'}">回款日</span>
            <span  v-show="detail.statusName!=='申购中'" class="full gray_color" :class="{'bulue_color':detail.statusName=='已回款'}"> <span><div  style="display:inline-block" v-show="!(getActiveClass(detail.endInterestTime))">预计：</div>{{detail.endInterestTime}}</span></span>
             <span  v-show="detail.statusName=='申购中'" class="full gray_color" :class="{'bulue_color':detail.statusName=='已回款'}"> <span><div style="display:inline-block" ></div>募集成功次日可查看</span></span>
          </li>
        </ul>
      </div>
      <div class="ag-buy" v-show="detail.status==4||detail.status==5" >项目协议
          <a href="javascript:void(0)" v-bind:key="item.agreeName" @click="jump(item.agreeId)" v-for="item in agreement">《{{item.agreeName}}》</a>
      </div>

    </div>
</template>
<script>
export default {
  name: "invest_detail",
  data() {
    return {
      name: "",
      time1: 1,
      time2: 0,
      detail: {},
      agreement: [],
      sessionid:this.$store.state.sessionid || "",
    };
  },
  methods: {
    getActiveClass(timestr) {
      try {
        var timestr = timestr.substring(0, 10);
        var oDate1 = new Date();
        var oDate2 = new Date(timestr);
        if (oDate1.getTime() >= oDate2.getTime()) {
          return true;
        } else {
          return false;
        }
      } catch (error) {}
    },
    jump(id) {
        this.$router.push({path: '/productarrge', query: {productId: this.detail.productId,partId:this.$route.query.partId,agreeId:id}})
    }
  },
  created() {
      //获取产品信息
      this.$http
        .get(this.Interface.GetInvestDetals, {
          params: { partId:this.$route.query.partId, sessionid: this.sessionid }
        })
        .then(res => {
          //alert(JSON.stringify(res.data));
          this.detail = res.data.data;
          this.$http //获取协议模板
            .get(this.Interface.getAgreeList, {
              params: { productId: this.detail.productId }
            })
            .then(response => {
              //alert(JSON.stringify(response.data));
              this.agreement = response.data.data.list;
            });
        })
        .catch(err => {
          console.log(err);
        });
  
  }
};
</script>
<style lang="less" scoped>
#invest_detail {
  background: #f4f4f4 !important;
  width: 100%;
  height: 100%;
  font-size: 30px;
  .font_size_small {
    font-size: 24px;
  }
  .blank_color {
    color: #333333;
  }
  .gray_color {
    color: #666666;
  }
  .red_color {
    color: #f2616d;
  }
  .bulue_color {
    color: #5261f7;
  }
  .header {
    background: url(../public/imgs/invite_top.png) no-repeat;
    background-size: cover;
    padding: 50px 30px;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    .title {
      font-weight: 900;
      font-size: 32px;
      color: #e5b673;
    }
    .secend_title {
      padding-top: 30px;
      color: #fff;
      display: flex;
      .mar_right {
        margin-right: 40px;
      }
      .tree {
        margin-right: 40px;
      }
      div {
        font-size: 28px;
      }
    }
  }
  .invest_money {
    margin-top: 8px;
    border-top: 1Px solid #e4e4e4;
    margin-bottom: 15px;
    background: #fff;
    padding: 45px 40px;
    border-bottom: 1Px solid #e4e4e4;
    display: flex;
    justify-content: space-between;
    align-items: center;
    div {
      text-align: center;
      font-size: 30px;
      height: 100%;
      line-height: 45px;
    }
  }
  .invest_money_none {
    padding: 30px 40px;
  }
  .item_card:last-child {
    border-bottom: 1Px solid #e4e4e4;
  }
  .item_card {
    .first_card {
      padding-bottom: 20px;
    }
    div {
      display: flex;
      justify-content: space-between;
      span {
        line-height: 50px;
      }
    }
    padding: 30px 35px 20px 35px;
    background: #fff;
    border-top: 1Px solid #e4e4e4;
  }
  .invest_time {
    .firstround {
      position: absolute;
      width: 10px;
      height: 10px;
      border-radius: 50%;
      background: #5261f7;
      top: 45px;
      left: 40px;
      z-index: 999;
      display: block;
    }
    position: relative;
    background: #fff;
    margin-top: 17px;
    padding-left: 50px;
    border-bottom: 1Px solid #e4e4e4;
    margin-bottom: 16px;
    font-size: 28px;
    ul {
      li {
        height: 95px;
        line-height: 95px;
        border-bottom: 1Px solid #e4e4e4;
        position: relative;
        margin-left: 40px;
        .left_item {
          margin-left: 36px;
          display: inline-block;
          width: 200px;
        }

        .round {
          position: absolute;
          width: 13px;
          height: 13px;
          border-radius: 50%;
          background: #5261f7;
          top: 138px;
          left: -53px;
          z-index: 999;
          display: block;
        }
        .gray {
          display: block;
          width: 1Px;
          height: 100px;
          background: #e6e6e6;
          position: absolute;
          content: "";
          top: 50px;
          left: -47px;
        }
        .bule {
          width: 1Px;
          height: 100px;
          background: #5261f7;
          position: absolute;
          content: "";
          top: 50px;
          left: -47px;
          z-index: 10;
          display: block;
        }
      }
    }
  }
  @media all and (max-width: 320px) {
    .full {
      display: inline-flex;
      width: 380px;
      position: relative;
      line-height: 38px;
      padding-top: 15px;
      span {
        position: absolute;
        top: -25px;
      }
    }
  }

  li:last-child .gray {
    display: none !important;
  }
  li:last-child .bule {
    display: none !important;
  }
  li:last-child .round {
    display: none !important;
  }
  li:last-child {
    border-bottom: none;
  }
  .ag-buy {
    a {
      text-decoration: none;
      color: #5261f7;
    }
    color: #999;
    font-size: 34px;
    margin-top: 50px;
    padding-left: 27px;
    font-size: 24px;
  }
}
</style>
