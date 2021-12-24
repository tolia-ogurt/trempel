package com.example.trempel.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recently_viewed")
data class RecentlyViewed(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title_product")
    val titleProduct: String,
    val price: Float,
    val image: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long
)
