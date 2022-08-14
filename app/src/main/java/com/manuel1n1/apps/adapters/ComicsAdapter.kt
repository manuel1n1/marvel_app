package com.manuel1n1.apps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuel1n1.apps.R
import com.manuel1n1.apps.data.characterDetails.ComicSummary
import com.manuel1n1.apps.databinding.ComicItemBinding

class ComicsAdapter(private val comicList: List<ComicSummary>) : RecyclerView.Adapter<ComicViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComicViewHolder(layoutInflater.inflate(R.layout.comic_item, parent, false))
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val item = comicList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = comicList.size
}

class ComicViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ComicItemBinding.bind(view)

    fun bind(item:ComicSummary) {
        binding.apply {
            comic = item
            println(item.name)
            executePendingBindings()
        }
    }
}