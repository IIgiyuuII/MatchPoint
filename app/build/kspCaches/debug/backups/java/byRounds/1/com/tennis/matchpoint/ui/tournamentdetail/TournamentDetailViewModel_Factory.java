package com.tennis.matchpoint.ui.tournamentdetail;

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
public final class TournamentDetailViewModel_Factory implements Factory<TournamentDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<TournamentRepository> tournamentRepositoryProvider;

  private final Provider<PlayerRepository> playerRepositoryProvider;

  public TournamentDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<TournamentRepository> tournamentRepositoryProvider,
      Provider<PlayerRepository> playerRepositoryProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.tournamentRepositoryProvider = tournamentRepositoryProvider;
    this.playerRepositoryProvider = playerRepositoryProvider;
  }

  @Override
  public TournamentDetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), tournamentRepositoryProvider.get(), playerRepositoryProvider.get());
  }

  public static TournamentDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<TournamentRepository> tournamentRepositoryProvider,
      Provider<PlayerRepository> playerRepositoryProvider) {
    return new TournamentDetailViewModel_Factory(savedStateHandleProvider, tournamentRepositoryProvider, playerRepositoryProvider);
  }

  public static TournamentDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      TournamentRepository tournamentRepository, PlayerRepository playerRepository) {
    return new TournamentDetailViewModel(savedStateHandle, tournamentRepository, playerRepository);
  }
}
