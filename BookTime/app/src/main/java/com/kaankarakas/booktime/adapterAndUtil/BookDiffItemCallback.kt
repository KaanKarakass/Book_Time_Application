package com.kaankarakas.booktime.adapterAndUtil
import androidx.recyclerview.widget.DiffUtil
import com.kaankarakas.booktime.bookDatabase.BookInfo

class BookDiffItemCallback: DiffUtil.ItemCallback<BookInfo>()  {
    override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo)
            =(oldItem.bookId == newItem.bookId)

    override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo) = (oldItem == newItem)
}