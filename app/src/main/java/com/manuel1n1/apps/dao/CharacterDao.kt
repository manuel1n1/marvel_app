package com.manuel1n1.apps.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuel1n1.apps.data.room.CharacterT

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: List<CharacterT>)

    @Query("SELECT * FROM character")
    suspend fun getAll(): List<CharacterT>

}