package com.manuel1n1.apps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manuel1n1.apps.R
import com.manuel1n1.apps.data.characterDetails.Character
import com.manuel1n1.apps.data.characterDetails.ComicSummary
import com.manuel1n1.apps.data.comicDetails.Comic
import com.manuel1n1.apps.databinding.CharacterItemBinding
import com.manuel1n1.apps.databinding.ComicItemBinding
import com.manuel1n1.apps.fragmets.CharacterListFragmentDirections

class ComicsAdapter : ListAdapter<Comic, RecyclerView.ViewHolder>(ComicsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ComicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    class ComicViewHolder(
        private val binding: ComicItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Comic) {
            binding.apply {
                comic = item
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ComicViewHolder).bind(item)
    }
}

private class ComicsDiffCallback : DiffUtil.ItemCallback<Comic>() {

    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem == newItem
    }
}