package com.dongnh.permissionsummary.ultil.interfaces

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import timber.log.Timber

interface LifecycleBehaviour : LifecycleObserver {

    var lifecycle: Lifecycle?


    fun bind(lifecycle: Lifecycle?) {
        if (this@LifecycleBehaviour.lifecycle != null) {
            return
        }

        this@LifecycleBehaviour.lifecycle = lifecycle
        this@LifecycleBehaviour.lifecycle?.addObserver(this@LifecycleBehaviour)
    }

    fun unbindIfNeeded() {
        this@LifecycleBehaviour.lifecycle?.removeObserver(this@LifecycleBehaviour)
        this@LifecycleBehaviour.lifecycle = null
    }


    fun lifecycleOnCreate() {
        Timber.i("${this@LifecycleBehaviour::class.java.simpleName} onCreate")
    }

    fun lifecycleOnStart() {
        Timber.i("${this@LifecycleBehaviour::class.java.simpleName} onStart")
    }

    fun lifecycleOnResume() {
        Timber.i("${this@LifecycleBehaviour::class.java.simpleName} onResume")
    }

    fun lifecycleOnPause() {
        Timber.i("${this@LifecycleBehaviour::class.java.simpleName} onPause")
    }

    fun lifecycleOnStop() {
        Timber.i("${this@LifecycleBehaviour::class.java.simpleName} onStop")
    }

    fun lifecycleOnDestroy() {
        this@LifecycleBehaviour.unbindIfNeeded()
        Timber.i("${this@LifecycleBehaviour::class.java.simpleName} onDestroy")
    }

}