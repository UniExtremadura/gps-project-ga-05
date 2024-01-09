package com.example.gps_asee_ga015.Model

import androidx.room.Entity

@Entity(primaryKeys=["idCliente","idProtectora"])
data class Valoracion(
    val idCliente: Long, //identificador del cliente que realiza la valoracion
    val idProtectora: String, //identificador de la protectora a la que realizan la valoracion
    val valoracion: String,
)
