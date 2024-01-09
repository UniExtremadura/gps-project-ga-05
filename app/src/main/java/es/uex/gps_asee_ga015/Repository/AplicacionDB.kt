package com.example.gps_asee_ga015.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gps_asee_ga015.DAO.*
import com.example.gps_asee_ga015.Model.*

@Database(
    entities = [ Animal::class, Cliente::class, Donacion::class, Favoritos::class, Protectora::class, SolicitudAdopcion::class, Valoracion::class],
    version = 22,
    exportSchema = false
)
abstract class AplicacionDB : RoomDatabase() {

    abstract fun animalDAO() : AnimalDAO
    abstract fun clienteDAO() : ClienteDAO
    abstract fun donacionDAO() : DonacionDAO

    abstract fun favoritosDAO() : FavoritosDAO
    abstract fun protectoraDAO() : ProtectoraDAO
    abstract fun solicitudAdopcionDAO() : SolicitudAdopcionDAO
    abstract fun valoracionDAO() : ValoracionDAO

    companion object {

        @Volatile
        private var INSTANCE: AplicacionDB? = null

        fun getInstance(context: Context): AplicacionDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    var instance = INSTANCE
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AplicacionDB::class.java,
                            "database"
                        )
                            .fallbackToDestructiveMigration() //Permite destruir el schema de base de datos anterior si este ha sido cambiado
                            .build()
                        INSTANCE = instance
                    }
                }
            }
            return INSTANCE!!
        }
    }

}


