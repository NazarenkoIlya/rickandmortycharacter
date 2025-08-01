package com.example.rickandmortyapplication.presentation.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapplication.databinding.FragmentCharactersBinding
import com.example.rickandmortyapplication.presentation.fragments.adapter.CharactersAdapter
import com.example.rickandmortyapplication.presentation.fragments.viewmodel.CharactersViewModel
import com.example.rickandmortyapplication.utils.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {


    private var binding: FragmentCharactersBinding by autoCleared()
    private val viewModel by viewModel<CharactersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 2)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = layoutManager


        val adapter = CharactersAdapter {
            viewModel.onEvent(it)
        }
        binding.recyclerView.adapter = adapter

        viewModel._state.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state)
        }
    }
}