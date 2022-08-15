package com.manuel1n1.apps.fragmets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.manuel1n1.apps.R
import com.manuel1n1.apps.adapters.CharactersAdapter
import com.manuel1n1.apps.adapters.ComicsAdapter
import com.manuel1n1.apps.databinding.FragmentCharacterListBinding
import com.manuel1n1.apps.databinding.FragmentComicsBinding
import com.manuel1n1.apps.viewmodels.ComicsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment : Fragment() {

    private val viewModel: ComicsViewModel by viewModels()
    private val args: ComicsFragmentArgs by navArgs()
    private lateinit var adapter: ComicsAdapter

    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.idCharacter = args.idCharacter
        viewModel.getComics()
        _binding = FragmentComicsBinding.inflate(inflater, container, false)
        configView()
        return binding.root
    }

    private fun configView() {
        adapter = ComicsAdapter()
        binding.comicsRecyclerView.adapter = adapter
        binding.comicsRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.comicList.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
        viewModel.loadingState.observe(viewLifecycleOwner) { state ->
            binding.state = state
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

}