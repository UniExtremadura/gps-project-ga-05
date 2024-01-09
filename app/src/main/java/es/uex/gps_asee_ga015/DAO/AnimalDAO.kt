package com.example.gps_asee_ga015.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gps_asee_ga015.Model.Animal

//import com.example.gps_asee_ga015.Model.animalEsApadrinadoPorClientes

@Dao
interface AnimalDAO {
    //Inserta un animal
    @Insert
    suspend fun insertAnimal(animal: Animal)

    //Get an animal by a given id
    @Query("SELECT * FROM Animal WHERE idAnimal = :idAnimal")
    suspend fun getAnimalById(idAnimal: Long): Animal

    //Get animals by a given org id
    @Query("SELECT * FROM Animal WHERE protectora = :idOrg")
    suspend fun getAnimalsByOrgId(idOrg: String): List<Animal>
}