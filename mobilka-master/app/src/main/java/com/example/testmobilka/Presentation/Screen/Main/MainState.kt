package com.example.testmobilka.Presentation.Screen.Main

import com.example.testmobilka.Domain.models.Posters
import com.example.testmobilka.Domain.models.category

data class MainState (
    val ListPoster: List<Posters> = listOf(),
    val CategoryList: List<category> = listOf(),
    val selectedID: category = category(0,"Все"),
    val searchState:String = ""
)