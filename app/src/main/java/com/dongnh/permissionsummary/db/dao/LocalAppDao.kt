package com.dongnh.permissionsummary.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dongnh.permissionsummary.db.entity.LocalApp

@Dao
interface LocalAppDao {
    @get:Query("SELECT * FROM local_app")
    val loadAll: List<LocalApp>

    @Insert
    fun insert(vararg localApp: LocalApp)

    @Insert
    fun insertList(vararg localApp: List<LocalApp>)

    @Query("DELETE FROM local_app")
    fun deleteAllLog()
}