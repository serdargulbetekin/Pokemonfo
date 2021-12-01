package com.example.soostonetask.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _pokemonItemList = MutableLiveData<List<PokemonItem>>()
    val pokemonItemList: LiveData<List<PokemonItem>>
        get() = _pokemonItemList

    init {
        getPokemonList()
    }
    private fun getPokemonList() {
        _progress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonItemList.postValue(pokemonRepository.getPokemonList())
            _progress.postValue(false)
        }
    }
}
