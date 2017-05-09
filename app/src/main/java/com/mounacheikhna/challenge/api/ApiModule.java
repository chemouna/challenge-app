package com.mounacheikhna.challenge.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mounacheikhna.challenge.BuildConfig;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class ApiModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder().baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkhttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }

    @Provides
    @Singleton
    TflApi provideSherpaApi(Retrofit retrofit) {
        return retrofit.create(TflApi.class);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().registerTypeAdapterFactory(ModelAdapterFactory.create()).create();
    }
}
