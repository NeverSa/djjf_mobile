<template>
    <div class="page-investlist">
        <div class="top">投资记录</div>
        <div class="tab_content" >
           <div class="tab_1" >
               
              <div class="list" v-for="item in list" v-show="list.length!==0">
                  <div class="left">
                      <div class="center">
                          <div class="name">{{item.name}}</div><div class="time">{{item.createdTime |newsdata}}</div>
                    </div>
                  </div>
                  <div class="red">{{(item.money/100).toFixed(2)}}元</div>
              </div>
              <div class="no_result" >
                  <div>没有更多了</div> 
               </div>
           </div>
        </div>
    </div>
</template>
<script>
import { Toast } from "mint-ui";

export default {
  name: "",
  data() {
    return {
        list:[],
    };
  },

  created() {
      this.init();
     
  },
  mounted() {},
  methods: {
    init(){
       this.$http.get(`${this.Interface.NodeServer}/plan/${this.$route.query.productid}/investments?page=1&limit=100&`).then(res=>{
        if(res.data.resultCode=="0"){
         this.list=res.data.resultData
        }
      })
    },
     
  }
};
</script>
<style lang="less">
body,html{
    background: #f2f2f2;
}

.page-investlist {
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



  .tab_content{
      .list{
            background: #fff;
          display: flex;
          border-bottom: 1Px solid #efefef;
          padding: 25px 15px;
          justify-content: space-between;
          align-items: center;
          img{width: 75px;height: 75px;border:3px solid #fff;border-radius: 50%;
           box-shadow: 1px 2px 3px 4px #ccc }
           .red{font-family: "DinPro";color: #f78f9c}
           .left{
               display: flex;
               justify-content: center;
               align-items: center;
               .center{
                   margin-left: 30px;
                   .name{
                    color: #000;
                    font-size: 26px;
                   }
                   .time{
                    font-size: 22px;
                    color: #999;
                    margin-top: 30px;
                   }
                   }
           }
      }
      .no_result{
          display: flex;
          justify-content: center;
          align-items: center;
          flex-direction: column;
          div{
              margin-top: 30px;
          }
      }
  }
}
</style>
