package com.trempel.categories.repo

import com.trempel.categories.model.CategoryDomainModel
import com.trempel.categories.model.CategoryProduct
import io.reactivex.Single

interface CategoryRepository {
    fun getAllCategories(): Single<List<CategoryDomainModel>>
    fun getAllProducts(category: CategoryDomainModel): Single<List<CategoryProduct>>
}
