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
import com.tennis.matchpoint.data.local.entity.MatchEntity;
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
public final class MatchDao_Impl implements MatchDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MatchEntity> __insertionAdapterOfMatchEntity;

  private final EntityDeletionOrUpdateAdapter<MatchEntity> __updateAdapterOfMatchEntity;

  public MatchDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMatchEntity = new EntityInsertionAdapter<MatchEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `matches` (`id`,`tournamentId`,`stage`,`roundLabel`,`groupIndex`,`orderIndex`,`roundNumber`,`player1Id`,`player2Id`,`pointsP1`,`pointsP2`,`gamesP1`,`gamesP2`,`setsP1`,`setsP2`,`setsHistory`,`isTiebreak`,`tiebreakP1`,`tiebreakP2`,`pointsHistory`,`status`,`winnerId`,`startedAt`,`finishedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MatchEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTournamentId());
        statement.bindString(3, entity.getStage());
        statement.bindString(4, entity.getRoundLabel());
        statement.bindLong(5, entity.getGroupIndex());
        statement.bindLong(6, entity.getOrderIndex());
        statement.bindLong(7, entity.getRoundNumber());
        if (entity.getPlayer1Id() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getPlayer1Id());
        }
        if (entity.getPlayer2Id() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getPlayer2Id());
        }
        statement.bindLong(10, entity.getPointsP1());
        statement.bindLong(11, entity.getPointsP2());
        statement.bindLong(12, entity.getGamesP1());
        statement.bindLong(13, entity.getGamesP2());
        statement.bindLong(14, entity.getSetsP1());
        statement.bindLong(15, entity.getSetsP2());
        statement.bindString(16, entity.getSetsHistory());
        final int _tmp = entity.isTiebreak() ? 1 : 0;
        statement.bindLong(17, _tmp);
        statement.bindLong(18, entity.getTiebreakP1());
        statement.bindLong(19, entity.getTiebreakP2());
        statement.bindString(20, entity.getPointsHistory());
        statement.bindString(21, entity.getStatus());
        if (entity.getWinnerId() == null) {
          statement.bindNull(22);
        } else {
          statement.bindLong(22, entity.getWinnerId());
        }
        if (entity.getStartedAt() == null) {
          statement.bindNull(23);
        } else {
          statement.bindLong(23, entity.getStartedAt());
        }
        if (entity.getFinishedAt() == null) {
          statement.bindNull(24);
        } else {
          statement.bindLong(24, entity.getFinishedAt());
        }
      }
    };
    this.__updateAdapterOfMatchEntity = new EntityDeletionOrUpdateAdapter<MatchEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `matches` SET `id` = ?,`tournamentId` = ?,`stage` = ?,`roundLabel` = ?,`groupIndex` = ?,`orderIndex` = ?,`roundNumber` = ?,`player1Id` = ?,`player2Id` = ?,`pointsP1` = ?,`pointsP2` = ?,`gamesP1` = ?,`gamesP2` = ?,`setsP1` = ?,`setsP2` = ?,`setsHistory` = ?,`isTiebreak` = ?,`tiebreakP1` = ?,`tiebreakP2` = ?,`pointsHistory` = ?,`status` = ?,`winnerId` = ?,`startedAt` = ?,`finishedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MatchEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTournamentId());
        statement.bindString(3, entity.getStage());
        statement.bindString(4, entity.getRoundLabel());
        statement.bindLong(5, entity.getGroupIndex());
        statement.bindLong(6, entity.getOrderIndex());
        statement.bindLong(7, entity.getRoundNumber());
        if (entity.getPlayer1Id() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getPlayer1Id());
        }
        if (entity.getPlayer2Id() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getPlayer2Id());
        }
        statement.bindLong(10, entity.getPointsP1());
        statement.bindLong(11, entity.getPointsP2());
        statement.bindLong(12, entity.getGamesP1());
        statement.bindLong(13, entity.getGamesP2());
        statement.bindLong(14, entity.getSetsP1());
        statement.bindLong(15, entity.getSetsP2());
        statement.bindString(16, entity.getSetsHistory());
        final int _tmp = entity.isTiebreak() ? 1 : 0;
        statement.bindLong(17, _tmp);
        statement.bindLong(18, entity.getTiebreakP1());
        statement.bindLong(19, entity.getTiebreakP2());
        statement.bindString(20, entity.getPointsHistory());
        statement.bindString(21, entity.getStatus());
        if (entity.getWinnerId() == null) {
          statement.bindNull(22);
        } else {
          statement.bindLong(22, entity.getWinnerId());
        }
        if (entity.getStartedAt() == null) {
          statement.bindNull(23);
        } else {
          statement.bindLong(23, entity.getStartedAt());
        }
        if (entity.getFinishedAt() == null) {
          statement.bindNull(24);
        } else {
          statement.bindLong(24, entity.getFinishedAt());
        }
        statement.bindLong(25, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final MatchEntity match, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMatchEntity.insertAndReturnId(match);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<MatchEntity> matches,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfMatchEntity.insertAndReturnIdsList(matches);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final MatchEntity match, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMatchEntity.handle(match);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MatchEntity>> observeForTournament(final long tournamentId) {
    final String _sql = "SELECT * FROM matches WHERE tournamentId = ? ORDER BY orderIndex ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tournamentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"matches"}, new Callable<List<MatchEntity>>() {
      @Override
      @NonNull
      public List<MatchEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfRoundLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "roundLabel");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfRoundNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roundNumber");
          final int _cursorIndexOfPlayer1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player1Id");
          final int _cursorIndexOfPlayer2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player2Id");
          final int _cursorIndexOfPointsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP1");
          final int _cursorIndexOfPointsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP2");
          final int _cursorIndexOfGamesP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP1");
          final int _cursorIndexOfGamesP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP2");
          final int _cursorIndexOfSetsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP1");
          final int _cursorIndexOfSetsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP2");
          final int _cursorIndexOfSetsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "setsHistory");
          final int _cursorIndexOfIsTiebreak = CursorUtil.getColumnIndexOrThrow(_cursor, "isTiebreak");
          final int _cursorIndexOfTiebreakP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP1");
          final int _cursorIndexOfTiebreakP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP2");
          final int _cursorIndexOfPointsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsHistory");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finishedAt");
          final List<MatchEntity> _result = new ArrayList<MatchEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MatchEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final String _tmpStage;
            _tmpStage = _cursor.getString(_cursorIndexOfStage);
            final String _tmpRoundLabel;
            _tmpRoundLabel = _cursor.getString(_cursorIndexOfRoundLabel);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final int _tmpRoundNumber;
            _tmpRoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
            final Long _tmpPlayer1Id;
            if (_cursor.isNull(_cursorIndexOfPlayer1Id)) {
              _tmpPlayer1Id = null;
            } else {
              _tmpPlayer1Id = _cursor.getLong(_cursorIndexOfPlayer1Id);
            }
            final Long _tmpPlayer2Id;
            if (_cursor.isNull(_cursorIndexOfPlayer2Id)) {
              _tmpPlayer2Id = null;
            } else {
              _tmpPlayer2Id = _cursor.getLong(_cursorIndexOfPlayer2Id);
            }
            final int _tmpPointsP1;
            _tmpPointsP1 = _cursor.getInt(_cursorIndexOfPointsP1);
            final int _tmpPointsP2;
            _tmpPointsP2 = _cursor.getInt(_cursorIndexOfPointsP2);
            final int _tmpGamesP1;
            _tmpGamesP1 = _cursor.getInt(_cursorIndexOfGamesP1);
            final int _tmpGamesP2;
            _tmpGamesP2 = _cursor.getInt(_cursorIndexOfGamesP2);
            final int _tmpSetsP1;
            _tmpSetsP1 = _cursor.getInt(_cursorIndexOfSetsP1);
            final int _tmpSetsP2;
            _tmpSetsP2 = _cursor.getInt(_cursorIndexOfSetsP2);
            final String _tmpSetsHistory;
            _tmpSetsHistory = _cursor.getString(_cursorIndexOfSetsHistory);
            final boolean _tmpIsTiebreak;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsTiebreak);
            _tmpIsTiebreak = _tmp != 0;
            final int _tmpTiebreakP1;
            _tmpTiebreakP1 = _cursor.getInt(_cursorIndexOfTiebreakP1);
            final int _tmpTiebreakP2;
            _tmpTiebreakP2 = _cursor.getInt(_cursorIndexOfTiebreakP2);
            final String _tmpPointsHistory;
            _tmpPointsHistory = _cursor.getString(_cursorIndexOfPointsHistory);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Long _tmpWinnerId;
            if (_cursor.isNull(_cursorIndexOfWinnerId)) {
              _tmpWinnerId = null;
            } else {
              _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            }
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpFinishedAt;
            if (_cursor.isNull(_cursorIndexOfFinishedAt)) {
              _tmpFinishedAt = null;
            } else {
              _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            }
            _item = new MatchEntity(_tmpId,_tmpTournamentId,_tmpStage,_tmpRoundLabel,_tmpGroupIndex,_tmpOrderIndex,_tmpRoundNumber,_tmpPlayer1Id,_tmpPlayer2Id,_tmpPointsP1,_tmpPointsP2,_tmpGamesP1,_tmpGamesP2,_tmpSetsP1,_tmpSetsP2,_tmpSetsHistory,_tmpIsTiebreak,_tmpTiebreakP1,_tmpTiebreakP2,_tmpPointsHistory,_tmpStatus,_tmpWinnerId,_tmpStartedAt,_tmpFinishedAt);
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
      final Continuation<? super List<MatchEntity>> $completion) {
    final String _sql = "SELECT * FROM matches WHERE tournamentId = ? ORDER BY orderIndex ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tournamentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MatchEntity>>() {
      @Override
      @NonNull
      public List<MatchEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfRoundLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "roundLabel");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfRoundNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roundNumber");
          final int _cursorIndexOfPlayer1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player1Id");
          final int _cursorIndexOfPlayer2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player2Id");
          final int _cursorIndexOfPointsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP1");
          final int _cursorIndexOfPointsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP2");
          final int _cursorIndexOfGamesP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP1");
          final int _cursorIndexOfGamesP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP2");
          final int _cursorIndexOfSetsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP1");
          final int _cursorIndexOfSetsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP2");
          final int _cursorIndexOfSetsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "setsHistory");
          final int _cursorIndexOfIsTiebreak = CursorUtil.getColumnIndexOrThrow(_cursor, "isTiebreak");
          final int _cursorIndexOfTiebreakP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP1");
          final int _cursorIndexOfTiebreakP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP2");
          final int _cursorIndexOfPointsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsHistory");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finishedAt");
          final List<MatchEntity> _result = new ArrayList<MatchEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MatchEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final String _tmpStage;
            _tmpStage = _cursor.getString(_cursorIndexOfStage);
            final String _tmpRoundLabel;
            _tmpRoundLabel = _cursor.getString(_cursorIndexOfRoundLabel);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final int _tmpRoundNumber;
            _tmpRoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
            final Long _tmpPlayer1Id;
            if (_cursor.isNull(_cursorIndexOfPlayer1Id)) {
              _tmpPlayer1Id = null;
            } else {
              _tmpPlayer1Id = _cursor.getLong(_cursorIndexOfPlayer1Id);
            }
            final Long _tmpPlayer2Id;
            if (_cursor.isNull(_cursorIndexOfPlayer2Id)) {
              _tmpPlayer2Id = null;
            } else {
              _tmpPlayer2Id = _cursor.getLong(_cursorIndexOfPlayer2Id);
            }
            final int _tmpPointsP1;
            _tmpPointsP1 = _cursor.getInt(_cursorIndexOfPointsP1);
            final int _tmpPointsP2;
            _tmpPointsP2 = _cursor.getInt(_cursorIndexOfPointsP2);
            final int _tmpGamesP1;
            _tmpGamesP1 = _cursor.getInt(_cursorIndexOfGamesP1);
            final int _tmpGamesP2;
            _tmpGamesP2 = _cursor.getInt(_cursorIndexOfGamesP2);
            final int _tmpSetsP1;
            _tmpSetsP1 = _cursor.getInt(_cursorIndexOfSetsP1);
            final int _tmpSetsP2;
            _tmpSetsP2 = _cursor.getInt(_cursorIndexOfSetsP2);
            final String _tmpSetsHistory;
            _tmpSetsHistory = _cursor.getString(_cursorIndexOfSetsHistory);
            final boolean _tmpIsTiebreak;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsTiebreak);
            _tmpIsTiebreak = _tmp != 0;
            final int _tmpTiebreakP1;
            _tmpTiebreakP1 = _cursor.getInt(_cursorIndexOfTiebreakP1);
            final int _tmpTiebreakP2;
            _tmpTiebreakP2 = _cursor.getInt(_cursorIndexOfTiebreakP2);
            final String _tmpPointsHistory;
            _tmpPointsHistory = _cursor.getString(_cursorIndexOfPointsHistory);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Long _tmpWinnerId;
            if (_cursor.isNull(_cursorIndexOfWinnerId)) {
              _tmpWinnerId = null;
            } else {
              _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            }
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpFinishedAt;
            if (_cursor.isNull(_cursorIndexOfFinishedAt)) {
              _tmpFinishedAt = null;
            } else {
              _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            }
            _item = new MatchEntity(_tmpId,_tmpTournamentId,_tmpStage,_tmpRoundLabel,_tmpGroupIndex,_tmpOrderIndex,_tmpRoundNumber,_tmpPlayer1Id,_tmpPlayer2Id,_tmpPointsP1,_tmpPointsP2,_tmpGamesP1,_tmpGamesP2,_tmpSetsP1,_tmpSetsP2,_tmpSetsHistory,_tmpIsTiebreak,_tmpTiebreakP1,_tmpTiebreakP2,_tmpPointsHistory,_tmpStatus,_tmpWinnerId,_tmpStartedAt,_tmpFinishedAt);
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
  public Flow<MatchEntity> observeById(final long id) {
    final String _sql = "SELECT * FROM matches WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"matches"}, new Callable<MatchEntity>() {
      @Override
      @Nullable
      public MatchEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfRoundLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "roundLabel");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfRoundNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roundNumber");
          final int _cursorIndexOfPlayer1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player1Id");
          final int _cursorIndexOfPlayer2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player2Id");
          final int _cursorIndexOfPointsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP1");
          final int _cursorIndexOfPointsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP2");
          final int _cursorIndexOfGamesP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP1");
          final int _cursorIndexOfGamesP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP2");
          final int _cursorIndexOfSetsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP1");
          final int _cursorIndexOfSetsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP2");
          final int _cursorIndexOfSetsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "setsHistory");
          final int _cursorIndexOfIsTiebreak = CursorUtil.getColumnIndexOrThrow(_cursor, "isTiebreak");
          final int _cursorIndexOfTiebreakP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP1");
          final int _cursorIndexOfTiebreakP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP2");
          final int _cursorIndexOfPointsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsHistory");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finishedAt");
          final MatchEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final String _tmpStage;
            _tmpStage = _cursor.getString(_cursorIndexOfStage);
            final String _tmpRoundLabel;
            _tmpRoundLabel = _cursor.getString(_cursorIndexOfRoundLabel);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final int _tmpRoundNumber;
            _tmpRoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
            final Long _tmpPlayer1Id;
            if (_cursor.isNull(_cursorIndexOfPlayer1Id)) {
              _tmpPlayer1Id = null;
            } else {
              _tmpPlayer1Id = _cursor.getLong(_cursorIndexOfPlayer1Id);
            }
            final Long _tmpPlayer2Id;
            if (_cursor.isNull(_cursorIndexOfPlayer2Id)) {
              _tmpPlayer2Id = null;
            } else {
              _tmpPlayer2Id = _cursor.getLong(_cursorIndexOfPlayer2Id);
            }
            final int _tmpPointsP1;
            _tmpPointsP1 = _cursor.getInt(_cursorIndexOfPointsP1);
            final int _tmpPointsP2;
            _tmpPointsP2 = _cursor.getInt(_cursorIndexOfPointsP2);
            final int _tmpGamesP1;
            _tmpGamesP1 = _cursor.getInt(_cursorIndexOfGamesP1);
            final int _tmpGamesP2;
            _tmpGamesP2 = _cursor.getInt(_cursorIndexOfGamesP2);
            final int _tmpSetsP1;
            _tmpSetsP1 = _cursor.getInt(_cursorIndexOfSetsP1);
            final int _tmpSetsP2;
            _tmpSetsP2 = _cursor.getInt(_cursorIndexOfSetsP2);
            final String _tmpSetsHistory;
            _tmpSetsHistory = _cursor.getString(_cursorIndexOfSetsHistory);
            final boolean _tmpIsTiebreak;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsTiebreak);
            _tmpIsTiebreak = _tmp != 0;
            final int _tmpTiebreakP1;
            _tmpTiebreakP1 = _cursor.getInt(_cursorIndexOfTiebreakP1);
            final int _tmpTiebreakP2;
            _tmpTiebreakP2 = _cursor.getInt(_cursorIndexOfTiebreakP2);
            final String _tmpPointsHistory;
            _tmpPointsHistory = _cursor.getString(_cursorIndexOfPointsHistory);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Long _tmpWinnerId;
            if (_cursor.isNull(_cursorIndexOfWinnerId)) {
              _tmpWinnerId = null;
            } else {
              _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            }
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpFinishedAt;
            if (_cursor.isNull(_cursorIndexOfFinishedAt)) {
              _tmpFinishedAt = null;
            } else {
              _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            }
            _result = new MatchEntity(_tmpId,_tmpTournamentId,_tmpStage,_tmpRoundLabel,_tmpGroupIndex,_tmpOrderIndex,_tmpRoundNumber,_tmpPlayer1Id,_tmpPlayer2Id,_tmpPointsP1,_tmpPointsP2,_tmpGamesP1,_tmpGamesP2,_tmpSetsP1,_tmpSetsP2,_tmpSetsHistory,_tmpIsTiebreak,_tmpTiebreakP1,_tmpTiebreakP2,_tmpPointsHistory,_tmpStatus,_tmpWinnerId,_tmpStartedAt,_tmpFinishedAt);
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
  public Object getById(final long id, final Continuation<? super MatchEntity> $completion) {
    final String _sql = "SELECT * FROM matches WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MatchEntity>() {
      @Override
      @Nullable
      public MatchEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfRoundLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "roundLabel");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfRoundNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roundNumber");
          final int _cursorIndexOfPlayer1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player1Id");
          final int _cursorIndexOfPlayer2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player2Id");
          final int _cursorIndexOfPointsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP1");
          final int _cursorIndexOfPointsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP2");
          final int _cursorIndexOfGamesP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP1");
          final int _cursorIndexOfGamesP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP2");
          final int _cursorIndexOfSetsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP1");
          final int _cursorIndexOfSetsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP2");
          final int _cursorIndexOfSetsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "setsHistory");
          final int _cursorIndexOfIsTiebreak = CursorUtil.getColumnIndexOrThrow(_cursor, "isTiebreak");
          final int _cursorIndexOfTiebreakP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP1");
          final int _cursorIndexOfTiebreakP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP2");
          final int _cursorIndexOfPointsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsHistory");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finishedAt");
          final MatchEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final String _tmpStage;
            _tmpStage = _cursor.getString(_cursorIndexOfStage);
            final String _tmpRoundLabel;
            _tmpRoundLabel = _cursor.getString(_cursorIndexOfRoundLabel);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final int _tmpRoundNumber;
            _tmpRoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
            final Long _tmpPlayer1Id;
            if (_cursor.isNull(_cursorIndexOfPlayer1Id)) {
              _tmpPlayer1Id = null;
            } else {
              _tmpPlayer1Id = _cursor.getLong(_cursorIndexOfPlayer1Id);
            }
            final Long _tmpPlayer2Id;
            if (_cursor.isNull(_cursorIndexOfPlayer2Id)) {
              _tmpPlayer2Id = null;
            } else {
              _tmpPlayer2Id = _cursor.getLong(_cursorIndexOfPlayer2Id);
            }
            final int _tmpPointsP1;
            _tmpPointsP1 = _cursor.getInt(_cursorIndexOfPointsP1);
            final int _tmpPointsP2;
            _tmpPointsP2 = _cursor.getInt(_cursorIndexOfPointsP2);
            final int _tmpGamesP1;
            _tmpGamesP1 = _cursor.getInt(_cursorIndexOfGamesP1);
            final int _tmpGamesP2;
            _tmpGamesP2 = _cursor.getInt(_cursorIndexOfGamesP2);
            final int _tmpSetsP1;
            _tmpSetsP1 = _cursor.getInt(_cursorIndexOfSetsP1);
            final int _tmpSetsP2;
            _tmpSetsP2 = _cursor.getInt(_cursorIndexOfSetsP2);
            final String _tmpSetsHistory;
            _tmpSetsHistory = _cursor.getString(_cursorIndexOfSetsHistory);
            final boolean _tmpIsTiebreak;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsTiebreak);
            _tmpIsTiebreak = _tmp != 0;
            final int _tmpTiebreakP1;
            _tmpTiebreakP1 = _cursor.getInt(_cursorIndexOfTiebreakP1);
            final int _tmpTiebreakP2;
            _tmpTiebreakP2 = _cursor.getInt(_cursorIndexOfTiebreakP2);
            final String _tmpPointsHistory;
            _tmpPointsHistory = _cursor.getString(_cursorIndexOfPointsHistory);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Long _tmpWinnerId;
            if (_cursor.isNull(_cursorIndexOfWinnerId)) {
              _tmpWinnerId = null;
            } else {
              _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            }
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpFinishedAt;
            if (_cursor.isNull(_cursorIndexOfFinishedAt)) {
              _tmpFinishedAt = null;
            } else {
              _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            }
            _result = new MatchEntity(_tmpId,_tmpTournamentId,_tmpStage,_tmpRoundLabel,_tmpGroupIndex,_tmpOrderIndex,_tmpRoundNumber,_tmpPlayer1Id,_tmpPlayer2Id,_tmpPointsP1,_tmpPointsP2,_tmpGamesP1,_tmpGamesP2,_tmpSetsP1,_tmpSetsP2,_tmpSetsHistory,_tmpIsTiebreak,_tmpTiebreakP1,_tmpTiebreakP2,_tmpPointsHistory,_tmpStatus,_tmpWinnerId,_tmpStartedAt,_tmpFinishedAt);
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
  public Object getNextScheduled(final long tournamentId,
      final Continuation<? super MatchEntity> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM matches\n"
            + "        WHERE tournamentId = ? AND status = 'SCHEDULED'\n"
            + "        ORDER BY orderIndex ASC LIMIT 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tournamentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MatchEntity>() {
      @Override
      @Nullable
      public MatchEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfRoundLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "roundLabel");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfRoundNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roundNumber");
          final int _cursorIndexOfPlayer1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player1Id");
          final int _cursorIndexOfPlayer2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player2Id");
          final int _cursorIndexOfPointsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP1");
          final int _cursorIndexOfPointsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP2");
          final int _cursorIndexOfGamesP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP1");
          final int _cursorIndexOfGamesP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP2");
          final int _cursorIndexOfSetsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP1");
          final int _cursorIndexOfSetsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP2");
          final int _cursorIndexOfSetsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "setsHistory");
          final int _cursorIndexOfIsTiebreak = CursorUtil.getColumnIndexOrThrow(_cursor, "isTiebreak");
          final int _cursorIndexOfTiebreakP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP1");
          final int _cursorIndexOfTiebreakP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP2");
          final int _cursorIndexOfPointsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsHistory");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finishedAt");
          final MatchEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final String _tmpStage;
            _tmpStage = _cursor.getString(_cursorIndexOfStage);
            final String _tmpRoundLabel;
            _tmpRoundLabel = _cursor.getString(_cursorIndexOfRoundLabel);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final int _tmpRoundNumber;
            _tmpRoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
            final Long _tmpPlayer1Id;
            if (_cursor.isNull(_cursorIndexOfPlayer1Id)) {
              _tmpPlayer1Id = null;
            } else {
              _tmpPlayer1Id = _cursor.getLong(_cursorIndexOfPlayer1Id);
            }
            final Long _tmpPlayer2Id;
            if (_cursor.isNull(_cursorIndexOfPlayer2Id)) {
              _tmpPlayer2Id = null;
            } else {
              _tmpPlayer2Id = _cursor.getLong(_cursorIndexOfPlayer2Id);
            }
            final int _tmpPointsP1;
            _tmpPointsP1 = _cursor.getInt(_cursorIndexOfPointsP1);
            final int _tmpPointsP2;
            _tmpPointsP2 = _cursor.getInt(_cursorIndexOfPointsP2);
            final int _tmpGamesP1;
            _tmpGamesP1 = _cursor.getInt(_cursorIndexOfGamesP1);
            final int _tmpGamesP2;
            _tmpGamesP2 = _cursor.getInt(_cursorIndexOfGamesP2);
            final int _tmpSetsP1;
            _tmpSetsP1 = _cursor.getInt(_cursorIndexOfSetsP1);
            final int _tmpSetsP2;
            _tmpSetsP2 = _cursor.getInt(_cursorIndexOfSetsP2);
            final String _tmpSetsHistory;
            _tmpSetsHistory = _cursor.getString(_cursorIndexOfSetsHistory);
            final boolean _tmpIsTiebreak;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsTiebreak);
            _tmpIsTiebreak = _tmp != 0;
            final int _tmpTiebreakP1;
            _tmpTiebreakP1 = _cursor.getInt(_cursorIndexOfTiebreakP1);
            final int _tmpTiebreakP2;
            _tmpTiebreakP2 = _cursor.getInt(_cursorIndexOfTiebreakP2);
            final String _tmpPointsHistory;
            _tmpPointsHistory = _cursor.getString(_cursorIndexOfPointsHistory);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Long _tmpWinnerId;
            if (_cursor.isNull(_cursorIndexOfWinnerId)) {
              _tmpWinnerId = null;
            } else {
              _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            }
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpFinishedAt;
            if (_cursor.isNull(_cursorIndexOfFinishedAt)) {
              _tmpFinishedAt = null;
            } else {
              _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            }
            _result = new MatchEntity(_tmpId,_tmpTournamentId,_tmpStage,_tmpRoundLabel,_tmpGroupIndex,_tmpOrderIndex,_tmpRoundNumber,_tmpPlayer1Id,_tmpPlayer2Id,_tmpPointsP1,_tmpPointsP2,_tmpGamesP1,_tmpGamesP2,_tmpSetsP1,_tmpSetsP2,_tmpSetsHistory,_tmpIsTiebreak,_tmpTiebreakP1,_tmpTiebreakP2,_tmpPointsHistory,_tmpStatus,_tmpWinnerId,_tmpStartedAt,_tmpFinishedAt);
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
  public Object getInProgress(final long tournamentId,
      final Continuation<? super MatchEntity> $completion) {
    final String _sql = "SELECT * FROM matches WHERE tournamentId = ? AND status = 'IN_PROGRESS' LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tournamentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MatchEntity>() {
      @Override
      @Nullable
      public MatchEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfRoundLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "roundLabel");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfRoundNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roundNumber");
          final int _cursorIndexOfPlayer1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player1Id");
          final int _cursorIndexOfPlayer2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player2Id");
          final int _cursorIndexOfPointsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP1");
          final int _cursorIndexOfPointsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP2");
          final int _cursorIndexOfGamesP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP1");
          final int _cursorIndexOfGamesP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP2");
          final int _cursorIndexOfSetsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP1");
          final int _cursorIndexOfSetsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP2");
          final int _cursorIndexOfSetsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "setsHistory");
          final int _cursorIndexOfIsTiebreak = CursorUtil.getColumnIndexOrThrow(_cursor, "isTiebreak");
          final int _cursorIndexOfTiebreakP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP1");
          final int _cursorIndexOfTiebreakP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP2");
          final int _cursorIndexOfPointsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsHistory");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finishedAt");
          final MatchEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final String _tmpStage;
            _tmpStage = _cursor.getString(_cursorIndexOfStage);
            final String _tmpRoundLabel;
            _tmpRoundLabel = _cursor.getString(_cursorIndexOfRoundLabel);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final int _tmpRoundNumber;
            _tmpRoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
            final Long _tmpPlayer1Id;
            if (_cursor.isNull(_cursorIndexOfPlayer1Id)) {
              _tmpPlayer1Id = null;
            } else {
              _tmpPlayer1Id = _cursor.getLong(_cursorIndexOfPlayer1Id);
            }
            final Long _tmpPlayer2Id;
            if (_cursor.isNull(_cursorIndexOfPlayer2Id)) {
              _tmpPlayer2Id = null;
            } else {
              _tmpPlayer2Id = _cursor.getLong(_cursorIndexOfPlayer2Id);
            }
            final int _tmpPointsP1;
            _tmpPointsP1 = _cursor.getInt(_cursorIndexOfPointsP1);
            final int _tmpPointsP2;
            _tmpPointsP2 = _cursor.getInt(_cursorIndexOfPointsP2);
            final int _tmpGamesP1;
            _tmpGamesP1 = _cursor.getInt(_cursorIndexOfGamesP1);
            final int _tmpGamesP2;
            _tmpGamesP2 = _cursor.getInt(_cursorIndexOfGamesP2);
            final int _tmpSetsP1;
            _tmpSetsP1 = _cursor.getInt(_cursorIndexOfSetsP1);
            final int _tmpSetsP2;
            _tmpSetsP2 = _cursor.getInt(_cursorIndexOfSetsP2);
            final String _tmpSetsHistory;
            _tmpSetsHistory = _cursor.getString(_cursorIndexOfSetsHistory);
            final boolean _tmpIsTiebreak;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsTiebreak);
            _tmpIsTiebreak = _tmp != 0;
            final int _tmpTiebreakP1;
            _tmpTiebreakP1 = _cursor.getInt(_cursorIndexOfTiebreakP1);
            final int _tmpTiebreakP2;
            _tmpTiebreakP2 = _cursor.getInt(_cursorIndexOfTiebreakP2);
            final String _tmpPointsHistory;
            _tmpPointsHistory = _cursor.getString(_cursorIndexOfPointsHistory);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Long _tmpWinnerId;
            if (_cursor.isNull(_cursorIndexOfWinnerId)) {
              _tmpWinnerId = null;
            } else {
              _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            }
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpFinishedAt;
            if (_cursor.isNull(_cursorIndexOfFinishedAt)) {
              _tmpFinishedAt = null;
            } else {
              _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            }
            _result = new MatchEntity(_tmpId,_tmpTournamentId,_tmpStage,_tmpRoundLabel,_tmpGroupIndex,_tmpOrderIndex,_tmpRoundNumber,_tmpPlayer1Id,_tmpPlayer2Id,_tmpPointsP1,_tmpPointsP2,_tmpGamesP1,_tmpGamesP2,_tmpSetsP1,_tmpSetsP2,_tmpSetsHistory,_tmpIsTiebreak,_tmpTiebreakP1,_tmpTiebreakP2,_tmpPointsHistory,_tmpStatus,_tmpWinnerId,_tmpStartedAt,_tmpFinishedAt);
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
  public Object getFinishedForPlayer(final long playerId,
      final Continuation<? super List<MatchEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM matches\n"
            + "        WHERE (player1Id = ? OR player2Id = ?) AND status = 'FINISHED'\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playerId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, playerId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MatchEntity>>() {
      @Override
      @NonNull
      public List<MatchEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTournamentId = CursorUtil.getColumnIndexOrThrow(_cursor, "tournamentId");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfRoundLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "roundLabel");
          final int _cursorIndexOfGroupIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "groupIndex");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfRoundNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roundNumber");
          final int _cursorIndexOfPlayer1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player1Id");
          final int _cursorIndexOfPlayer2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "player2Id");
          final int _cursorIndexOfPointsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP1");
          final int _cursorIndexOfPointsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsP2");
          final int _cursorIndexOfGamesP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP1");
          final int _cursorIndexOfGamesP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "gamesP2");
          final int _cursorIndexOfSetsP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP1");
          final int _cursorIndexOfSetsP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "setsP2");
          final int _cursorIndexOfSetsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "setsHistory");
          final int _cursorIndexOfIsTiebreak = CursorUtil.getColumnIndexOrThrow(_cursor, "isTiebreak");
          final int _cursorIndexOfTiebreakP1 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP1");
          final int _cursorIndexOfTiebreakP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "tiebreakP2");
          final int _cursorIndexOfPointsHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsHistory");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finishedAt");
          final List<MatchEntity> _result = new ArrayList<MatchEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MatchEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTournamentId;
            _tmpTournamentId = _cursor.getLong(_cursorIndexOfTournamentId);
            final String _tmpStage;
            _tmpStage = _cursor.getString(_cursorIndexOfStage);
            final String _tmpRoundLabel;
            _tmpRoundLabel = _cursor.getString(_cursorIndexOfRoundLabel);
            final int _tmpGroupIndex;
            _tmpGroupIndex = _cursor.getInt(_cursorIndexOfGroupIndex);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final int _tmpRoundNumber;
            _tmpRoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
            final Long _tmpPlayer1Id;
            if (_cursor.isNull(_cursorIndexOfPlayer1Id)) {
              _tmpPlayer1Id = null;
            } else {
              _tmpPlayer1Id = _cursor.getLong(_cursorIndexOfPlayer1Id);
            }
            final Long _tmpPlayer2Id;
            if (_cursor.isNull(_cursorIndexOfPlayer2Id)) {
              _tmpPlayer2Id = null;
            } else {
              _tmpPlayer2Id = _cursor.getLong(_cursorIndexOfPlayer2Id);
            }
            final int _tmpPointsP1;
            _tmpPointsP1 = _cursor.getInt(_cursorIndexOfPointsP1);
            final int _tmpPointsP2;
            _tmpPointsP2 = _cursor.getInt(_cursorIndexOfPointsP2);
            final int _tmpGamesP1;
            _tmpGamesP1 = _cursor.getInt(_cursorIndexOfGamesP1);
            final int _tmpGamesP2;
            _tmpGamesP2 = _cursor.getInt(_cursorIndexOfGamesP2);
            final int _tmpSetsP1;
            _tmpSetsP1 = _cursor.getInt(_cursorIndexOfSetsP1);
            final int _tmpSetsP2;
            _tmpSetsP2 = _cursor.getInt(_cursorIndexOfSetsP2);
            final String _tmpSetsHistory;
            _tmpSetsHistory = _cursor.getString(_cursorIndexOfSetsHistory);
            final boolean _tmpIsTiebreak;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsTiebreak);
            _tmpIsTiebreak = _tmp != 0;
            final int _tmpTiebreakP1;
            _tmpTiebreakP1 = _cursor.getInt(_cursorIndexOfTiebreakP1);
            final int _tmpTiebreakP2;
            _tmpTiebreakP2 = _cursor.getInt(_cursorIndexOfTiebreakP2);
            final String _tmpPointsHistory;
            _tmpPointsHistory = _cursor.getString(_cursorIndexOfPointsHistory);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Long _tmpWinnerId;
            if (_cursor.isNull(_cursorIndexOfWinnerId)) {
              _tmpWinnerId = null;
            } else {
              _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            }
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpFinishedAt;
            if (_cursor.isNull(_cursorIndexOfFinishedAt)) {
              _tmpFinishedAt = null;
            } else {
              _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            }
            _item = new MatchEntity(_tmpId,_tmpTournamentId,_tmpStage,_tmpRoundLabel,_tmpGroupIndex,_tmpOrderIndex,_tmpRoundNumber,_tmpPlayer1Id,_tmpPlayer2Id,_tmpPointsP1,_tmpPointsP2,_tmpGamesP1,_tmpGamesP2,_tmpSetsP1,_tmpSetsP2,_tmpSetsHistory,_tmpIsTiebreak,_tmpTiebreakP1,_tmpTiebreakP2,_tmpPointsHistory,_tmpStatus,_tmpWinnerId,_tmpStartedAt,_tmpFinishedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
