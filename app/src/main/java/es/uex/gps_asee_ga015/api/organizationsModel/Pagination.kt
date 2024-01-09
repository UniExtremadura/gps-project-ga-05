package com.example.gps_asee_ga015.api.organizationsModel

data class Pagination(
    val _links: LinksX,
    val count_per_page: Int,
    val current_page: Int,
    val total_count: Int,
    val total_pages: Int
)