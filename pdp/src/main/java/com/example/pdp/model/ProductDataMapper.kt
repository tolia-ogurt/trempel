package com.example.trempel.network

import com.example.core_network.DomainModel
import com.example.trempel.network.model.ProductModel
import com.example.trempel.network.model.Rating
import com.example.core_network.RatingDomain

internal fun ProductModel.toDomainModel(): DomainModel {
    return DomainModel(
        category = this.category,
        description = this.description,
        id = this.id,
        image = this.image,
        price = this.price,
        rating = this.rating.toRatingDomain(),
        title = this.title
    )
}

private fun Rating.toRatingDomain(): RatingDomain {
    return RatingDomain(this.count, this.rate)
}