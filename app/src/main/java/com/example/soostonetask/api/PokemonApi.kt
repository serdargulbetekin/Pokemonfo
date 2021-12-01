package com.example.soostonetask.api

import com.example.soostonetask.pokemonlist.PokemonItem
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokemon.json")
    suspend fun getPokemonList(): List<PokemonItem>
}