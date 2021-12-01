package com.example.soostonetask.pokemonlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soostonetask.databinding.RowPokemonBinding

class PokemonListAdapter(private val onClick: (PokemonItem) -> Unit) :
    RecyclerView.Adapter<JourneyViewHolder>() {

    private val itemList = mutableListOf<PokemonItem>()

    fun updateData(itemList: List<PokemonItem>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JourneyViewHolder {
        return JourneyViewHolder(
            RowPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: JourneyViewHolder, position: Int) {
        holder.bind(itemList[position], onClick)
    }
}

class JourneyViewHolder(private val binding: RowPokemonBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PokemonItem, onClick: (PokemonItem) -> Unit) {
        binding.apply {
            textViewPokemonName.text = item.name
            textViewPokemonDescription.text = item.description
            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .centerCrop()
                .into(binding.imageViewPokemon)
            constraintLayout.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }
}