package com.example.gps_asee_ga015.api.TypeModel

data class Type(
    val _links: Links,
    val coats: List<String>,
    val colors: List<String>,
    val genders: List<String>,
    val name: String
)