package com.hiren.livecodingtest.di

import com.hiren.livecodingtest.data.remote.ApiService
import com.hiren.livecodingtest.data.repository.UserRepositoryImp
import com.hiren.livecodingtest.domin.UserRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    private const val BASE_URL = "https://launch-labs-api-test.fusecup.workers.dev/"

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val loginInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(loginInterceptor).build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImp(apiService)
    }

}