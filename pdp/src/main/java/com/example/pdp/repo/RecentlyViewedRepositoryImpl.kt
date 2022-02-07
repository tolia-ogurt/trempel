package com.example.pdp.repo

import com.example.pdp.db.RecentlyViewed
import com.example.pdp.db.RecentlyViewedDao
import com.example.core_network.DomainModel
import com.example.trempel.network.toRecentlyViewed
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class RecentlyViewedRepositoryImpl @Inject constructor(
    private val recentlyViewedDao: RecentlyViewedDao
) : RecentlyViewedRepository {

    override fun getLatestRecentlyViewed(id: Int): Single<List<RecentlyViewed>> {
        return recentlyViewedDao.getRecentlyProductsViewed(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addRecentlyViewed(productDomainModel: DomainModel): Completable {
        return recentlyViewedDao.addRecentlyProductViewed(productDomainModel.toRecentlyViewed())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}