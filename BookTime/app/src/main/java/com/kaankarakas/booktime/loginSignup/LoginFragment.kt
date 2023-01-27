package com.kaankarakas.booktime.loginSignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kaankarakas.booktime.loginDatabase.UserInfoDatabase
import com.kaankarakas.booktime.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root


        //build the database
        val application = requireNotNull(this.activity).application
        val dao = UserInfoDatabase.getInstance(application).userInfoDao

        //get view model
        val viewModelFactory = LoginViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        binding.nextLoginButton.setOnClickListener {
            viewModel.checkUser(context, activity)
        }

        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}