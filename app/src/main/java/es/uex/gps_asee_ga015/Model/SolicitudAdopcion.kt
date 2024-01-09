package com.example.gps_asee_ga015.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SolicitudAdopcion(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val idCliente: Long,
    val idAnimal: Long,
    val fecha: String?
)
