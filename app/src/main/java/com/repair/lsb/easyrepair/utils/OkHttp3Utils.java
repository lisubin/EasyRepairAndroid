package com.repair.lsb.easyrepair.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttp3Utils {


    private static OkHttpClient okHttpClient = null;

    public OkHttp3Utils() {
    }

    private static OkHttpClient getOkHttpClient() {
        synchronized (OkHttp3Utils.class) {
            if (okHttpClient == null) {
                okHttpClient = new OkHttpClient();
            }
        }
        return okHttpClient;
    }

    //上传文件
    public static void loadFile(String url,File file, String fileName, Callback callback) {
        OkHttpClient okHttpClient = getOkHttpClient();
        //设置文件类型
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //设置请求体
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("jpg", fileName, requestBody)
                .build();
        //请求方式
        Request request = new Request.Builder().url(url).post(body).build();

        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 1.接口地址
     * 2.接口回调
     */
    public static void doGet(String url, Callback callback) {
        OkHttpClient okHttpClient = getOkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }


//    public static void doPost(String url, Map<String,String> map, Callback callback){
//        OkHttpClient okHttpClient = getOkHttpClient();
//        FormBody.Builder builder = new FormBody.Builder();
//
//        //遍历map集合   设置请求体
//        for (String mapKey : map.keySet()){
//            builder.add(mapKey,map.get(mapKey));
//        }
//        //设置请求方式
//        Request request = new Request.Builder().url(url).post(builder.build()).build();
//        //执行请求方式    接口回调
//        okHttpClient.newCall(request).enqueue(callback);
//    }

    /**
     * 1.地址
     * 2.接口回调
     * 3.请求体
     */

    public static void doPost(String url, String json, Callback callback) {
        OkHttpClient okHttpClient = getOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, json)).build();
        //执行请求方式    接口回调
        okHttpClient.newCall(request).enqueue(callback);
    }

}