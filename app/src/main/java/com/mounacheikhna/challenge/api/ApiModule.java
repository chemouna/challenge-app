package com.mounacheikhna.challenge.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.mounacheikhna.challenge.BuildConfig;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class ApiModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder().baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build();
    }

    @Provides
    @Singleton
    static OkHttpClient.Builder provideOkhttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }

    @Provides
    @Singleton
    public OkHttpClient provideApiClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    static TflApi provideSherpaApi(Retrofit retrofit) {
        return retrofit.create(TflApi.class);
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return new GsonBuilder().registerTypeAdapterFactory(ModelAdapterFactory.create()).create();
    }
}
