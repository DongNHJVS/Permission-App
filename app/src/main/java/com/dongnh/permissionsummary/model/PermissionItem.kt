package com.dongnh.permissionsummary.model

import android.graphics.drawable.Drawable

data class PermissionItem(
    var permissionName: String? = "",
    var packageName: String? = "",
    var granted: Int? = -1,
    var drawableIcon: Drawable? = null
)