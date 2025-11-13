package com.example.app.data.dao.converters

import androidx.room.TypeConverter
import com.example.app.data.dao.models.PlantType

class PlantTypeConverter {
    @TypeConverter
    fun fromStringToPlantType(plantType: String): PlantType {
        return  PlantType.valueOf(plantType)
    }

    @TypeConverter
    fun fromPlantTypeToString(plantType: PlantType): String {
        return  plantType.name
    }
}