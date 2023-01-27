package com.kaankarakas.booktime.adapterAndUtil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaankarakas.booktime.bookDatabase.BookInfo
import com.kaankarakas.booktime.databinding.BookItemBinding

class BookItemAdapter(val clickListener: (bookId:Long) ->Unit)
    :ListAdapter<BookInfo, BookItemAdapter.BookItemViewHolder>(BookDiffItemCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : BookItemViewHolder = BookItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class BookItemViewHolder(val binding: BookItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        companion object{
            fun inflateFrom(parent: ViewGroup): BookItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookItemBinding.inflate(layoutInflater, parent, false)
                return BookItemViewHolder(binding)
            }
        }
        fun bind(item:BookInfo, clickListener: (bookId: Long) -> Unit){
            binding.bookInfo = item
            binding.root.setOnClickListener{clickListener(item.bookId)}
        }
    }
}