package com.dongnh.permissionsummary.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dongnh.permissionsummary.base.BaseViewModel
import com.dongnh.permissionsummary.model.AppPermission

class ItemAppPermissionViewModel : BaseViewModel() {
    val appPerMission: MutableLiveData<AppPermission> = MutableLiveData()
    fun binding(apper : AppPermission) {
        this@ItemAppPermissionViewModel.appPerMission.value = apper
    }
}