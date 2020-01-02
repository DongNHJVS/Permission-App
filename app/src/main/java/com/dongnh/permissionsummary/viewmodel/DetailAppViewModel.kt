package com.dongnh.permissionsummary.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dongnh.permissionsummary.adapter.AdaterDetailPermission
import com.dongnh.permissionsummary.base.BaseViewModel
import com.dongnh.permissionsummary.model.AppPermission

class DetailAppViewModel : BaseViewModel() {
    val appPermission: MutableLiveData<AppPermission> = MutableLiveData()
    val adapterDefault = AdaterDetailPermission()
}
