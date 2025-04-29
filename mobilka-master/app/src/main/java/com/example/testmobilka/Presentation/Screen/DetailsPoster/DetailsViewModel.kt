package com.example.testmobilka.Presentation.Screen.DetailsPoster

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmobilka.Domain.Constants.supabase
import com.example.testmobilka.Domain.States.PostersState
import com.example.testmobilka.Domain.models.Posters
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class DetailsViewModel(posterID: String):ViewModel() {

    var id = posterID
    lateinit var poster:Posters
    private val _state = mutableStateOf(PostersState())
    val state: PostersState get() = _state.value

    fun getPoster(context: Context){
    viewModelScope.launch {
        try {
            poster = supabase.postgrest.from("posters").select() {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<Posters>()

            _state.value = PostersState(
                id = poster.id,
                description = poster.description,
                category = poster.category.toString()
            )
        }
        catch (e: Exception){

            Toast.makeText(context, "Ошибка с постером", Toast.LENGTH_LONG).show()
        }



    }

}




}