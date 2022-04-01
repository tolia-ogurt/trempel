package com.trempel.home_page.model

import com.trempel.core_network.ProductModel

internal fun ProductModel.toSearchResultModel(): SearchResultModel {
    return SearchResultModel(
        category = this.category,
        id = this.id,
        image = this.image,
        price = this.price,
        title = this.title
    )
}