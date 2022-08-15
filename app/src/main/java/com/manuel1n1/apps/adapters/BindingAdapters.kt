package com.manuel1n1.apps.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.manuel1n1.apps.viewmodels.LoadingStates

@BindingAdapter("isGone")
fun isGone(view: View, state: LoadingStates?) {
    view.visibility = if (state == LoadingStates.ERROR) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

