package com.example.trempel.network

import com.example.trempel.network.model.DomainModel
import com.example.trempel.network.model.ProductModel
import com.example.trempel.network.model.RatingDomain

internal object ProductDataMapper : Map<ProductModel, DomainModel> {

    override fun map(input: ProductModel): DomainModel {
        return DomainModel(
            category = input.category,
            description = input.description,
            id = input.id,
            image = input.image,
            price = input.price,
            rating = RatingDomain(input.rating.count, input.rating.rate),
            title = input.title
        )
    }
}