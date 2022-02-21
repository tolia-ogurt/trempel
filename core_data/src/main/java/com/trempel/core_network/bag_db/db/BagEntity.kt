package com.trempel.core_network.bag_db.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bag")
data class BagEntity(
    @PrimaryKey val productId: Int,
    var quantity: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: Long
)
