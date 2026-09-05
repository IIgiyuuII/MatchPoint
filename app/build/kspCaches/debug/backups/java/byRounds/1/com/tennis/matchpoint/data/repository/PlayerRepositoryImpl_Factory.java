package com.tennis.matchpoint.data.repository;

import com.tennis.matchpoint.data.local.dao.MatchDao;
import com.tennis.matchpoint.data.local.dao.PlayerDao;
import com.tennis.matchpoint.data.local.dao.TournamentDao;
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
public final class PlayerRepositoryImpl_Factory implements Factory<PlayerRepositoryImpl> {
  private final Provider<PlayerDao> playerDaoProvider;

  private final Provider<MatchDao> matchDaoProvider;

  private final Provider<TournamentDao> tournamentDaoProvider;

  public PlayerRepositoryImpl_Factory(Provider<PlayerDao> playerDaoProvider,
      Provider<MatchDao> matchDaoProvider, Provider<TournamentDao> tournamentDaoProvider) {
    this.playerDaoProvider = playerDaoProvider;
    this.matchDaoProvider = matchDaoProvider;
    this.tournamentDaoProvider = tournamentDaoProvider;
  }

  @Override
  public PlayerRepositoryImpl get() {
    return newInstance(playerDaoProvider.get(), matchDaoProvider.get(), tournamentDaoProvider.get());
  }

  public static PlayerRepositoryImpl_Factory create(Provider<PlayerDao> playerDaoProvider,
      Provider<MatchDao> matchDaoProvider, Provider<TournamentDao> tournamentDaoProvider) {
    return new PlayerRepositoryImpl_Factory(playerDaoProvider, matchDaoProvider, tournamentDaoProvider);
  }

  public static PlayerRepositoryImpl newInstance(PlayerDao playerDao, MatchDao matchDao,
      TournamentDao tournamentDao) {
    return new PlayerRepositoryImpl(playerDao, matchDao, tournamentDao);
  }
}
