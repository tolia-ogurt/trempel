package com.trempel.categories.repo

import com.trempel.categories.model.CategoryDomainModel
import com.trempel.categories.model.CategoryProduct
import com.trempel.categories.model.toCategories
import com.trempel.categories.model.toCategoryProduct
import com.trempel.categories.service.CategoryService
import com.trempel.core_ui.exceptions.toTrempelException
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val retrofitService: CategoryService,
) : CategoryRepository {

    override fun getAllCategories(): Single<List<CategoryDomainModel>> {
        return retrofitService.getAllCategories()
            .subscribeOn(Schedulers.io())
            .map { it.toCategories() }
            .doOnError { throw it.toTrempelException() }
    }

    override fun getAllProducts(category: CategoryDomainModel): Single<List<CategoryProduct>> {
        return if (category == CategoryDomainModel.ALL_PRODUCTS) {
            retrofitService.getAllProducts()
        } else {
            retrofitService.getCategoryProducts(category.title)
        }
            .subscribeOn(Schedulers.io())
            .map { it.map { productModel -> productModel.toCategoryProduct() } }
            .doOnError { throw it.toTrempelException() }
    }
}