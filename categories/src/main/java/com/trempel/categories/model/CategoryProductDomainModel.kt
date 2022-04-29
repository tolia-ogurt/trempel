package com.trempel.categories.model

import com.trempel.core_network.ProductModel

fun ProductModel.toCategoryProduct(): CategoryProduct {
    return CategoryProduct(
        category = this.category,
        id = this.id,
        image = this.image,
        price = this.price,
        title = this.title
    )
}
