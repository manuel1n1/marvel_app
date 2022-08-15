package com.manuel1n1.apps.dao


import com.manuel1n1.apps.data.room.CharacterT
import com.manuel1n1.apps.data.characterDetails.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao
){
    suspend fun insertCharacters(list: List<Character>) {
        println("Insert into Character table")
        val listT : MutableList<CharacterT> = arrayListOf()
        for(item:Character in list) {
            listT.add(getCharacterFromJS(item))
        }
        characterDao.insert(listT)
    }

    suspend fun getCharactersList() = characterDao.getAll()

    private fun getCharacterFromJS(item: Character):CharacterT {
        return CharacterT(
            item.id!!, item.name!!,
            item.description!!,
            item.resourceURI!!
        )
    }
}