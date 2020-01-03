package com.dongnh.permissionsummary.singleton

import androidx.lifecycle.MutableLiveData
import com.dongnh.permissionsummary.api.CiaoApiService
import com.dongnh.permissionsummary.model.AppPermission
import com.dongnh.permissionsummary.model.db.dao.TrackingDao

object SingletonArgument {
    var appPermission = AppPermission()

    val reloadData: MutableLiveData<Boolean> = MutableLiveData()

    // Two object for viewmodel or worker, when can't get this
    lateinit var trackingDao: TrackingDao
    lateinit var apiService: CiaoApiService
}