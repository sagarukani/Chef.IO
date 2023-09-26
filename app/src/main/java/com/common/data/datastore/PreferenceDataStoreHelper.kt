package com.common.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.common.data.datastore.model.UserPreferences
import com.common.data.datastore.serializer.UserPreferencesSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Pintu Singh on 10-02-2022
 */

class PreferenceDataStoreHelper(private val context: Context) {

    companion object {
        private const val APP_PREFERENCES_NAME = "app_preference_datastore"
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

        private val AUTH_TOKEN = stringPreferencesKey("AUTH_TOKEN")
        private val IS_LOGIN = booleanPreferencesKey("IS_LOGIN")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = APP_PREFERENCES_NAME,
        corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
        produceMigrations = { listOf(SharedPreferencesMigration(context, "")) }
    )
    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer,
        corruptionHandler = ReplaceFileCorruptionHandler { UserPreferences.getDefaultInstance() },
    )

    private val dataStore = context.dataStore
    private val userPreferencesStore = context.userPreferencesStore

    fun getAuthToken(): Flow<String> = dataStore.data.map { it[AUTH_TOKEN] ?: "" }

    suspend fun setAuthToken(authToken: String?) {
        dataStore.edit {
            if (authToken.isNullOrEmpty()) it.remove(AUTH_TOKEN) else it[AUTH_TOKEN] = authToken
        }
    }

    fun getIsLogin(): Flow<Boolean?> = dataStore.data.map { it[IS_LOGIN] }

    suspend fun setIsLogin(isLogin: Boolean?) {
        dataStore.edit {
            if (isLogin == null) it.remove(IS_LOGIN) else it[IS_LOGIN] = isLogin
        }
    }

    fun getUserPreference(): Flow<UserPreferences> = userPreferencesStore.data

    suspend fun setUserPreference(userPreferences: UserPreferences) {
        userPreferencesStore.updateData { userPreferences }
    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
        userPreferencesStore.updateData { UserPreferences.getDefaultInstance() }
    }
}