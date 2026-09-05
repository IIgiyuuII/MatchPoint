package com.tennis.matchpoint.ui.home;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<TournamentRepository> tournamentRepositoryProvider;

  public HomeViewModel_Factory(Provider<TournamentRepository> tournamentRepositoryProvider) {
    this.tournamentRepositoryProvider = tournamentRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(tournamentRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<TournamentRepository> tournamentRepositoryProvider) {
    return new HomeViewModel_Factory(tournamentRepositoryProvider);
  }

  public static HomeViewModel newInstance(TournamentRepository tournamentRepository) {
    return new HomeViewModel(tournamentRepository);
  }
}
