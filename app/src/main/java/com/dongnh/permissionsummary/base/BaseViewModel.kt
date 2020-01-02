package com.dongnh.permissionsummary.base

import androidx.lifecycle.ViewModel
import com.dongnh.permissionsummary.ultil.interfaces.OnLoadDataFormAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    // CompositeDisposable rxjava
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var interfaceLoad : OnLoadDataFormAPI? = null

    // Api geter
    lateinit var _subscription: Disposable

    override fun onCleared() {
        if (::_subscription.isInitialized) {
            _subscription.dispose()
        }
        compositeDisposable.clear()
        super.onCleared()
    }

    fun startWorking() {
        interfaceLoad!!.onLoading()
    }

    fun stopWorking() {
        interfaceLoad!!.onComplete()
    }
}