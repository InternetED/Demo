package com.interneted.demo.di

import android.content.Context
import com.interneted.demo.di.AppModule.Companion.baseUrl
import com.interneted.demo.model.TravelRepository
import com.interneted.demo.model.TravelRepositoryImpl
import com.interneted.demo.model.remote.TravelApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Creator: ED
 * Date: 2023/9/13 10:23
 * Mail: salahayo3192@gmail.com
 *
 * **/
class AppModule private constructor(context: Context) {
    companion object {
        private lateinit var instance: AppModule
        fun getInstance(context: Context): AppModule {
            if (!::instance.isInitialized) {
                instance = AppModule(context)
            }
            return instance
        }

        private const val baseUrl = "https://www.travel.taipei/open-api/"
    }


    private val travelApi: TravelApi by lazy {
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.HEADERS
                })
                .hostnameVerifier { a, w -> true }
                .build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TravelApi::class.java)
    }

    val travelRepository: TravelRepository by lazy {
        TravelRepositoryImpl(travelApi)
    }


}