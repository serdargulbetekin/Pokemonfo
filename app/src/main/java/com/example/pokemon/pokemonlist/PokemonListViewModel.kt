package com.example.pokemon.pokemonlist

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

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int>
        get() = _progress

    private val _pokemonItemList = MutableLiveData<List<PokemonItem>>()
    val pokemonItemList: LiveData<List<PokemonItem>>
        get() = _pokemonItemList

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        _progress.postValue(PROGRESS_VISIBLE)
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonItemList.postValue(pokemonRepository.getPokemonList())
            _progress.postValue(PROGRESS_GONE)
        }
    }

    companion object{
        private const val PROGRESS_VISIBLE = 0
        private const val PROGRESS_GONE = 8
    }
}
