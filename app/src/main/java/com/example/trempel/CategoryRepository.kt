package com.example.trempel

import com.example.trempel.network.CategoryDomainModel
import com.example.trempel.network.service.CategoryService
import com.example.trempel.network.toCategories
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class CategoryRepository @Inject constructor(val retrofitService: CategoryService) {

    fun getAllCategories(): Single<List<CategoryDomainModel>> {
        return retrofitService.getAllCategories()
            .subscribeOn(Schedulers.io())
            .map {
                it.toCategories()
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}