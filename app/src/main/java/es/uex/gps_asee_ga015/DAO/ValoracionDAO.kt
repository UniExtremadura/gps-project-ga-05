package com.example.gps_asee_ga015.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.gps_asee_ga015.Model.Valoracion

@Dao
interface ValoracionDAO{
    @Query("INSERT INTO Valoracion (idCliente, idProtectora, valoracion) VALUES (:idCliente,:idProtectora,:valoracion)")
    suspend fun setValoracion(idCliente: Long, idProtectora: String, valoracion: String)

    @Query("SELECT * FROM Valoracion WHERE idProtectora = :idProtectora")
    suspend fun getValoracionByIdProtectora(idProtectora: String) : List<Valoracion>

    @Query("SELECT * FROM Valoracion WHERE idProtectora = :idProtectora AND idCliente = :idCliente")
    suspend fun getValoracionByIdProtectoraCliente(idProtectora: String, idCliente: Long) : Valoracion
}
