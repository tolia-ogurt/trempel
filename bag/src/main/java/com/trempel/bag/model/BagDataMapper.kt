package com.trempel.bag.model

import com.trempel.core_network.bag_db.db.BagEntity
import com.trempel.core_network.ProductModel

fun ProductModel.toBagDomainModel(productQuantity: Int): BagDomainModel {
    return BagDomainModel(
        id = this.id,
        image = this.image,
        price = this.price,
        title = this.title,
        quantity = productQuantity
    )
}

fun BagDomainModel.toBagEntity(quantity: Int = 1): BagEntity {
    return BagEntity(
        productId = this.id,
        quantity = quantity,
        createdAt = System.currentTimeMillis()
    )
}