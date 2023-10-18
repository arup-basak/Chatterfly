package com.arup.chatapp.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login_data")

    companion object {
        val TOKEN = stringPreferencesKey("TOKEN")
        val USER_ID = stringPreferencesKey("USER_ID")
    }

    suspend fun saveLoginDataToDataStore(token: String, userId: String) {
        context.dataStore.edit {
            it[TOKEN] = token
            it[USER_ID] = userId
        }
    }
    suspend fun clearData() = context.dataStore.edit {
        it.clear()
    }
}