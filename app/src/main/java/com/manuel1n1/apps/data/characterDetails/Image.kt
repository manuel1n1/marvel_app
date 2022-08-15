package com.manuel1n1.apps.data.characterDetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val path:String?,
    val extension:String?,
) : Parcelable {
    fun getUrlImage():String {
        return "$path.$extension"
    }
}
