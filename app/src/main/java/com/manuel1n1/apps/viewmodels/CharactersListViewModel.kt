package com.manuel1n1.apps.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuel1n1.apps.dao.CharacterRepository
import com.manuel1n1.apps.data.CharacterDataContainer
import com.manuel1n1.apps.data.CharacterDataWrapper
import com.manuel1n1.apps.data.characterDetails.Character
import com.manuel1n1.apps.data.room.CharacterT
import com.manuel1n1.apps.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

enum class LoadingStates {
    LOADING, LOADED, ERROR
}

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    val repository: CharacterRepository
) : ViewModel() {
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

    private fun updateWithResults(result: List<Character>) {
        _characterList.postValue(result.toMutableList())
        addToCount(result.size)
    }

    fun getCharacters() {
        viewModelScope.launch {
            val prevList = repository.getCharactersList()
            if(prevList.isNotEmpty()){
                println(prevList)
            } else {
                try {
                    val apiResult = apiService.getNextCharacters(ApiService.PUBLIC_KEY, ApiService.TIMESTAMP, ApiService.HASH, countList, "name")
                    if(apiResult.isSuccessful) {
                        val list: Array<Character> = apiResult.body()?.data?.results ?: emptyArray()
                        repository.insertCharacters(list.toList())
                        updateWithResults(list.toList())
                    }
                } catch (ex: Exception) {
                    loadingState.postValue(LoadingStates.ERROR)
                }
            }
        }
    }

    companion object {
        private var _characterList = MutableLiveData<List<Character>>()
    }
}