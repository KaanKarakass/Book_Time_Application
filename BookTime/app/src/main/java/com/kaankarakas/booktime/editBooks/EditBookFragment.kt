package com.kaankarakas.booktime.editBooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.kaankarakas.booktime.bookDatabase.BookInfoDatabase
import com.kaankarakas.booktime.databinding.FragmentEditBookBinding

class EditBookFragment : Fragment() {
    private var _binding: FragmentEditBookBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditBookBinding.inflate(inflater, container, false)
        val view = binding.root

        val bookId = EditBookFragmentArgs.fromBundle(requireArguments()).bookId

        val application = requireNotNull(this.activity).application
        val dao = BookInfoDatabase.getInstance(application).bookInfoDao

        val viewModelFactory = EditBookViewModelFactory(bookId, dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(EditBookViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer {navigate ->
            if(navigate){
                view.findNavController()
                    .popBackStack()
                viewModel.onNavigatedToList()
            }
        })
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}