package com.trempel.home_page.repository

import com.trempel.home_page.model.SearchResultModel
import com.trempel.home_page.model.toSearchResultModel
import com.trempel.home_page.service.SearchService
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRepository {

    override suspend fun getProductsBySearchQuery(
        searchQuery: String
    ): List<SearchResultModel> {
        val keyList = searchQuery.split(" ", ".", ",", ":", "?")
        return searchService.getAllProducts().filter {
            keyList.all { key ->
                "${it.title} ${it.description} ${it.category}".contains(key, ignoreCase = true)
            }
        }.map { productModel -> productModel.toSearchResultModel() }
    }
}
