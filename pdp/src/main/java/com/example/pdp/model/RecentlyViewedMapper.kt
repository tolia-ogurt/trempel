package com.example.trempel.network

import com.example.pdp.db.RecentlyViewed
import com.example.core_network.DomainModel


internal fun DomainModel.toRecentlyViewed(): RecentlyViewed {
    return RecentlyViewed(
        id = this.id,
        titleProduct = this.title,
        price = this.price,
        image = this.image,
        createdAt = System.currentTimeMillis()
    )
}