<template>
  <div class="page_projectdetail">
    <div class="top">项目详情</div>
     <div v-html="detail"></div>
</div>
</template>
<script>
export default {
  name: "",
  data() {
    return {
     detail:"",
    };
  },
   components: {
  },
    computed: {
    
    },
    created() {
     this.init();
    },
  mounted() {
   
  },
  methods: {
    init(){
       this.$http.get(`${this.Interface.NodeServer}/plan/${this.$route.query.productid}`).then(res=>{
        if(res.data.resultCode=="0"){
          this.detail=res.data.resultData.description;
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
/*项目详情*/
.projectDetailWrap{
	width: 100%;

}

.projectDetailDiv{
	border-bottom:1Px dashed #9C9C9C;
	padding: 0.5rem 30px;
  line-height: 40px;
  background: #fff;
}

.projectDetailDiv h3{
	font-size:26px;
	font-weight: 600;
	color: #333;
	margin: 0.15625rem 0 0.3125rem 0;
	
}

[data-dpr="2"] .projectDetailDiv h3 {
		font-size: 32px;
	}
[data-dpr="3"] .projectDetailDiv h3 {
		font-size: 48px;
	}

.projectDetailDiv .projectDetailHtml,.projectDetailHtml h3{
	font-size: 12px;
    line-height: 150%;
    color: #666;
}

[data-dpr="2"] .projectDetailDiv .projectDetailHtml,[data-dpr="2"] .projectDetailHtml h3{
		font-size: 24px;
	}
[data-dpr="3"] .projectDetailDiv .projectDetailHtml ,[data-dpr="3"] .projectDetailHtml h3 {
		font-size: 36px;
	}

.projectDetailDiv .tableone {
  vertical-align: middle;
    width: 100%;
    border-collapse: collapse;
    border-spacing: 0;
    border-left: 1Px solid #ddd;
    border-top: 1Px solid #ddd;
}

.projectDetailDiv .tableone td {
    border-bottom: 1Px solid #ddd;
    border-right: 1Px solid #ddd;
    height: 0.859375rem;
   font-size: 22px !important;
    text-align: center;
    vertical-align: middle;
   /* width: 192px;*/
   span{font-size: 22px !important}
}
[data-dpr="2"] .projectDetailDiv .tableone td {
		font-size: 24px;
	}
[data-dpr="3"] .projectDetailDiv .tableone td {
		font-size: 36px;
	}
.projectDetailDiv .tableone td:nth-child(odd) {
    background: #f9f9f9;
   /* width: 134px;*/
}
} 
</style>
