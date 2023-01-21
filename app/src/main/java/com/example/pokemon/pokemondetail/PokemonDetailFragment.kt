package com.example.pokemon.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pokemon.pokemonlist.PokemonItem
import com.example.pokemon.pokemonlist.PokemonListFragment.Companion.EXTRAS_POKEMON_ITEM
import com.example.pokemon.databinding.FragmentPokemonDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private val viewBinding by lazy { FragmentPokemonDetailBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonItem = arguments?.getParcelable<PokemonItem>(EXTRAS_POKEMON_ITEM)
        viewBinding.apply {
            toolbar.show(pokemonItem?.name ?: "", showBack = {
                findNavController().popBackStack()
            })
            Glide.with(imageViewPokemon.context.applicationContext)
                .load(pokemonItem?.imageUrl)
                .centerCrop()
                .into(imageViewPokemon)
            textViewPokemonDescription.text = pokemonItem?.description
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = viewBinding.root
}