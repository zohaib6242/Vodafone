package com.zohaib.vodafone.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.zohaib.vodafone.domain.model.User
import com.zohaib.vodafone.domain.repository.SecureStorage

class AppPreferencesImpl(context: Context) : SecureStorage {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs_unsecured", Context.MODE_PRIVATE)

    override fun saveUser(user: User) {

    }
    override fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    override fun clearAll() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val KEY_TOKEN = "key_token"
    }
}
