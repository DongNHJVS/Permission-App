package com.dongnh.permissionsummary.model

import android.graphics.drawable.Drawable

data class AppPermission(
    var name: String? = "",
    var versionName: String? = "",
    var drawable: Drawable? = null,
    var permissions: ArrayList<PermissionItem>? = arrayListOf(),
    var packagesName: String? = "",
    var isSystem: Boolean? = false,
    var isLock: Boolean? = false,
    var isHide: Boolean? = false
)