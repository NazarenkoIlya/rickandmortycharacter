package com.example.rickandmortyapplication.presentation.fragments.detailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.rickandmortyapplication.databinding.FragmentDetailsBinding
import com.example.rickandmortyapplication.utils.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding by autoCleared()
    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel._state.observe(viewLifecycleOwner) { state ->
            binding.nameTextView.text = state.name
            binding.informationTextView.text = state.information
            binding.imageView.load(state.image) {
                crossfade(true)
            }
        }

        binding.backImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}