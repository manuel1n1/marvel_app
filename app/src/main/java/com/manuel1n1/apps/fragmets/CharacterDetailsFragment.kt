package com.manuel1n1.apps.fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.navigation.findNavController
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
class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val args: CharacterDetailsFragmentArgs by navArgs()

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
        binding.comicHeader.setOnClickListener {
            binding.character.let { character ->
                if(character?.comics != null) {
                    if(character?.comics.items != null && character?.comics.items.isNotEmpty()) {
                        val action =
                            CharacterDetailsFragmentDirections.actionSecondFragmentToComicsFragment(
                                character?.id!!
                            )
                        it.findNavController().navigate(action)
                    } else
                        Toast.makeText(context, "No comics available", Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(context, "No comics available", Toast.LENGTH_SHORT).show()
            }
        }
        binding.executePendingBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}