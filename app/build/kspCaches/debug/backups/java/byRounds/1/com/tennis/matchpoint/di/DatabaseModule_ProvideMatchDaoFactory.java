package com.tennis.matchpoint.di;

import com.tennis.matchpoint.data.local.AppDatabase;
import com.tennis.matchpoint.data.local.dao.MatchDao;
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
public final class DatabaseModule_ProvideMatchDaoFactory implements Factory<MatchDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideMatchDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public MatchDao get() {
    return provideMatchDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideMatchDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideMatchDaoFactory(dbProvider);
  }

  public static MatchDao provideMatchDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMatchDao(db));
  }
}
