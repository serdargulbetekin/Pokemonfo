package com.example.soostonetask.pokemonlist

import com.example.soostonetask.api.PokemonApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    suspend fun getPokemonList() = pokemonApi.getPokemonList()
}