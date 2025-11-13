package com.example.app.data.dao.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perfume")
data class Perfume (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var officialName: String,
    var type: FragranceType,
    var scentStrength: ScentStrength,
    var meaning: String,
    var favourite: Boolean = false
)