package com.example.gps_asee_ga015.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.gps_asee_ga015.Model.Cliente
import com.example.gps_asee_ga015.Model.Protectora
import com.example.gps_asee_ga015.Model.ProtectoraAnunciaAnimales
//import com.example.gps_asee_ga015.Model.ProtectoraEsDonadaPorClientes
//import com.example.gps_asee_ga015.Model.ProtectoraEsValoradaPorClientes

@Dao
interface ProtectoraDAO {
    //Devuelve una lista de las organizaciones que hay en la base de datos local
    @Transaction
    @Query("SELECT * FROM Protectora")
    suspend fun getOrganizaciones() : List<Protectora>

    //Devuelve una lista de las organizaciones que hay en la base de datos local
    @Query("SELECT * FROM Protectora WHERE idProtectora = :idProtectora")
    suspend fun getOrganizacionId(idProtectora: Long) : Protectora

    //Inserta una Organizacion
    @Insert
    suspend fun insertOrganizacion(organizacion: Protectora)
}