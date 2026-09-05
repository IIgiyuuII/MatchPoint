package com.tennis.matchpoint.ui.playerprofile;

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
public final class PlayerProfileViewModel_Factory implements Factory<PlayerProfileViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<PlayerRepository> playerRepositoryProvider;

  private final Provider<TournamentRepository> tournamentRepositoryProvider;

  public PlayerProfileViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<PlayerRepository> playerRepositoryProvider,
      Provider<TournamentRepository> tournamentRepositoryProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.playerRepositoryProvider = playerRepositoryProvider;
    this.tournamentRepositoryProvider = tournamentRepositoryProvider;
  }

  @Override
  public PlayerProfileViewModel get() {
    return newInstance(savedStateHandleProvider.get(), playerRepositoryProvider.get(), tournamentRepositoryProvider.get());
  }

  public static PlayerProfileViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<PlayerRepository> playerRepositoryProvider,
      Provider<TournamentRepository> tournamentRepositoryProvider) {
    return new PlayerProfileViewModel_Factory(savedStateHandleProvider, playerRepositoryProvider, tournamentRepositoryProvider);
  }

  public static PlayerProfileViewModel newInstance(SavedStateHandle savedStateHandle,
      PlayerRepository playerRepository, TournamentRepository tournamentRepository) {
    return new PlayerProfileViewModel(savedStateHandle, playerRepository, tournamentRepository);
  }
}
