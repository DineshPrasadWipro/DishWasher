package com.jlp.dishwasher.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jlp.dishwasher.R
import com.jlp.dishwasher.model.CustomProduct


class ProductListAdapter internal constructor(
    productList: List<CustomProduct>,
    clickListener: ItemClickListener,
    context: Context
) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private var mData: List<CustomProduct>
    private var mContext: Context
    private var mClickListener: ItemClickListener? = null

    init {
        mData = productList
        mClickListener = clickListener
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = mData[position].title
        holder.price.text = "£ " +
                "" + mData[position].price.now
        holder.lyt.setOnClickListener {
            mClickListener?.onItemClick(mData[position])
        }
        Glide.with(mContext)
            .load("https://" + mData[position].image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName: TextView
        var lyt: LinearLayout
        var productImage: ImageView
        var price: TextView


        init {
            productName = itemView.findViewById(R.id.title)
            lyt = itemView.findViewById(R.id.lyt)
            productImage = itemView.findViewById(R.id.productImage)
            price = itemView.findViewById(R.id.price)

        }
    }


    internal interface ItemClickListener {
        fun onItemClick(product: CustomProduct)
    }


}