package com.manuel1n1.apps.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manuel1n1.apps.dao.CharacterDao
import com.manuel1n1.apps.data.room.CharacterT

@Database(entities = [CharacterT::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CharacterDao():CharacterDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        fun getInstance(context:Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, "characters_db").build()
                }
            }
            return instance!!
        }
    }
}