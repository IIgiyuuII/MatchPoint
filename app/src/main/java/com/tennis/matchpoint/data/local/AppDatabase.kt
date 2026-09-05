package com.tennis.matchpoint.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tennis.matchpoint.data.local.dao.MatchDao
import com.tennis.matchpoint.data.local.dao.PlayerDao
import com.tennis.matchpoint.data.local.dao.TournamentDao
import com.tennis.matchpoint.data.local.dao.TournamentPlayerDao
import com.tennis.matchpoint.data.local.entity.MatchEntity
import com.tennis.matchpoint.data.local.entity.PlayerEntity
import com.tennis.matchpoint.data.local.entity.TournamentEntity
import com.tennis.matchpoint.data.local.entity.TournamentPlayerEntity

@Database(
    entities = [
        PlayerEntity::class,
        TournamentEntity::class,
        TournamentPlayerEntity::class,
        MatchEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun tournamentDao(): TournamentDao
    abstract fun tournamentPlayerDao(): TournamentPlayerDao
    abstract fun matchDao(): MatchDao
}
