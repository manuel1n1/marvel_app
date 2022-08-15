package com.manuel1n1.apps.data

import com.manuel1n1.apps.data.comicDetails.Comic

data class ComicDataContainer(
    val offset:Int?,
    val limit:Int?,
    val total:Int?,
    val count:Int?,
    val results: Array<Comic>?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ComicDataContainer

        if (offset != other.offset) return false
        if (limit != other.limit) return false
        if (total != other.total) return false
        if (count != other.count) return false
        if (results != null) {
            if (other.results == null) return false
            if (!results.contentEquals(other.results)) return false
        } else if (other.results != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = offset ?: 0
        result = 31 * result + (limit ?: 0)
        result = 31 * result + (total ?: 0)
        result = 31 * result + (count ?: 0)
        result = 31 * result + (results?.contentHashCode() ?: 0)
        return result
    }

}