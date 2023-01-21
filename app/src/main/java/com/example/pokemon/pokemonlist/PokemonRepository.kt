package com.example.pokemon.pokemonlist

import com.example.pokemon.api.PokemonApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    suspend fun getPokemonList() = pokemonApi.getPokemonList()
}