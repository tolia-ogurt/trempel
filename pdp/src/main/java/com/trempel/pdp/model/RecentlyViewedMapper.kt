package com.trempel.pdp.model

import com.trempel.pdp.db.RecentlyViewed

internal fun ProductDomainModel.toRecentlyViewed(): RecentlyViewed {
    return RecentlyViewed(
        id = this.id,
        titleProduct = this.title,
        price = this.price,
        image = this.image,
        createdAt = System.currentTimeMillis()
    )
}