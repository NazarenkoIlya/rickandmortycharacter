package com.example.rickandmortyapplication.presentation.fragments.charactersfragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication.R
import com.example.rickandmortyapplication.databinding.FragmentCharactersBinding
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.GoToDetail
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.GoToFilters
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.OnFilterClicked
import com.example.rickandmortyapplication.presentation.fragments.charactersfragment.model.UIState
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.NameChanged
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

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
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

        binding.filterImageButton.setOnClickListener {
            viewModel.onEvent(OnFilterClicked)
        }

        viewModel._navigationState.observe(viewLifecycleOwner) { state ->
            when (state) {
                GoToDetail -> findNavController().navigate(R.id.action_charactersFragment_to_detailsFragment)
                GoToFilters -> findNavController().navigate(R.id.action_charactersFragment_to_filterFragment)
                else -> null
            }
            viewModel.resetStateNavigate()
        }

        viewModel._uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorMessageTextView.visibility = View.GONE
                }

                is UIState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorMessageTextView.visibility = View.GONE
                    binding.swipeRefreshLayout.visibility = View.VISIBLE
                }

                is UIState.Error -> {

                    binding.progressBar.visibility = View.GONE
                    binding.errorMessageTextView.visibility = View.VISIBLE
                    binding.errorMessageTextView.text = uiState.message
                    binding.swipeRefreshLayout.visibility = View.GONE
                }
            }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)

                val layoutManager = rv.layoutManager as GridLayoutManager

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                val isNearBottom = firstVisibleItemPosition + visibleItemCount >= totalItemCount - 4

                if (isNearBottom) {
                    viewModel.loadData()
                }
            }
        })


        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}