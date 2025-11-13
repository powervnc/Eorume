package com.example.app.data.dao.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")
data class Plant (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var biologicalName: String,
    var type: PlantType,
    var bloomTime: BloomTime,
    var meaning: String,
    var favourite: Boolean = false
)