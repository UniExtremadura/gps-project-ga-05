package com.example.gps_asee_ga015.Repository

import com.example.gps_asee_ga015.Model.Animal
import com.example.gps_asee_ga015.Model.SolicitudAdopcion

class solicitudAdopciónRepository(instance: AplicacionDB) {
    var store = instance

    suspend fun setSolicitud(idCliente: Long, idAnimal: Long) =
        store.solicitudAdopcionDAO().setSolicitud(idCliente, idAnimal)

    suspend fun setSolicitudCompleta(idCliente: Long, idAnimal: Long, fecha: String) =
        store.solicitudAdopcionDAO().setSolicitudCompleta(idCliente, idAnimal, fecha)

    suspend fun getSolicitud(idCliente: Long, idAnimal: Long): SolicitudAdopcion =
        store.solicitudAdopcionDAO().getSolicitud(idCliente, idAnimal)

    suspend fun getAnimalAdop(idAnimal: Long): SolicitudAdopcion =
        store.solicitudAdopcionDAO().getAnimalAdop(idAnimal)

    suspend fun removeSolicitud(idCliente: Long, idAnimal: Long) =
        store.solicitudAdopcionDAO().removeSolicitud(idCliente, idAnimal)

    suspend fun getAdopByIdClient(id: Long): List<SolicitudAdopcion> =
        store.solicitudAdopcionDAO().getAdopByIdClient(id)

}