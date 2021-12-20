package com.example.trempel

import com.example.trempel.network.CategoryDomainModel
import com.example.trempel.network.model.CategoryModelItem
import com.example.trempel.network.service.MensCategoryService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class MensCategoryRepository @Inject constructor(private val retrofitService: MensCategoryService) {

    fun getMensCategory(category: CategoryDomainModel): Single<List<CategoryModelItem>> {
        return retrofitService.getProductForCategory(category.title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}