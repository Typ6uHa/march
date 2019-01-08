package aa.com.marchenko

import android.content.SharedPreferences
import android.preference.PreferenceManager

object TokenStorage {

    private val preferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(App.context)
    }

    fun getToken(): Boolean {
        return preferences.getBoolean("token", false)
    }

    fun putToken() {
        preferences.edit().putBoolean("token", true).apply()
    }

    fun removeToken() {
        preferences.edit().remove("token").apply()
    }
}