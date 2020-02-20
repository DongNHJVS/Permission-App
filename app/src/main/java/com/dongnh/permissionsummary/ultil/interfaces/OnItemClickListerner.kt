package com.dongnh.permissionsummary.ultil.interfaces

import android.view.View
import com.dongnh.permissionsummary.model.AppPermission

// interface Click on item
interface OnItemClickListener {
    fun onClickSetting(view: View, entity: AppPermission)
    fun onClickHide(view: View, entity: AppPermission)
    fun onClickLock(view: View, entity: AppPermission)
}