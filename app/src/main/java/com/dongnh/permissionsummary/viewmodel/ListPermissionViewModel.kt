package com.dongnh.permissionsummary.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongnh.permissionsummary.adapter.AdapterAppPermission
import com.dongnh.permissionsummary.base.BaseViewModel

class ListPermissionViewModel : BaseViewModel() {
    val adapterApp: AdapterAppPermission = AdapterAppPermission()
    val viewList: MutableLiveData<Int> = MutableLiveData()
    val viewNull: MutableLiveData<Int> = MutableLiveData()

    init {
        viewList.value = View.VISIBLE
        viewNull.value = View.GONE
    }
}
