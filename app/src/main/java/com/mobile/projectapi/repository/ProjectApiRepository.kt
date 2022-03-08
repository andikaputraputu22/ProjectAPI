package com.mobile.projectapi.repository

import com.mobile.projectapi.api.Api
import com.mobile.projectapi.database.ProjectApiDatabase
import com.mobile.projectapi.database.ProjectApiEntity

class ProjectApiRepository(
    private val api: Api,
    private val db: ProjectApiDatabase
) {
    suspend fun getArea() = api.getArea()
    suspend fun getSize() = api.getSize()

    suspend fun store(projectApiEntity: ProjectApiEntity) {
        db.projectApiDao().insert(projectApiEntity)
    }

    fun getKomoditas() = db.projectApiDao().getListHarga()
}