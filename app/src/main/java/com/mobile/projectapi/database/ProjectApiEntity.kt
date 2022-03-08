package com.mobile.projectapi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableHarga")
data class ProjectApiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String? = "",
    val province: String? = "",
    val city: String? = "",
    val size: String? = "",
    val price: Int? = 0
)
