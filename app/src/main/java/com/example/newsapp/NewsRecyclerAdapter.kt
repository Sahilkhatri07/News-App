package com.example.newsapp

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.squareup.picasso.Picasso

class NewsRecyclerAdapter(val context: Context,val itemList : ArrayList<TopHeadline>) : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    class NewsViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val title : TextView = view.findViewById(R.id.title)
        val llContent : RelativeLayout = view.findViewById(R.id.llContent)
        val image : ImageView = view.findViewById(R.id.image)
        val author : TextView = view.findViewById(R.id.author)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row_activity,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val news = itemList[position]
        holder.title.text = news.title
        holder.author.text = news.author
        Picasso.get().load(news.imageUrl).into(holder.image)

        holder.llContent.setOnClickListener {
            Toast.makeText(context,"Chrome Opened",Toast.LENGTH_SHORT).show()
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(news.url))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}