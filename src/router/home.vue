<template>
  <div class="page_home">
    
    <swiper :options="swiperOption" ref="mySwiper">
    <!-- slides -->
    <swiper-slide  v-for="(item, key, index) in swiperSlides" >
      <img :src="item.picture" alt="" @click="swiperjump(item)">
    </swiper-slide>
    <div class="swiper-pagination"  slot="pagination"></div>
  </swiper>
      <div class="noticeBg" @click="jumplist()">
					<div class="noticeTitleName">
			    	 <span><img src="../public/imgs/index/icon_laba.png" alt="" class="icon_laba"></span>  
            <ul>
              <li v-for="x in noticeList ">
                <span class="liTitle">{{x.title}}</span>
              </li>
            </ul>
					</div>
          <div class="right_icon"> </div>
		</div>

   <div class="recomand" v-for="item in recomdlist">
      <div class="title">
        <div>
          <span><img src="../public/imgs/index/icon_tuijian.png" alt="" class="icon_laba"></span> <span class="title_nav">推荐</span> 
        </div>
        <div class="center">{{item.name}}</div>
        <div class="right_icon"> </div>
      </div>

      <div class="product" @click="godetail(item.id)">
         <div class="percent">{{((item.rasingRate+item.rate)/10).toFixed(1)}}%</div>
           <div class="day">投资期限{{item.staging}}天</div>
           <div class="yuan">起购100元</div>
      </div>
      <div class="invest" @click="godetail(item.id)">立即投资</div>
   </div>

 		<div class="recomand" style="margin-top:0.2rem">
       <div class="title">
        <div>
          <span><img src="../public/imgs/index/icon_fire.png" alt="" class="icon_fire"></span> <span class="title_nav">热推</span> 
        </div>
        <div class="right_icon"> </div>
      </div>
     </div>

    <div class="product_list" v-for="item in list" @click="godetail(item.id)">
        <div class="title">{{item.name}}</div>
        <div class="detail">
          <div class="year_con">{{((item.rasingRate+item.rate)/10).toFixed(1)}}%<span>预计年化</span></div>
          <div class="day"  v-if="item.stagingUnit=='day'">{{item.staging}}天<span>投标期限</span></div>
          <div class="day"  v-if="item.stagingUnit=='month'">{{item.staging}}月<span>投标期限</span></div>
          <div class="pre">{{parseInt(((item.nowSum /item.amount) * 100)>100 ? 100:(item.nowSum / item.amount*100))}}%<span>投标进度</span></div>
        </div>
    </div>


     		<div class="recomand" style="margin-top:0.2rem">
       <div class="title">
        <div>
          <span><img src="../public/imgs/index/icon_mes.png" alt="" class="icon_fire"></span> <span class="title_nav">活动公告</span> 
        </div>
        <div class="right_icon"> </div>
      </div>
     </div>

     <div class="news_list" v-for="item in newslist" @click="gomedetail(item.id)">
       <img :src="item.coverImage" alt="" class="left_img" >
       <div class="right_content">
         <div class="title">{{item.title}}</div>
         <div class="des">{{item.subTitle}}</div>
         <div class="time">{{item.updatedTime |newDate}}</div>
       </div>
     </div>

  <div class="empt"></div>
   <topbar selected="首页"></topbar> 

</div>
</template>
<script>
import "swiper/dist/css/swiper.css";
import { swiper, swiperSlide } from "vue-awesome-swiper";
import topbar from "../components/topbar.vue";
 import moment from "moment";
export default {
  components: {
    swiper,
    swiperSlide,
    topbar
  },
  name: "",
  data() {
    return {
      animate: false, 
      noticeList:"",
      swiperOption: {
        notNextTick: true,
        autoplay: true,
        autoplay: 3000,
        direction: "horizontal",
        setWrapperSize: true,
        pagination: ".swiper-pagination",
        debugger: true,
        onTransitionStart(swiper) {}
      },
      swiperSlides: [],
      recomdlist:[],
      list:[],
      newslist:[],
    }
  },
  computed: {
    swiper() {
      return this.$refs.mySwiper.swiper;
    }
  },
  mounted() {},
  created() {
      this.getbanner();
      this.getNotice();
        setInterval(this.showMarquee, 2000) ;
        this.getrecomand()
        this.getList()
        this.getNewsList()
},
  methods: {
    gomedetail(id){
             this.$router.push({path: '/medialistdetail', query: {id:id}})
    },
    godetail(id){
       this.$router.push({path: '/productdetail', query: {productid:id}})
    },
    jumplist(){
      this.$router.push("newslist")
    },
    showMarquee(){
       this.animate = true; 
                setTimeout(()=>{ 
                    this.noticeList.push(this.noticeList[0]); 
                this.noticeList.shift(); 
                this.animate = false; 
            },500)}, 
  
    	//获取平台公告
	  getNotice() {
			this.$http.get(this.Interface.notice_list+'1'
			).then(res=>{
        if(res.data.resultCode==0){
           this.noticeList=res.data.resultData;
         
        }
      })
		},

    swiperjump(url) {
        window.location.href = url.url;
    },
    getbanner() {
      this.$http
        .get(this.Interface.Picture, { params: {cache: true} })
        .then(res => {
          if (res.data.resultCode==0) {
            this.swiperSlides = res.data.resultData;
          }
        });
    },
 //获取推荐
  getrecomand(){
        this.$http.get(`${this.Interface.NodeServer}/plans?order=desc&type=1&limit=1`).then(res=>{
        if(res.data.resultCode=="0"){
          this.recomdlist=res.data.resultData
          console.log(this.recomdlist)  
        }
      })
  },
  //列表
    getList(){
    this.$http.get(`${this.Interface.NodeServer}/plan/notnew?order=desc&limit=2&page=1`).then(res=>{
      if(res.data.resultCode=="0"){
        this.list=res.data.resultData;
      }
    })
  },
  //获取公告
  getNewsList(){
     this.$http.get(`${this.Interface.NodeServer}/news/list?limit=9&page=1`).then(res=>{
      if(res.data.resultCode=="0"){
        this.newslist=res.data.resultData;
        for(let i=0;i++;i<this.newslist.length){
          this.newslist[i].updatedTime=moment(this.newslist[i].updatedTime).format("YYYY-MM-DD")
        }
      }
    })
  },
  }
  
};
</script>
<style lang="less">
.right_icon{
  background: url(../public/imgs/index/icon_nav_right.png) no-repeat;
  background-size: cover;
  width: 12px;
  height: 22px;;
}

.icon_fire{width: 18px;}
body {
  background: #f4f4f4;
}
.icon_laba{width: 24px;}
.page_home {
.empt{height: 120px;;}
.noticeBg{
  background: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 25px 30px;
  font-size: 28px;
  margin-bottom: 15px;
  color: #666666;
  .noticeTitleName{
    display: flex;
    height: 30px;
    overflow: hidden;
    ul{padding-left: 20px;}
  }
}
	
.recomand{
  background: #fff;
  .title{
    display: flex;
    padding: 25px 0px;
    margin: 0px 30px;
    border-bottom: 1Px solid #f2f2f2;
    justify-content: space-between;
    align-items: center;
    div{display: flex;justify-content: center;align-items: center}
    img{width:25px; }
    .center{flex:1;text-align: center;font-size: 28px;
    color: #666;margin-right: 80px;}
    .title_nav{
      color: #b89a54;
      font-size: 32px;
      margin-left: 10px;
    }
  }
  .product{
    text-align: center;
    background: url(../public/imgs/index/top_bg.png) no-repeat center center;
    background-size: 364px 364px;
    padding: 100px 0px;
    .percent{font-size:72px;color:#b89a54; border-bottom: 5px solid #b89a54;margin: 0 auto;width: 250px;margin-bottom: 30px;padding-bottom: 20px;};
    .day{font-size: 24px;color: #b89a54;padding-bottom: 20px;};
    .yuan{font-size: 24px;color: #b89a54;border-bottom: 2px solid #b89a54;;margin: 0 auto;width: 140px;padding-bottom: 30px; }
  }
  .invest{height: 80px;display: flex;justify-content: center;align-items: center;color: #fff;font-size: 36px;background: #b89a54}
}	
.product_list{
  background: #f4f4f4;
  padding: 15px 30px 0px 30px;;
  .title{
    height: 50px;
    background: url(../public/imgs/index/productlist_title.png) no-repeat #fff  ;
    background-position:center center;
    background-size: contain;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 26px;
    color: #666;
    border-bottom: 1Px solid #f2f2f2;
  }
  .detail{
      display: flex;
      padding: 30px 95px;
      justify-content:space-between;
      align-items: center;
      background: #fff;
      text-align: center;
      .year_con{font-weight: 600;font-size: 36px;color: #f7ad2b};
      .day{font-weight: 600;font-size: 36px;color: #323333;}
      .pre{font-weight: 600;font-size: 36px;color: #fe4c58}
      span{
        display: block;
        font-size: 22px;
        color: #666666;
        padding-top: 15px;
        font-family: inherit;
      }
    }
}
.news_list{
  display: flex;
  justify-content: space-between;
  padding: 20px 30px;
  border-bottom: 1Px solid #f2f2f2;
  background: #fff;
  .left_img{
    width: 280px;
    height: 200px;
  }
  .right_content{
    flex: 1;
    margin-left: 30px;
    .title{
      font-size: 28px;
      color: #323333;
      font-weight: 600;
      line-height: 40px;
      overflow: hidden;
      height: 70px;
    }
    .des{
      font-size: 24px;
      color: #666666;
      margin-top: 10px;
       line-height: 40px;
        overflow: hidden;
        height: 70px;
    }
    .time{
       margin-top: 10px;
      color: #999999;
      font-size: 24px;
      text-align: right;
    }
  }
}
}
</style>
