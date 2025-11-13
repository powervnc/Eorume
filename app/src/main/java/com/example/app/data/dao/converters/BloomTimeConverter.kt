package com.example.app.data.dao.converters

import androidx.room.TypeConverter
import com.example.app.data.dao.models.BloomTime

class BloomTimeConverter {
    @TypeConverter
    fun fromStringToBloomTime(bloomTime: String): BloomTime {
        return  BloomTime.valueOf(bloomTime)
    }

    @TypeConverter
    fun fromBloomTimeToString(bloomTime: BloomTime): String {
        return  bloomTime.name
    }
}