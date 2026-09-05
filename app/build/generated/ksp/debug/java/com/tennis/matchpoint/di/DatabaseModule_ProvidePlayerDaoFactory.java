package com.tennis.matchpoint.di;

import com.tennis.matchpoint.data.local.AppDatabase;
import com.tennis.matchpoint.data.local.dao.PlayerDao;
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
public final class DatabaseModule_ProvidePlayerDaoFactory implements Factory<PlayerDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvidePlayerDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PlayerDao get() {
    return providePlayerDao(dbProvider.get());
  }

  public static DatabaseModule_ProvidePlayerDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvidePlayerDaoFactory(dbProvider);
  }

  public static PlayerDao providePlayerDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePlayerDao(db));
  }
}
