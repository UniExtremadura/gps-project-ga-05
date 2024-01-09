package com.example.gps_asee_ga015.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.gps_asee_ga015.Model.*

@Dao
interface DonacionDAO {
    //Obtiene todas las donaciones
    @Query("SELECT * FROM Donacion")
    suspend fun getDonaciones() : List<Donacion>

    //Inserta una lista de donaciones
    @Insert
    suspend fun setDonaciones(donaciones : List<Donacion>)

    @Query("UPDATE Donacion SET cantidad = :cantidad WHERE idCliente = :idCliente AND idProtectora = :idProtectora")
    suspend fun updateDonacion(cantidad: Float, idCliente: Long, idProtectora: String)
}