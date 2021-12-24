package com.example.trempel.network

import com.example.trempel.db.RecentlyViewed
import com.example.trempel.network.model.DomainModel
import com.example.trempel.network.model.ProductModel
import com.example.trempel.network.model.Rating
import com.example.trempel.network.model.RatingDomain

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

internal fun DomainModel.toRecentlyViewed(): RecentlyViewed {
    return RecentlyViewed(
        id = this.id,
        titleProduct = this.title,
        price = this.price,
        image = this.image,
        createdAt = System.currentTimeMillis()
    )
}