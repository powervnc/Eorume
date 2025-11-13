package com.example.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.app.data.dao.models.Perfume

import kotlinx.coroutines.flow.Flow
@Dao
interface PerfumeDao {

    @Query("Select * from perfume order by name asc")
    fun getPerfumes(): Flow<List<Perfume>>

    @Query("Select * from perfume where id = :id")
    fun getPlantById(id: Int): Flow<List<Perfume>>

    @Delete
    suspend fun deletePerfume(perfume: Perfume)

    @Upsert
    suspend fun addPerfume(perfume: Perfume)

    @Query("SELECT * FROM perfume WHERE favourite = 1")
    fun getFavoritePlants(): List<Perfume>

    /// for future functionality
    @Query("Select * from perfume order by name asc")
    fun getPerfumesOrderedByName(): Flow<List<Perfume>>

    @Query("Select * from perfume order by officialName asc")
    fun getPerfumesOrderedByScientificName(): Flow<List<Perfume>>

    @Query("Select * from perfume where type = :type order by name asc")
    fun getPerfumesByType(type: String): Flow<List<Perfume>>

    @Query("Select * from perfume where scentStrength = :scentStrength order by name asc")
    fun getPerfumesByScentStrength(scentStrength: String): Flow<List<Perfume>>


}