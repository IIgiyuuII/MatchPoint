package com.tennis.matchpoint.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tennis.matchpoint.data.local.entity.TournamentEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TournamentDao_Impl implements TournamentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TournamentEntity> __insertionAdapterOfTournamentEntity;

  private final EntityDeletionOrUpdateAdapter<TournamentEntity> __updateAdapterOfTournamentEntity;

  public TournamentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTournamentEntity = new EntityInsertionAdapter<TournamentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `tournaments` (`id`,`name`,`format`,`status`,`setsToWin`,`gamesPerSet`,`groupSize`,`createdAt`,`winnerPlayerId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TournamentEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getFormat());
        statement.bindString(4, entity.getStatus());
        statement.bindLong(5, entity.getSetsToWin());
        statement.bindLong(6, entity.getGamesPerSet());
        statement.bindLong(7, entity.getGroupSize());
        statement.bindLong(8, entity.getCreatedAt());
        if (entity.getWinnerPlayerId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getWinnerPlayerId());
        }
      }
    };
    this.__updateAdapterOfTournamentEntity = new EntityDeletionOrUpdateAdapter<TournamentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tournaments` SET `id` = ?,`name` = ?,`format` = ?,`status` = ?,`setsToWin` = ?,`gamesPerSet` = ?,`groupSize` = ?,`createdAt` = ?,`winnerPlayerId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TournamentEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getFormat());
        statement.bindString(4, entity.getStatus());
        statement.bindLong(5, entity.getSetsToWin());
        statement.bindLong(6, entity.getGamesPerSet());
        statement.bindLong(7, entity.getGroupSize());
        statement.bindLong(8, entity.getCreatedAt());
        if (entity.getWinnerPlayerId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getWinnerPlayerId());
        }
        statement.bindLong(10, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final TournamentEntity tournament,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTournamentEntity.insertAndReturnId(tournament);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final TournamentEntity tournament,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTournamentEntity.handle(tournament);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TournamentEntity>> observeAll() {
    final String _sql = "SELECT * FROM tournaments ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tournaments"}, new Callable<List<TournamentEntity>>() {
      @Override
      @NonNull
      public List<TournamentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSetsToWin = CursorUtil.getColumnIndexOrThrow(_cursor, "setsToWin");
          final int _cursorIndexOfGamesPerSet = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesPerSet");
          final int _cursorIndexOfGroupSize = CursorUtil.getColumnIndexOrThrow(_cursor, "groupSize");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfWinnerPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerPlayerId");
          final List<TournamentEntity> _result = new ArrayList<TournamentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TournamentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpFormat;
            _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final int _tmpSetsToWin;
            _tmpSetsToWin = _cursor.getInt(_cursorIndexOfSetsToWin);
            final int _tmpGamesPerSet;
            _tmpGamesPerSet = _cursor.getInt(_cursorIndexOfGamesPerSet);
            final int _tmpGroupSize;
            _tmpGroupSize = _cursor.getInt(_cursorIndexOfGroupSize);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpWinnerPlayerId;
            if (_cursor.isNull(_cursorIndexOfWinnerPlayerId)) {
              _tmpWinnerPlayerId = null;
            } else {
              _tmpWinnerPlayerId = _cursor.getLong(_cursorIndexOfWinnerPlayerId);
            }
            _item = new TournamentEntity(_tmpId,_tmpName,_tmpFormat,_tmpStatus,_tmpSetsToWin,_tmpGamesPerSet,_tmpGroupSize,_tmpCreatedAt,_tmpWinnerPlayerId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TournamentEntity>> observeOngoingAndUpcoming() {
    final String _sql = "SELECT * FROM tournaments WHERE status != 'FINISHED' ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tournaments"}, new Callable<List<TournamentEntity>>() {
      @Override
      @NonNull
      public List<TournamentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSetsToWin = CursorUtil.getColumnIndexOrThrow(_cursor, "setsToWin");
          final int _cursorIndexOfGamesPerSet = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesPerSet");
          final int _cursorIndexOfGroupSize = CursorUtil.getColumnIndexOrThrow(_cursor, "groupSize");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfWinnerPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerPlayerId");
          final List<TournamentEntity> _result = new ArrayList<TournamentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TournamentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpFormat;
            _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final int _tmpSetsToWin;
            _tmpSetsToWin = _cursor.getInt(_cursorIndexOfSetsToWin);
            final int _tmpGamesPerSet;
            _tmpGamesPerSet = _cursor.getInt(_cursorIndexOfGamesPerSet);
            final int _tmpGroupSize;
            _tmpGroupSize = _cursor.getInt(_cursorIndexOfGroupSize);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpWinnerPlayerId;
            if (_cursor.isNull(_cursorIndexOfWinnerPlayerId)) {
              _tmpWinnerPlayerId = null;
            } else {
              _tmpWinnerPlayerId = _cursor.getLong(_cursorIndexOfWinnerPlayerId);
            }
            _item = new TournamentEntity(_tmpId,_tmpName,_tmpFormat,_tmpStatus,_tmpSetsToWin,_tmpGamesPerSet,_tmpGroupSize,_tmpCreatedAt,_tmpWinnerPlayerId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TournamentEntity>> observeFinished() {
    final String _sql = "SELECT * FROM tournaments WHERE status = 'FINISHED' ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tournaments"}, new Callable<List<TournamentEntity>>() {
      @Override
      @NonNull
      public List<TournamentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSetsToWin = CursorUtil.getColumnIndexOrThrow(_cursor, "setsToWin");
          final int _cursorIndexOfGamesPerSet = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesPerSet");
          final int _cursorIndexOfGroupSize = CursorUtil.getColumnIndexOrThrow(_cursor, "groupSize");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfWinnerPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerPlayerId");
          final List<TournamentEntity> _result = new ArrayList<TournamentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TournamentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpFormat;
            _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final int _tmpSetsToWin;
            _tmpSetsToWin = _cursor.getInt(_cursorIndexOfSetsToWin);
            final int _tmpGamesPerSet;
            _tmpGamesPerSet = _cursor.getInt(_cursorIndexOfGamesPerSet);
            final int _tmpGroupSize;
            _tmpGroupSize = _cursor.getInt(_cursorIndexOfGroupSize);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpWinnerPlayerId;
            if (_cursor.isNull(_cursorIndexOfWinnerPlayerId)) {
              _tmpWinnerPlayerId = null;
            } else {
              _tmpWinnerPlayerId = _cursor.getLong(_cursorIndexOfWinnerPlayerId);
            }
            _item = new TournamentEntity(_tmpId,_tmpName,_tmpFormat,_tmpStatus,_tmpSetsToWin,_tmpGamesPerSet,_tmpGroupSize,_tmpCreatedAt,_tmpWinnerPlayerId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<TournamentEntity> observeById(final long id) {
    final String _sql = "SELECT * FROM tournaments WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tournaments"}, new Callable<TournamentEntity>() {
      @Override
      @Nullable
      public TournamentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSetsToWin = CursorUtil.getColumnIndexOrThrow(_cursor, "setsToWin");
          final int _cursorIndexOfGamesPerSet = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesPerSet");
          final int _cursorIndexOfGroupSize = CursorUtil.getColumnIndexOrThrow(_cursor, "groupSize");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfWinnerPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerPlayerId");
          final TournamentEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpFormat;
            _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final int _tmpSetsToWin;
            _tmpSetsToWin = _cursor.getInt(_cursorIndexOfSetsToWin);
            final int _tmpGamesPerSet;
            _tmpGamesPerSet = _cursor.getInt(_cursorIndexOfGamesPerSet);
            final int _tmpGroupSize;
            _tmpGroupSize = _cursor.getInt(_cursorIndexOfGroupSize);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpWinnerPlayerId;
            if (_cursor.isNull(_cursorIndexOfWinnerPlayerId)) {
              _tmpWinnerPlayerId = null;
            } else {
              _tmpWinnerPlayerId = _cursor.getLong(_cursorIndexOfWinnerPlayerId);
            }
            _result = new TournamentEntity(_tmpId,_tmpName,_tmpFormat,_tmpStatus,_tmpSetsToWin,_tmpGamesPerSet,_tmpGroupSize,_tmpCreatedAt,_tmpWinnerPlayerId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final long id, final Continuation<? super TournamentEntity> $completion) {
    final String _sql = "SELECT * FROM tournaments WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TournamentEntity>() {
      @Override
      @Nullable
      public TournamentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSetsToWin = CursorUtil.getColumnIndexOrThrow(_cursor, "setsToWin");
          final int _cursorIndexOfGamesPerSet = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesPerSet");
          final int _cursorIndexOfGroupSize = CursorUtil.getColumnIndexOrThrow(_cursor, "groupSize");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfWinnerPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerPlayerId");
          final TournamentEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpFormat;
            _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final int _tmpSetsToWin;
            _tmpSetsToWin = _cursor.getInt(_cursorIndexOfSetsToWin);
            final int _tmpGamesPerSet;
            _tmpGamesPerSet = _cursor.getInt(_cursorIndexOfGamesPerSet);
            final int _tmpGroupSize;
            _tmpGroupSize = _cursor.getInt(_cursorIndexOfGroupSize);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpWinnerPlayerId;
            if (_cursor.isNull(_cursorIndexOfWinnerPlayerId)) {
              _tmpWinnerPlayerId = null;
            } else {
              _tmpWinnerPlayerId = _cursor.getLong(_cursorIndexOfWinnerPlayerId);
            }
            _result = new TournamentEntity(_tmpId,_tmpName,_tmpFormat,_tmpStatus,_tmpSetsToWin,_tmpGamesPerSet,_tmpGroupSize,_tmpCreatedAt,_tmpWinnerPlayerId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TournamentEntity>> observeTournamentsForPlayer(final long playerId) {
    final String _sql = "\n"
            + "        SELECT t.* FROM tournaments t\n"
            + "        INNER JOIN tournament_players tp ON tp.tournamentId = t.id\n"
            + "        WHERE tp.playerId = ?\n"
            + "        ORDER BY t.createdAt DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playerId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tournaments",
        "tournament_players"}, new Callable<List<TournamentEntity>>() {
      @Override
      @NonNull
      public List<TournamentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSetsToWin = CursorUtil.getColumnIndexOrThrow(_cursor, "setsToWin");
          final int _cursorIndexOfGamesPerSet = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesPerSet");
          final int _cursorIndexOfGroupSize = CursorUtil.getColumnIndexOrThrow(_cursor, "groupSize");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfWinnerPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerPlayerId");
          final List<TournamentEntity> _result = new ArrayList<TournamentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TournamentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpFormat;
            _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final int _tmpSetsToWin;
            _tmpSetsToWin = _cursor.getInt(_cursorIndexOfSetsToWin);
            final int _tmpGamesPerSet;
            _tmpGamesPerSet = _cursor.getInt(_cursorIndexOfGamesPerSet);
            final int _tmpGroupSize;
            _tmpGroupSize = _cursor.getInt(_cursorIndexOfGroupSize);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpWinnerPlayerId;
            if (_cursor.isNull(_cursorIndexOfWinnerPlayerId)) {
              _tmpWinnerPlayerId = null;
            } else {
              _tmpWinnerPlayerId = _cursor.getLong(_cursorIndexOfWinnerPlayerId);
            }
            _item = new TournamentEntity(_tmpId,_tmpName,_tmpFormat,_tmpStatus,_tmpSetsToWin,_tmpGamesPerSet,_tmpGroupSize,_tmpCreatedAt,_tmpWinnerPlayerId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
