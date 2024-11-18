package com.example.moviedb.network

import com.example.moviedb.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object MovieApiClient {

    private const val BASE_URL = BuildConfig.BASE_URL

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val apiClient: MovieApiInterface by lazy {

        val contentType = "application/json".toMediaType()

        val json = Json {
            ignoreUnknownKeys = true
        }

        val converterFactory: Converter.Factory = json.asConverterFactory(contentType)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return@lazy retrofit.create(MovieApiInterface::class.java)
    }
}
