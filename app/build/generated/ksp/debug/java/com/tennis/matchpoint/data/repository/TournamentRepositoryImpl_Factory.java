package com.tennis.matchpoint.data.repository;

import com.tennis.matchpoint.data.local.dao.MatchDao;
import com.tennis.matchpoint.data.local.dao.PlayerDao;
import com.tennis.matchpoint.data.local.dao.TournamentDao;
import com.tennis.matchpoint.data.local.dao.TournamentPlayerDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class TournamentRepositoryImpl_Factory implements Factory<TournamentRepositoryImpl> {
  private final Provider<TournamentDao> tournamentDaoProvider;

  private final Provider<MatchDao> matchDaoProvider;

  private final Provider<TournamentPlayerDao> tournamentPlayerDaoProvider;

  private final Provider<PlayerDao> playerDaoProvider;

  public TournamentRepositoryImpl_Factory(Provider<TournamentDao> tournamentDaoProvider,
      Provider<MatchDao> matchDaoProvider,
      Provider<TournamentPlayerDao> tournamentPlayerDaoProvider,
      Provider<PlayerDao> playerDaoProvider) {
    this.tournamentDaoProvider = tournamentDaoProvider;
    this.matchDaoProvider = matchDaoProvider;
    this.tournamentPlayerDaoProvider = tournamentPlayerDaoProvider;
    this.playerDaoProvider = playerDaoProvider;
  }

  @Override
  public TournamentRepositoryImpl get() {
    return newInstance(tournamentDaoProvider.get(), matchDaoProvider.get(), tournamentPlayerDaoProvider.get(), playerDaoProvider.get());
  }

  public static TournamentRepositoryImpl_Factory create(
      Provider<TournamentDao> tournamentDaoProvider, Provider<MatchDao> matchDaoProvider,
      Provider<TournamentPlayerDao> tournamentPlayerDaoProvider,
      Provider<PlayerDao> playerDaoProvider) {
    return new TournamentRepositoryImpl_Factory(tournamentDaoProvider, matchDaoProvider, tournamentPlayerDaoProvider, playerDaoProvider);
  }

  public static TournamentRepositoryImpl newInstance(TournamentDao tournamentDao, MatchDao matchDao,
      TournamentPlayerDao tournamentPlayerDao, PlayerDao playerDao) {
    return new TournamentRepositoryImpl(tournamentDao, matchDao, tournamentPlayerDao, playerDao);
  }
}
