package com.example.gps_asee_ga015.Repository

import com.example.gps_asee_ga015.Model.Protectora
import com.example.gps_asee_ga015.api.RetrofitService
import com.example.gps_asee_ga015.api.organizationsModel.OrgRemoteModel
import com.example.gps_asee_ga015.api.organizationsModel.OrganizationsRemoteModel
import com.example.gps_asee_ga015.auth

class protectoraRepository(instance: AplicacionDB, service: RetrofitService) {
    var store = instance
    var source = service

    suspend fun getOrganizaciones(): List<Protectora> = store.protectoraDAO().getOrganizaciones()

    suspend fun getOrganizacionId(idProtectora: Long): Protectora =
        store.protectoraDAO().getOrganizacionId(idProtectora)

    suspend fun insertOrganizacion(organizacion: Protectora) =
        store.protectoraDAO().insertOrganizacion(organizacion)

    suspend fun getOrganizations(): OrganizationsRemoteModel = source.getOrganizations(auth)

    suspend fun getUniqueOrganization(idProtectora: String): OrgRemoteModel =
        source.getUniqueOrganization(auth, idProtectora)

}