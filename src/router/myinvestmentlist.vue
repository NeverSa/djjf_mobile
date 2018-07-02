<template>
<div>
    <div class="page-myinvestmentlist">
      <div class="table_nav">
          <div class="item" :class="{'active':selected==1}" @click="selected=1">待计息</div>
          <div class="item" :class="{'active':selected==2}"  @click="selected=2">计息中</div>
          <div class="item" :class="{'active':selected==3}"  @click="selected=3">已回款</div>
      </div>
      <div class="empt"></div>
       <div class="table_content">
                <div class="item" v-show="selected==1">
                    <div class="item_list" v-for="item in list1" v-show="list1.length!==0" @click="jump(item)">
                        <div class="name">
                           <div style="color:#191919">{{item.productName}}</div>
                           <div class="status">申购中</div>
                        </div>
                        <div class="list_content">
                          <div class="left">
                              <div>投资期限<span>{{item.interestDay}}天</span></div>
                               <div class="bottom">投资金额<span>{{item.totalPrincipal}}元</span></div>
                          </div>
                          <div class="right">
                               <div>投资时间<span>{{item.dealTime}}</span></div>
                               <div class="bottom">待收收益<span class="red">{{item.interest}}元</span></div>
                          </div>
                        </div>
                    </div>
                    <div class="noresult" v-show="list1.length==0">
                        暂无投资记录
                    </div>
                </div>
                <div class="item" v-show="selected==2" >
                     <div class="item_list" v-for="item in list2" v-show="list2.length!==0" @click="jump(item)">
                        <div class="name">
                           <div style="color:#191919">{{item.productName}}</div>
                           <div class="status" style="background:rgb(245, 90, 89)">计息中</div>
                        </div>
                        <div class="list_content">
                          <div class="left">
                              <div>投资期限<span>{{item.interestDay}}天</span></div>
                               <div class="bottom">投资金额<span>{{item.totalPrincipal}}元</span></div>
                          </div>
                          <div class="right">
                               <div>投资时间<span>{{item.dealTime}}</span></div>
                               <div class="bottom">待收收益<span class="red">{{item.interest}}元</span></div>
                          </div>
                        </div>
                          <div class="noresult" v-show="list2.length==0">
                             暂无投资记录
                            </div>
                    </div>
                </div>
                <div class="item" v-show="selected==3">
                     <div class="item_list" v-for="item in list3" v-show="list3.length!==0" @click="jump(item)">
                        <div class="name">
                           <div style="color:#191919">{{item.productName}}</div>
                           <div class="status" style="background:#ecca9d">已回款</div>
                        </div>
                        <div class="list_content">
                          <div class="left">
                              <div>投资期限<span>{{item.interestDay}}天</span></div>
                               <div class="bottom">投资金额<span>{{item.totalPrincipal}}元</span></div>
                          </div>
                          <div class="right">
                               <div>投资时间<span>{{item.dealTime}}</span></div>
                               <div class="bottom">待收收益<span class="red">{{item.interest}}元</span></div>
                          </div>
                        </div>
                          <div class="noresult" v-show="list3.length==0">
                                暂无投资记录
                            </div>
                    </div>
                </div>
      </div>
    </div>
</div>
</template>

<script>
export default {
  props: [],
  data() {
    return {
      selected: 1,
      sessionid: this.$store.state.sessionid || "",
      list1: [],
      list2: [],
      list3: []
    };
  },
  methods: {
      jump(item){
           this.$router.push({path: '/myinvestmentdetail', query: {partId: item.partId}})
      },
    getList(type) {
      this.$http
        .get(this.Interface.getInvestList, {
          params: {
            pageNo: 1,
            pageSize: 100,
            sessionid: this.sessionid,
            type: type
          }
        })
        .then(res => {
          if (res.data.success) {
            if (type == 0) {
              if (res.data.data.list == null) {
                this.list1 = [];
              } else {
                this.list1 = res.data.data.list;
              }
            } else if (type == 1) {
              if (res.data.data.list == null) {
                this.list2 = [];
              } else {
                this.list2 = res.data.data.list;
              }
            } else {
              if (res.data.data.list == null) {
                this.list3 = [];
              } else {
                this.list3 = res.data.data.list;
              }
            }
          }
        });
    }
  },
  created() {
    this.getList(0);
    this.getList(1);
    this.getList(2);
  }
};
</script>
<style lang="less">
body {
  background: #f4f4f4;
}
.page-myinvestmentlist {
  height: 100%;
  background: #f4f4f4;
  .empt {
    height: 20px;
    background: #f4f4f4;
  }
  .table_nav {
      background: #fff;
    display: flex;
    justify-content: space-around;
    align-items: center;
    .item {
      padding: 30px;
      position: relative;
      overflow: hidden;
      background: #fff;
    }
    .item:after {
      content: "";
      display: block;
      position: absolute;
      width: 100%;
      height: 100%;
      top: 0;
      left: 0;
      pointer-events: none;
      //设置径向渐变
      background-image: radial-gradient(circle, #666 10%, transparent 10.01%);
      background-repeat: no-repeat;
      background-position: 50%;
      transform: scale(10, 10);
      opacity: 0;
      transition: transform 0.3s, opacity 0.5s;
    }
    .item:active:after {
      transform: scale(0, 0);
      opacity: 0.3;
      //设置初始状态
      transition: 0s;
      background: #fff;
    }
    .active {
      border-bottom: 1px solid #5261f7;
    }
  }
  .table_content {
      .noresult{
          background: #f4f4f4;
          text-align: center;
          padding-top: 300px;
      }
    .item_list {
        background: #fff;
      padding: 30px 0px;
      margin-bottom: 30px;
      .name {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0px 30px 30px 30px;
        border-bottom: 1px solid #efefef;
        font-size: 26px;
        color: #191919;
        .status {
          background: #5261f7;
          border-radius: 5px;
          color: #fff;
          padding: 5px;
        }
      }
      .list_content {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 30px 30px 30px 30px;
        color: #000;
        .red {
          color: #f68986 !important;
        }
        .bottom {
          margin-top: 30px;
        }
        .left {
          width: 50%;
          border-right: 1px solid #b9b9b9;
          span {
            margin-left: 30px;
            color: #949494;
          }
        }
        .right {
          text-align: right;
          span {
            margin-left: 30px;
            color: #949494;
          }
        }
      }
    }
  }
}
</style>
