package com.example.app.data.dao.converters

import androidx.room.TypeConverter
import com.example.app.data.dao.models.ScentStrength

class BloomTimeConverter {
    @TypeConverter
    fun fromStringToBloomTime(bloomTime: String): ScentStrength {
        return  ScentStrength.valueOf(bloomTime)
    }

    @TypeConverter
    fun fromBloomTimeToString(scentStrength: ScentStrength): String {
        return  scentStrength.name
    }
}