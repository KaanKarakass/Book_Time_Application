package com.kaankarakas.booktime.navigationDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.kaankarakas.booktime.R
import com.kaankarakas.booktime.databinding.FragmentProfileBinding
import com.kaankarakas.booktime.databinding.FragmentSearchBookBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        val intent = activity?.intent
        val userMail = intent?.getStringExtra("key")

        binding.sumText.text = userMail.toString()
        binding.backButton.setOnClickListener {
            view.findNavController().popBackStack()
        }
        return view

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}