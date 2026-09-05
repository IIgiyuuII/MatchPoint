package com.tennis.matchpoint.ui.createtournament;

import com.tennis.matchpoint.domain.repository.PlayerRepository;
import com.tennis.matchpoint.domain.repository.TournamentRepository;
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
public final class CreateTournamentViewModel_Factory implements Factory<CreateTournamentViewModel> {
  private final Provider<PlayerRepository> playerRepositoryProvider;

  private final Provider<TournamentRepository> tournamentRepositoryProvider;

  public CreateTournamentViewModel_Factory(Provider<PlayerRepository> playerRepositoryProvider,
      Provider<TournamentRepository> tournamentRepositoryProvider) {
    this.playerRepositoryProvider = playerRepositoryProvider;
    this.tournamentRepositoryProvider = tournamentRepositoryProvider;
  }

  @Override
  public CreateTournamentViewModel get() {
    return newInstance(playerRepositoryProvider.get(), tournamentRepositoryProvider.get());
  }

  public static CreateTournamentViewModel_Factory create(
      Provider<PlayerRepository> playerRepositoryProvider,
      Provider<TournamentRepository> tournamentRepositoryProvider) {
    return new CreateTournamentViewModel_Factory(playerRepositoryProvider, tournamentRepositoryProvider);
  }

  public static CreateTournamentViewModel newInstance(PlayerRepository playerRepository,
      TournamentRepository tournamentRepository) {
    return new CreateTournamentViewModel(playerRepository, tournamentRepository);
  }
}
