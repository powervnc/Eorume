package com.example.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app.data.dao.converters.StrengthTypeConverter
import com.example.app.data.dao.converters.PerfumeTypeConverter
import com.example.app.data.dao.PerfumeDao
import com.example.app.data.dao.models.Perfume

@Database(
    entities = [Perfume::class],
    version = 4
)
@TypeConverters(PerfumeTypeConverter::class, StrengthTypeConverter::class)
abstract class Database: RoomDatabase() {
    abstract val perfumeDao: PerfumeDao
}