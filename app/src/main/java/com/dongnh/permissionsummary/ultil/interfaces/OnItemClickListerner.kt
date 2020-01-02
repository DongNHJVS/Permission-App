package com.dongnh.permissionsummary.ultil.interfaces

import android.view.View
import com.dongnh.permissionsummary.model.AppPermission

// interface Click on item
interface OnItemClickListener {
    fun onClick(view: View, entity: AppPermission)
}