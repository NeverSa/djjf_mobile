<template>
  <div class="page_ass">
    <div class="tab">
      <div class="item active_left " :class="{'active':step1==1}" @click="change(1)">资产分析</div>
      <div class="item active_right" :class="{'active_right_c':step1==2}" @click="change(2)">收益分析</div>
    </div>
    <div class="step_1" v-show="step1==2">
      <div class="empt"></div>
      <div class="canvans_bg">
        <span class="roud " :class="getClass()"></span>
        <div class="click_mask">
          <div class="item" v-for="(index, i) in 7" @click="onClick(index-1)"></div>
        </div>
        <div class="shouyi">
          <div class="title">{{month}}月{{day}}日收益（元） <img class="icon_qus" src="../public/imgs/assetanalysis_icon_qus.png" @click="masktask=true"/> </div>
          <div class="money">
           {{showattr[selectIndex]}}</div>
        </div>
        <chart :options="polar" class="bar"></chart>
      </div>
      <div class="list_content">
        <div class="item" v-for="item  in detailList">
          <div><img src="../public/imgs/assetanalysis_icon_1.png" alt="">{{item.description}}</div>
          <div style="color:#666">{{item.income}}元</div>
        </div>
        <div v-show="!detailList" class="noresult">
          <img src="../public/imgs/noone-img.png" alt="">
          <div>您当日暂无收益</div> 
        </div>
      </div>
    </div>

    <div class="step_2" v-show="step1==1">
      <div class="empt"></div>
      <div class="canvans_bg">
        <div class="money_content">
          <div class="totolemoney">总资产
            <span @click="eyeoption()" v-show="eye"><img src="../public/imgs/assetanalysis_cion_eye.png" alt=""> </span>
            <span v-show="!eye" @click="eyeoption()"><img src="../public/imgs/assetanalysis_cion_eye_2.png" alt=""></span>
          </div>
          <div class="totolemoney_number">{{wallet.totalAssets|NumFormat}}</div>
          <div class="forsale" @click="jump('accumulated')">
            <span>待收收益</span>
            <span class="left_1">
              <span v-show="eye">+</span>{{wallet.expectInterest|NumFormat}}</span>
            <span class="left_2">已收收益</span>
            <span class="bulle">{{wallet.recoveredInterest|NumFormat}}</span>
          </div>
        </div>
        <div class="money_content_detail">
          <div class="left" @click="jump('myinvestmentlist')">
            <div class="title">我的投资（元）</div>
            <div class="number">{{wallet.expectPrincipal|NumFormat}}</div>
          </div>
          <div class="right" @click="jump('balance')">
            <div class="title">账户余额（元）</div>
            <div class="number">{{wallet.balance|NumFormat}}</div>
          </div>
        </div>
      </div>

      <div class="recommended_list">
        <div class="title">
          优选推荐
        </div>
        <div class="list" v-for="item in productlist" @click="buy(item)">
          <div class="title_nav">
            {{item.productName}}
          </div>
          <div class="content_c">
            <div class="left_content">
              <div class="pross">{{item.yearIncome}}%</div>
              <div class="wihite">预期年化</div>
            </div>
            <div class="center_content"></div>
            <div class="right_content">
              <div class="product_des">{{item.interestLimitDes}}
                <span class="small">{{item.interestLimitType}}</span>
                <span class="line"></span>{{item.partInvestAmount}}
                <span class="small">元起购</span>
              </div>
              <div class="remain" v-if="item.productType!==5">
                <div class="remain_text" v-show="item.surplusInvestAmount!==0"> 剩余{{item.surplusInvestAmount/10000}}万元</div>
                <div class="remain_text" v-show="item.surplusInvestAmount==0">已完成</div>
                <div class="prograss" v-show="item.progress>10" :style="{ width: item.progress+'%', transition: 'all 2s'}"></div>
                <div class="prograss" v-show="item.progress<10" style="width:5%, transition: all 2s"></div>
              </div>
              <div v-if="item.productType==5">
                <div class="text_money"> 我出金额,你享收益</div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  
  <div class="task" v-show="masktask">
    <div class="task_contet">
      <div class="title">公告</div>
      <div class="des">该收益是根据用户投资、邀请、使用优惠券等行为产生的预计收益拆分到每天计算而来，实际收益以到账金额为准</div>
      <div class="btn" @click="masktask=false">确认</div>
    </div>
  </div>

  </div>
</template>
<script>
import ECharts from 'vue-echarts/components/ECharts'
 import moment from "moment";
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/tooltip'
export default {
    components: {
    chart:ECharts,
  },
  data: function() {
    return {
      step1: 2,
      productlist: [],
      sessionid: this.$store.state.sessionid || "",
      selectIndex: 0,
      eye: true,
      timearry:[],
      dataarry:[],
      showattr:[],
      truetimearry:[],
      month:"",
      day:'',
      detailList:[],
      masktask:false,
      wallet: {
        totalAssets: 0.0,
        expectInterest: 0.0,
        recoveredInterest: 0.0,
        expectPrincipal: 0.0,
        balance: 0.0
      },
      copywallet: {},

      polar: {
        toolbox: {
          show: false
        },
        grid: {
          x: 10,
          y: 110,
          x2: 10,
          y2: 45
        },
        calculable: false,
        xAxis: {
          axisTick: {
            show: false
          },
          type: "category",
          show: true,
          data: ["06/4", "06/4", "06/4", "06/4", "06/4", "06/4", "06/4"],
          axisLine: {
            lineStyle: {
              color: "#737478"
            }
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: ["#fff"]
            }
          }
        },
        yAxis: [
          {
            type: "value",
            show: false
          }
        ],
        series: {
          barWidth: 10,
          type: "bar",
          itemStyle: {
            normal: {
              color: function(params) {
                var colorList = [
                  "rgb(255,255,255)",
                  "rgb(120,121,140)",
                  "rgb(120,121,140)",
                  "rgb(120,121,140)",
                  "rgb(120,121,140)",
                  "rgb(120,121,140)",
                  "rgb(120,121,140)"
                ];
                return colorList[params.dataIndex];
              }
            }
          },
          data: [0, 0, 0, 0, 0, 0, 0],
          markPoint: {}
        }
      }
    };
  },
  mounted() {
    this.step1 = 1;
    if(this.$route.query.type==1){
      this.step1=1
    }else{
      this.step1=2
    }
    console.log(this.$route.query.type)
  },
  created() {
    this.getWallet();
    this.getProductLict();
    this.getIncome();
  },
  methods: {
    jump(url){
      this.$router.push(url)
    },
    //获取单日详情
    getDetail(){
     this.$http.get(this.Interface.getDailyIncomeDetails,{
        params: { sessionid: this.sessionid,settleDate:this.truetimearry[this.selectIndex]}
     }).then(res=>{
       if(res.data.success){
         this.detailList=res.data.data.list;
        //  console.log(this.detailList)
       }
     })
    },
    //最近七日收益
    getIncome(){
      // console.log(this.polar.xAxis.data)
      this.$http.get(this.Interface.getdailyIncomeSeven,{
         params: { sessionid: this.sessionid }
      }).then(res=>{
        if(res.data.success){
       
          // console.log(res.data.data.list)
        for (let i of res.data.data.list){
           this.truetimearry.push(i.settleDate);
          this.timearry.push(moment(i.settleDate).format("MM/DD"));
          if(isNaN(parseFloat(i.income))){
              this.dataarry.push(0.00)
          }else{
              this.dataarry.push(parseFloat(i.income))
          }
          this.showattr.push(i.income)
        }
        this.getDetail()
        this.polar.xAxis.data=this.timearry;
        this.polar.series.data=this.dataarry;
        this.month=this.timearry[this.selectIndex].substring(1,2)
         this.day=this.timearry[this.selectIndex].substring(3,5);
           console.log(this.dataarry)
           console.log(this.showattr)
        }
      })
    },
    
    eyeoption() {
      this.eye = !this.eye;
      if (this.eye == true) {
        this.wallet = this.copywallet;
      } else {
        this.wallet = {
          totalAssets: "****",
          expectInterest: "****",
          recoveredInterest: "****",
          expectPrincipal: "****",
          balance: "****"
        };
      }
    },
    getProductLict() {
      //产品列表
      this.$http
        .get(this.Interface.getProductRecommend, {
          params: { sessionid: this.sessionid }
        })
        .then(res => {
          if (res.data.success) {
            if (!res.data.data.list[0].activitys) {
              res.data.data.list[0].activetag = "";
            } else {
              res.data.data.list[0].activetag =
                res.data.data.list[0].activitys[0].activityDesc;
            }
            this.productlist = res.data.data.list;
          }
        });
    },
    getWallet() {
      this.$http
        .get(this.Interface.getWalletAccount, {
          params: { sessionid: this.sessionid }
        })
        .then(res => {
          if (res.data.success) {
            this.wallet = res.data.data;
            this.copywallet = this.wallet;
            console.log(this.wallet);
          }
        });
    },
    getClass() {
      return `active_${this.selectIndex}`;
    },
    change(item) {
      this.step1 = item;
    },
    buy(productlist) {
      if (productlist.productType == 5) {
        this.Uitis.Jump({
          link: `${this.Interface.getH5}productdetail_t?productid=${
            productlist.productId
          }`
        });
      } else {
        this.Uitis.Jump({
          link: `${this.Interface.getH5}productdetail?productid=${
            productlist.productId
          }`
        });
      }
    },
    onClick(index) {
      this.selectIndex = index;
        this.month=this.timearry[this.selectIndex].substring(1,2);
         this.day=this.timearry[this.selectIndex].substring(3,5);
           this.getDetail()
      this.polar.series.itemStyle.normal = {
        color: function(params) {
          var colorList = [
            "rgb(120,121,140)",
            "rgb(120,121,140)",
            "rgb(120,121,140)",
            "rgb(120,121,140)",
            "rgb(120,121,140)",
            "rgb(120,121,140)",
            "rgb(120,121,140)"
          ];
          colorList[index] = "rgb(255,255,255)";
          return colorList[params.dataIndex];
        }
      };
    }
  }
};
</script>
<style lang="less" scoped>
.page_ass {
  height: 100%;
  background: #f2f2f2;
  .task{
   position: fixed;
   width: 100%;
   height: 100%;
   left: 0;
   right: 0;
   top: 0;
   bottom: 0;
   background: rgba(0, 0, 0, 0.6);
   z-index: 10002;
   .task_contet{
     padding:0px 30px;
     background: #fff;
     border-radius: 10px;
     transform: translate3d(-50%,-50%,0);
     position: absolute;
     left: 50%;
     top:50%;
     width:500px;
     .title{
       text-align: center;
       color: #000;
       font-size: 30px;
       margin: 30px 0px;;
     }
     .des{
       color: #666;
       line-height: 35px;
        padding-bottom: 20px;
        border-bottom: 1Px solid #efefef;
     }
     .btn{
        text-align: center;
       color: #f2616d;
       font-size: 30px;
       margin-top: 10px;
     }
   }
  }
  .icon_qus{
    width: 30px;
  }
  .empt {
    height: 108px;
  }
  .noresult{
    display: flex;
    flex: 1;
    justify-content: center;
    align-items: center;
    padding-top: 30%;
    flex-direction: column;
    background: #f2f2f2;
    div{
      margin-top:30px;
      font-size: 28px;
      color: #666; 
    }
    img{
      width: 154px;
      height: 215px;;
    }
  }
  .recommended_list {
    background: #fff;
    padding: 0px 40px;
    margin-top: 20px;
    .list {
      padding: 40px;
      padding-left: 0px;
      background: #fff;
      display: flex;
      flex-direction: column;
      margin-bottom: 15px;
      border-bottom: 1Px solid #efefef;
      a {
        text-decoration: none;
      }
      .text_money {
        margin-top: 20px;
      }
      .title_nav {
        font-size: 28px;
        color: #333;
        position: relative;
        display: flex;
        align-items: center;
        .trage {
          display: block;
          position: absolute;
          background: #ffbd33;
          color: #fff;
          font-size: 18px;
          border-radius: 5px;
          padding: 2px 5px;
          height: 30px;
          line-height: 30px;
          right: 0px;
          top: -10px;
        }
        .new_year_tag {
          background: #feeff0;
          color: #f2616d;
          padding: 2px 10px 0px 10px;
          border-radius: 5px;
          display: inline-block;
          margin-left: 20px;
          height: 30px;
          box-sizing: border-box;
          font-size: 22px;
        }
        .activ_tag {
          color: #f2616d;
          padding: 5px 10px;
          border-radius: 5px;
          display: inline-block;
          margin-left: 5px;
          white-space: nowrap;
          text-overflow: ellipsis;
          overflow: hidden;
          width: 300px;
          font-size: 22px;
        }
      }
      .content_c {
        margin-top: 35px;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        .left_content {
          width: 50%;
          .pross {
            font-weight: 600;
            font-size: 44px;
            color: #f2616d;
            .small_pross {
              font-size: 30px;
              padding-left: 10px;
            }
          }

          .wihite {
            font-size: 22px;
            color: #999;
            margin-top: 20px;
          }
        }
        .center_content {
          width: 1Px;
          height: 85px;
          background: #cccccc;
        }
        .right_content {
          flex: 1;
          box-sizing: border-box;
          padding-left: 45px;
          .product_des {
            font-size: 18px;
            color: #666666;
            .small {
              font-size: 18px;
              color: #999;
              margin-left: 4px;
            }
            .line {
              display: inline-block;
              width: 1Px;
              background: #999;
              height: 26px;
              margin-left: 28px;
              margin-right: 8px;
            }
          }
          .remain {
            position: relative;
            margin-top: 20px;
            border: 1Px solid #d8b78c;
            background: #fff;
            text-align: center;
            color: #d8b78c;
            width: 198px;
            height: 28px;
            line-height: 30px;
            font-size: 18px;
            border-radius: 25px;
            .remain_text {
              position: absolute;
              background: transparent;
              top: 0;
              left: 0;
              z-index: 2;
              width: 100%;
              text-align: center;
            }
            .prograss {
              background: #faf0e3;
              border-radius: 25px;
              height: 98%;
              position: absolute;
              top: 0;
              left: 0;
            }
          }
        }
      }
    }
    .title {
      font-size: 30px;
      color: #000;
      border-bottom: 1Px solid #e0e0e0;
      padding: 40px 0px;
    }
  }
  .list_content {
    background: #fff;
    .item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 35px 20px;
      background: #fff;
      border-bottom: 1Px solid #efefef;
      margin: 0px 20px;
      img {
        width: 50px;
        margin-right: 20px;
      }
    }
  }

  canvas {
    background: #575865;
  }
  .canvans_bg {
    background: url(../public/imgs/assetanalysis_bg.jpg) no-repeat;
    background-size: cover;
    position: relative;
    width: 100%;
    .click_mask {
      width: 100%;
      height: 100%;
      position: absolute;
      display: flex;
      padding: 0px 20px;
      box-sizing: border-box;
      z-index: 999;
      top: 150px;;
      .item {
        width: 16%;
      }
    }
    .shouyi {
      position: absolute;
      top: 40px;
      left: 30px;
      z-index: 6;
      .title {
        font-size: 24px;
        color: #ccc;
        font-family: "苹方";
      }
      .money {
        margin-top: 15px;
        font-size: 56px;
        color: #fff;
        font-family: "DinPro";
      }
    }
    .money_content {
      padding: 50px 0px 30px 0px;
      border-bottom: 1Px solid #666;
      margin: 0px 40px;
      .totolemoney {
        font-size: 24px;
        font-family: "苹方";
        color: #8a8a91;
        img{width: 35px;margin-left: 10px;}
      }
      .totolemoney_number {
        font-family: "DinPro";
        font-size: 54px;
        color: #fff;
        margin-top: 30px;
      }
      .forsale {
        font-size: 26px;
        color: #fff;
        font-family: "DinPro";
        margin-top: 20px;
        .left_1 {
          margin-left: 10px;
        }
        .left_2 {
          margin-left: 25px;
          margin-right: 20px;
        }
        .bulle {
          color: #ffbd33;
        }
      }
    }
    .money_content_detail {
      display: flex;
      margin: 0px 40px;
      padding: 40px 0px;
      justify-content: space-between;
      align-items: center;
      .left {
        width: 50%;
        border-right: 1Px solid #666;
      }
      .right {
        flex: 1;
        padding-left: 30px;
      }
      .title {
        font-size: 24px;
        color: #8a8a91;
        margin-bottom: 20px;
      }
      .number {
        font-size: 40px;
        font-family: "DinPro";
        color: #fff;
      }
    }
    .roud {
      position: absolute;
      display: block;
      width: 0px;
      height: 0px;
      border-top: 15px solid rgba(0, 0, 0, 0);
      border-right: 15px solid rgba(0, 0, 0, 0);
      border-bottom: 15px solid #fff;
      border-left: 15px solid rgba(0, 0, 0, 0);
      bottom: 0px;
      left: 9%;
      transition: all 0.5s;
    }
    .active_0 {
      left: 55px;
    }
    .active_1 {
      left: 157px;
    }
    .active_2 {
      left: 259px;
    }
    .active_3 {
      left: 361px;
    }
    .active_4 {
      left: 463px;
    }
    .active_5 {
      left: 565px;
    }
    .active_6 {
      left: 667px;
    }
  }
  .bar {
    width: 100%;
    height: 450px;
  }
}
.tab {
  position: fixed;
  display: flex;
  width: 375px;
  left: 50%;
  transform: translateX(-50%);
  height: 108px;
  justify-content: center;
  align-items: center;
  z-index: 2;
  box-sizing: border-box;
  background: url(../public/imgs/assetanalysis_bg_top.jpg) no-repeat;
  width: 100%;
  background-size: cover;
  background-position-x: -18px;
  z-index: 10;
  .item {
    width: 200px;
    border: 1Px solid #5e5f6a;
    height: 50px;
    font-size: 28px;
    color: #8a8a91;
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    line-height: normal;
  }
  .item:first-child {
    border-top-right-radius: 0px !important;
    border-bottom-right-radius: 0px !important;
  }
  .item:last-child {
    border-top-left-radius: 0px !important;
    border-bottom-left-radius: 0px !important;
    border-left: none;
  }
  .active {
    border-color: #8e8d8d;
    color: #fff;
  }
  .active_right_c {
    border: 1Px solid #8e8d8d !important;
    color: #fff;
  }
}
</style>
