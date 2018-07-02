<template>
    <div class="page-partner">
        <div class="swiper-container cp-swiper-father">
            <div class="swiper-wrapper">
                <div class="swiper-slide" :style="{background: 'url(' + cpBg1 + ') center center/100%'}">
                    <div class="cp-head cp-head-1"><img :src="cphead1"></div>
                    <div class="cp-body cp-body-1"><img :src="cpbody1"></div>
                    <div class="cp-gtyk cp-gtyk-1">GTYK</div>
                    <div class="cp-furtune rotate-90">YX FURTUNE</div>
                </div>
                <div class="swiper-slide" style="background: #171520;">
                    <div class="cp-star"><img :src="cpStar"></div>
                    <div class="cp-head cp-head-2"><img :src="cphead2"></div>
                    <div class="cp-body-2 swiper-container cp-swiper-son">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide" :class="{'swiper-slide-1':index==0,'swiper-slide-2':index==1,'swiper-slide-3':index==2}" @click="blowUp(index,'.pop-wrap')" v-for="(item,index) in swiperImg"><img class="swiper-item" v-bind:src="item" /></div>
                        </div>
                    </div>
                    <div class="cp-con-2">辽宁国投源康集团，是由“辽宁省投资集团”参股的混合所有制企业，是以蛹虫草（北虫草）深加工为主营业务的科技型企业。公司与清华大学、辽宁中医药大学、辽宁省农业科学院、沈阳药科大学、沈阳农业大学等科研院所及高校进行合作，利用公司1.5万余平方米十万级净化车间及多台自动化生产设备，生产系列的保健食品及护肤品等，所生产的产品覆盖整个大健康领域。</div>
                    <div class="cp-gtyk cp-gtyk-2">GTYK</div>
                    <div class="cp-furtune y-move">YX FURTUNE</div>
                </div>
                <div class="swiper-slide" style="background: #171520;">
                    <div class="cp-star"><img :src="cpStar"></div>
                    <div class="cp-head cp-head-3"><img :src="cphead3"></div>
                    <div class="cp-body cp-body-3"><img :src="cpbody3"></div>
                    <div class="cp-con-3">通过与国资背景的公司强强联手，优选理财将致力于为用户打造出更高收益和更安全的金融理财产品。</div>
                    <div class="cp-slogn"><img :src="cpSlogn"></div>
                    <div class="cp-gtyk cp-gtyk-3">GTYK</div>
                    <div class="cp-furtune rotate-90">YX FURTUNE</div>
                </div>
            </div>
            <div class="pagination"></div>
        </div>
        <mt-popup v-model="popupVisible" popup-transition="popup-fade" position="center" class="pop-wrap" style="text-align: center;">
          <div class="pop-con"><img :src="magnifyImg"></div>
        </mt-popup>
        <div class="plarform-next slideInUp" v-show="isNext"><img :src="iconNext"></div>
    </div>
</template>

<script>
import { Popup } from "mint-ui";
import "@/public/libs/swiper.css";
import Swiper from "@/public/libs/swiper.js";
export default {
  name: "invite_info",
  data() {
    return {
      isNext: true,
      popupVisible: false,
      classTrue: true,
      magnifyImg: '',
      iconNext: require("@/public/imgs/platform_next.png"),
      cpBg1: require("@/public/imgs/cp-bg-1.png"),
      cpStar: require("@/public/imgs/cp-star.png"),
      cphead1: require("@/public/imgs/cp-head-1.png"),
      cphead2: require("@/public/imgs/cp-head-2.png"),
      cphead3: require("@/public/imgs/cp-head-3.png"),
      cpbody1: require("@/public/imgs/cp-body-1.png"),
      cpbody3: require("@/public/imgs/cp-body-3.png"),
      cpSlogn: require("@/public/imgs/cp-slogn.png"),
      swiperImg: [
        require("@/public/imgs/cp-swiper-1.png"),
        require("@/public/imgs/cp-swiper-2.png"),
        require("@/public/imgs/cp-swiper-3.png")
      ],
      blowUpImg: [
        require("@/public/imgs/cp-swiper-big1.jpg"),
        require("@/public/imgs/cp-swiper-big2.png"),
        require("@/public/imgs/cp-swiper-big3.jpg")
      ]
      
    };
  },
  methods: {
    blowUp(data, selector) {
      // 图片放大
      // console.log(data,selector);
      this.magnifyImg = this.blowUpImg[data];
      this.popupVisible = true;
      this.eventDefault(selector);
    },
    eventDefault(selector) {
      //弹出框 页面滚动穿透解决
      this.$nextTick(function() {
        var vModel = document.querySelector(selector);
        vModel.addEventListener("touchmove", function(e) {
          e.preventDefault();
        });
      });
    }
  },
  mounted() {
    this.$nextTick(function() {
      //轮播功能
      var _this = this;
      var tabsSwiper;
      tabsSwiper = new Swiper(".cp-swiper-father", {
        direction: "vertical",
        onTouchEnd: function(swiper) {
          //console.log(tabsSwiper.getWrapperTranslate('x'));
        },
        onTap: function(swiper) {
          // console.log(tabsSwiper.realIndex);
          // console.log(tabsSwiper.activeIndex);
        },
        onTransitionEnd: function(swiper, speed) {
          for (var i = 0; i < swiper.slides.length; i++) {}
          if (tabsSwiper.activeIndex == 2) {
            _this.isNext = false;
          } else {
            _this.isNext = true;
          }
        }
      });
      var cpSwiper;
      cpSwiper = new Swiper(".cp-swiper-son", {
        centeredSlides: true,
        paginationClickable: true,
        spaceBetween: 30,
        loop: false,
        slidesPerView: "auto",
        loopedSlides: 2,
        initialSlide: 1,
        onTouchEnd: function(swiper) {
          //console.log(tabsSwiper.getWrapperTranslate('x'));
        },
        onTap: function(swiper) {
          // console.log(tabsSwiper.realIndex);
          // console.log(tabsSwiper.activeIndex);
        }
      });
    });
  }
};
</script>

<style lang="less">
html,
body {
  position: relative;
  height: 100%;
}
body {
  background: #eee;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  font-size: 14px;
  color: #000;
  margin: 0;
  padding: 0;
}
.page-partner {
  width: 100%;
  height: 100vh;
  .swiper-container {
    width: 100%;
    height: 100%;
  }
  .swiper-slide {
    position: relative;
    font-size: 18px;
    background: #fff;
    /* Center slide text vertically */
    display: -webkit-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    -webkit-justify-content: center;
    justify-content: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    -webkit-align-items: center;
    align-items: center;
  }
  .cp-head,
  .cp-body {
    width: 100%;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
  }
  .cp-head-1 {
    width: 390px;
    top: 110px;
  }
  .cp-head-2 {
    width: 418px;
    top: 85px;
  }
  .cp-head-3 {
    width: 360px;
    top: 85px;
  }
  .cp-body-1 {
    width: 100%;
    top: 50%;
    transform: translate3d(-50%,-50%,0);
    -webkit-transform: translate3d(-50%,-50%,0);
  }
  .cp-body-3 {
    width: 100%;
    top: 190px;
  }
  .cp-con-2 {
    position: absolute;
    left: 0;
    top: 640px;
    padding: 0 50px;
    font-size: 24px;
    color: #FFF;
    line-height: 60px;
    text-indent: 48px;
  }
  .cp-con-3 {
    position: absolute;
    left: 0;
    top: 850px;
    padding: 0 50px;
    font-size: 24px;
    color: #FFF;
    line-height: 60px;
    text-indent: 48px;
  }
  .cp-gtyk {
    position: absolute;  
    top: 30px;
    font-size: 14px;
    color: rgba(117, 139, 201, 0.3);
    letter-spacing: 2px;
    &:after {
      display: block;
      content: "";
      position: absolute;   
      top: 25px;
      width: 24px;
      height: 4px;
      background: #242941;    
    }
  }
  .cp-gtyk-1 {
    right: 50px;
    &:after {
      right: 0;
    }
  }
  .cp-gtyk-2 {
    left: 50px;
    &:after {
      left: 0;
    }
  }
  .cp-gtyk-3 {
    right: 50px;
    &:after {
      right: 0;
    }
  }
  .cp-furtune {
    position: absolute;
    left: 50px;
    bottom: 140px;
    font-size: 14px;
    letter-spacing: 2px;
    color: rgba(117, 139, 201, 0.3);
  }
  .rotate-90 {
    transform: rotate(90deg);
    transform-origin: 0 0;
    left: 64px;
  }
  .y-move {
    transform: translateY(90px);
  }
  .cp-star {
    position: absolute;
    left: 0;
    bottom: 0;
  }
  .cp-swiper-son {
    position: absolute;
    left: 0;
    top: 210px;
    width: 100%;
    height: 400px;
    .swiper-slide {
      text-align: center;
      background: transparent;
      img {
        opacity: 0.8;
      }
    }
    .swiper-slide-1 {
      width: 332px;
    }
    .swiper-slide-2 {
      width: 444px;
    }
    .swiper-slide-3 { 
      width: 218px;
    }
    .swiper-slide-active {
      opacity: 1;
      -webkit-transform: scale(1.2);
      transform: scale(1.2);
      img {
        opacity: 1;
      }
    }
  }
  .cp-slogn {
    width: 100%;
    position: absolute;
    left: 0;
    bottom: 60px;
    text-align: center;
    img {
      width: 200px;
    }
  }
  .v-modal {
    opacity: 0.8;
  }
  .pop-wrap {
    padding: 0 40px;
    width: 100%;
    background: transparent;
    box-sizing: border-box;
  }
  .plarform-next {
    position: fixed;
    width: 100%;
    left: 0;
    bottom: 50px;
    text-align: center;
    z-index: 9;
  }
  .plarform-next img {
    width: 26px;
  }
  @media screen and (max-height: 480px) {
    .cp-swiper-son {
      top: 120px;
    }
    .cp-con-2 {
      position: absolute;
      left: 0;
      top: 550px;
      padding: 0 50px;
      font-size: 24px;
      color: #FFF;
      line-height: 1.7;
      text-indent: 48px;
    }
    .cp-body-3 {
      width: 100%;
      top: 80px;
    }
    .cp-con-3 {
      top: 700px;
    }
  }
  @-webkit-keyframes slideInUp {
    from {
      -webkit-transform: translate3d(0, 100%, 0);
      transform: translate3d(0, 100%, 0);
      visibility: visible;
    }

    to {
      -webkit-transform: translate3d(0, 0, 0);
      transform: translate3d(0, 0, 0);
    }
  }

  @keyframes slideInUp {
    0%,
    30% {
      opacity: 0;
      -webkit-transform: translate3d(0, 10px, 0);
      transform: translate3d(0, 10px, 0);
    }
    60% {
      opacity: 1;
      -webkit-transform: translate3d(0, 0, 0);
      transform: translate3d(0, 0, 0);
    }
    100% {
      opacity: 0;
      -webkit-transform: translate3d(0, -3px, 0);
      transform: translate3d(0, -3px, 0);
    }
  }
  .slideInUp {
    -webkit-animation: slideInUp 1.5s infinite ease-in-out;
    animation: slideInUp 1.5s infinite ease-in-out;
  }
}
</style>
