<template>
  <div class="page_projectdetail">
    <div class="top">相关资料</div>
       <transition name="slide-fade" class="fadeView">
            <div v-if="show">
                <image-view :imgArr="imgArr" 
                            :showImageView="true"
                            :imageIndex="imageIndex"
                            v-on:hideImage="hideImageView"></image-view>
            </div>
        </transition>
     <div class="imglist">
       <img v-for="(item, index) in imgArr" :src="item" @click="selectImg(index)">
     </div>
</div>
</template>
<script>
import imageView from 'vue-imageview'
export default {
  name: "",
  data() {
    return {
     imgArr:[],
      show: false,
        // 从哪一张图片开始
        imageIndex: 0
    };
  },
   components: {
       'image-view': imageView
  },
    computed: {
    
    },
    created() {
     this.init();
    },
  mounted() {
   
  },
  methods: {
       hideImageView () {
        this.show = false
      },
       selectImg (index) {
        this.show = true
        this.imageIndex = index
      },
    init(){
       this.$http.get(`${this.Interface.NodeServer}/plan/${this.$route.query.productid}`).then(res=>{
        if(res.data.resultCode=="0"){
            for(let item of res.data.resultData.images){
                this.imgArr.push(item.url)
            }
        }
      })
    },
 
  },
   
};
</script>
<style lang="less">

.page_projectdetail{
  background: #fff;
  height: 100%;
.slide-fade-enter-active {
        transition: opacity .5s ease;
    }

    .slide-fade-leave-active {
        transition: opacity .5s ease;
    }

    .slide-fade-enter, .slide-fade-leave-active {
        opacity: 0;
    }
    h1, h2 {
        margin: 0;
        padding: 0;
    }
    .imglist{
        display: flex;
        flex-wrap: wrap;
        img{width: 250px;height: 250px;;}
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
     border-bottom: 1Px solid #f2f2f2;
   }
} 
</style>
