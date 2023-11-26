package es.uex.gps_asee_ga015.api.model

data class RemoteModelPage(
    val animals: List<Animal>,
    val pagination: Pagination
)