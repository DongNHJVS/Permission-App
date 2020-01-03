package com.dongnh.permissionsummary.di

import android.content.Context
import com.dongnh.permissionsummary.BuildConfig
import com.dongnh.permissionsummary.const.AUTH_HEADER_NAME
import com.dongnh.permissionsummary.const.RESPONSE_CACHE_PATH_NAME
import com.dongnh.permissionsummary.const.RESPONSE_CACHE_SIZE
import com.dongnh.permissionsummary.ultil.helper.SharePreferenceHelper
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

val networkModule = module {
    single { createSharePreferenceHelper(get()) }
    single { createInterceptor(get()) }
    single { createOkHttpClient(androidContext(), get()) }
    single { createNetworkClient(get(), BuildConfig.BASE_URL) } // Setting api config build Type
}

private fun createSharePreferenceHelper(context: Context): SharePreferenceHelper {
    return SharePreferenceHelper(context)
}

private fun createNetworkClient(okHttpClient: OkHttpClient, baseUrl: String) = Retrofit
    .Builder()
    .baseUrl(baseUrl)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Handle response bad request with message body
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private fun createOkHttpClient(context: Context, requestInterceptor: Interceptor): OkHttpClient {
    val cacheDir = File(context.cacheDir, RESPONSE_CACHE_PATH_NAME)
    val builder = OkHttpClient.Builder()
        .cache(Cache(cacheDir, RESPONSE_CACHE_SIZE))
        .addInterceptor(requestInterceptor)
        .addInterceptor(log(BuildConfig.DEBUG))
    return builder.build()
}

private fun createInterceptor(preferenceHelper: SharePreferenceHelper) = Interceptor { chain ->
    val authHeaderValue = preferenceHelper.getAuthData()
    val original = chain.request()
    val builder = original.newBuilder()
        .method(original.method(), original.body())
        .addHeader(
            AUTH_HEADER_NAME,
            authHeaderValue
        )
    val newRequest = builder.build()
    return@Interceptor chain.proceed(newRequest)
}

private fun log(enabled: Boolean): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (enabled) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}