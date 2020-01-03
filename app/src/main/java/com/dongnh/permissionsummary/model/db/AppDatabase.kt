package com.dongnh.permissionsummary.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dongnh.permissionsummary.model.db.dao.TrackingDao
import com.dongnh.permissionsummary.model.db.entity.TrackingEntity

@Database(entities = [TrackingEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackingDao(): TrackingDao
}