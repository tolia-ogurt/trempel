package com.trempel.pdp.model

import com.trempel.core_network.ProductModel
import com.trempel.core_network.Rating

internal fun ProductModel.toDomainModel(): ProductDomainModel {
    return ProductDomainModel(
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
