package com.tennis.matchpoint.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.tennis.matchpoint.data.local.dao.MatchDao;
import com.tennis.matchpoint.data.local.dao.MatchDao_Impl;
import com.tennis.matchpoint.data.local.dao.PlayerDao;
import com.tennis.matchpoint.data.local.dao.PlayerDao_Impl;
import com.tennis.matchpoint.data.local.dao.TournamentDao;
import com.tennis.matchpoint.data.local.dao.TournamentDao_Impl;
import com.tennis.matchpoint.data.local.dao.TournamentPlayerDao;
import com.tennis.matchpoint.data.local.dao.TournamentPlayerDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile PlayerDao _playerDao;

  private volatile TournamentDao _tournamentDao;

  private volatile TournamentPlayerDao _tournamentPlayerDao;

  private volatile MatchDao _matchDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `players` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `colorSeed` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tournaments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `format` TEXT NOT NULL, `status` TEXT NOT NULL, `setsToWin` INTEGER NOT NULL, `gamesPerSet` INTEGER NOT NULL, `groupSize` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `winnerPlayerId` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tournament_players` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `tournamentId` INTEGER NOT NULL, `playerId` INTEGER NOT NULL, `groupIndex` INTEGER NOT NULL, `seed` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `matches` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `tournamentId` INTEGER NOT NULL, `stage` TEXT NOT NULL, `roundLabel` TEXT NOT NULL, `groupIndex` INTEGER NOT NULL, `orderIndex` INTEGER NOT NULL, `roundNumber` INTEGER NOT NULL, `player1Id` INTEGER, `player2Id` INTEGER, `pointsP1` INTEGER NOT NULL, `pointsP2` INTEGER NOT NULL, `gamesP1` INTEGER NOT NULL, `gamesP2` INTEGER NOT NULL, `setsP1` INTEGER NOT NULL, `setsP2` INTEGER NOT NULL, `setsHistory` TEXT NOT NULL, `isTiebreak` INTEGER NOT NULL, `tiebreakP1` INTEGER NOT NULL, `tiebreakP2` INTEGER NOT NULL, `pointsHistory` TEXT NOT NULL, `status` TEXT NOT NULL, `winnerId` INTEGER, `startedAt` INTEGER, `finishedAt` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fd73dcfb0ead79a7a47cdb2bf1f3c2b3')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `players`");
        db.execSQL("DROP TABLE IF EXISTS `tournaments`");
        db.execSQL("DROP TABLE IF EXISTS `tournament_players`");
        db.execSQL("DROP TABLE IF EXISTS `matches`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsPlayers = new HashMap<String, TableInfo.Column>(4);
        _columnsPlayers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("colorSeed", new TableInfo.Column("colorSeed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlayers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlayers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlayers = new TableInfo("players", _columnsPlayers, _foreignKeysPlayers, _indicesPlayers);
        final TableInfo _existingPlayers = TableInfo.read(db, "players");
        if (!_infoPlayers.equals(_existingPlayers)) {
          return new RoomOpenHelper.ValidationResult(false, "players(com.tennis.matchpoint.data.local.entity.PlayerEntity).\n"
                  + " Expected:\n" + _infoPlayers + "\n"
                  + " Found:\n" + _existingPlayers);
        }
        final HashMap<String, TableInfo.Column> _columnsTournaments = new HashMap<String, TableInfo.Column>(9);
        _columnsTournaments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournaments.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournaments.put("format", new TableInfo.Column("format", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournaments.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournaments.put("setsToWin", new TableInfo.Column("setsToWin", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournaments.put("gamesPerSet", new TableInfo.Column("gamesPerSet", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournaments.put("groupSize", new TableInfo.Column("groupSize", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournaments.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournaments.put("winnerPlayerId", new TableInfo.Column("winnerPlayerId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTournaments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTournaments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTournaments = new TableInfo("tournaments", _columnsTournaments, _foreignKeysTournaments, _indicesTournaments);
        final TableInfo _existingTournaments = TableInfo.read(db, "tournaments");
        if (!_infoTournaments.equals(_existingTournaments)) {
          return new RoomOpenHelper.ValidationResult(false, "tournaments(com.tennis.matchpoint.data.local.entity.TournamentEntity).\n"
                  + " Expected:\n" + _infoTournaments + "\n"
                  + " Found:\n" + _existingTournaments);
        }
        final HashMap<String, TableInfo.Column> _columnsTournamentPlayers = new HashMap<String, TableInfo.Column>(5);
        _columnsTournamentPlayers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournamentPlayers.put("tournamentId", new TableInfo.Column("tournamentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournamentPlayers.put("playerId", new TableInfo.Column("playerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournamentPlayers.put("groupIndex", new TableInfo.Column("groupIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTournamentPlayers.put("seed", new TableInfo.Column("seed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTournamentPlayers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTournamentPlayers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTournamentPlayers = new TableInfo("tournament_players", _columnsTournamentPlayers, _foreignKeysTournamentPlayers, _indicesTournamentPlayers);
        final TableInfo _existingTournamentPlayers = TableInfo.read(db, "tournament_players");
        if (!_infoTournamentPlayers.equals(_existingTournamentPlayers)) {
          return new RoomOpenHelper.ValidationResult(false, "tournament_players(com.tennis.matchpoint.data.local.entity.TournamentPlayerEntity).\n"
                  + " Expected:\n" + _infoTournamentPlayers + "\n"
                  + " Found:\n" + _existingTournamentPlayers);
        }
        final HashMap<String, TableInfo.Column> _columnsMatches = new HashMap<String, TableInfo.Column>(24);
        _columnsMatches.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("tournamentId", new TableInfo.Column("tournamentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("stage", new TableInfo.Column("stage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("roundLabel", new TableInfo.Column("roundLabel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("groupIndex", new TableInfo.Column("groupIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("roundNumber", new TableInfo.Column("roundNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("player1Id", new TableInfo.Column("player1Id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("player2Id", new TableInfo.Column("player2Id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("pointsP1", new TableInfo.Column("pointsP1", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("pointsP2", new TableInfo.Column("pointsP2", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("gamesP1", new TableInfo.Column("gamesP1", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("gamesP2", new TableInfo.Column("gamesP2", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("setsP1", new TableInfo.Column("setsP1", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("setsP2", new TableInfo.Column("setsP2", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("setsHistory", new TableInfo.Column("setsHistory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("isTiebreak", new TableInfo.Column("isTiebreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("tiebreakP1", new TableInfo.Column("tiebreakP1", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("tiebreakP2", new TableInfo.Column("tiebreakP2", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("pointsHistory", new TableInfo.Column("pointsHistory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("winnerId", new TableInfo.Column("winnerId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("startedAt", new TableInfo.Column("startedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatches.put("finishedAt", new TableInfo.Column("finishedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMatches = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMatches = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMatches = new TableInfo("matches", _columnsMatches, _foreignKeysMatches, _indicesMatches);
        final TableInfo _existingMatches = TableInfo.read(db, "matches");
        if (!_infoMatches.equals(_existingMatches)) {
          return new RoomOpenHelper.ValidationResult(false, "matches(com.tennis.matchpoint.data.local.entity.MatchEntity).\n"
                  + " Expected:\n" + _infoMatches + "\n"
                  + " Found:\n" + _existingMatches);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fd73dcfb0ead79a7a47cdb2bf1f3c2b3", "f4dd37a62d09e83b016073ee6f7c5073");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "players","tournaments","tournament_players","matches");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `players`");
      _db.execSQL("DELETE FROM `tournaments`");
      _db.execSQL("DELETE FROM `tournament_players`");
      _db.execSQL("DELETE FROM `matches`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PlayerDao.class, PlayerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TournamentDao.class, TournamentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TournamentPlayerDao.class, TournamentPlayerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MatchDao.class, MatchDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PlayerDao playerDao() {
    if (_playerDao != null) {
      return _playerDao;
    } else {
      synchronized(this) {
        if(_playerDao == null) {
          _playerDao = new PlayerDao_Impl(this);
        }
        return _playerDao;
      }
    }
  }

  @Override
  public TournamentDao tournamentDao() {
    if (_tournamentDao != null) {
      return _tournamentDao;
    } else {
      synchronized(this) {
        if(_tournamentDao == null) {
          _tournamentDao = new TournamentDao_Impl(this);
        }
        return _tournamentDao;
      }
    }
  }

  @Override
  public TournamentPlayerDao tournamentPlayerDao() {
    if (_tournamentPlayerDao != null) {
      return _tournamentPlayerDao;
    } else {
      synchronized(this) {
        if(_tournamentPlayerDao == null) {
          _tournamentPlayerDao = new TournamentPlayerDao_Impl(this);
        }
        return _tournamentPlayerDao;
      }
    }
  }

  @Override
  public MatchDao matchDao() {
    if (_matchDao != null) {
      return _matchDao;
    } else {
      synchronized(this) {
        if(_matchDao == null) {
          _matchDao = new MatchDao_Impl(this);
        }
        return _matchDao;
      }
    }
  }
}
