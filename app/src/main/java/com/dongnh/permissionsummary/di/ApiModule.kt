package com.dongnh.permissionsummary.di

import com.dongnh.permissionsummary.api.CiaoApiService
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { createCiaoApiService(get()) }
}

internal fun createCiaoApiService(retrofit: Retrofit) : CiaoApiService =
    retrofit.create(CiaoApiService::class.java)

private fun log(enabled: Boolean): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (enabled) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}