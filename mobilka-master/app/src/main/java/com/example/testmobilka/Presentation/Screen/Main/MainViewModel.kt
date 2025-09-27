package com.example.testmobilka.Presentation.Screen.Main
import android.content.Context
import android.media.Image
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmobilka.Domain.Constants
import com.example.testmobilka.Domain.Constants.supabase
import com.example.testmobilka.Domain.States.PostersState
import com.example.testmobilka.Domain.models.Posters
import com.example.testmobilka.Domain.models.category
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel: ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: MainState get() = _state.value



    fun updateState(newState: MainState) {
        _state.value = newState
    }



    fun loadPosters(context: Context) {

        viewModelScope.launch {
            try {
                val PostersAll = Constants.supabase.postgrest.from("posters").select().decodeList<Posters>()
                val categor = supabase.postgrest.from("category").select().decodeList<category>().toMutableList()
                categor.add(0,category(0,"Все"))

                Log.e("1", PostersAll.toString())
                updateState(state.copy(CategoryList = categor))
                updateState(state.copy(ListPoster = PostersAll))
                Log.d("GET all posters","Good")
                Log.d("posters",state.ListPoster.toString())

            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка с постерами", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getData(context: Context) {
        viewModelScope.launch {
            try {



                val category = Constants.supabase.from("category").select().decodeList<category>()
                val posters = Constants.supabase.from("posters").select().decodeList<Posters>()

                Log.e("category",category.toString())

                updateState(state.copy(CategoryList = category, ListPoster = posters))

                Log.e("category",state.CategoryList.toString())
                Log.e("posters",state.ListPoster.toString())

            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка с категориями", Toast.LENGTH_LONG).show()
                Log.e("cat",e.message.toString())
            }
        }
    }

}


