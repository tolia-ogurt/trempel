package com.example.categories.repo

import com.example.core_network.CategoryDomainModel
import com.example.core_network.CategoryModelItem
import io.reactivex.Single

interface CategoryRepository {

    fun getAllCategories(): Single<List<CategoryDomainModel>>
    fun getAllProducts(category: CategoryDomainModel): Single<List<CategoryModelItem>>

}