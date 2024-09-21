package com.techgv.disasterhelper.data.dto

data class Community(
    val id: Int,
    val name: String,
    val city: City,
    val latitude: Location,
    val contactPerson: Volunteer,
    val utility: Utility
)
