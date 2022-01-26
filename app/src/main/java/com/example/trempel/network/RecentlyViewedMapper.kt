package com.example.trempel.network

import com.example.trempel.db.RecentlyViewed
import com.example.trempel.network.model.DomainModel

internal fun DomainModel.toRecentlyViewed(): RecentlyViewed {
    return RecentlyViewed(
        id = this.id,
        titleProduct = this.title,
        price = this.price,
        image = this.image,
        createdAt = System.currentTimeMillis()
    )
}