package com.example.trempel

import com.example.trempel.network.CategoryDomainModel
import com.example.trempel.network.model.CategoryModelItem
import com.example.trempel.network.service.CategoryService
import com.example.trempel.network.toCategories
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class CategoryRepository @Inject constructor(private val retrofitService: CategoryService) {

    fun getAllCategories(): Single<List<CategoryDomainModel>> {
        return retrofitService.getAllCategories()
            .subscribeOn(Schedulers.io())
            .map { it.toCategories() }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProducts(category: CategoryDomainModel): Single<List<CategoryModelItem>> {
        return if (category == CategoryDomainModel.ALL_PRODUCTS) {
            retrofitService.getAllProducts()
        } else {
            retrofitService.getCategoryProducts(category.title)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}