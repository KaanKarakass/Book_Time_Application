package com.kaankarakas.booktime.loginSignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.kaankarakas.booktime.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.signUpButton.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToSignUpFragment()
            view.findNavController().navigate(action)
        }
        binding.LoginButton.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }
}