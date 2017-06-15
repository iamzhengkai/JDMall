package com.kevin.jdmall.api;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Function:
 *
 * @FileName: com.kevin.network_demo.api.LoggingInterceptor.java
 * @author: zk
 * @date: 2017-03-11 12:19
 */

public class RestfulInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Response response = chain.proceed(chain.request());
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        try {
            Logger.i("content===========>" + content);
            JSONObject jsonObject = new JSONObject(content);
            //TODO 增加null判断
            if(!jsonObject.getBoolean("success") && jsonObject.getString("result").equals("")){
                Logger.d("content===============>" + content);
                String[] splitArray = content.split(",");
                String split = splitArray[2].split(":")[0] + ":null}";
                content = splitArray[0] +"," + splitArray[1] + "," + split;
                Logger.d("content===============>" + content);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Logger.d(content);
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}