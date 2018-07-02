<template>
  <div class="page_newslist">
    <div class="top">媒体资讯</div>
           <div class="t" @click="changeli(index,item)" v-for="(item,index) in headerData">
                <div class="item_t">
                     <div> {{item.title}}</div>
                       <div class="jump" :class="{'anmate':item.show}"></div>
             </div>
                       <div v-show="item.show"  class="content"> 
                        <!-- 循环二级菜单数据并使用.stop阻止冒泡 -->
                        <div  v-on:click.stop="doThis(index)" ><pre>{{item.content}}</pre></div>
             </div>
           </div>
        
</div>
</template>
<script>

export default {
  name: "",
  data() {
    return {
      headerData:"",
    };
  },
   components: {
  },
    computed: {
    
    },
    created() {
      this.getNewsList();
     
    },
  mounted() {
   
  },
  methods: {
        changeli: function(ind, item) {
                // 先循环数据中的show将其全部置为false,此时模板里的v-if判断生效关闭全部二级菜单,并移除样式
                this.headerData.forEach(i => {
                    // 判断如果数据中的headerData[i]的show属性不等于当前数据的show属性那么headerData[i]等于false
                    if (i.show !== this.headerData[ind].show) {
                        i.show = false;
                    };
                });
                // 取反(true或false)
                item.show = !item.show;
                console.log(item.name)
            },
            doThis: function(index) {
                // alert(index)
            },
  //获取公告
  getNewsList(){
     this.$http.get(`${this.Interface.NodeServer}/help/list?page=1&limit=10`).then(res=>{
      if(res.data.resultCode=="0"){
      
       for(let i=0;i<res.data.resultData.length;i++){
           res.data.resultData[i].show=false;
       }
         this.headerData=res.data.resultData;
       console.log(this.headerData)
      }
    })
  },

 
  },
   
};
</script>
<style lang="less">

.page_newslist{
  background: #f7f7f7;
  height: 100%;
    .anmate{
            transform:rotate(270deg);
      }
  .item_t{
    
            width: 100%;
                border-bottom: 1Px solid #f2f2f2;
                color: 20px;
                display: flex;
                background: #fff;
                padding: 20px 35px;
                box-sizing: border-box;
                justify-content: space-between;
                align-items: center;
                font-size: 30px;color: #000;
                   .jump{
                    width: 34px;
                    height: 54px;
                    background: url(../public/imgs/faxian/icon_right_jump.jpg) no-repeat ;
                    background-size: cover;
                  }
  }
  pre{
      line-height: 40px;
      padding: 10px 30px;
      word-break:break-all; /* 支持IE和chrome，FF不支持*/
word-wrap:break-word; /* 以上三个浏览器均支持 */
    width: 750px;
    overflow: hidden;
    box-sizing: border-box;
    display: block;
  }
}
</style>
