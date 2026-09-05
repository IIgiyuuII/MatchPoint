package com.tennis.matchpoint.ui.match;

import androidx.lifecycle.SavedStateHandle;
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
public final class MatchViewModel_Factory implements Factory<MatchViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<TournamentRepository> tournamentRepositoryProvider;

  private final Provider<PlayerRepository> playerRepositoryProvider;

  public MatchViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<TournamentRepository> tournamentRepositoryProvider,
      Provider<PlayerRepository> playerRepositoryProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.tournamentRepositoryProvider = tournamentRepositoryProvider;
    this.playerRepositoryProvider = playerRepositoryProvider;
  }

  @Override
  public MatchViewModel get() {
    return newInstance(savedStateHandleProvider.get(), tournamentRepositoryProvider.get(), playerRepositoryProvider.get());
  }

  public static MatchViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<TournamentRepository> tournamentRepositoryProvider,
      Provider<PlayerRepository> playerRepositoryProvider) {
    return new MatchViewModel_Factory(savedStateHandleProvider, tournamentRepositoryProvider, playerRepositoryProvider);
  }

  public static MatchViewModel newInstance(SavedStateHandle savedStateHandle,
      TournamentRepository tournamentRepository, PlayerRepository playerRepository) {
    return new MatchViewModel(savedStateHandle, tournamentRepository, playerRepository);
  }
}
