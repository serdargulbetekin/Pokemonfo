package com.example.pokemon.api

import com.example.pokemon.pokemonlist.PokemonItem
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokemon.json")
    suspend fun getPokemonList(): List<PokemonItem>
}