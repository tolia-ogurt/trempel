package com.example.trempel

import com.example.trempel.db.RecentlyViewed
import com.example.trempel.db.RecentlyViewedDao
import com.example.trempel.network.model.DomainModel
import com.example.trempel.network.toRecentlyViewed
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class RoomRecentlyViewedRepository @Inject constructor(
    private val recentlyViewedDao: RecentlyViewedDao
) {

    fun getLatestRecentlyViewed(id: Int): Single<MutableList<RecentlyViewed>> {
        return recentlyViewedDao.getRecentlyProductsViewed(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addRecentlyProductViewed(productDomainModel: DomainModel): Completable {
        return recentlyViewedDao.addRecentlyProductViewed(productDomainModel.toRecentlyViewed())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateRecentlyProductViewed(productDomainModel: DomainModel): Completable {
        return recentlyViewedDao.addRecentlyProductViewed(productDomainModel.toRecentlyViewed())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}