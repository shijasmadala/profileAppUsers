package com.shijas.profileapp.app.di

import com.shijas.profileapp.BuildConfig
import com.shijas.profileapp.data.remote.source.WeatherService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {



        @Provides
        @Singleton
        fun provideHttpClient(): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                okHttpClient.addInterceptor(httpLoggingInterceptor)
            }
            return okHttpClient.build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
                .build()
        }

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService{
        return retrofit.create()
    }
}


//    onecall?lat=12.9082847623315&lon=77.65197822993314&units=imperial&appid=b143bb707b2ee117e62649b358207d3e