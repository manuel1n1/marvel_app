package com.manuel1n1.apps.data.characterDetails

data class Image(
    val path:String?,
    val extension:String?,
) {
    fun getUrlImage():String {
        return "$path.$extension"
    }
}
