package com.wangbo.familychat.common;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class OkHttpClientUtil {


    public static String post(String url, int port, Map<String, String> requestBody) {
        String res = null;

        OkHttpClient client = new OkHttpClient();

        Set<Map.Entry<String, String>> entries = requestBody.entrySet();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : entries) {
            builder.add(entry.getKey(), entry.getValue());
        }

        RequestBody formBody = builder.build();


        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            res = response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }






    public static void main(String[] args) throws Exception {

    }
}
