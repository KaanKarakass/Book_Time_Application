package com.kaankarakas.booktime.showAllBooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kaankarakas.booktime.adapterAndUtil.BookItemAdapter
import com.kaankarakas.booktime.bookDatabase.BookInfo
import com.kaankarakas.booktime.bookDatabase.BookInfoDatabase
import com.kaankarakas.booktime.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root

        // I must enter the books for roomdatabase It only saves once.

        val books = listOf(
            BookInfo(0, "To Kill a Mockingbird", "Harper Lee", "Fiction",false),
            BookInfo(0, "1984", "George Orwell", "Dystopian Fiction",false),
            BookInfo(0, "Frankenstein", "Mary Shelley", "Horror Fiction",false),
            BookInfo(0, "The Alchemist", "Paulo Coelho", "Fiction",false),
            BookInfo(0, "Crime and Punishment", "Fyodor Dostoevsky", "Fiction",false),
            BookInfo(0, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction",false),
            BookInfo(0, "The Catcher in the Rye", "J.D. Salinger", "Fiction",false),
            BookInfo(0, "Wuthering Heights", "Emily Brontë", "Romance Fiction",false),
            BookInfo(0, "The Picture of Dorian Gray", "Oscar Wilde", "Fiction",false),
            BookInfo(0, "The Book Thief", "Markus Zusak", "Historical Fiction",false),
            BookInfo(0, "One Hundred Years of Solitude", "Gabriel García Márquez", "Magical Realism",false),
            BookInfo(0, "Moby-Dick", "Herman Melville", "Adventure Fiction",false),
            BookInfo(0, "The Divine Comedy", "Dante Alighieri", "Epic Poetry",false),
            BookInfo(0, "The Old Man and the Sea", "Ernest Hemingway", "Fiction",false),
            BookInfo(0, "Frankenstein; or, The Modern Prometheus", "Mary Shelley", "Horror Fiction",false),
            BookInfo(0, "Dracula", "Bram Stoker", "Horror Fiction",false),
            BookInfo(0, "Sense and Sensibility", "Jane Austen", "Romance Fiction",false),
            BookInfo(0, "The Grapes of Wrath", "John Steinbeck", "Fiction",false),
            BookInfo(0, "Animal Farm", "George Orwell", "Political Fiction",false),
            BookInfo(0, "A Tale of Two Cities", "Charles Dickens", "Historical Fiction",false),
            BookInfo(0, "The Great Expectations", "Charles Dickens", "Fiction",false),
            BookInfo(0, "The Adventures of Huckleberry Finn", "Mark Twain", "Fiction",false),
            BookInfo(0, "The Invisible Man", "H.G. Wells", "Science Fiction",false),
            BookInfo(0, "Lord of the Flies", "William Golding", "Adventure Fiction",false),
            BookInfo(0, "The Return of Sherlock Holmes", "Arthur Conan Doyle", "Mystery Fiction",false),
            BookInfo(0, "The Time Machine", "H.G. Wells", "Science Fiction",false),
            BookInfo(0, "The War of the Worlds", "H.G. Wells", "Science Fiction",false),
            BookInfo(0, "Heart of Darkness", "Joseph Conrad", "Adventure Fiction",false),
            BookInfo(0, "The Hunchback of Notre Dame", "Victor Hugo", "Historical Fiction",false),
            BookInfo(0, "The Red Badge of Courage", "Stephen Crane", "Fiction",false),
            BookInfo(0, "To Kill a Kingdom", "Alexandra Christo", "Fantasy",false),
            BookInfo(0, "The Alchemist's Son", "Matthew Skelton", "Historical Fiction",false),
            BookInfo(0, "The Night Circus", "Erin Morgenstern", "Fantasy",false),
            BookInfo(0, "Little Women", "Louisa May Alcott", "Fiction",false),
            BookInfo(0, "The Help", "Kathryn Stockett", "Fiction",false),
            BookInfo(0, "The Giver", "Lois Lowry", "Dystopian Fiction",false),
            BookInfo(0, "The Color Purple", "Alice Walker", "Fiction",false),
            BookInfo(0, "The Old Man and the Sea", "Ernest Hemingway", "Fiction",false),
            BookInfo(0, "The Handmaid's Tale", "Margaret Atwood", "Dystopian Fiction",false),
            BookInfo(0, "The Lion, the Witch and the Wardrobe", "C.S. Lewis", "Fantasy",false)
        )



        val application = requireNotNull(this.activity).application
        val dao = BookInfoDatabase.getInstance(application).bookInfoDao

        val viewModelFactory = ResultViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(ResultViewModel::class.java)

        viewModel.insertBooks(books)
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
                val action = ResultFragmentDirections
                    .actionResultFragmentToEditBookFragment(bookId)
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