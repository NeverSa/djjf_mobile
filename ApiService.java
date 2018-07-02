package com.android.fengbao.http;

import com.android.fengbao.base.BaseResponseBean;
import com.android.fengbao.bean.AccountInfoRetBean;
import com.android.fengbao.bean.BankCardItem;
import com.android.fengbao.bean.BannerBean;
import com.android.fengbao.bean.BindBandCardInfoBean;
import com.android.fengbao.bean.CollectionPlanBean;
import com.android.fengbao.bean.FmInvestmentBean;
import com.android.fengbao.bean.FundDetailBean;
import com.android.fengbao.bean.HelpListBean;
import com.android.fengbao.bean.InvestDetailRetBean;
import com.android.fengbao.bean.InvestRecordBean;
import com.android.fengbao.bean.InvitFriendRetBean;
import com.android.fengbao.bean.LoginRetBean;
import com.android.fengbao.bean.MyCouponBean;
import com.android.fengbao.bean.MyInvestRecordBean;
import com.android.fengbao.bean.NewsDetailRetBean;
import com.android.fengbao.bean.OpenScreenPicRetBean;
import com.android.fengbao.bean.PlateformNoticeBean;
import com.android.fengbao.bean.RechargeRecordBean;
import com.android.fengbao.bean.RechargeRetBean;
import com.android.fengbao.bean.RegistOrLoginRetBean;
import com.android.fengbao.bean.SettingUserInfoRetBean;
import com.android.fengbao.bean.TotalDataRetBean;
import com.android.fengbao.bean.UpdateCheckRetBean;
import com.android.fengbao.bean.UserInfoBean;
import com.android.fengbao.bean.WithDrawalsCountRetBean;
import com.android.fengbao.bean.WithDrawalsRecordBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bjl on 2017/10/21.
 */

public interface ApiService {

    //登录code 	string 	是 	验证码 		6381 from 	string 	是 	端 		web name 	string 	是 	用户名或手机号码 		17774587458 password 	string 	是 	密码
    @POST("user/login")
    Call<BaseResponseBean<LoginRetBean>> login(@Query("name") String name, @Query("password") String password, @Query("code") String code, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //登录code 	string 	是 	验证码 		6381 from 	string 	是 	端 		web name 	string 	是 	用户名或手机号码 		17774587458 password 	string 	是 	密码
    @POST("user/login")
    Observable<BaseResponseBean<LoginRetBean>> login2(@Query("name") String name, @Query("password") String password, @Query("code") String code, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //用户注册
//    password 	object 	是 	用户 		123456
//    phone 	object 	是 	用户信息 		15512345689
//    code 	number 	是 	短信验证码 		56430
//    recommendCode 	string 	是 	推荐码 		17796669183
//    from 	string 	是
//    channel
    //imei
    // mac
    @POST("user/register")
    Call<BaseResponseBean<UserInfoBean>> register(@Query("phone") String phone, @Query("password") String password, @Query("code") String code, @Query("recommendCode") String recommendCode, @Query("channel") String channel,
                                                  @Query("imei") String imei, @Query("mac") String mac, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //更改密码token 	string 	是 	令牌
//    userId 	number 	是 	用户ID
//    password 	number 	是 	老密码
//    passwordNew 	number 	是 	新密码
    @POST("user/{userId}/updatePassword")
    Call<BaseResponseBean> updatePassword(@Path("userId") String userId, @Query("password") String password, @Query("passwordNew") String passwordNew, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //重置密码
    @POST("user/resetpwd")
    Call<BaseResponseBean> resetpwd(@Field("username") String username, @Query("userpwd") String userpwd, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //注册：发送短信
//    phone 	string 	是 			18812345678
//    code 	string 	是 	图形验证码 		5511 app不传
//    from 	string 	是
    @POST("sms/register")
    Call<BaseResponseBean> smsRegister(@Query("phone") String phone, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //找回密码：发送短信 phone 	string 	是 	手机号码 		15236269381
//    code 	number 	是 	短信验证码 		50356
//    pwd1 	number 	是 	密码1 		123456
//    pwd2 	number 	是 	密码2
    @POST("sms/findpwd")
    Call<BaseResponseBean> smsfindpwd(@Query("phone") String phone, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //找回密码phone 	string 	是 	手机号码 		15236269381
//    code 	number 	是 	短信验证码 		50356
//    pwd 	number 	是 	新密码
    @POST("user/findpwd")
    Call<BaseResponseBean> findpwd(@Query("phone") String phone, @Query("code") String code, @Query("pwd") String pwd, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //1.1、个人中心-我的账户
    @GET("user/{userId}/accountInfo")
    Call<BaseResponseBean<AccountInfoRetBean>> accountInfo(@Path("userId") String userId, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //1.2、个人中心-投资数据
    @POST("user/6/investData")
    Call<BaseResponseBean> investData(@Field("username") String username, @Query("userpwd") String userpwd);

    //1.3、个人中心-回款计划page 	string 	是 	1
//    limit 	string 	是 	10
//    status 	string 	是 	还款状态
//    startDate 	string 	是 	开始时间
//    endDate 	string 	是 	开始时间
    @GET("user/{userId}/repays")
    Call<BaseResponseBean<List<CollectionPlanBean>>> repays(@Path("userId") String userId, @Query("page") String page, @Query("limit") String limit, @Query("status") String status, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //1.4、个人中心-查询用户信息
    @GET("user/{userId}/userInfo")
    Call<BaseResponseBean<SettingUserInfoRetBean>> getUserInfo(@Path("userId") String userId, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //1.5、个人中心-查询投资记录
    @GET("user/{userId}/investments")
    Call<BaseResponseBean<List<MyInvestRecordBean>>> userinvestments(@Path("userId") String userId, @Query("page") String page, @Query("limit") String limit, @Query("status") String status, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //1.6、个人中心-获取用户实名信息
    @POST("user/idcard")
    Call<BaseResponseBean> idcard(@Field("username") String username, @Query("userpwd") String userpwd);

    //1.8、个人中心-资金明细page 	string 	是 			1
//    limit 	string 	是 			1
//    startDate 	string 	是 			1509884174000
//    endDate 	string 	是
//    state 	string 	是
    @GET("user/{userId}/tradeRecords")
    Call<BaseResponseBean<List<FundDetailBean>>> tradeRecords(@Path("userId") String userId,
                                                              @Query("page") String page, @Query("limit") String limit, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //设置或更新交易密码token 	string 	是 	令牌 		a07e4ece9ac60dc5e5e2870b362139fc
//    pwd 	string 	是 	密码 		123456
//    code 	string 	是 	短信验证码
    @POST("user/{userId}/account/tradepwd")
    Call<BaseResponseBean> tradepwd(@Path("userId") String userId, @Query("pwd") String pwd, @Query("code") String code, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //设置交易密码发送短信
    @GET("sms/trade")
    Call<BaseResponseBean> smsTrade(@Query("from") String from, @Query("version") String version, @Query("token") String token);

    //2.2、个人中心-获取绑定银行卡
    @GET("user/{userId}/account/cards")
    Call<BaseResponseBean<List<BindBandCardInfoBean>>> getBindcards(@Path("userId") String userId, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //2.3、个人中心-快捷充值
    @POST("user/account/recharge/dir")
    Call<BaseResponseBean> dir(@Field("username") String username, @Query("userpwd") String userpwd);

    //2.4、个人中心-网银充值
    @POST("user/account/recharge/union")
    Call<BaseResponseBean> union(@Field("username") String username, @Query("userpwd") String userpwd);

    //推广记录
    @POST("user/recommend")
    Call<BaseResponseBean> recommend(@Field("username") String username, @Query("userpwd") String userpwd);

    //奖励记录
    @POST("user/reward")
    Call<BaseResponseBean> reward(@Field("username") String username, @Query("userpwd") String userpwd);

    //2.5、个人中心-获取累计资金数据
    @POST("user/{userId}/totalData")
    Call<BaseResponseBean<TotalDataRetBean>> totalData(@Path("userId") String userId, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //2.6、个人中心-获取推荐链接
    @POST("user/account/2/recommend/url")
    Call<BaseResponseBean> recommendurl(@Field("username") String username, @Query("userpwd") String userpwd);


    //1.1、查询计划列表
//    type 	number 	否 	计划类型（1，新手标；0，非新手标） 		0
//    page 	number 	否 	页码 		1
//    limit 	number 	否 	条数
    @GET("plans")
    Call<BaseResponseBean<List<FmInvestmentBean>>> plans(@Query("type") String type, @Query("page") String page, @Query("limit") String limit, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //1.2、用户投资
    //        token 	string 	是 	令牌
//        userId 	number 	是 	用户ID
//        planId 	number 	是 	计划ID
//        money 	number 	是 	金额
//        pocketId 	number 	否 	红包ID
//        raisingId 	number 	否 	加息券
//        specialPlanPassword 	string 	否 	定向标密码（定向标才有）
//        tradePassword 	string 	否 	交易密码（现流程不校验，不传）
    @POST("plan/{planId}/join")
    Call<BaseResponseBean<InvestDetailRetBean>> planJoin(@Path("planId") String planId, @Query("userId") String userId, @Query("money") int money, @Query("pocketId") String pocketId, @Query("raisingId") String raisingId,
                                                         @Query("specialPlanPassword") String specialPlanPassword, @Query("tradePassword") String tradePassword, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //1.3、获取用户券
    @POST("user/2/account/vouchers")
    Call<BaseResponseBean> vouchers(@Field("username") String username, @Query("userpwd") String userpwd);

    //1.4、查询计划详情
    @GET("plan/{planId}")
    Call<BaseResponseBean<InvestDetailRetBean>> plandetail(@Path("planId") String planId, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //1.5、获取计划投资列表
    @GET("plan/{planId}/investments")
    Call<BaseResponseBean<List<InvestRecordBean>>> planInvestments(@Path("planId") String planId, @Query("page") String page, @Query("limit") String limit, @Query("from") String from, @Query("version") String version, @Query("token") String token);


    //获取公告列表page 	number 	是 	页码 		1
//    limit 	number 	是 	条数
    @GET("notice/list")
    Call<BaseResponseBean<List<PlateformNoticeBean>>> noticeList(@Query("page") String page, @Query("limit") String limit, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //4、获取公告详情
    @GET("notice/details/{noticeId}")
    Call<BaseResponseBean<NewsDetailRetBean>> noticeDetails(@Path("noticeId") String noticeId, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //获取新闻列表 page 	number 	是 	页码 		1
//    limit 	number 	是 	条数
    @GET("news/list")
    Call<BaseResponseBean<List<PlateformNoticeBean>>> newsList(@Query("page") String page, @Query("limit") String limit, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //5、获取新闻详情
    @GET("news/details/{newsId}")
    Call<BaseResponseBean<NewsDetailRetBean>> newsDetails(@Path("newsId") String newsId, @Query("from") String from, @Query("version") String version, @Query("token") String token);


    //优质项目推荐1
    @GET("plan/notnew")
    Call<BaseResponseBean<List<FmInvestmentBean>>> planNotNew(@Query("page") String page, @Query("limit") String limit, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //获取用户可用的券
//    token 	string 	是 			8cbf0979f9061f15dbd91a4b4e859a5f
//    type 	number 	否 	券类型（0：红包券，1：体验金券，2：利息券） 		0
//    limit 	number 	否 	条数 		10
//    page 	number 	否 	页码 		1
//    amount 	number 	是 	金额 		100000
//    cycle 	number 	是 	周期（天数） 		100
    //int type, int page, int limit, int amount, int cycle);
    @GET("user/{userId}/account/canusevouchers/{planId}")
    Call<BaseResponseBean<List<MyCouponBean>>> canusevouchers(@Path("userId") String userId, @Path("planId") String planId, @Query("type") int type, @Query("page") int page, @Query("limit") int limit, @Query("amount") int amount,
                                                              @Query("from") String from, @Query("version") String version, @Query("token") String token);


    //1.3、获取用户券
//    token 	string 	是 	令牌 		02e83940bd508e853d9be8e628b47fec
//    type 	number 	是 	券类型（0红包券，1体验金券，2利息券） 		1
//    page 	number 	否 	页码 		1
//    limit 	number 	否 	条数 		10
//    status 	number 	否 	券的状态：（0：正常 1：过期2：已使用 ）
    @GET("user/{userId}/account/vouchers")
    Call<BaseResponseBean<List<MyCouponBean>>> vouchers(@Path("userId") String userId, @Query("type") int type, @Query("page") int page, @Query("limit") int limit, @Query("status") int status,
                                                        @Query("from") String from, @Query("version") String version, @Query("token") String token);


    //2.8、获取银行列表
    @GET("user/bankcode/list")
    Call<BaseResponseBean<List<BankCardItem>>> bankList(@Query("from") String from, @Query("version") String version, @Query("token") String token);

    //实名与绑卡
//    token 	string 	是 			da28b0def04acec8eaf8eeb206d76672
//    realName 	string 	是 			杜亚磊
//    idCard 	string 	是 			411403199108032031
//    province 	string 	是 			浙江
//    city 	string 	是 			杭州
//    district 	string 	是 			西湖
//    subbranch 	string 	是 			西湖支行
//    cardNumber 	string 	是 			6236681280000622413
//    bankCode 	string 	是 			0801050000
    @POST("user/{userId}/account/card/binding")
    Call<BaseResponseBean> userAndCardBind(@Path("userId") String userId, @Query("realName") String realName, @Query("idCard") String idCard, @Query("subbranch") String subbranch, @Query("cardNumber") String cardNumber, @Query("bankCode") String bankCode, @Query("province") String province,
                                           @Query("city") String city, @Query("district") String district, @Query("from") String from, @Query("version") String version, @Query("token") String token);


    //1、获取首页banner列表
//    type 	string 	是 	pc端传：index/app传：index_mobile
//    from 	string 	是 	android、iOS
    @GET("banner/list")
    Call<BaseResponseBean<List<BannerBean>>> bannerList(@Query("type") String type, @Query("from") String from, @Query("version") String version);


    //2.9、个人中心-手机快捷充值
//    token 	string 	是 	69fb79e9ed63ffc53eb46af9b58c936b
//    tradePassword 	string 	是 	123456
//    money 	string 	是 	10000
    @POST("user/{userId}/account/recharge/android")
    Call<BaseResponseBean<RechargeRetBean>> rechargeAndroid(@Path("userId") String userId, @Query("tradePassword") String tradePassword, @Query("money") String money,
                                                            @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //2.0、个人中心-申请提现
//    token 	string 	是 			0888669c8705abbd367bc1478fd01904
//    tradePassword 	string 	是
//    money 	string 	是 			100000
    @POST("user/account/{userId}/withdraw")
    Call<BaseResponseBean> withdraw(@Path("userId") String userId, @Query("tradePassword") String tradePassword, @Query("money") String money,
                                    @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //2.7、个人中心-获取提现次数
    @GET("user/account/{userId}/withdraw/count")
    Call<BaseResponseBean<WithDrawalsCountRetBean>> withdrawCount(@Path("userId") String userId, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //3.0、查询充值记录
//    token 	string 	是
//    page 	string 	是
//    limit 	string 	是
    @GET("user/{userId}/recharge/list")
    Call<BaseResponseBean<List<RechargeRecordBean>>> rechargeList(@Path("userId") String userId, @Query("page") String page, @Query("limit") String limit, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //获取提现列表
//    token 	string 	是 			cd56119a577e6ea7450454e42ca0340a
//    status 	string 	是
//    startDate 	string 	是
//    endDate 	string 	是
//    page 	string 	是
//    limit 	string 	是
    @GET("user/{userId}/withdraw/list")
    Call<BaseResponseBean<List<WithDrawalsRecordBean>>> withdrawList(@Path("userId") String userId, @Query("page") String page, @Query("limit") String limit, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //9、更新检查
    @GET("update/check")
    Call<BaseResponseBean<UpdateCheckRetBean>> updateCheck(@Query("from") String from, @Query("version") String version);

    //8、帮助问题列表
    @GET("help/list")
    Call<BaseResponseBean<List<HelpListBean>>> helpList(@Query("page") String page, @Query("limit") String limit, @Query("from") String from, @Query("version") String version);

    //9、更新检查
    @GET("activity/invitFriend")
    Call<BaseResponseBean<InvitFriendRetBean>> invitFriend(@Query("from") String from, @Query("version") String version);

    //2.0、退出登录
    @POST("user/logout/{userId}")
    Call<BaseResponseBean> userLogout(@Path("userId") String userId, @Query("from") String from, @Query("version") String version, @Query("token") String token);

    //2.0、检测手机号是否已注册
//  phone 	string 	是
    @POST("user/check")
    Call<BaseResponseBean<RegistOrLoginRetBean>> userCheck(@Query("phone") String phone, @Query("from") String from, @Query("version") String version);

    //1、头条广告激活
//  imei 	string 	是 	Android必输
//  mac 	string 	是 	必输
    @POST("channel/toutiao/activate")
    Call<BaseResponseBean> toutiaoActivate(@Query("imei") String imei, @Query("mac") String mac, @Query("from") String from, @Query("version") String version);

    //13、手机APP开屏页
    //from 	string 	是 	（安卓：android；水果：iOS） 		iOS
//    type 	string 	是 101-720*1280 102 1080*1920
    @GET("openscreen/pic")
    Call<BaseResponseBean<OpenScreenPicRetBean>> openScreenPic(@Query("type") String type, @Query("from") String from, @Query("version") String version);

}
