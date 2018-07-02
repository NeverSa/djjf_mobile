<template>
    <div class="page-financia">
    <div class="top">资金明细</div>
     <div class="item_fi" v-for="item in list">
         <div class="s_1">
             <span v-if="item.type==2">投资成功</span>
              <span v-if="item.type==3">充值成功</span>
                <span v-if="item.type==4">提现成功</span>
                  <span v-if="item.type==0">申请提现</span>
             <span>可用余额(元)</span>
         </div>
         <div class="s_2">
             <span class="bule" v-if="item.type==2||item.type==4||item.type==0">+{{(item.money/100).toFixed(2)}}</span>
              <span class="red" v-if="item.type==3">-{{(item.money/100).toFixed(2)}}</span>
             <span>{{(item.balance/100).toFixed(2)}}</span>
         </div>
         <div class="s_3">
             <span>{{item.createdTime|newsdata}}</span>
         </div>
     </div>
    </div>
</template>

<script>

export default {
  name: "aboutUs",
  data() {
    return {
            list:[],
       sessionid: this.$store.state.sessionid || "",
      userId:this.$store.state.userId || "",
    }
  },
  methods: {
    init(){
       this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/tradeRecords?page=1&limit=100&token=${this.sessionid}`).then(res=>{
        if(res.data.resultCode=="0"){
         this.list=res.data.resultData
        }
      })
    },
     
  
  },
  created() {
      this.init()
  },
  mounted() {

  }
};
</script>

<style lang="less">

.page-financia {
    height: 100%;
    background: #fff;
   .item_fi{
    display: flex;
    padding: 30px 35px;
    background: #fff;
    border-bottom: 1Px solid #f2f2f2;
    flex-direction: column;
    .s_1{
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: #999;
        font-size: 14px;
    }
    .s_2{
         display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 30px;
        padding: 10px 0px;
        .bule{color:#4cacfe}
        .red{color: #fe4c58}
          span:last-child{
            color: #000;
        }
    }
    .s_3{
        text-align: right;
 color: #999;
        font-size: 14px;
    }
   }
}
</style>
