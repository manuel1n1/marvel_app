package com.manuel1n1.apps.data.characterDetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Url(
    val type:String?,
    val url:String?
) : Parcelable