package com.trempel.pdp.repo

import com.trempel.pdp.model.ProductDomainModel
import com.trempel.pdp.db.RecentlyViewed
import io.reactivex.Completable
import io.reactivex.Single

interface RecentlyViewedRepository {

    fun getLatestRecentlyViewed(id: Int): Single<List<RecentlyViewed>>
    fun addRecentlyViewed(productDomainModel: ProductDomainModel): Completable
}