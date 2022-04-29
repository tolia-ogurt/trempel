package com.trempel.home_page.repository

import com.trempel.home_page.model.SearchResultModel

interface SearchRepository {

    suspend fun getProductsBySearchQuery(searchQuery: String): List<SearchResultModel>
}
