package com.manuel1n1.apps.fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.manuel1n1.apps.adapters.CharactersAdapter
import com.manuel1n1.apps.databinding.FragmentFirstBinding
import com.manuel1n1.apps.viewmodels.CharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private val viewModel: CharactersListViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        configView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonReload.setOnClickListener {
            viewModel.getCharacters()
        }
    }

    private fun configView() {
        adapter = CharactersAdapter()
        binding.characterRecyclerView.adapter = adapter
        binding.characterRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.characterList.observe(viewLifecycleOwner) { characters ->
            adapter.submitList(characters)
        }
        viewModel.loadingState.observe(viewLifecycleOwner) { state ->
            binding.state = state
        }
        binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _: Int, scrollY: Int, _: Int, _: Int ->
            if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.getCharacters()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}