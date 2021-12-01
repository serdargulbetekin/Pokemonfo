package com.example.soostonetask.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soostonetask.R
import com.example.soostonetask.databinding.FragmentPokemonListBinding
import com.example.soostonetask.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel by viewModels<PokemonListViewModel>()
    private val viewBinding by lazy { FragmentPokemonListBinding.inflate(layoutInflater) }

    private val pokemonListAdapter by lazy {
        PokemonListAdapter {
            onPokemonItemClick(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = pokemonListAdapter
            }
            toolbar.show(
                title = getString(R.string.pokemon),
                showMenu = {
                    context?.showToast(R.string.menu_error)
                }
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVM()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = viewBinding.root

    private fun observeVM() {
        viewModel.apply {
            lifecycleScope.launch {
                pokemonItemList.observe(viewLifecycleOwner, {
                    pokemonListAdapter.updateData(it)
                })
            }
        }
    }

    private fun onPokemonItemClick(pokemonItem: PokemonItem) {
        findNavController().navigate(
            R.id.action_pokemonListFragment_to_pokemonDetailFragment,
            Bundle().apply {
                putParcelable(EXTRAS_POKEMON_ITEM, pokemonItem)
            })
    }

    companion object {
        const val EXTRAS_POKEMON_ITEM = "EXTRAS_POKEMON_ITEM"
    }
}