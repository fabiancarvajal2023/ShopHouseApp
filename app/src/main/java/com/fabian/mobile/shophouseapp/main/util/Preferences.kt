package com.fabian.mobile.shophouseapp.main.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "preferences_name")

class Preferences(private val context: Context) {

    suspend fun saveBoolean(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        savePreference(preferencesKey,value)
    }

    suspend fun saveInt(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        savePreference(preferencesKey,value)
    }

    suspend fun saveString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        savePreference(preferencesKey,value)
    }

    suspend fun saveLong(key: String, value: Long) {
        val preferencesKey = longPreferencesKey(key)
        savePreference(preferencesKey,value)
    }

    private suspend fun <T>savePreference(preferencesKey:androidx.datastore.preferences.core.Preferences.Key<T>,value:T){
        context.dataStore.edit {preferences->
            preferences[preferencesKey] = value
        }
    }

    suspend fun getBoolean(key: String): Boolean {
        val preferencesKey = booleanPreferencesKey(key)
        return getPreferences(preferencesKey)?:false
    }

    suspend fun getInt(key: String): Int {
        val preferencesKey = intPreferencesKey(key)
        return getPreferences(preferencesKey)?:-1
    }

    suspend fun getLong(key: String): Long {
        val preferencesKey = longPreferencesKey(key)
        return getPreferences(preferencesKey)?:-1L
    }

    suspend fun getString(key: String): String {
        val preferencesKey = stringPreferencesKey(key)
        return getPreferences(preferencesKey).orEmpty()
    }

    private suspend fun <T>getPreferences(preferencesKey:androidx.datastore.preferences.core.Preferences.Key<T>):T?{
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }

}