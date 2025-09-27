package com.example.testmobilka.Presentation.Screen.DetailsPoster

import com.example.testmobilka.Domain.models.category

data class DetailsState (
    val description:String = "",
    val image:String = "",
    val ticket_price:Int =0,
    val id:String = "",
    val category:Int = 0,
    val CategoryList: List<category> = listOf()
)