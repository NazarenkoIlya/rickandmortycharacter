package com.example.rickandmortyapplication.presentation.fragments.holders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapplication.databinding.CharacterItemLayoutBinding
import com.example.rickandmortyapplication.presentation.model.CharacterForRecycle
import com.example.rickandmortyapplication.presentation.model.OnItemClicked
import com.example.rickandmortyapplication.presentation.model.OnStateChanged
import com.example.rickandmortyapplication.presentation.model.ViewItem
import com.example.rickandmortyapplication.presentation.model.statusColor


class CharacterViewHolder(
    val binding: CharacterItemLayoutBinding,
    private val onStateChange: (OnStateChanged) -> Unit
) : RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {
        item as CharacterForRecycle
        binding.nameTextView.text = item.name
        binding.statusTextView.text = item.status
        binding.statusColorView.setBackgroundColor(item.statusColor())
        binding.genderAndSpeciesTextView.text = item.genderAndSpecies
        binding.imageView.load(item.image.url) {
            error(item.image.error)
            crossfade(true)
        }
        binding.root.setOnClickListener {
            onStateChange.invoke(
                OnItemClicked
            )
        }

    }
}