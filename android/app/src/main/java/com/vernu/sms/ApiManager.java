package com.vernu.sms;

import android.content.Context;

import com.vernu.sms.helpers.ApiEndpointHelper;
import com.vernu.sms.services.GatewayApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static GatewayApiService apiService;
    private static String lastBaseUrl;

    public static GatewayApiService getApiService(Context context) {
        String baseUrl = ApiEndpointHelper.getApiBaseUrl(context);
        if (apiService == null || lastBaseUrl == null || !lastBaseUrl.equals(baseUrl)) {
            apiService = createApiService(baseUrl);
            lastBaseUrl = baseUrl;
        }
        return apiService;
    }

    public static void clearCachedApiService() {
        apiService = null;
        lastBaseUrl = null;
    }

    private static GatewayApiService createApiService(String baseUrl) {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(GatewayApiService.class);

        return retrofit.create(GatewayApiService.class);
    }
}
