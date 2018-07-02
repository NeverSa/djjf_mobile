import Vue from 'vue';
import $ from 'public/libs/zepto';
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import 'normalize.css';
import 'public/style/base.css';
require('vue2-animate/dist/vue2-animate.min.css')
import axios from "axios"
import Interface from "./GlobalData"
import Utils from "public/libs/Utils"
import router from './routers';
import vuex from 'vuex'
import VueForm from 'vue-form';
import VueHeader from "./components/vueheader"


Vue.use(VueForm);
Vue.use(vuex);
Vue.component('VueHeader', VueHeader)
var store = new vuex.Store({//store对象
  state: {
    sessionid: "",
    userId:"",
    hasBankCard:"",
    hasTradePassword:"",
  },
  mutations: {
     //显式的更改state里的数据
     login:function(state,user){
      state.sessionid=user.userAuth.token;
      localStorage.setItem("sessionid", user.userAuth.token);

      state.userId=user.userAuth.userId;
      localStorage.setItem("userId", user.userAuth.userId);
    //实名认证
      state.hasBankCard=user.hasBankCard;
      localStorage.setItem("hasBankCard", user.hasBankCard); 

    //交易密码
      state.hasTradePassword=user.hasTradePassword;
      localStorage.setItem("hasTradePassword", user.hasTradePassword);
   },
    logout:function(state){
      state.sessionid="";
      localStorage.setItem("sessionid", "");
      state.userId="";
      localStorage.setItem("userId", "");
      state.hasBankCard="";
      localStorage.setItem("hasBankCard", "");
      state.hasTradePassword="";
      localStorage.setItem("hasTradePassword", "");
    }
    //显式的更改state里的数据
  },
  getters: {
    //过滤state数据
  },
  actions: {
    //
  }
})


Vue.config.productionTip = false
Vue.prototype.$http = axios;
Vue.prototype.Axios = axios;
Vue.prototype.Interface = Interface;
Vue.prototype.Uitis = Utils;
window.APP = Utils;

Vue.use(MintUI);

 // 添加响应拦截器
axios.interceptors.response.use(function (response) {
  if((response.data.resultMsg=="已从其他设备登陆，请重新登陆"||response.data.resultMsg=="登录已过期，请重新登录")&&localStorage.getItem("sessionid")!==""){
    localStorage.setItem("sessionid",'');
    router.push("login")
     window.location.reload() 
  

   
  }
	try {
		if (typeof (response.data.data) == "string") {
			var Data = JSON.parse(response.data.data);
      response.data.data = Data
		}
	} catch (e) {
		// Toast("处理返回的数据发生异常~");
	}
	return response;
}, function (error) {
	// 对响应错误做点什么
	console.log("错误消息：" + error);
	return Promise.reject(error);
});

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  if (to.matched.length ===0) {                                        //如果未匹配到路由
     next('/home');   //如果上级也未匹配到路由则跳转登录页面，如果上级能匹配到则转上级路由
  } else {
    next();                                                                            //如果匹配到正确跳转
  }
})

Vue.filter('phone', function(value) {
  if(value){
    return value.substring(value.length-4,value.length)
  }else{
    return ""
  }
});
Vue.filter('NumFormat', function(value) {
  if(!value) return '0.00';
  if(value=="****") return "****"
  var intPart = Number(value).toFixed(0); //获取整数部分
  var intPartFormat = intPart.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,'); //将整数部分逢三一断

  var floatPart = ".00"; //预定义小数部分
  var value2Array = value.toString().split(".");

  //=2表示数据有小数位
  if(value2Array.length == 2) {
      floatPart = value2Array[1].toString(); //拿到小数部分

      if(floatPart.length == 1) { //补0,实际上用不着
          return intPartFormat ;
      } else {
          return intPartFormat ;
      }

  } else {
      return intPartFormat ;
  }

})
Vue.filter('newsdata', function(input) {
  if(input!=undefined){
    var pattern = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/;
      var formatedDate = input.replace(pattern, '$1-$2-$3 $4:$5:$6');
      return formatedDate
      
    }
});

Vue.filter('newDate', function (input) {
   if(input!=undefined){
    var pattern = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/;
      var formatedDate = input.replace(pattern, '$1-$2-$3 $4:$5:$6');
      return formatedDate.substring(0,10)
      
    }
      
 
})

//定义路由
new Vue({
  router,
  store,
  created(){
    // window.addEventListener("popstate", function(e) { 
    //   console.log(e) 
    //   }, false);

    if(localStorage.getItem("sessionid")===null){
      localStorage.setItem("sessionid",'');
      localStorage.setItem("hasBankCard",'');
      localStorage.setItem("userId",'');
      localStorage.setItem("hasTradePassword",'');
    }
    this.$store.state.sessionid= localStorage.getItem("sessionid");
    this.$store.state.hasBankCard= localStorage.getItem("hasBankCard");
    this.$store.state.hasTradePassword= localStorage.getItem("hasTradePassword");
    this.$store.state.userId= localStorage.getItem("userId");

  }
},
).$mount("#app");




//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG
//          佛曰:
//                  写字楼里写字间，写字间里程序员；
//                  程序人员写程序，又拿程序换酒钱。
//                  酒醒只在网上坐，酒醉还来网下眠；
//                  酒醉酒醒日复日，网上网下年复年。
//                  但愿老死电脑间，不愿鞠躬老板前；
//                  奔驰宝马贵者趣，公交自行程序员。
//                  别人笑我忒疯癫，我笑自己命太贱；
//                  不见满街漂亮妹，哪个归得程序员？

