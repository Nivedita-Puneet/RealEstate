package com.nivedita.realestate.di.module;

import com.nivedita.realestate.BuildConfig;
import com.nivedita.realestate.model.network.PropertyService;
import com.nivedita.realestate.util.ConstantsUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A dagger module which provides all the services to consume End Point API.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {

        if (BuildConfig.DEBUG) {

            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
        }
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        return new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).build();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitBuilder() {

        return new Retrofit.Builder().
                client(provideOkHttpClient())
                .addConverterFactory(provideGsonConverterFactory())
                .addCallAdapterFactory(provideRxJava2CallAdapterFactory())
                .baseUrl(ConstantsUtil.BASE_URL).build();
    }

    @Provides
    @Singleton
    PropertyService providePropertyService(){

        return provideRetrofitBuilder().create(PropertyService.class);
    }
}
