package com.dongnh.permissionsummary.viewmodel

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.base.BaseViewModel
import com.dongnh.permissionsummary.const.ALLOW
import com.dongnh.permissionsummary.const.GRANTED
import com.dongnh.permissionsummary.const.NOT_GRANTED
import com.dongnh.permissionsummary.model.PermissionItem
import com.dongnh.permissionsummary.ultil.exts.toNamePermission

class ItemPermissionViewModel : BaseViewModel() {

    val namePermission = MutableLiveData<String>()
    var icon: Drawable? = null
    val granted = MutableLiveData<Int>()
    val color = MutableLiveData<Int>()
    val backgroundItem = MutableLiveData<Int>()

    fun binding(permission: PermissionItem) {
        namePermission.value = permission.permissionName?.toNamePermission()

        icon = permission.drawableIcon

        // Find by key flag
        when (permission.granted) {
            GRANTED -> {
                backgroundItem.value = R.drawable.bg_textview_alert
                granted.value = R.string.app_detail_granted
                color.value = R.color.red
            }
            NOT_GRANTED -> {
                backgroundItem.value = R.drawable.bg_textview_green
                granted.value = R.string.app_detail_not_granted
                color.value = R.color.green
            }
            ALLOW -> {
                backgroundItem.value = R.drawable.bg_textview_allow_when_use
                granted.value = R.string.app_detail_when_use
                color.value = R.color.orange
            }
            else -> {
                backgroundItem.value = R.drawable.bg_textview_not_know
                granted.value = R.string.app_detail_not_know
                color.value = R.color.darkPale
            }
        }

    }
}