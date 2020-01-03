package com.dongnh.permissionsummary.di

import androidx.room.Room
import com.dongnh.permissionsummary.model.db.AppDatabase
import com.dongnh.permissionsummary.model.db.dao.TrackingDao
import com.dongnh.permissionsummary.model.db.entity.TrackingEntity
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "tracking_data").build()
    }
    single { get<AppDatabase>().trackingDao() } // Singleton
    single { insertTrackingToDB(get(), get()) } // Singleton
}

fun insertTrackingToDB(trackingDao: TrackingDao, trackingEntity: TrackingEntity) {
    trackingDao.insert(trackingEntity)
}