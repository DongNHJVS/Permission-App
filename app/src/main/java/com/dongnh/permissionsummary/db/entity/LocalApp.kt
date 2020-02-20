package com.dongnh.permissionsummary.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_app")
data class LocalApp(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var appName: String? = "",
    var packageName : String? = "",
    var isLook: Boolean? = false,
    var isHide: Boolean? = false
    )