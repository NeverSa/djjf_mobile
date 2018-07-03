<template>
  <div style="height:100%;overflow: hidden;">
    <div class="page-accumulated">
      <div class="top">回款计划</div>
      <div class="table_nav">
        <div class="border_go" :class="{'goactive':selected==2}"></div>
        <div class="item" :class="{'active':selected==1}" @click="selected=1">待回款</div>
        <div class="item" :class="{'active':selected==2}" @click="selected=2">已回款</div>
      </div>
      <div class="des_acc">
        已收本金(元)：<span>{{(wallet.repayedCorpus/100).toFixed(2)}}</span>，已收收益(元)：<span>{{(wallet.repayedInterest/100).toFixed(2)}}</span>
      </div>
      <div style="flex:1; overflow: hidden; " class="tab_content" v-show="selected==1">
       <div class="list_item" v-for="item in expectlist">
        <div class="list_top">
          <div class="name">{{item.name}}</div>
           <div class="des"><span>{{(item.corpus/100).toFixed(2)}}</span><span>{{((item.total-item.corpus)/100).toFixed(2)}}  </span></div>
            <div class="des"><span>回款本金(元)</span><span>回款利息(元) </span></div>
        </div>
        <div class="list_bom">
            <span>应收日期</span>
            <span>{{item.repayDay |newDate}}</span>
        </div>
         <div class="list_bom">
            <span>回款日期</span>
            <span></span>
        </div>
         <div class="list_bom">
            <span>本期/总期</span>
            <span>{{item.period}}/{{item.length}}期</span>
        </div>
       </div>
      </div>
      <div style="flex:1; overflow: hidden; " class="tab_content" v-show="selected==2">
              <div class="list_item" v-for="item in recoveredlist">
        <div class="list_top">
          <div class="name">{{item.name}}</div>
           <div class="des"><span>{{(item.corpus/100).toFixed(2)}}</span><span>{{((item.total-item.corpus)/100).toFixed(2)}}  </span></div>
            <div class="des"><span>回款本金(元)</span><span>回款利息(元) </span></div>
        </div>
        <div class="list_bom">
            <span>应收日期</span>
            <span>{{item.repayDay |newDate}}</span>
        </div>
         <div class="list_bom">
            <span>回款日期</span>
            <span>2017-07-22asdas</span>
        </div>
         <div class="list_bom">
            <span>本期/总期</span>
            <span>2017-07-22asdas</span>
        </div>
       </div>
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
      userId:this.$store.state.userId || "",
      expectlist: [],
      recoveredlist: [],
      wallet: []
    };
  },
  methods: {

   getwallet(){
      this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/totalData?page=1&limit=100&token=${this.sessionid}`).then(res=>{
                    if(res.data.resultCode=="0"){
                    this.wallet=res.data.resultData
                    }
                  })
   },

    getRecoveredList(type) {
        this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/repays?status=${type}&page=1&limit=100&token=${this.sessionid}`).then(res=>{
              if(res.data.resultCode=="0"){
                if(type==1){
                  this.expectlist=res.data.resultData
                }else if(type==2){
                   this.recoveredlist=res.data.resultData
                }
              
              }
            })
    }
  },
  created() {
    this.getwallet();
    this.getRecoveredList(1);
     this.getRecoveredList(2);
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
      left: 130px;
    }
    .goactive {
      left: 505px;
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
