package com.mobile.projectapi.repository

import com.mobile.projectapi.api.Api

class ProjectApiRepository(
    private val api: Api
) {
    suspend fun getArea() = api.getArea()
}