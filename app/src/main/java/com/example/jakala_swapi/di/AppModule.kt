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
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory(NetworkConstants.DEFAULT_MEDIA_TYPE))
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) = retrofit.create(SwapiService::class.java)

    @Provides
    @Singleton
    fun providesSwapiRepository(swapiRepositoryImpl: SwapiRepositoryImpl): SwapiRepository = swapiRepositoryImpl

}