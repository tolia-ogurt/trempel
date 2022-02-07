package com.example.pdp.repo

import com.example.pdp.db.RecentlyViewed
import com.example.core_network.DomainModel
import io.reactivex.Completable
import io.reactivex.Single

interface RecentlyViewedRepository {

    fun getLatestRecentlyViewed(id: Int): Single<List<RecentlyViewed>>
    fun addRecentlyViewed(productDomainModel: DomainModel): Completable
}