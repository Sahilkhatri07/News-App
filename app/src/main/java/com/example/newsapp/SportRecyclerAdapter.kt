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

class SportRecyclerAdapter(val context: Context,val itemList : ArrayList<Sport>) : RecyclerView.Adapter<SportRecyclerAdapter.SportViewHolder>() {

    class SportViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val title : TextView = view.findViewById(R.id.title)
        val llContent : RelativeLayout = view.findViewById(R.id.llContent)
        val image : ImageView = view.findViewById(R.id.image)
        val author : TextView = view.findViewById(R.id.author)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_sport,parent,false)
        return SportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val text = itemList[position]
        holder.title.text = text.title
        holder.author.text = text.author
        Picasso.get().load(text.imageUrl).into(holder.image)

        holder.llContent.setOnClickListener {
            Toast.makeText(context,"Open Chrome",Toast.LENGTH_SHORT).show()
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(text.url))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}