package com.dongnh.permissionsummary.api

import io.reactivex.Observable
import retrofit2.http.GET

interface CiaoApiService {
    /**
     * Api to create key access for app
     */
    @GET("token")
    fun getDataForView(): Observable<Any> // Used any for get all, not pass to object
}