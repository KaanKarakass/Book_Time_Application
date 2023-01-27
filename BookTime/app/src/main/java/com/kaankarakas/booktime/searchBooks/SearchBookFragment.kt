package com.kaankarakas.booktime.searchBooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kaankarakas.booktime.adapterAndUtil.BookItemAdapter
import com.kaankarakas.booktime.bookDatabase.BookInfoDatabase
import com.kaankarakas.booktime.databinding.FragmentSearchBookBinding

class SearchBookFragment : Fragment() {
    private var _binding: FragmentSearchBookBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentSearchBookBinding.inflate(inflater, container, false)
        val view = binding.root

        val text:String
        val choose = SearchBookFragmentArgs.fromBundle(requireArguments()).choose
        if(choose == "Author"){
            text = "Enter Author Name"
        }else if(choose == "Name"){
            text = "Enter Book Name"
        }else{
            text = "Enter Category"
        }


        val application = requireNotNull(this.activity).application
        val dao = BookInfoDatabase.getInstance(application).bookInfoDao

        val viewModelFactory = SearchBookViewModelFactory(dao,text)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(SearchBookViewModel::class.java)
        binding.viewModel = viewModel


        val adapter = BookItemAdapter{bookId ->
            viewModel.onTaskClicked(bookId)
        }
        binding.bookList.adapter = adapter


        viewModel.books.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer {bookId->
            bookId?.let {
                val action = SearchBookFragmentDirections
                    .actionSearchBookFragmentToEditBookFragment(bookId)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigated()
            }
        })
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}