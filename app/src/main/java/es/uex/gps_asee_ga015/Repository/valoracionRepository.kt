package com.example.gps_asee_ga015.Repository

import com.example.gps_asee_ga015.Model.Protectora
import com.example.gps_asee_ga015.Model.Valoracion

class valoracionRepository(instance: AplicacionDB) {
    var store = instance

    suspend fun setValoracion(idCliente: Long, idProtectora: String, valoracion: String) =
        store.valoracionDAO().setValoracion(idCliente, idProtectora, valoracion)

    suspend fun getValoracionByIdProtectora(idProtectora: String): List<Valoracion> =
        store.valoracionDAO().getValoracionByIdProtectora(idProtectora)

    suspend fun getValoracionByIdProtectoraCliente(idProtectora: String, idCliente: Long): Valoracion =
        store.valoracionDAO().getValoracionByIdProtectoraCliente(idProtectora, idCliente)
}