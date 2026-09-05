package com.tennis.matchpoint.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "match_point_prefs")

/**
 * Хранит небольшие пользовательские настройки (например, "какой я игрок" для быстрого доступа
 * к своему профилю) через Jetpack DataStore — современная замена SharedPreferences.
 */
@Singleton
class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val myPlayerIdKey = longPreferencesKey("my_player_id")

    val myPlayerId: Flow<Long?> = context.dataStore.data.map { prefs ->
        prefs[myPlayerIdKey]?.takeIf { it > 0 }
    }

    suspend fun setMyPlayerId(playerId: Long) {
        context.dataStore.edit { it[myPlayerIdKey] = playerId }
    }
}
