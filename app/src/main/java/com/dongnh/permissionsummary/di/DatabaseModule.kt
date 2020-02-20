package com.dongnh.permissionsummary.di

import androidx.room.Room
import com.dongnh.permissionsummary.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "log_entity").build() }
    single { get<AppDatabase>().localAppDao() }
}