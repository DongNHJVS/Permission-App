package com.dongnh.permissionsummary.model

data class AppPermission(
    var name: String? = "",
    var permissions: ArrayList<PermissionItem>? = arrayListOf(),
    var packagesName: String? = ""
)