package com.dongnh.permissionsummary.singleton

import androidx.lifecycle.MutableLiveData
import com.dongnh.permissionsummary.model.AppPermission

object SingletonArgument {
    var appPermission = AppPermission()

    val reloadData: MutableLiveData<Boolean> = MutableLiveData()
}