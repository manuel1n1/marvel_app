package com.manuel1n1.apps.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuel1n1.apps.data.ComicDataWrapper
import com.manuel1n1.apps.data.comicDetails.Comic
import com.manuel1n1.apps.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject internal constructor(): ViewModel() {

    var idCharacter: Int = 0
    val loadingState = MutableLiveData<LoadingStates>()
    val comicList: MutableLiveData<List<Comic>> = _comicList
    private val apiService : ApiService = ApiService.create()

    init {
        loadingState.postValue(LoadingStates.ERROR)
        _comicList = MutableLiveData()
    }

    private fun updateWithResults(result: Response<ComicDataWrapper>) {
        println(result.body())
        if(result.isSuccessful) {
            val list: Array<Comic> = result.body()?.data?.results ?: emptyArray()
            _comicList.postValue(list.toMutableList())
        }
    }

    fun getComics() {
        viewModelScope.launch {
            try {
                val apiResult = apiService.getComicsFromCharacter(
                    idCharacter,
                    ApiService.PUBLIC_KEY,
                    ApiService.TIMESTAMP, ApiService.HASH)
                updateWithResults(apiResult)
                loadingState.postValue(LoadingStates.LOADED)
            } catch (ex: Exception) {
                println(ex)
                loadingState.postValue(LoadingStates.ERROR)
            }
        }
    }

    private companion object {
        private var _comicList = MutableLiveData<List<Comic>>()
    }
}