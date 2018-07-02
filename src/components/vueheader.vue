<template>
        <header>
            <div class="back_btn" @click="backgo()">
               <img src="../public/imgs/header_back.png" alt="" v-show="!back">
            </div>
            <div class="title">
                {{title}}
            </div>
            <div  class="optiona">
               <slot name="right"></slot>
            </div>
        </header>
</template>
<script>
export default {
  data() {
    return {
     
    };
  },
  props: ["title","back"],
  mounted() {
  },
  methods: {
    backgo(){
        if(!this.back){
          this.$router.go("-1")
        }
     
    }
  },
     created() {
     console.log(this.back)
   },
};
</script>
<style lang="less" scoped>
header{
    width: 100%;
    position: fixed;
    top:0px;
    z-index: 10;
    background: #fff;
    display: flex;
   height: 100px;
   border-bottom: 1Px solid #efefef;
   justify-content: space-between;
   align-items: center;
   padding: 0 40px;
   box-sizing: border-box;
   .back_btn{
       img{
           width: 19px;
           height: 33px;;
       }
   }
   .title,.option{
       font-size: 32px;
       color: #333;
   }
}
</style>
