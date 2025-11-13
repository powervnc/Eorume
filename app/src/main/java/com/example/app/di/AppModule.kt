package com.example.app.di

import android.content.Context
import androidx.room.Room
import com.example.app.data.dao.PerfumeDao
import com.example.app.data.database.Database
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
    fun provideDatabase(@ApplicationContext context: Context): Database {
         return Room.databaseBuilder(
             context,
             Database::class.java,
             "perfume.database"
         )
             .allowMainThreadQueries() // optional, remove for production
             .fallbackToDestructiveMigration()
             .build()
    }


    @Provides
    @Singleton
    fun providePerfumeDao(database: Database): PerfumeDao = database.perfumeDao

}