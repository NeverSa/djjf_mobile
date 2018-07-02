
<template>
    <div class="d-container page-withdraw">
        <div>
            <div class="pace_one">
                <div>提现申请已提交，等待处理</div>
                <div class="lab">{{time}}</div>
            </div>
            <div class="pace_two">
                <div>预计1个工作日到账</div>
                <div  class="lab">{{nextday}}</div>
            </div>
        </div>
        <div v-if="ad.length!==0">
          <div class="forbanner" v-bind:key="item.link" v-for="item in ad">
            <a href="javascript:void(0)" @click="jump(item.link)">
              <img v-lazy="item.url" alt="">
            </a>
          </div>
        </div>
     <div class="wancheng" @click="close">
            完成
        </div>
    </div>
</template>

<script>
import { Header } from "mint-ui";
export default {
  props: [],
  data() {
    return {
      ad:[],
      time: "",
      nextday: ""
    };
  },
  methods: {
          jump(url){
    if(url!==""){
     window.location.href=url
    }
}, 
    close(){
      this.$router.push("personalcenter")
    }
  },
  created() {
    this.time = this.Uitis.getDateAfter(0);
    this.nextday = this.Uitis.getDateAfter(1, 1);
        this.$http
      .get(this.Interface.getAdList, { params: { adspaceId: 29 } })
      .then(res => {
        if (res.data.success == true) {
          this.ad = res.data.data.list;
        }
      });
  }
};
</script>
<style lang="less">
html {
  background: #f4f4f4;
}
    .wancheng{
  width: 80%;
  margin: 0 auto;
  background: #5261f7;
  font-size: 28px;
  color: #fff;
  text-align: center;
  padding: 20px 0px;
  border-radius: 15px;
  margin-top: 30px;
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

.page-withdraw {

  & > div {
    &:first-child {
      background: #fff;
      padding: 52px 56px 0;
    }
  }
  .pace_one,
  .pace_two {
    padding-left: 58px;
  }

  .pace_one,
  .pace_two {
    height: 144px;
    position: relative;
    font-size: 32px;
    color: #5261f7;
    .lab {
      color: #999;
      margin-top: 5px;
    }
  }
  .pace_two {
    color: #999;
    margin-top: -5px;
  }

  .pace_one:after {
    background: url(../public/imgs/busuccess_one.png) no-repeat bottom;
    display: block;
    content: "";
    width: 38px;
    height: 92px;
    background-size: 38px 92px;
    position: absolute;
    left: 0;
    top: 0;
  }

  .pace_two:after {
    background: url(../public/imgs/pace_two.png) no-repeat bottom;
    display: block;
    content: "";
    width: 38px;
    height: 92px;
    background-size: 38px 92px;
    position: absolute;
    left: 0;
    top: -48px;
  }

  .forbanner {
    width: 100%;
    margin-top: 32px;
    a {
      display: block;
      width: 100%;
    }
    img {
      display: block;
      width: 100%;
    }
  }
}
</style>
