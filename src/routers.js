import Vue from 'vue';
import Router from 'vue-router';
const path = require('path');


Vue.use(Router);
export default new Router({
  // mode:"history",
  base: path.resolve(__dirname),
  app: "/",
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: resolve => require(['@/router/home'], resolve),
      meta: {
        title: '首页'
      }
    },
    {      // 产品列表
      path: '/productlist',
      name: 'productlist',
      component: resolve => require(['@/router/productlist'], resolve),
      meta: {
        title: '产品列表'
      }
    },
        {
      //发现
      path: '/appactive',
      name: 'appactive',
      component: resolve => require(['@/router/appactive'], resolve),
      meta: {
        title: "发现"
      }
    },
        {
      // 个人中心
      path: '/personalcenter',
      name: 'personalcenter',
      component: resolve => require(['@/router/personalcenter'], resolve),
      meta: {
        title: '个人中心'
      }
    },
    {  //公告列表
      path: '/newslist',
      name: 'newslist',
      component: resolve => require(['@/router/newslist'], resolve),
      meta: {
        title: '平台公告'
      }
    },
    {  //公告详情
      path: '/newslistdetail',
      name: 'newslistdetail',
      component: resolve => require(['@/router/newslistdetail'], resolve),
      meta: {
        title: '公告'
      }
    },
    {  //公告详情
      path: '/productdetail',
      name: 'productdetail',
      component: resolve => require(['@/router/productdetail'], resolve),
      meta: {
        title: '产品详情'
      }
    },
    {  //项目详情
      path: '/projectdetail',
      name: 'projectdetail',
      component: resolve => require(['@/router/projectdetail'], resolve),
      meta: {
        title: '项目详情'
      }
    },
    {  //项目资料
      path: '/projectimg',
      name: 'projectimg',
      component: resolve => require(['@/router/projectimg'], resolve),
      meta: {
        title: '相关资料'
      }
    },
    {  //投资记录
      path: '/investlist',
      name: 'investlist',
      component: resolve => require(['@/router/investlist'], resolve),
      meta: {
        title: '投资记录'
      }
    },
   { // 登录||注册
      path: '/login',
      name: 'login',
      component: resolve => require(['@/router/login'], resolve),
      meta: {
        title: '登录'
      }
    },
    { // 登录||注册
      path: '/login1',
      name: 'login1',
      component: resolve => require(['@/router/login1'], resolve),
      meta: {
        title: '登录'
      }
    },
    
    {
      //注册
      path: '/register',
      name: 'register',
      component: resolve => require(['@/router/register'], resolve),
      meta: {
        title: '注册'
      }
    },
        {
      //实名认证
      path: '/realname',
      name: 'realname',
      component: resolve => require(['@/router/realname'], resolve),
      meta: {
        title: '实名认证'
      }
    },
        {
      //首次设置交易密码
      path: '/changetradingpassword_new',
      name: 'changetradingpassword_new',
      component: resolve => require(['@/router/changetradingpassword_new'], resolve),
      meta: {
        title: "设置交易密码"
      }
    },
    {
      //选择银行卡
      path: '/banklist',
      name: 'banklist',
      component: resolve => require(['@/router/banklist'], resolve),
      meta: {
        title: "选择银行卡"
      }
    },
        {
      //投资确认正常产品
      path: '/investmentconform',
      name: 'investmentconform',
      component: resolve => require(['@/router/investmentconform'], resolve),
      meta: {
        title: "投资确认"
      }
    },
    {
      //选择红包             
      path: '/selectpackets',
      name: 'selectpackets',
      component: resolve => require(['@/router/selectpackets'], resolve),
      meta: {
        title: "投资确认"
      }
    },
    {
      //选择加息券            
      path: '/selectrate',
      name: 'selectrate',
      component: resolve => require(['@/router/selectrate'], resolve),
      meta: {
        title: "投资确认"
      }
    },
        {
      //充值
      path: '/recharge',
      name: 'recharge',
      component: resolve => require(['@/router/recharge'], resolve),
      meta: {
        title: "充值"
      }
    },
    {
      //媒体资讯列表
      path: '/medialist',
      name: 'medialist',
      component: resolve => require(['@/router/medialist'], resolve),
      meta: {
        title: "媒体资讯"
      }
    },
    {
      //媒体资讯详情
      path: '/medialistdetail',
      name: 'medialistdetail',
      component: resolve => require(['@/router/medialistdetail'], resolve),
      meta: {
        title: "资讯详情"
      }
    },
    {
      //常见问题
      path: '/question',
      name: 'question',
      component: resolve => require(['@/router/question'], resolve),
      meta: {
        title: "常见问题"
      }
    },
    {  //关于我们
      path: '/aboutus',
      name: 'aboutus',
      component: resolve => require(['@/router/aboutus'], resolve),
      meta: {
        title: "关于我们"
      }
    },
    {  //投资记录
      path: '/investlist_p',
      name: 'investlist_p',
      component: resolve => require(['@/router/investlist_p'], resolve),
      meta: {
        title: "投资记录"
      }
    },
    {  //资金明细
      path: '/financialdetails',
      name: 'financialdetails',
      component: resolve => require(['@/router/financialdetails'], resolve),
      meta: {
        title: "资金明细"
      }
    },
        {
      //回款计划
      path: '/accumulated',
      name: 'accumulated',
      component: resolve => require(['@/router/accumulated'], resolve),
      meta: {
        title: "回款计划"
      }
    },
        {
      //我的优惠券列表
      path: '/mycouponlist',
      name: 'mycouponlist',
      component: resolve => require(['@/router/mycouponlist'], resolve),
      meta: {
        title: "红包"
      }
    },
    {
      //加息券
      path: '/myrates',
      name: 'myrates',
      component: resolve => require(['@/router/myrates'], resolve),
      meta: {
        title: "加息券"
      }
    },
        {
      //设置中心
      path: '/seting',
      name: 'seting',
      component: resolve => require(['@/router/seting'], resolve),
      meta: {
        title: "设置"
      }
    },
        {
      //修改登录密码
      path: '/changeloginpassword',
      name: 'changeloginpassword',
      component: resolve => require(['@/router/changeloginpassword'], resolve),
      meta: {
        title: "修改登录密码"
      }
    },
        {
      //我的银行卡
      path: '/mybanklist',
      name: 'mybanklist',
      component: resolve => require(['@/router/mybanklist'], resolve),
      meta: {
        title: "我的银行卡"
      }
    },
        {
      //提现
      path: '/withdrawal',
      name: 'withdrawal',
      component: resolve => require(['@/router/withdrawal'], resolve),
      meta: {
        title: "提现"
      }
    },
        {
      //提现说明
      path: '/withdrawexplain',
      name: 'withdrawexplain',
      component: resolve => require(['@/router/withdrawexplain'], resolve),
      meta: {
        title: "提现说明"
      }
    },

  ]
})







































