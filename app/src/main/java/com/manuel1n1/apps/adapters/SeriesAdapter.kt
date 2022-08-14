package com.manuel1n1.apps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuel1n1.apps.R
import com.manuel1n1.apps.data.characterDetails.SeriesSummary
import com.manuel1n1.apps.databinding.SerieItemBinding

class SeriesAdapter(private val serieList: List<SeriesSummary>) : RecyclerView.Adapter<SerieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SerieViewHolder(layoutInflater.inflate(R.layout.serie_item, parent, false))
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val item = serieList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = serieList.size
}

class SerieViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = SerieItemBinding.bind(view)

    fun bind(item:SeriesSummary) {
        binding.apply {
            serie = item
            println(item.name)
            executePendingBindings()
        }
    }
}