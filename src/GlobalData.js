let dev = true;
let NodeServer;
if (dev) {
  NodeServer = "http://dj.hzdjjf.com/api";
} else {
  NodeServer = "https://m.youxuanqb.com/router/";
}
var Interface = {
  //首页banner
    NodeServer:NodeServer,
    Picture:NodeServer +"/banner/list?type=index_mobile",
    //获取平台公告
   notice_list:NodeServer +"/notice/list?limit=7&page=",

};

export default Interface;
