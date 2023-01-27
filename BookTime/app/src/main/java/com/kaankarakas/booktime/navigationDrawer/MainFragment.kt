package com.kaankarakas.booktime.navigationDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kaankarakas.booktime.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.allBooks.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToResultFragment()
            view.findNavController().navigate(action)
        }

        binding.searchBooks.setOnClickListener {
            if(binding.searchGroup.checkedRadioButtonId == -1){
                val snack = Snackbar.make(it, "Choose what to search using",Snackbar.LENGTH_LONG )
                snack.show()
            }else{
                val choose = when(binding.searchGroup.checkedRadioButtonId){
                    binding.byAuthor.id -> "Author"
                    binding.byBook.id -> "Name"
                    else -> "Category"
                }
                val action = MainFragmentDirections.actionMainFragmentToSearchBookFragment(choose)
                view.findNavController().navigate(action)
            }
        }


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}