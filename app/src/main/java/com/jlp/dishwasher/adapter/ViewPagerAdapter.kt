package com.jlp.dishwasher.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jlp.dishwasher.R

class ViewPagerAdapter(var urlList: List<String>, private val context: Context) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.pager_cell, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return urlList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load("https://" + urlList[position])
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.imageView)

        Log.e("URL", urlList[position])
    }

    open class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {

            imageView = itemView.findViewById(R.id.productImage)
        }
    }

}