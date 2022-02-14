package com.trempel.categories.model

private fun String.toDomainModel(): CategoryDomainModel? {
    return when (this) {
        CategoryDomainModel.ELECTRONICS.title -> CategoryDomainModel.ELECTRONICS
        CategoryDomainModel.WOMENS_CLOTHING.title -> CategoryDomainModel.WOMENS_CLOTHING
        CategoryDomainModel.MENS_CLOTHING.title -> CategoryDomainModel.MENS_CLOTHING
        CategoryDomainModel.JEWELERY.title -> CategoryDomainModel.JEWELERY
        else -> null
    }
}

internal fun List<String>.toCategories(): List<CategoryDomainModel> {
    return this.mapNotNull { it.toDomainModel() }
        .plus(CategoryDomainModel.ALL_PRODUCTS)
        .sorted()
}
