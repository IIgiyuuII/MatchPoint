package com.tennis.matchpoint.di

import com.tennis.matchpoint.data.repository.PlayerRepositoryImpl
import com.tennis.matchpoint.data.repository.TournamentRepositoryImpl
import com.tennis.matchpoint.domain.repository.PlayerRepository
import com.tennis.matchpoint.domain.repository.TournamentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPlayerRepository(impl: PlayerRepositoryImpl): PlayerRepository

    @Binds
    abstract fun bindTournamentRepository(impl: TournamentRepositoryImpl): TournamentRepository
}
