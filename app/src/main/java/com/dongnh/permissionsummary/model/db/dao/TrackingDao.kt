package com.dongnh.permissionsummary.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dongnh.permissionsummary.model.db.entity.TrackingEntity

@Dao
interface TrackingDao {
    @get:Query("SELECT * FROM tracking_data")
    val getAll: List<TrackingEntity>

    @Insert
    fun insert(vararg users: TrackingEntity)

    @Query("DELETE FROM tracking_data")
    fun deleteAllTracking()
}