package com.tennis.matchpoint.di

import android.content.Context
import androidx.room.Room
import com.tennis.matchpoint.data.local.AppDatabase
import com.tennis.matchpoint.data.local.dao.MatchDao
import com.tennis.matchpoint.data.local.dao.PlayerDao
import com.tennis.matchpoint.data.local.dao.TournamentDao
import com.tennis.matchpoint.data.local.dao.TournamentPlayerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "match_point.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePlayerDao(db: AppDatabase): PlayerDao = db.playerDao()

    @Provides
    fun provideTournamentDao(db: AppDatabase): TournamentDao = db.tournamentDao()

    @Provides
    fun provideTournamentPlayerDao(db: AppDatabase): TournamentPlayerDao = db.tournamentPlayerDao()

    @Provides
    fun provideMatchDao(db: AppDatabase): MatchDao = db.matchDao()
}
