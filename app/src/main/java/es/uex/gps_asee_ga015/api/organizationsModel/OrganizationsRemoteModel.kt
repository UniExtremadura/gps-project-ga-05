package es.uex.gps_asee_ga015.api.organizationsModel

data class OrganizationsRemoteModel(
    val organizations: List<Organization>,
    val pagination: Pagination
)