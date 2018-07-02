<template>
    <div style="height:100%;overflow: hidden;">
        <div class="page-balance">
            <div class="nav">
                <div class="left">
                    <div class="number">{{wallet.balance}}</div>
                    <div class="des">可用余额(元)</div>
                </div>
                <div class="right">
                    <div class="number">{{wallet.cashFrozen}}</div>
                    <div class="des">提现中余额(元)</div>
                </div>
            </div>

            <div class="option_nav">
                <div class="rechage_btn btn" @click="rechange('recharge')">
                    充值
                </div>
                <div class="withdrawal_btn btn" @click="rechange('withdrawal')">
                    提现
                </div>
            </div>

            <div class="empt"></div>
            <div style="flex:1;
                    overflow: hidden; ">
                <pull-to :bottom-load-method="loadmore" @bottom-state-change="stateChange">
                    <ul class="list">
                        <li v-for="item in dataList">
                            <div class="first">
                                <div class="roud red" v-if="item.transCode=='支付'">支</div>
                                <div class="roud bule" v-if="item.transCode=='充值'||item.transCode=='回款'">充</div>
                                <div class="second">
                                    <div class="type">{{item.subTransCode}}(普通支付)</div>
                                    <div class="time">{{item.createTime}}</div>
                                </div>
                            </div>
                            <div class="amont">{{item.amount}}</div>
                        </li>
                    </ul>
                    <!-- vue 2.5 use slot-scope -->
                    <template slot="bottom-block" slot-scope="props">
                        <div class="bottom-load-wrapper">
                            {{ props.stateText }}
                        </div>
                    </template>
                </pull-to>
            </div>

        </div>
    </div>

</template>

<script>
import PullTo from "vue-pull-to";
export default {
  props: [],
  components: {
    PullTo
  },
  data() {
    return {
      cardList: [],
   sessionid: this.$store.state.sessionid || "",
      dataList: [],
      defaultpage: 1,
      wallet: []
    };
  },
  methods: {
    rechange(url) {
      this.$router.push(url);
    },
    loadmore(loaded) {
      this.$http
        .get(this.Interface.getAccountFlowList, {
          params: {
            sessionid: this.sessionid,
            pageNo: this.defaultpage,
            pageSize: 20
          }
        })
        .then(res => {
          if (res.data.success) {
            this.dataList = this.dataList.concat(res.data.data.list);
            this.defaultpage++;
            loaded("done");
          }
        });
    },
    stateChange(state) {
      if (state === "pull" || state === "trigger") {
        this.iconLink = "#icon-arrow-bottom";
      } else if (state === "loading") {
        this.iconLink = "#icon-loading";
      } else if (state === "loaded-done") {
        this.iconLink = "#icon-finish";
      }
    },
    getAccountList() {
      this.$http
        .get(this.Interface.getAccountFlowList, {
          params: {
            sessionid: this.sessionid,
            pageNo: this.defaultpage,
            pageSize: 20
          }
        })
        .then(res => {
          if (res.data.success) {
            this.dataList = res.data.data.list;
            this.defaultpage++;
          }
        });
    }
  },
  created() {
    this.$http
      .get(this.Interface.getWalletAccount, {
        params: { sessionid: this.sessionid }
      })
      .then(res => {
        if (res.data.success) {
          this.wallet = res.data.data;
        }
      });
    this.getAccountList();
  }
};
</script>
<style lang="less">
.page-balance {
  background: #fff;
  .bottom-load-wrapper {
    text-align: center;
    padding-top: 20px;
  }
  display: flex;
  flex-direction: column;
  height: 100%;
  .empt {
    background: #f4f4f4;
    height: 20px;
  }
  .nav {
    margin-top: 100px;
    display: flex;
    .number {
      font-weight: 600;
      font-size: 36px;
      color: #4f4f4f;
    }
    .des {
      color: #b2b2b2;
      font-size: 30px;
    }
    .left,
    .right {
      width: 50%;
      border-right: 1px solid #efefef;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      height: 130px;
    }
  }
  .option_nav {
    justify-content: space-between;
    display: flex;
    align-items: center;
    padding: 80px 30px 30px 30px;
    .btn {
      width: 320px;
      height: 70px;
      color: #fff;
      text-align: center;
      border-radius: 5px;
      line-height: 70px;
      font-size: 30px;
    }
    .rechage_btn {
      background: #5261f7;
    }
    .withdrawal_btn {
      background: #e6c9a1;
    }
  }
  .list {
    display: flex;
    flex-direction: column;
    li {
      border-bottom: 1px solid #efefef;
      padding: 40px 30px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      .first {
        display: flex;
        align-items: center;
        .roud {
          width: 50px;
          height: 50px;
          border-radius: 50%;
          color: #fff;
          font-size: 28px;
          line-height: 50px;
          text-align: center;
          font-weight: bold;
          display: flex;
          justify-content: center;
          align-items: center;
        }
        .red {
          background: #ff8c9d;
        }
        .bule {
          background: #8a73ff;
        }
        .second {
          margin-left: 30px;
          .type {
            font-size: 26px;
            font-weight: 600;
          }
          .time {
            font-size: 20px;
            color: #898989;
            margin-top: 20px;
          }
        }
      }
      .amont {
        font-size: 26px;
        font-weight: 600;
      }
    }
  }
}
</style>
