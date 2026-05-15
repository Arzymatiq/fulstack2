package com.example.fulstack.read

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fulstack.R

class ProductAdapter(
    private var products: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView    = view.findViewById(R.id.tv_title)
        val tvPrice: TextView    = view.findViewById(R.id.tv_price)
        val tvCategory: TextView = view.findViewById(R.id.tv_category)
        val tvLocation: TextView = view.findViewById(R.id.tv_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.tvTitle.text    = product.title
        holder.tvPrice.text    = "${product.price} сом"
        holder.tvCategory.text = product.category
        holder.tvLocation.text = "📍 ${product.location}"

        // ← вот этого не было
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title",       product.title)
                putExtra("price",       product.price)
                putExtra("category",    product.category)
                putExtra("description", product.description)
                putExtra("location",    product.location)
                putExtra("sellerName",  product.sellerName)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = products.size

    fun updateList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}