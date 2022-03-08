package com.mobile.projectapi.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProjectApiDao {
    @Insert
    suspend fun insert(projectApiEntity: ProjectApiEntity)

    @Query("SELECT * FROM tableHarga")
    fun getListHarga(): LiveData<List<ProjectApiEntity>>
}