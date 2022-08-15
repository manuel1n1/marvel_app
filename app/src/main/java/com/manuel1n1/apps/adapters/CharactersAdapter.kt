package com.manuel1n1.apps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manuel1n1.apps.data.characterDetails.Character
import com.manuel1n1.apps.databinding.CharacterItemBinding
import com.manuel1n1.apps.fragmets.CharacterListFragmentDirections

class CharactersAdapter : ListAdapter<Character, RecyclerView.ViewHolder>(CharacterDiffCallback()) {
//class CharactersAdapter(private val characterList: List<Character>) : RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)
        (holder as CharacterViewHolder).bind(character)
    }

    class CharacterViewHolder(
        private val binding: CharacterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.character?.let { character ->
                    navigateToDetails(character, it)
                }
            }
        }

        private fun navigateToDetails(
            character: Character,
            view : View
        ) {
            val action = CharacterListFragmentDirections.actionFirstFragmentToSecondFragment(character)
            view.findNavController().navigate(action)
        }

        fun bind(item:Character) {
            binding.apply {
                character = item
                executePendingBindings()
            }
        }
    }
}


private class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}