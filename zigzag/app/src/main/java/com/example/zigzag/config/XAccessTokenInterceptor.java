package com.example.zigzag.config;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.zigzag.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.zigzag.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        System.out.println("인터셉트 진입");
        final Request.Builder builder = chain.request().newBuilder();
        System.out.println("빌더 생성");
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken);
        }
        System.out.println("토큰 처리");
        return chain.proceed(builder.build());
    }
}
