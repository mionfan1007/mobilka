package com.example.testmobilka.Presentation.Screen.DetailsPoster

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.testmobilka.Domain.Constants
import com.example.testmobilka.Domain.Constants.supabase
import com.example.testmobilka.Domain.States.PostersState
import com.example.testmobilka.Domain.models.Posters
import com.example.testmobilka.Domain.models.category
import com.example.testmobilka.Presentation.Navigation.NavigationRoutes
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class DetailsViewModel(id: String):ViewModel() {

    val _state = mutableStateOf(DetailsState())
    val state: DetailsState get() = _state.value

    fun UpdState(new: DetailsState) {
        _state.value = new
    }

    init{
        getPoster(id)
    }

    fun getPoster(id:String){
    viewModelScope.launch {
        try {

            val categor = supabase.postgrest.from("category").select().decodeList<category>()
                .toMutableList()
            categor.add(0, category(0, "Все"))
            UpdState(state.copy(CategoryList = categor))
            Log.d("upd", "Success")
            Log.e("sdwdadf", id)
            val poster = Constants.supabase.from("posters").select() {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<Posters>()

            Log.d("decode", "Success")
            UpdState(
                state.copy(
                    id = poster.id,
                    description = poster.description,
                    category = poster.category,
                    image = poster.image
                )
            )
            Log.d("One poster", "Success")
        }
        catch (e: Exception) {
            Log.e("One poster", e.message.toString())
        }



    }

}
    fun UpdatePosters(controller: NavController){
        viewModelScope.launch {
            try {
                supabase.from("posters").update(
                    {
                        set("description",state.description)
                        set("category",state.category)
                    }
                ) {
                    filter {
                        eq("id",state.id)
                    }
                }

                Log.e(" update","success")


                controller.navigate(NavigationRoutes.AUTHORISED)
            }catch (e:Exception){
                Log.e("Error update",e.message.toString())
            }
        }
    }



}