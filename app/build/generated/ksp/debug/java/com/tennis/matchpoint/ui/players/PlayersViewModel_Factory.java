package com.tennis.matchpoint.ui.players;

import com.tennis.matchpoint.domain.repository.PlayerRepository;
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
public final class PlayersViewModel_Factory implements Factory<PlayersViewModel> {
  private final Provider<PlayerRepository> playerRepositoryProvider;

  public PlayersViewModel_Factory(Provider<PlayerRepository> playerRepositoryProvider) {
    this.playerRepositoryProvider = playerRepositoryProvider;
  }

  @Override
  public PlayersViewModel get() {
    return newInstance(playerRepositoryProvider.get());
  }

  public static PlayersViewModel_Factory create(
      Provider<PlayerRepository> playerRepositoryProvider) {
    return new PlayersViewModel_Factory(playerRepositoryProvider);
  }

  public static PlayersViewModel newInstance(PlayerRepository playerRepository) {
    return new PlayersViewModel(playerRepository);
  }
}
