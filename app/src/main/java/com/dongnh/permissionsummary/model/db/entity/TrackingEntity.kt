package com.dongnh.permissionsummary.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tracking_data")
data class TrackingEntity (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var latitude: Double? = -1.0,
    var longitude: Double? = -1.0,
    var action: String? = "", // <view/click/play>
    @SerializedName("item_id")
    var item_id: String? = "",
    @SerializedName("duaration")
    var duaration: Int? = 0,
    @SerializedName("created_time")
    var created_time: Double? = 0.0
)