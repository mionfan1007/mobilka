package com.example.testmobilka.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Posters(
    val id:String,
    val description:String,
    val category:Int,
    val image:String,
    val ticket_price:Int


)
