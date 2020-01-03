package com.dongnh.permissionsummary.worker

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dongnh.permissionsummary.model.db.entity.TrackingEntity
import com.dongnh.permissionsummary.singleton.SingletonArgument.trackingDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class TrackingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    @SuppressLint("CheckResult")
    override fun doWork(): Result {
        return try {
            Observable.fromCallable { trackingDao.getAll }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Timber.e("RxJava : Load ok")
                        callUploadTracking(it)
                    },
                    {
                        Timber.e(it)
                        Result.failure() // return false this worker
                    }
                )
            Result.success() // for control it post complete
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Result.failure()
        }
    }

    @SuppressLint("CheckResult")
    private fun callUploadTracking(listTrackingInput: List<TrackingEntity>) {
        // Call method upload or any for hear
//        apiService.uploadTracking(trackItem)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe {  }
//            .doOnTerminate {  }
//            .subscribe(
//                { result -> onRetrieveUploadSuccess(result) },
//                { error -> onRetrieveUploadError(error) }
//            )
    }

    @SuppressLint("CheckResult")
    private fun onRetrieveUploadSuccess(tracking: Any) { // Change any to object need for get form server
        Observable.fromCallable { trackingDao.deleteAllTracking() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Delete All : Ok")
                },
                {
                    Timber.e(it)
                }
            )
    }

    private fun onRetrieveUploadError(error: Throwable) {
        Timber.e(error)
    }
}