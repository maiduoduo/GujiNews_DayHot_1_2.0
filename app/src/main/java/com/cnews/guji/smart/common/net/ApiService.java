package com.cnews.guji.smart.common.net;

import com.cnews.guji.smart.common.bean.ClothesBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsMainBean;
import com.cnews.guji.smart.ui.model.NewsDetailModel;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @package: ApiService
 * @author： JSYL-DCL
 * @describe： 网络请求地址配置
 * @email： 11442865
 */
public interface ApiService {
    //https://suggest.taobao.com/sug?code=utf-8&q=裤子
    //https://ditu.amap.com/service/regeo?longitude=121.04925573429551&latitude=31.315590522490712

    /**
     * 获取淘宝服装样式
     * @param code
     * @param q
     * @return
     */
    @GET("sug")
    Flowable<ClothesBean> getClothes(@Query("code") String code, @Query("q") String q);


    /**
     * 头条
     * @param id
     * @param start
     * @param end
     * @return
     */
    //http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
    @GET("nc/article/headline/{id}/{start}-{end}.html")
    Flowable<NewsMainBean> getHeaderNews(@Path("id") String id,
                                         @Path("start") int start,
                                         @Path("end") int end);

    /**
     * 新闻详情
     * @param articleUrl
     *          @Url 它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
     *           baseUrl 需要符合标准，为空、""、或不合法将会报错
     * @return
     */
    @GET
    Flowable<NewsDetailBean> getNewsDetail(@Url String articleUrl);
}
