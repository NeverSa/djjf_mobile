<template>
  <div style="height:100%;overflow: hidden;">
    <div class="page-accumulated">
      <div class="top">加息券</div>
      <div class="table_nav">
        <div class="border_go" :class="{'goactive':selected==2,'goactive1':selected==3}"></div>
        <div class="item" :class="{'active':selected==1}" @click="selected=1">未使用</div>
        <div class="item" :class="{'active':selected==2}" @click="selected=2">已过期</div>
         <div class="item" :class="{'active':selected==3}" @click="selected=3">已使用</div>
      </div>
      
      <div style="flex:1; overflow: scroll; " class="tab_content" v-show="selected==1">
          <div class="packge_item" v-for="item in list0" >
            <div class="money"><span>{{(item.voucherValue/10).toFixed(1)}}%</span></div>
            <div class="des">
              <div class="title">{{item.name}}</div>
              <div class="detail">{{item.moneyCondition}}</div>
              <div class="detail">{{item.dayCondition}}</div>
              <div class="detail">{{item.beginTime |newDate}}至{{item.expiredTime |newDate}}</div>
            </div>
        </div>
        <div class="noresult">没有更多了</div>
      </div>
        <div style="flex:1; overflow: scroll; " class="tab_content" v-show="selected==2">

             <div class="packge_item user" v-for="item in list1" >
            <div class="money"><span>{{(item.voucherValue/10).toFixed(1)}}%</span></div>
            <div class="des">
              <div class="title">{{item.name}}</div>
              <div class="detail">{{item.moneyCondition}}</div>
              <div class="detail">{{item.dayCondition}}</div>
              <div class="detail">{{item.beginTime |newDate}}至{{item.expiredTime |newDate}}</div>
            </div>
        </div>
         <div class="noresult">没有更多了</div>
      </div>
        <div style="flex:1; overflow: scroll; " class="tab_content" v-show="selected==3">
             <div class="packge_item user" v-for="item in list2" >
            <div class="money"> <span>{{(item.voucherValue/10).toFixed(1)}}%</span></div>
            <div class="des">
              <div class="title">{{item.name}}</div>
              <div class="detail">{{item.moneyCondition}}</div>
              <div class="detail">{{item.dayCondition}}</div>
              <div class="detail">{{item.beginTime |newDate}}至{{item.expiredTime |newDate}}</div>
            </div>
        </div>
         <div class="noresult">没有更多了</div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  props: [],
  components: {
  },
  data() {
    return {
      selected: 1,
      sessionid: this.$store.state.sessionid || "",
      userId: this.$store.state.userId || "",
       list0: [],
         list1: [],
        list2: [],
    };
  },
  methods: {


    getInterestList(type) {
     this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/account/vouchers?type=2&status=${type}&page=1&limit=100&token=${this.sessionid}`).then(res=>{
              if(res.data.resultCode=="0"){
                if(type==0){
                  this.list0=res.data.resultData
                }else if(type==1){
                   this.list1=res.data.resultData
                }else if(type==2){
                   this.list2=res.data.resultData
                }
              
              }
            })
    },
  
  },
  created() {
    this.getInterestList(0);
    this.getInterestList(1);
     this.getInterestList(2);
  }
};
</script>
<style lang="less">
.page-accumulated {
  background: #fff;
  .bottom-load-wrapper {
    text-align: center;
    padding-top: 20px;
  }
  display: flex;
  flex-direction: column;
  height: 100%;

  .table_nav {
    border-bottom: 1Px solid #efefef;
    .border_go {
      position: absolute;
      transition: all 0.5s linear;
      height: 2px;
      width: 120px;
      bottom: 0px;
      background: #e6ca92;
      z-index: 2;
      left:70px;
    }
    .goactive {
      left: 320px;
    }
       .goactive1 {
      left: 570px;
    }
    position: relative;
    background: #fff;
    display: flex;
    justify-content: space-around;
    align-items: center;
    .item {
      padding: 30px;
      position: relative;
      overflow: hidden;
      background: #fff;
      width: 50%;
      text-align: center;
      font-size: 28px;
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
      color: #e6ca92;
    }
  }
.des_acc{
  background: #fff9ef;
  padding: 10px 35px;
 font-size: 24px;
 span{color: #fe4c58}
}
  .tab_content {
    .packge_item{
       background: #fff;
       border: 1pX solid #fe4c58;
       border-radius: 10px;
       padding: 25px 0px;
       display: flex;
       align-items: center;
       margin: 0px 40px;
       margin-top: 10px;
       margin-bottom: 10px;
       .money{
           text-align: center;
           width: 205px;
           border-right: 1Px dashed #ebeaea;
           color: #fe4c58;
           font-size: 26px;
           height: 140px;
           justify-content: center;
           display: flex;
           align-items: center;
           span{font-size: 60px}
       }
       .des{
           margin-left: 30px;
           .title{color: #000;font-size: 24px;padding-top: 10px;}
           .detail{font-size: 20px;color: #666;margin-top: 15px;}
       }
   }
    .user{
      border: 1Px solid #e0e0e0;
      .money{color:#999;}
    }
    background: #f2f2f2;
  .list_item{
    margin: 0 auto;
    margin-top:25px;
    width: 690px;
    background: #fff;
    border-radius: 10px;
    .list_top{
      background: #fff9ef;
      border: 1Px solid #debf8c;
      border-bottom: none;
      color: #fe4c58;
      padding: 20px 25px;
      border-top-left-radius: 10px;
       border-top-right-radius: 10px;
       .name{padding-bottom: 15px;}
       .des{
         margin-top: 10px;
         display: flex;
         text-align: center;
        span{width: 50%;}
       }
    }
    .list_bom{
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1Px solid #f2f2f2;
      padding: 20px 25px;
      span:first-child{
        color: #666;
      }
      span:last-child{
        color: #999;
      }
    }
  }
  }
}
</style>
