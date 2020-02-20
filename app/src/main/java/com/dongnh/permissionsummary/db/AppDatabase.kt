package com.dongnh.permissionsummary.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dongnh.permissionsummary.db.dao.LocalAppDao
import com.dongnh.permissionsummary.db.entity.LocalApp

@Database(entities = [LocalApp::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun localAppDao(): LocalAppDao
}