package com.example.gps_asee_ga015.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys=["idCliente","idAnimal"])
data class Favoritos(
    val idCliente: Long, //Cliente que marca un animal como favorito
    val idAnimal: Long //Animal que es marcado como favorito
)
