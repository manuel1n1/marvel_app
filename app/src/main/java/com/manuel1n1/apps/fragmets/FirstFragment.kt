package com.manuel1n1.apps.fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.manuel1n1.apps.adapters.CharactersAdapter
import com.manuel1n1.apps.data.CharacterDataWrapper
import com.manuel1n1.apps.data.characterDetails.Character
import com.manuel1n1.apps.databinding.CharacterItemBinding
import com.manuel1n1.apps.databinding.FragmentFirstBinding
import com.manuel1n1.apps.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), CoroutineScope {

    private lateinit var adapter: CharactersAdapter
    private val charactersList = mutableListOf<Character>()

    private var job : Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        initRecyclerView()
        launch {
            try {
                val apiInterface = ApiService.create().getCharacters(ApiService.PUBLIC_KEY, ApiService.TIMESTAMP, ApiService.HASH, "name")
                onResult(apiInterface)
            } catch (ex: Exception) {
                println(ex)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonReload.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            launch {
                try {
                    val apiInterface = ApiService.create().getCharacters(ApiService.PUBLIC_KEY, ApiService.TIMESTAMP, ApiService.HASH, "name")
                    onResult(apiInterface)
                } catch (ex: Exception) {
                    println(ex)
                }
            }
        }
    }

    private fun suscribeUI(adapter: CharactersAdapter, binding: CharacterItemBinding) {

    }

    private fun initRecyclerView() {
        adapter = CharactersAdapter(charactersList)
        binding.characterListView.layoutManager = LinearLayoutManager(context)
        binding.characterListView.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        job.cancel()
    }

    private fun onResult(result: Response<CharacterDataWrapper>) {
        println(result.body())
        if(result.isSuccessful) {
            val list: Array<Character> = result.body()?.data?.results ?: emptyArray()
            charactersList.addAll(list)
            adapter.notifyDataSetChanged()
        } else {
            //error
        }
    }
}