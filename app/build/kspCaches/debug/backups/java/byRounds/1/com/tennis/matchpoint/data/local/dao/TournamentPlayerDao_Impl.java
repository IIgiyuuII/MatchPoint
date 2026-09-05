package com.tennis.matchpoint.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tennis.matchpoint.data.local.entity.TournamentPlayerEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class TournamentPlayerDao_Impl implements TournamentPlayerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TournamentPlayerEntity> __insertionAdapterOfTournamentPlayerEntity;

  public TournamentPlayerDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTournamentPlayerEntity = new EntityInsertionAdapter<TournamentPlayerEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `tournament_players` (`id`,`tournamentId`,`playerId`,`groupIndex`,`seed`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TournamentPlayerEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTournamentId());
        statement.bindLong(3, entity.getPlayerId());
        statement.bindLong(4, entity.getGroupIndex());
        statement.bindLong(5, entity.getSeed());
      }
    };
  }

  @Override
  public Object insertAll(final List<TournamentPlayerEntity> entries,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTournamentPlayerEntity.insert(entries);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TournamentPlayerEntity>> observeForTournament(final long tournamentId) {
    final String _sql = "SELECT * FROM tournament_players WHERE tournamentId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tournamentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tournament_players"}, new Callable<List<TournamentPlayerEntity>>() {
      @Override
      @NonNull
      public List<TournamentPlayerEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "playerId");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfSeed = CursorUtil.getColumnIndexOrThrow(_cursor, "seed");
          final List<TournamentPlayerEntity> _result = new ArrayList<TournamentPlayerEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TournamentPlayerEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final long _tmpPlayerId;
            _tmpPlayerId = _cursor.getLong(_cursorIndexOfPlayerId);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpSeed;
            _tmpSeed = _cursor.getInt(_cursorIndexOfSeed);
            _item = new TournamentPlayerEntity(_tmpId,_tmpTournamentId,_tmpPlayerId,_tmpGroupIndex,_tmpSeed);
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
  public Object getForTournament(final long tournamentId,
      final Continuation<? super List<TournamentPlayerEntity>> $completion) {
    final String _sql = "SELECT * FROM tournament_players WHERE tournamentId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tournamentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TournamentPlayerEntity>>() {
      @Override
      @NonNull
      public List<TournamentPlayerEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "playerId");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfSeed = CursorUtil.getColumnIndexOrThrow(_cursor, "seed");
          final List<TournamentPlayerEntity> _result = new ArrayList<TournamentPlayerEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TournamentPlayerEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final long _tmpPlayerId;
            _tmpPlayerId = _cursor.getLong(_cursorIndexOfPlayerId);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpSeed;
            _tmpSeed = _cursor.getInt(_cursorIndexOfSeed);
            _item = new TournamentPlayerEntity(_tmpId,_tmpTournamentId,_tmpPlayerId,_tmpGroupIndex,_tmpSeed);
            _result.add(_item);
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
  public Flow<Integer> observeTournamentCountForPlayer(final long playerId) {
    final String _sql = "SELECT COUNT(*) FROM tournament_players WHERE playerId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playerId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tournament_players"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
