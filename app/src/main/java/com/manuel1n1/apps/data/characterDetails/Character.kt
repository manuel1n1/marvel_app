package com.manuel1n1.apps.data.characterDetails

import java.util.*

data class Character(
    val id:Int?,
    val name:String?,
    val description:String?,
    val modified:Date?,
    val resourceURI:String?,
    val urls: Array<Url>?,
    val thumbnail: Image?,
    val comics: ComicList?,
    val stories: StoryList?,
    val events: EventList?,
    val series: SeriesList?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (modified != other.modified) return false
        if (resourceURI != other.resourceURI) return false
        if (urls != null) {
            if (other.urls == null) return false
            if (!urls.contentEquals(other.urls)) return false
        } else if (other.urls != null) return false
        if (thumbnail != other.thumbnail) return false
        if (comics != other.comics) return false
        if (stories != other.stories) return false
        if (events != other.events) return false
        if (series != other.series) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (modified?.hashCode() ?: 0)
        result = 31 * result + (resourceURI?.hashCode() ?: 0)
        result = 31 * result + (urls?.contentHashCode() ?: 0)
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        result = 31 * result + (comics?.hashCode() ?: 0)
        result = 31 * result + (stories?.hashCode() ?: 0)
        result = 31 * result + (events?.hashCode() ?: 0)
        result = 31 * result + (series?.hashCode() ?: 0)
        return result
    }
}
