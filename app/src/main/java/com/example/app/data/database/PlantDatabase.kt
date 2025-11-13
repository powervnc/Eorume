package com.example.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app.data.dao.converters.BloomTimeConverter
import com.example.app.data.dao.converters.PlantTypeConverter
import com.example.app.data.dao.PlantDao
import com.example.app.data.dao.models.Plant

@Database(
    entities = [Plant::class],
    version = 3
)
@TypeConverters(PlantTypeConverter::class, BloomTimeConverter::class)
abstract class PlantDatabase: RoomDatabase() {
    abstract val plantDao: PlantDao
}