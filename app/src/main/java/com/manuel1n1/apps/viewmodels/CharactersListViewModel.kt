package com.manuel1n1.apps.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuel1n1.apps.data.CharacterDataWrapper
import com.manuel1n1.apps.data.characterDetails.Character
import com.manuel1n1.apps.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

enum class LoadingStates {
    LOADING, LOADED, ERROR
}

@HiltViewModel
class CharactersListViewModel @Inject internal constructor() : ViewModel() {
    //<>

    var countList = 0
    val loadingState = MutableLiveData<LoadingStates>()
    val characterList: MutableLiveData<List<Character>> = _characterList

    private val apiService : ApiService = ApiService.create()

    init {
        loadingState.postValue(LoadingStates.LOADING)
        getCharacters()
    }

    private fun addToCount(size:Int) {
        countList += size
    }

    private fun updateWithResults(result: Response<CharacterDataWrapper>) {
        println(result.body())
        if(result.isSuccessful) {
            val list: Array<Character> = result.body()?.data?.results ?: emptyArray()
            if(_characterList.value != null) {
                _characterList.postValue(list.toList())
            }
            else
                _characterList.postValue(list.toMutableList())
            addToCount(list.size)
        } else {
            //error
            //_characterList.postValue(_characterList.value?.toMutableList() ?: emptyList<Character>().toMutableList())
        }
    }

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val apiResult = apiService.getNextCharacters(ApiService.PUBLIC_KEY, ApiService.TIMESTAMP, ApiService.HASH, countList, "name")
                updateWithResults(apiResult)
            } catch (ex: Exception) {
                loadingState.postValue(LoadingStates.ERROR)
            }
        }
    }

    private companion object {
        private var _characterList = MutableLiveData<List<Character>>()
    }
}