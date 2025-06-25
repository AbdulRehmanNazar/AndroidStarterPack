package com.android.starterpack.di

import com.android.starterpack.data.remote.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/repos/ruby/ruby/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<APIService> {
        get<Retrofit>().create(APIService::class.java)
    }
}