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
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                // Передаем весь объект (нужно, чтобы в Product.kt была аннотация @Parcelize)
                putExtra("product", product)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.tv_title)
        private val price: TextView = view.findViewById(R.id.tv_price)
        private val category: TextView = view.findViewById(R.id.tv_category)
        private val location: TextView = view.findViewById(R.id.tv_location)

        fun bind(product: Product) {
            title.text = product.title
            price.text = "${product.price} сом"
            category.text = product.category
            location.text = "📍 ${product.location}"
        }
    }
}