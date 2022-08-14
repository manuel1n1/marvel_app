package com.manuel1n1.apps.fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.manuel1n1.apps.R
import com.manuel1n1.apps.adapters.CharactersAdapter
import com.manuel1n1.apps.adapters.ComicsAdapter
import com.manuel1n1.apps.adapters.SeriesAdapter
import com.manuel1n1.apps.data.characterDetails.Character
import com.manuel1n1.apps.data.characterDetails.ComicSummary
import com.manuel1n1.apps.databinding.FragmentSecondBinding
import com.manuel1n1.apps.network.ApiService
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var adapterComics : ComicsAdapter
    private lateinit var adapterSeries : SeriesAdapter
    private var _binding: FragmentSecondBinding? = null
    private val args: SecondFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.character = args.characterItem
        initRecyclerView()
        binding.buttonSecond.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.executePendingBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        if(binding.character?.comics == null)
            adapterComics = ComicsAdapter(mutableListOf())
        else
            adapterComics = ComicsAdapter(binding.character?.comics?.items?.asList() ?: mutableListOf())
        if(binding.character?.series == null)
            adapterSeries = SeriesAdapter(mutableListOf())
        else
            adapterSeries = SeriesAdapter(binding.character?.series?.items?.asList() ?: mutableListOf())
        binding.comicsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.seriesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.comicsRecyclerView.adapter = adapterComics
        binding.seriesRecyclerView.adapter = adapterSeries
    }

}