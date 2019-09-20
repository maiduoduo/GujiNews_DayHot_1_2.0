package com.cnews.guji.smart.common.net;


import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @package: RetrofitClient
 * @author： JSYL-DCL
 * @describe： 网络请求框架Retrofit的封装工具类
 * @email： 11442865
 */
public class RetrofitClient {

    private static volatile RetrofitClient instance;
    private ApiService apiService;
    private static Context _context;
//    https://suggest.taobao.com/sug?code=utf-8&q=裤子
//    https://ditu.amap.com/service/regeo?longitude=121.04925573429551&latitude=31.315590522490712
//    private String baseUrl = "https://ditu.amap.com/";
    private String baseUrl = "https://suggest.taobao.com/";

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance(Context context) {
        _context = context;
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    /**
     * 设置Header
     *
     * @return
     */
    private Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        //添加Token
                        .header("token", "");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

    }

    /**
     * 设置拦截器
     * @return
     */
    private Interceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //显示日志
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public ApiService getApi() {
        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient().newBuilder()
                //设置Header
                .addInterceptor(getHeaderInterceptor())
                //设置拦截器
                .addInterceptor(getInterceptor())
                .addInterceptor(new AddCookiesInterceptor(_context))
                .addInterceptor(new SaveCookiesInterceptor(_context))
                .connectTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .readTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .writeTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                //设置网络请求的Url地址
                .baseUrl(baseUrl)
                //设置数据解析器,添加gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                //添加rxjava转换器,设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建—— 网络请求接口—— 实例
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }


}
