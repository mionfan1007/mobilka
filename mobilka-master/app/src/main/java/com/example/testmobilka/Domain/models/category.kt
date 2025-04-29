package com.example.testmobilka.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class category(
    val id:Int,
    val category:String
)
