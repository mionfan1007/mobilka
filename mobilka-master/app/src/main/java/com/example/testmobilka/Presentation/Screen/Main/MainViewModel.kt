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

    private val _state = mutableStateOf(PostersState())
    val state: PostersState get() = _state.value

    private val _posters = MutableLiveData<List<Posters>>()
    val posters: LiveData<List<Posters>> get() = _posters

    private val _categories = MutableLiveData<List<category>>()
    val categories: LiveData<List<category>> get() = _categories

    private var PostersAll: List<Posters> = listOf()

    fun updatestate(newstate: PostersState) {
        _state.value = newstate
    }

    fun updateState(newState: PostersState) {
        _state.value = newState
    }

    fun getData() {
        viewModelScope.launch {
            val getInfo = com.example.testmobilka.Domain.Constants.supabase
            val responseSupa = getInfo.postgrest["posters"].select()
            val data = responseSupa.decodeList<PostersState>()
        }
    }

    fun loadPosters(context: Context) {

        viewModelScope.launch {
            try {
                PostersAll = Constants.supabase.postgrest.from("posters").select().decodeList<Posters>()
                _posters.value = PostersAll
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

                updatestate(state.copy(allCategories = category, allPosters = posters))

                Log.e("category",state.allCategories.toString())
                Log.e("posters",state.allPosters.toString())

            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка с категориями", Toast.LENGTH_LONG).show()
                Log.e("cat",e.message.toString())
            }
        }
    }

    fun filterList(query: String?, categoryId: Int?) {
        val filteredPosters = PostersAll.filter { poster ->
            val matchesDesc = query.isNullOrEmpty() || poster.description.contains(query, ignoreCase = true)
            val matchesCategory = categoryId == -1 || poster.category == categoryId
            matchesDesc && matchesCategory

        }
        _posters.value = filteredPosters

    }
}


