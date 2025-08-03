package com.example.rickandmortyapplication.presentation.fragments.filterfragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyapplication.databinding.FragmentFilterBinding
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.Gender
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.NameChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.OnApplyClicked
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.OnResetClicked
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.RadioButtonGenderChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.RadioButtonStatusChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.SpeciesChanged
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.Status
import com.example.rickandmortyapplication.presentation.fragments.filterfragment.model.TypeChanged
import com.example.rickandmortyapplication.utils.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue


class FilterFragment : Fragment() {
    private var binding: FragmentFilterBinding by autoCleared()
    private val viewModel by viewModel<FilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel._state.observe(viewLifecycleOwner) { state ->
            state?.let {
                binding.nameEditTextText.setText(state.name)
                binding.nameEditTextText.setSelection(binding.nameEditTextText.text.length)
                binding.typeEditTextText.setText(state.type)
                binding.typeEditTextText.setSelection(binding.typeEditTextText.text.length)
                binding.speciesEditTextText.setText(state.species)
                binding.speciesEditTextText.setSelection(binding.speciesEditTextText.text.length)
                when (state.status) {
                    Status.Alive -> binding.radioAlive.isChecked = true
                    Status.Dead -> binding.radioDead.isChecked = true
                    Status.Unknown -> binding.radioUnknown.isChecked = true
                    Status.None -> binding.radioNone.isChecked= true
                }
                when (state.gender) {
                    Gender.Female -> binding.radioFemale.isChecked = true
                    Gender.Male -> binding.radioMale.isChecked = true
                    Gender.Genderless -> binding.radioGenderless.isChecked = true
                    Gender.Unknown -> binding.radiogenderUnknown.isChecked = true
                    Gender.None -> binding.radioGenderNone.isChecked= true
                }

            }
        }

        viewModel._stateButton.observe(viewLifecycleOwner) { state ->
            when(state){
                true -> binding.applyButton.visibility = View.GONE
                false -> binding.applyButton.visibility = View.VISIBLE
            }
        }
        binding.nameEditTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {

                s?.toString()?.let { newValue ->
                    viewModel.onEvent(
                        NameChanged(
                            newValue
                        )
                    )
                }
            }
        })
        binding.typeEditTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {

                s?.toString()?.let { newValue ->
                    viewModel.onEvent(
                        TypeChanged(
                            newValue
                        )
                    )
                }
            }
        })
        binding.speciesEditTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {

                s?.toString()?.let { newValue ->
                    viewModel.onEvent(
                        SpeciesChanged(
                            newValue
                        )
                    )
                }
            }
        })

        binding.statusRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            val tag = selectedRadioButton.tag as? String
            tag?.let {
                viewModel.onEvent(RadioButtonStatusChanged(it))
            }
        }

        binding.genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            val tag = selectedRadioButton.tag as? String
            tag?.let {
                viewModel.onEvent(RadioButtonGenderChanged(it))
            }
        }

        binding.backImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.applyButton.setOnClickListener {
            findNavController().navigateUp()
            viewModel.onEvent(OnApplyClicked)
        }
        binding.resetButton.setOnClickListener {
            viewModel.onEvent(OnResetClicked)
        }
    }
}