<template>
    <div class="page-banklist">
        <div class="top">实名认证/绑定银行卡</div>
      <div class="item" v-for="item in list" @click="goback(item)">
          <span>{{item.bankName}}</span>
      </div>
    </div>
</template>
<script>
export default {
  name: "appactive",
  data() {
    return {
     list:[],
    };
  },
  components: {
   
  },
  created () {
   try {
     this.realName=this.$route.query.realName||"";
     this.idCard=this.$route.query.idCard||"";
   
     
   } catch (error) {
     
   }
    this.init()  
  },
  mounted() {
  },
  methods: {
    init() {
       this.$http
        .get(
          `${this.Interface.NodeServer}/user/bankcode/list?`,
        ).then(res=>{
            if(res.data.resultCode=="0"){
                this.list=res.data.resultData
            }
        })
    },
    // 查看活动详情
    goback(item) {
         this.$router.push({path: '/realname', query: {bankCode:item.bankCode,bankname:item.bankName,realName:this.realName,idCard:this.idCard}})
    }
  }
};
</script>
<style lang="less" scoped>
.page-banklist{
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
 .item{
   display: flex;
   padding: 30px ;
   border-bottom: 1Px solid #f3f3f3;
   background: #fff;
   align-items: center;
   img{width: 59px;}
   .title{color:#000;font-size: 26px; margin-left: 5px;}
   .jump{
     width: 34px;
     height: 54px;
     background: url(../public/imgs/faxian/icon_right_jump.jpg) no-repeat ;
     background-size: cover;
   }
 }
}
</style>
