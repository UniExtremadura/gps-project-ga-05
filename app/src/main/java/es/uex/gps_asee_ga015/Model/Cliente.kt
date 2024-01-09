package com.example.gps_asee_ga015.Model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Cliente (
    val nombre: String,
    val appellidos: String,
    val nickname: String,
    val email: String,
    val password : String,
    val numTelefono: String?,
    val descripcion: String?
){
    @PrimaryKey(autoGenerate = true)
    var idCliente: Long = 0
}

data class ClienteSolicitaAdoptar(
    @Embedded
    val cliente: Cliente,
    @Relation(
        parentColumn="idCliente",
        entityColumn="idAnimal"
    )
    val animales : List<Animal>
)
