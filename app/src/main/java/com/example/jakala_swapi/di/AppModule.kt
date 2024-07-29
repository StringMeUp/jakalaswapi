package com.example.jakala_swapi.di

import com.example.jakala_swapi.BuildConfig
import com.example.jakala_swapi.data.repository.SwapiRepositoryImpl
import com.example.jakala_swapi.data.repository.SwapiRepository
import com.example.jakala_swapi.helper.NetworkConstants
import com.example.jakala_swapi.networking.SwapiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLoggerInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> println(message) }
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.HEADERS }
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return interceptor
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(loggerInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(NetworkConstants.C_R_W_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.C_R_W_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.C_R_W_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(loggerInterceptor).build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(NetworkConstants.DEFAULT_MEDIA_TYPE))
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) = retrofit.create(SwapiService::class.java)

    @Provides
    @Singleton
    fun providesSwapiRepository(swapiRepositoryImpl: SwapiRepositoryImpl): SwapiRepository =
        swapiRepositoryImpl

}