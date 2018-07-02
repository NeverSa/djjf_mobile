<template>
<div>
    <transition
         name="fade"
        >
     <div class="Actionsheet" v-show="sheetVisible">
         <div class="title">
         <div @click="close()"> <img :src="img1" alt=""></div>
         <div>活动列表</div>
         <div></div> 
         </div>
          <div class="item" v-for="item in list" @click="jump(item)">
              <div>
                  <span class="tag">{{item.activityTitle}}</span>
                  <span class="des">{{item.activityDesc}}</span>
              </div>
              <div class="icon">
                <img :src="img2" alt="">
              </div>
             
         </div>
      </div>
      </transition>
      <div class="mask" v-show="sheetVisible"></div>
</div>
     
    
</template>
<script>
export default {
  data() {
    return {
     img1:require("../public/imgs/input_X.png"),
      img2:require("../public/imgs/icon_right.png"),
    };
  },
  props: ["list","sheetVisible"],
  mounted() {
  },

  methods: {
    jump(item){
       
        this.$emit('update:sheetVisible', false);
          $("#app").css("overflow","auto")
        window.location.href=item.activityUrl;
    },
    close(){
          $("#app").css("overflow","auto")
          this.$emit('update:sheetVisible', false);
       
    }
  },
     created() {
    console.log(this.sheetVisible)
   },
};
</script>
<style lang="less" scoped>
.fade-enter-active,
.fade-leave-active {
  transition: all 0.5s linear;
  transform: translate3d(0, 0, 0);
}
.fade-enter,
.fade-leave-to {
     transition: all 0.5s ;
  transform: translate3d(0, 100%, 0);
}
.mask{
    position: absolute;
    height: 100%;
    width: 100%;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background: rgba(000, 000,000, .5)
}
.Actionsheet{
    position: fixed;
   left: 0;
   right: 0;
   bottom: 0;
   background: #fff;
   width: 100%;
   z-index: 1000;
   .title{
       display:flex;
       justify-content:space-between;
       align-items: center;
       padding: 30px ;
       border-bottom: 1Px solid #efefef;
       font-weight: 600;
       img{
           width: 28px;
       }
   }
   .item{
        padding: 30px ; 
        border-bottom: 1Px solid #efefef;
        color: #e57881;
      display: flex;
      justify-content: space-between;
      align-items: center;
        .tag{
              border-radius: 10px;
                 border: 1Px solid #e57881;
            display: inline-block;
            padding: 10px 15px;
        }
        .des{
            margin-left: 30px;
        }
        .icon{
            img{
                width: 12px;
            }
        }
   }
}
</style>
