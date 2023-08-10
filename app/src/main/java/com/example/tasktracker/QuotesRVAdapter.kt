package com.example.tasktracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter

class QuotesRVAdapter: PagerAdapter() {

    var quotes = Quotes()

    var quotesList = quotes.quotes
    var quotesAuthorsList = quotes.authors

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val quote = holder.itemView.findViewById<TextView>(R.id.quote)
        val quoteAuthor = holder.itemView.findViewById<TextView>(R.id.quoteAuthor)

        quote.text = quotesList[position]
        quoteAuthor.text = quotesAuthorsList[position]
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        TODO("Not yet implemented")
    }

}