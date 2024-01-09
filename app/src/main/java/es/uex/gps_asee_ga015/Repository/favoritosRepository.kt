package com.example.gps_asee_ga015.Repository

import com.example.gps_asee_ga015.Model.Favoritos

class favoritosRepository(instance: AplicacionDB) {
    var store = instance

    suspend fun getFavsByIdClient(id: Long): List<Favoritos> =
        store.favoritosDAO().getFavsByIdClient(id)

    suspend fun getFavByIdAnimal(idAnimal: Long, idCliente: Long): Favoritos =
        store.favoritosDAO().getFavByIdAnimal(idAnimal, idCliente)

    suspend fun setFav(fav: Favoritos) = store.favoritosDAO().setFav(fav)

    suspend fun deleteFav(fav: Favoritos) = store.favoritosDAO().deleteFav(fav)
}