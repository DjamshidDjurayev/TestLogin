package com.example.djamshiddjuraev.testlogin.di.module

import com.example.djamshiddjuraev.testlogin.BASE_URL
import com.example.djamshiddjuraev.testlogin.data.api.AppService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class NetworkModule {

  @Provides
  @Singleton
  fun provideGson(): Gson = GsonBuilder().create()

  fun buildOkHttpClient(): OkHttpClient {
    val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    okHttpClient
        .connectTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor {
          val request: Request = it.request().newBuilder()
              .addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
              .addHeader("X-RapidAPI-Key", "ffe13f248emshc2cdb9c345b5b26p14cc87jsne2f0a9f514d5")
              .build()
          it.proceed(request)
        }

    return okHttpClient.build()
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient = buildOkHttpClient()

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

  @Provides
  @Singleton
  fun provideAppService(retrofit: Retrofit): AppService = retrofit.create(AppService::class.java)
}