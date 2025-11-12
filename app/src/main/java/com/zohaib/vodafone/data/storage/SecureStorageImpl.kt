package com.zohaib.vodafone.data.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.zohaib.vodafone.common.Constants
import com.zohaib.vodafone.domain.model.User
import com.zohaib.vodafone.domain.repository.SecureStorage

class SecureStorageImpl(context: Context) : SecureStorage {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val prefs = EncryptedSharedPreferences.create(
        Constants.PREFS_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveUser(user: User) {
        prefs.edit().putInt(KEY_USER_ID, user.id).putString(KEY_EMAIL, user.email).apply()
    }

    override fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    override fun clearAll() { prefs.edit().clear().apply() }

    companion object {
        private const val KEY_TOKEN = "key_token"
        private const val KEY_USER_ID = "key_user_id"
        private const val KEY_EMAIL = "key_user_email"
    }
}