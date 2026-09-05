package com.tennis.matchpoint.di;

import com.tennis.matchpoint.data.local.AppDatabase;
import com.tennis.matchpoint.data.local.dao.TournamentPlayerDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideTournamentPlayerDaoFactory implements Factory<TournamentPlayerDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideTournamentPlayerDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TournamentPlayerDao get() {
    return provideTournamentPlayerDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideTournamentPlayerDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideTournamentPlayerDaoFactory(dbProvider);
  }

  public static TournamentPlayerDao provideTournamentPlayerDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTournamentPlayerDao(db));
  }
}
