package com.manuel1n1.apps.data.characterDetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesList(
    val available:Int?,
    val returned:Int?,
    val collectionURI:String?,
    val items:Array<SeriesSummary>?,
) : Parcelable {

    fun getSeriesList():String {
        var result: String = ""
        if (items != null) {
            for (item: SeriesSummary in items) {
                result += item.name + "\n"
            }
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SeriesList

        if (available != other.available) return false
        if (returned != other.returned) return false
        if (collectionURI != other.collectionURI) return false
        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = available ?: 0
        result = 31 * result + (returned ?: 0)
        result = 31 * result + (collectionURI?.hashCode() ?: 0)
        result = 31 * result + items.contentHashCode()
        return result
    }
}
