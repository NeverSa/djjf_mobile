<template>
  <div class="page_list">
    <div class="top">投资</div>
    <topbar selected="理财"></topbar> 
    <div class="list_content">
       <div class="list" v-for="item in list" @click="godetail(item.id)">
         <div class="left">
           <div class="title">{{item.name}}<span class="tag" v-if="getTag(item.labelType)!==''">{{getTag(item.labelType)}}</span></div>
           <div class="des"><span>预期年化</span><span>投资期限</span></div>
           <div class="number"><span>{{(item.rate/10).toFixed(1)}}<span class="small"><span v-show="item.rasingRate!==0">+{{(item.rasingRate/10).toFixed(1)}}</span>%</span></span>
           <span v-show="item.stagingUnit=='day'">{{item.staging}}<span class="small">天</span></span>
            <span v-show="item.stagingUnit=='month'">{{item.staging}}<span class="small">月</span></span>
           </div>
         </div>
         <div class="right">
             <circle-progress v-if="item.state==0" :list='100-((item.amount-item.nowSum)/item.amount*100).toFixed(0)' ></circle-progress>
              <div class="small_c" v-if="item.state==0">立即<br>投资</div>
             <div class="small_c_a" v-if="item.state==1">收益中</div>
         </div>
       </div>

      <div class="empt"></div>
    </div>
</div>
</template>
<script>
import topbar from "../components/topbar.vue"
import circleProgress  from "../components/circleProgress.vue"
export default {
  name: "",
  data() {
    return {
      list:[],
    };
  },
   components: {
    topbar,
    circleProgress, 
  },
    computed: {
    
    },
    created() {
      this.getList();
     
    },
  mounted() {
   
  },
  methods: {
    godetail(id){
       this.$router.push({path: '/productdetail', query: {productid:id}})
    },
    getTag(type){
      if(type==0){
        return ""
      }else if(type==1){
        return "加息"
      }else{
        return ""
      }
    },
    compare(property){
    return function(a,b){
            var value1 = a[property];
            var value2 = b[property];
            return value1 - value2;
        }
    },
    getnewList(){
      this.$http.get(`${this.Interface.NodeServer}/plans?order=desc&type=1&limit=100`).then(res=>{
        if(res.data.resultCode=="0"){
          this.list=this.list.concat(res.data.resultData);
           this.list.sort(this.compare('state'))
          console.log(this.list)
        }
      })
    },
  getList(){
    this.$http.get(`${this.Interface.NodeServer}/plan/notnew?order=desc&limit=100&page=1`).then(res=>{
      if(res.data.resultCode=="0"){
        this.list=res.data.resultData;
         this.getnewList()
      }
    })
  }
 
  },
   
};
</script>
<style lang="less">

.page_list{
  background: #f7f7f7;
  height: 100%;
  .empt{height: 120px;;}
  .bottom_empt{
    height: 100px;;
  }
  .tag{
    display:inline-flex;
    font-size: 18px;
    background: #ff4657;
    color: #fff;
    padding: 5px 10px;
    border-radius: 5px;
    margin-left: 10px;
     align-items: center;
     justify-content: center;
  }
   .top{
     color: #e6ca92;
     text-align: center;
     display: flex;
     justify-content: center;
     align-items: center;
     font-size: 30px;
     padding: 30px 0px;
     background: #fff;
   }
   .list_content{
   background: #f7f7f7;
   margin: 20px;
   .list{
     display: flex;
     padding: 20px 30px;
     background: #fff;
     margin-bottom: 15px;
     justify-content: space-between;
     align-items: center;
    .left{
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      width: 350px;
      .title{
        color: #000;
        font-size: 28px;
      }
      .des{
        margin-top: 30px;
        color: #999;
        margin-bottom: 20px;
        display: flex;
        justify-content: space-between;
      }
      .number{
          display: flex;
        justify-content: space-between;
        font-size: 38px;
        color: #c5ae6b;
        font-weight: 400;
        font-family: "DinPro";
        .small{
          font-size: 22px;
          margin-left: 5px;
        }
      }
    }
  
  .right{
    position: relative;
     border-radius: 50%;
     display: flex;
     justify-content: center;
     align-items: center;
     width: 150px;
     height: 150px;;
     .small_c{
       background: #b89a54;
       border-radius: 50%;
       width: 70%;
       height: 70%;
       display: flex;
        justify-content: center;
     align-items: center;color: #fff;
     font-size: 26px;
     position: absolute;
     left: 50%;
     top: 50%;
     transform: translate3d(-50%,-50%,0)
      
     }
     .small_c_a{
        position: absolute;
          background: #d4c298;
       border-radius: 50%;
       width: 70%;
       height: 70%;
       display: flex;
        justify-content: center;
     align-items: center;color: #fff;
     font-size: 26px;
      left: 50%;
     top: 50%;
     transform: translate3d(-50%,-50%,0)
     }
  
  }
   }
}
} 
</style>
