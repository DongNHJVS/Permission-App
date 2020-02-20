package com.dongnh.permissionsummary.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.base.BaseViewModel
import com.dongnh.permissionsummary.model.AppPermission
import org.koin.core.KoinComponent
import org.koin.core.get

class ItemAppPermissionViewModel : BaseViewModel() , KoinComponent{

    val iconShow : MutableLiveData<Int> = MutableLiveData()
    val iconLock : MutableLiveData<Int> = MutableLiveData()
    val isSystemString : MutableLiveData<String> = MutableLiveData()
    val appPerMission: MutableLiveData<AppPermission> = MutableLiveData()
    val context: Context = get()

    fun binding(apper : AppPermission) {
        this@ItemAppPermissionViewModel.appPerMission.value = apper

        if (apper.isHide!!) {
            iconShow.value = R.drawable.ic_unhide
        } else {
            iconShow.value = R.drawable.ic_hide
        }

        if (apper.isLock!!) {
            iconLock.value = R.drawable.ic_unlock
        } else {
            iconLock.value = R.drawable.ic_lock
        }

        if (apper.isSystem!!) {
            isSystemString.value = context.getString(R.string.app_item_system)  + " " + "<font color=#ff0000>" + context.getString(R.string.app_item_system_yes) + "</font>"
        } else {
            isSystemString.value = context.getString(R.string.app_item_system)  + " " + "<font color=#0DAE64>" + context.getString(R.string.app_item_system_no) + "</font>"
        }
    }
}