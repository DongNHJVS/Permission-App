package com.dongnh.permissionsummary.viewmodel

import android.content.Context
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.dongnh.permissionsummary.api.CiaoApiService
import com.dongnh.permissionsummary.base.BaseViewModel
import com.dongnh.permissionsummary.model.db.dao.TrackingDao
import com.dongnh.permissionsummary.singleton.SingletonArgument
import com.dongnh.permissionsummary.worker.TrackingWorker
import java.util.concurrent.TimeUnit

class MainViewModel(val context: Context, var callApiService: CiaoApiService, val trackingDao: TrackingDao) : BaseViewModel() {

    // Woker manager
    private val workManager = WorkManager.getInstance(context)

    // Time to call upload
    val TIME_UPLOAD: Long = 7 // minute

    init {
        SingletonArgument.apiService = callApiService
        SingletonArgument.trackingDao = trackingDao
    }

    /**
     * Start post tracking
     */
    internal fun startWorkerInBackground() {
        workManager.enqueue(PeriodicWorkRequest.Builder(TrackingWorker::class.java, TIME_UPLOAD, TimeUnit.MINUTES).build())
    }
}