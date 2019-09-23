package com.ys.baselib.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

/**
 * base retrofit
 */
class RetrofitUtils {

    companion object {

        private val retrofitClient by lazy {
            HashMap<String,Retrofit>()
        }

        private val okHttpClient : OkHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            //todo 添加各种拦截器
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(HttpCache.cache)
                .retryOnConnectionFailure(true)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        private fun getRetrofitClient(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getService(baseUrl: String): Retrofit {
            var service = retrofitClient[baseUrl]
            if (service == null) {
                service = getRetrofitClient(baseUrl)
                retrofitClient.put(baseUrl, service)
            }
            return service
        }

    }

}