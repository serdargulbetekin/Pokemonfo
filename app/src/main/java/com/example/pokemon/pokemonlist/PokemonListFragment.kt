package com.example.pokemon.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

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
                title = getString(R.string.pokemon)
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
        lifecycleScope.launch {
            viewModel.apply {
                pokemonItemList.observe(viewLifecycleOwner) {
                    pokemonListAdapter.updateData(it)
                }
                progress.observe(viewLifecycleOwner) {
                    viewBinding.progressBar.visibility = it
                }
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