package com.example.rickandmortyapplication.presentation.fragments.charactersfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication.R
import com.example.rickandmortyapplication.databinding.CharacterItemLayoutBinding
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.holders.CharacterViewHolder
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.holders.MainViewHolder
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.CharacterForRecycle
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.OnStateChanged
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.ViewItem


class CharactersAdapter(
    private val onStateChange: (OnStateChanged) -> Unit
) : ListAdapter<ViewItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CharacterForRecycle -> R.layout.character_item_layout
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            else -> {
                val binding = CharacterItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CharacterViewHolder(
                    binding,
                    onStateChange
                )
            }
        }

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        getItem(position)?.let { item ->
            (holder as MainViewHolder).bind(item)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ViewItem>() {
    override fun areItemsTheSame(oldItem: ViewItem, newItem: ViewItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ViewItem, newItem: ViewItem): Boolean {
        return oldItem == newItem
    }
}