package com.example.app.data.dao.converters

import androidx.room.TypeConverter
import com.example.app.data.dao.models.FragranceType

class PlantTypeConverter {
    @TypeConverter
    fun fromStringToPlantType(plantType: String): FragranceType {
        return  FragranceType.valueOf(plantType)
    }

    @TypeConverter
    fun fromPlantTypeToString(plantType: FragranceType): String {
        return  plantType.name
    }
}