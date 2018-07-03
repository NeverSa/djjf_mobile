<template>
  <div class="page_pakege">
    <div class="top">加息券</div>
    <div class="packge_item" v-for="item in list" @click="gotoinvest(item)">
        <div class="money"> <span>{{(item.voucherValue/10).toFixed(1)}}%</span></div>
        <div class="des">
          <div class="title">{{item.name}}</div>
          <div class="detail">{{item.moneyCondition}}</div>
          <div class="detail">{{item.dayCondition}}</div>
          <div class="detail">{{item.beginTime |newDate}}至{{item.expiredTime |newDate}}</div>
        </div>
    </div>
    <div class="noresult">没用更多了</div>
</div>
</template>
<script>
import topbar from "../components/topbar.vue"
export default {
  name: "",
  data() {
    return {
         sessionid: this.$store.state.sessionid || "",
        userId:this.$store.state.userId || "",
        list:[],
      productid:this.$route.query.productid||"",
         money:this.$route.query.money||"",
           pakagemoney:this.$route.query.pakagemoney||"",
          pocketId:this.$route.query.pocketId||"",
          raisingId:this.$route.query.raisingId||"",
          ratesmoney:this.$route.query.ratesmoney||"",
    };
  },
   components: {
    topbar,
  },
    computed: {
    
    },
    created() {
      this.getdetail();
    
    },
  mounted() {
   
  },
  methods: {
    gotoinvest(item){
        this.$router.push({path: '/investmentconform', query: {raisingId:item.id,ratesmoney:item.voucherValue/10,productid:this.productid,money:this.money,pakagemoney:this.pakagemoney,pocketId:this.pocketId}})
    },
    getdetail(){
      this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/account/canusevouchers/${this.$route.query.productid}?type=2&page=1&limit=100&amount=${this.money*100}&token=${this.sessionid}&from=android&version=100`).then(res=>{
        if(res.data.resultCode=="0"){
          this.list=res.data.resultData;
       
        }
      })
    },

 
  },
   
};
</script>
<style lang="less">

.page_pakege{
  background: #f2f2f2;
  height: 100%;
 
   .top{
     color: #e6ca92;
     text-align: center;
     display: flex;
     justify-content: center;
     align-items: center;
     font-size: 30px;
     padding: 30px 0px;
     background: #fff;
     border-bottom: 1Px solid #f2f2f2;
   }
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

} 
</style>
