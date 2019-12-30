package com.bawei.day1230.utils;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * ProjectName: Day1230
 * PackageName: com.bawei.day1230.utils
 * ClassName:   OkHttpUtils
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/30_13:42
 */
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler;

    private OkHttpUtils() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public void doGet(String url, Map<String, String> params, final OkHttpDataCallBack httpDataCallBack) {
        String httpUrl = url + "?";
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
                httpUrl += stringStringEntry.getKey() + "=" + stringStringEntry.getValue() + "&";
            }
        } else {
            httpUrl = url;
        }


        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                final IOException error = e;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (httpDataCallBack != null) {
                            httpDataCallBack.failure(error);
                        }
                    }
                });

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (httpDataCallBack != null) {
                            httpDataCallBack.success(string);
                        }
                    }
                });
            }
        });

    }


    public void doPost(String url, Map<String, String> params, final OkHttpDataCallBack httpDataCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
                builder.add(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                final IOException error = e;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (httpDataCallBack != null) {
                            httpDataCallBack.failure(error);
                        }
                    }
                });

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (httpDataCallBack != null) {
                            httpDataCallBack.success(string);
                        }
                    }
                });
            }
        });

    }


    public interface OkHttpDataCallBack {
        void success(String json);

        void failure(Throwable throwable);
    }

}
