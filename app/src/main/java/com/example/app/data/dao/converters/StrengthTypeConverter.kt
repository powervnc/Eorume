package com.example.app.data.dao.converters

import androidx.room.TypeConverter
import com.example.app.data.dao.models.ScentStrength

class StrengthTypeConverter {
    @TypeConverter
    fun fromStringToBloomTime(strengthType: String): ScentStrength {
        return  ScentStrength.valueOf(strengthType)
    }

    @TypeConverter
    fun fromBloomTimeToString(scentStrength: ScentStrength): String {
        return  scentStrength.name
    }
}