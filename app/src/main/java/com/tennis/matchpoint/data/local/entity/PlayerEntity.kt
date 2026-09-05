package com.tennis.matchpoint.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val colorSeed: Int,
    val createdAt: Long = System.currentTimeMillis()
)
