package com.trempel.core_network.favorites_db.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesEntity(
    @PrimaryKey val productId: Int,
    var quantity: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: Long
)
