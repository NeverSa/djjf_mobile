<template>
<div>

    <div class="page-banklist">
        <div class="nobank">
            <div>
                <img src="../public/imgs/nobank_card.png" alt="">
            </div>
            <div class="des">
                 您还没绑定银行卡
            </div>
            <div class="rebind" @click="rebind()">
                    立即绑定
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
    
    };
  },
  methods: {
     rebind(){
          this.$router.push('bindcard')
     }
  },
  created() {
   
  }
};
</script>
<style lang="less">
html {
  background: #f4f4f4;
}
.mint-header {
  background: #fff;
  color: #333;
  height: 100px;;
  border-bottom: 1Px solid #e3e1e1;
    .mint-header-title {
    font-weight: 900;
  }
}

.page-banklist {
     .nobank{
         display: flex;
         flex-direction: column;
         align-items: center;
         margin-top: 300px;
         img{
             width: 216px;
             height: 121px;
         }
         .des{
             font-size: 28px;
             color: #999;
             margin-top: 120px;
         }
         .rebind{
             width: 245px;
             height: 70px;
             line-height: 70px;
             border-radius: 10px;
             border: 1Px solid #666;
             color: #666;
             text-align: center;
             font-size: 28px;
             margin-top: 54px;
         }
     }
}
</style>
