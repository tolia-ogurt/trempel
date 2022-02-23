package com.trempel.pdp.repo

import com.trempel.pdp.db.RecentlyViewed
import com.trempel.pdp.db.RecentlyViewedDao
import com.trempel.pdp.model.toRecentlyViewed
import com.trempel.pdp.model.ProductDomainModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class RecentlyViewedRepositoryImpl @Inject constructor(
    private val recentlyViewedDao: RecentlyViewedDao
) : RecentlyViewedRepository {

    override fun getLatestRecentlyViewed(id: Int): Single<List<RecentlyViewed>> {
        return recentlyViewedDao.getRecentlyProductsViewed(id)
            .subscribeOn(Schedulers.io())
    }

    override fun addRecentlyViewed(productDomainModel: ProductDomainModel): Completable {
        return recentlyViewedDao.addRecentlyProductViewed(productDomainModel.toRecentlyViewed())
            .subscribeOn(Schedulers.io())
    }
}