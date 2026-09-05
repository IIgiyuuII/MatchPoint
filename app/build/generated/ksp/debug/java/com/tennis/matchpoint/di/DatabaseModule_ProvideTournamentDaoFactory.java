package com.tennis.matchpoint.di;

import com.tennis.matchpoint.data.local.AppDatabase;
import com.tennis.matchpoint.data.local.dao.TournamentDao;
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
public final class DatabaseModule_ProvideTournamentDaoFactory implements Factory<TournamentDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideTournamentDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TournamentDao get() {
    return provideTournamentDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideTournamentDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideTournamentDaoFactory(dbProvider);
  }

  public static TournamentDao provideTournamentDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTournamentDao(db));
  }
}
