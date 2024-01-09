package com.example.gps_asee_ga015.Repository

import com.example.gps_asee_ga015.Model.Animal
import com.example.gps_asee_ga015.api.RetrofitService
import com.example.gps_asee_ga015.api.TypeModel.TypeRemoteModel
import com.example.gps_asee_ga015.api.model.RemoteModelPage
import com.example.gps_asee_ga015.api.model.RemoteResult
import com.example.gps_asee_ga015.auth

class animalRepository(instance: AplicacionDB, service: RetrofitService) {
    var store: AplicacionDB = instance
    var source = service

    suspend fun getAnimalById(idAnimal: Long): com.example.gps_asee_ga015.Model.Animal =
        store.animalDAO().getAnimalById(idAnimal)

    suspend fun insertAnimal(animal: com.example.gps_asee_ga015.Model.Animal) =
        store.animalDAO().insertAnimal(animal)

    suspend fun getAnimalByOrgId(idOrg: String): List<Animal> = store.animalDAO().getAnimalsByOrgId(idOrg)

    suspend fun getAnimalById(idAnimal: String): RemoteResult =
        source.getAnimals(auth, idAnimal)

    suspend fun getAnimalsRandom(sort: String): RemoteModelPage =
        source.getAnimalsRandom(auth, sort)

    suspend fun getAnimalsName(sort: String, name: String): RemoteModelPage =
        source.getAnimalsName(auth, sort, name)

    suspend fun getAnimalsByOrganization(organization: String): RemoteModelPage =
        source.getAnimalsByOrganization(auth, organization)

    suspend fun getAnimalsFilters(
        sort: String,
        type: String,
        size: String,
        gender: String,
        age: String
    ): RemoteModelPage = source.getAnimalsFilters(auth, sort, type, size, gender, age)

    suspend fun getSearchTypes(): TypeRemoteModel = source.getSearchTypes(auth)

}