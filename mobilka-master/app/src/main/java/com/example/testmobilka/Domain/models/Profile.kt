package com.example.testmobilka.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id:String,
    val name:String,
    val image:String?,
    val phone:String,
    val age:String,
    val email:String
)
