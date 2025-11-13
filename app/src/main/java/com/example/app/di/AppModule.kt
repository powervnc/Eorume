package com.example.app.di

import android.content.Context
import androidx.room.Room
import com.example.app.data.dao.PlantDao
import com.example.app.data.database.PlantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PlantDatabase {
         return Room.databaseBuilder(
             context,
             PlantDatabase::class.java,
             "plant_database"
         )
             .allowMainThreadQueries() // optional, remove for production
             .fallbackToDestructiveMigration()
             .build()
    }


    @Provides
    @Singleton
    fun providePlantDao(database: PlantDatabase): PlantDao = database.plantDao

}