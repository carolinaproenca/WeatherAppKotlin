package com.example.android.application.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.application.R
import com.example.android.application.databinding.StartFragmentBinding


class StartFragment : Fragment() {

    private lateinit var binding: StartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartFragmentBinding.inflate(inflater, container, false)

        binding.nextButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_startFragment_to_weatherFragment)
        }
        return binding.root
    }
}