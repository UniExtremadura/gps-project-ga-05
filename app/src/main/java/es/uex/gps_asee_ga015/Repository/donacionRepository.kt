package com.example.gps_asee_ga015.Repository

import com.example.gps_asee_ga015.Model.Donacion

class donacionRepository(instance: AplicacionDB) {
    var store = instance

    suspend fun getDonaciones(): List<Donacion> = store.donacionDAO().getDonaciones()

    suspend fun setDonaciones(donaciones: List<Donacion>) =
        store.donacionDAO().setDonaciones(donaciones)

    suspend fun updateDonaciones(cantidad: Float, idCliente: Long, idProtectora: String) =
        store.donacionDAO().updateDonacion(cantidad, idCliente, idProtectora)
}