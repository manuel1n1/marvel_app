package com.manuel1n1.apps.data.comicDetails

import com.manuel1n1.apps.data.characterDetails.ComicSummary
import com.manuel1n1.apps.data.characterDetails.Image
import com.manuel1n1.apps.data.characterDetails.SeriesSummary
import java.util.*

data class Comic(
    val id:Int?,
    val digitalId: Int?,
    val title: String?,
    val issueNumber : Double?,
    val variantDescription: String?,
    val description: String?,
    val modified: Date?,
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format : String?,
    val pageCount: Int?,
    val resourceURI: String?,
    val series: SeriesSummary?,
    val variants : Array<ComicSummary>,
    val collections:Array<ComicSummary>?,
    val collectedIssues: Array<ComicSummary>?,
    val images: Array<Image>?
) {

    fun getImageDemo():String {
        return if (images != null && images.isNotEmpty())
            images[0].getUrlImage()
        else
            ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comic

        if (id != other.id) return false
        if (digitalId != other.digitalId) return false
        if (title != other.title) return false
        if (issueNumber != other.issueNumber) return false
        if (variantDescription != other.variantDescription) return false
        if (description != other.description) return false
        if (modified != other.modified) return false
        if (isbn != other.isbn) return false
        if (upc != other.upc) return false
        if (diamondCode != other.diamondCode) return false
        if (ean != other.ean) return false
        if (issn != other.issn) return false
        if (format != other.format) return false
        if (pageCount != other.pageCount) return false
        if (resourceURI != other.resourceURI) return false
        if (series != other.series) return false
        if (!variants.contentEquals(other.variants)) return false
        if (collections != null) {
            if (other.collections == null) return false
            if (!collections.contentEquals(other.collections)) return false
        } else if (other.collections != null) return false
        if (collectedIssues != null) {
            if (other.collectedIssues == null) return false
            if (!collectedIssues.contentEquals(other.collectedIssues)) return false
        } else if (other.collectedIssues != null) return false
        if (images != null) {
            if (other.images == null) return false
            if (!images.contentEquals(other.images)) return false
        } else if (other.images != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (digitalId ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (issueNumber?.hashCode() ?: 0)
        result = 31 * result + (variantDescription?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (modified?.hashCode() ?: 0)
        result = 31 * result + (isbn?.hashCode() ?: 0)
        result = 31 * result + (upc?.hashCode() ?: 0)
        result = 31 * result + (diamondCode?.hashCode() ?: 0)
        result = 31 * result + (ean?.hashCode() ?: 0)
        result = 31 * result + (issn?.hashCode() ?: 0)
        result = 31 * result + (format?.hashCode() ?: 0)
        result = 31 * result + (pageCount ?: 0)
        result = 31 * result + (resourceURI?.hashCode() ?: 0)
        result = 31 * result + (series?.hashCode() ?: 0)
        result = 31 * result + variants.contentHashCode()
        result = 31 * result + (collections?.contentHashCode() ?: 0)
        result = 31 * result + (collectedIssues?.contentHashCode() ?: 0)
        result = 31 * result + (images?.contentHashCode() ?: 0)
        return result
    }

}