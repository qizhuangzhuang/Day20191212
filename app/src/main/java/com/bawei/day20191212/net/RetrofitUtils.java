package com.bawei.day20191212.net;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:祁壮壮
 *@Date: 2019/12/12
 *@Time:9:10
 *@Description功能: * */
public class RetrofitUtils {

    public static RetrofitUtils instance = new RetrofitUtils();
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/small/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String ss) {
                        if (ss.startsWith("{") || ss.startsWith("[")){
                            Logger.json(ss);
                        }else {
                            Logger.d(ss);
                        }
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY)).build()).build();
    }

    public static RetrofitUtils getInstance(){
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
