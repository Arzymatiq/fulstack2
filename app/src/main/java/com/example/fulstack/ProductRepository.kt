package com.example.fulstack.read

object ProductRepository {
    private val products = mutableListOf<Product>(
        Product(1, "iPhone 13", "45 000", "Телефоны", "Бишкек", "Идеал", "Азиз")
    )

    fun getAll(): List<Product> = products

    fun add(product: Product) {
        products.add(0, product)
    }

    fun update(updatedProduct: Product) {
        val index = products.indexOfFirst { it.id == updatedProduct.id }
        if (index != -1) {
            products[index] = updatedProduct
        }
    }

    fun delete(id: Int) {
        products.removeAll { it.id == id } // Теперь товар действительно будет удален из памяти
    }
}