package com.emranul.heroquiz.di

import com.emranul.heroquiz.data.network.Api
import com.emranul.heroquiz.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun  providesOkHttpClient() =  OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder().apply {
                header("Accept", "application/json")
                header("Content-Type", "application/json")
            }
            return@Interceptor chain.proceed(builder.build())
        })
        .build()


    @Singleton
    @Provides
    fun providesRetrofitInstance(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Const.BASE_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit) = retrofit.create<Api>()

}