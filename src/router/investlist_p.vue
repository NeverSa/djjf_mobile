<template>
    <div class="page-investlist">
        <div class="top">投资记录</div>
    <table width="100%" class="table">
        <thead>
            <tr>
                <th>
                    项目名称    
                </th>
                <th>
                    金额(元)
                </th>
                <th>
                    加息劵(%)
                </th>
                <th>
                    时间
                </th>
            </tr>
        </thead>
        <tr v-for="item in list">
            <td>
               {{item.name}}
            </td>
            <td class="c_1">
                 {{(item.money/100).toFixed(2)}}
            </td>
            <td class="c_2">
              {{item.raisingRates/10}}
            </td>
            <td>
            {{item.createdTime|newDate}}
            </td>
        </tr>
  
      
    </table>
    <div class="noresult">没用更多了</div>
    </div>
</template>
<script>
import { Toast } from "mint-ui";

export default {
  name: "",
  data() {
    return {
        list:[],
       sessionid: this.$store.state.sessionid || "",
      userId:this.$store.state.userId || "",
    };
  },

  created() {
      this.init();
     
  },
  mounted() {},
  methods: {
    init(){
       this.$http.get(`${this.Interface.NodeServer}/user/${this.userId}/investments?page=1&limit=100&token=${this.sessionid}`).then(res=>{
        if(res.data.resultCode=="0"){
         this.list=res.data.resultData
        }
      })
    },
     
  }
};
</script>
<style lang="less">
body,html{
    background: #fff;
}

.page-investlist {
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
.c_1{color: #ff8605}
.c_2{color: #fe4c58}
table
        {
            border-collapse: collapse;
            margin: 0 auto;
            text-align: center;
        }
        table td, table th
        {
            color: #666;
            height: 30px;
        }
        table thead th
        {
            background-color: #f7f7f7;
            width: 100px;
            padding: 15px 0px;
        }
        table tr
        {
            background: #fff;
            border-bottom: 1Px solid #f2f2f2;
           
        }
       
      table tr td{
           padding: 20px 0px;
      }

  
}
</style>
