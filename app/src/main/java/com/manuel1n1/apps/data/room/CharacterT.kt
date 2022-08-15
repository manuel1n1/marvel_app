package com.manuel1n1.apps.data.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.manuel1n1.apps.data.characterDetails.*
import java.util.*


@Entity(tableName = "character")
data class CharacterT(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val description:String,
    val resourceURI:String,
    /*@Ignore
    val urls: Array<Url>,
    @Ignore
    val thumbnail: Image,
    @Ignore
    val comics: ComicList,
    @Ignore
    val stories: StoryList,
    @Ignore
    val events: EventList,
    @Ignore
    val series: SeriesList*/
){

}
