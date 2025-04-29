package com.example.testmobilka.Domain.States

import com.example.testmobilka.Domain.models.Posters
import com.example.testmobilka.Domain.models.category

data class PostersState(
    val id: String = "",
    val description: String = "",
    val category: String = "",
    val image: String ="",
    val ticket_price: String ="",
    var search:String = "",
    var allCategories:List<category> = listOf(),
    var allPosters:List<Posters> = listOf()
)
