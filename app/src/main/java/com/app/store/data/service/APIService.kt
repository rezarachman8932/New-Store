package com.app.store.data.service

import com.app.store.BuildConfig
import com.app.store.data.local.SharedPref
import com.app.store.shared.constant.NetworkConstant
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object APIService {

    const val BASE_URL = "https://favqs.com/api/"

    inline fun <reified I> create(sharedPreferences: SharedPref) : I {
        return builder(BASE_URL, sharedPreferences)
    }

    inline fun <reified I> builder(url: String, sharedPreferences: SharedPref) : I {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(getOKHttp(sharedPreferences))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(I::class.java)
    }

    fun getOKHttp(sharedPreferences: SharedPref) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(getInterceptorChain(sharedPreferences))
        .addInterceptor(getHTTPLoggingInterceptor())
        .connectTimeout(59, TimeUnit.SECONDS)
        .writeTimeout(59, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private fun getHTTPLoggingInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    private fun getInterceptorChain(sharedPreferences: SharedPref): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request().newBuilder()
                .header("Authorization", "Token token="+NetworkConstant.API_KEY)
                .header("User-Token", sharedPreferences.getToken())
                .header("Accept", "application/vnd.favqs.v2+json;")
                .header("Etag", "558f0b9bea2e6910b1f93de7f4c0d47c")
                .header("Rate-Limit-Remaining", "24")
                .cacheControl(CacheControl.Builder().noCache().build())
                .build()
            chain.proceed(request)
        }
    }

}