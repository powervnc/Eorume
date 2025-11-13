package com.example.app.data.dao.converters

import androidx.room.TypeConverter
import com.example.app.data.dao.models.FragranceType

class PerfumeTypeConverter {
    @TypeConverter
    fun fromStringToPlantType(perfumeType: String): FragranceType {
        return  FragranceType.valueOf(perfumeType)
    }

    @TypeConverter
    fun fromPlantTypeToString(perfumeType: FragranceType): String {
        return  perfumeType.name
    }
}