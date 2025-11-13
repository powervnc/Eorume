package com.example.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.app.data.dao.models.Plant

import kotlinx.coroutines.flow.Flow
@Dao
interface PlantDao {

    @Query("Select * from plant order by name asc")
    fun getPlants(): Flow<List<Plant>>

    @Query("Select * from plant where id = :id")
    fun getPlantById(id: Int): Flow<List<Plant>>

    @Delete
    suspend fun deletePlant(plant: Plant)

    @Upsert
    suspend fun addPlant(plant: Plant)

    @Query("SELECT * FROM plant WHERE favourite = 1")
    fun getFavoritePlants(): List<Plant>

    /// for future functionality
    @Query("Select * from plant order by name asc")
    fun getPlantsOrderedByName(): Flow<List<Plant>>

    @Query("Select * from plant order by biologicalName asc")
    fun getPlantsOrderedByScientificName(): Flow<List<Plant>>

    @Query("Select * from plant where type = :typePlant order by name asc")
    fun getPlantsByType(typePlant: String): Flow<List<Plant>>

    @Query("Select * from plant where bloomTime = :bloomTime order by name asc")
    fun getPlantsByBloomTime(bloomTime: String): Flow<List<Plant>>


}