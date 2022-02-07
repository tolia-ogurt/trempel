package com.example.categories.repo

import com.example.categories.service.CategoryService
import com.example.core_network.CategoryDomainModel
import com.example.core_network.CategoryModelItem
import com.example.categories.model.toCategories
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val retrofitService: CategoryService
) : CategoryRepository {

    override fun getAllCategories(): Single<List<CategoryDomainModel>> {
        return retrofitService.getAllCategories()
            .subscribeOn(Schedulers.io())
            .map { it.toCategories() }
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllProducts(category: CategoryDomainModel): Single<List<CategoryModelItem>> {
        return if (category == CategoryDomainModel.ALL_PRODUCTS) {
            retrofitService.getAllProducts()
        } else {
            retrofitService.getCategoryProducts(category.title)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}