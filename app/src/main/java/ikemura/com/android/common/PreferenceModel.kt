package ikemura.com.android.common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceModel(private val context: Context) {
    private val TAG: String = PreferenceModel::class.java.simpleName

    companion object {
        const val KEY: String = "favorite"
    }

    private var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setFavorite(id: String) {
        val favorite: MutableList<String> = getFavorite()
        Log.d(TAG, favorite.toString())
        favorite += id
        val json = Gson().toJson(favorite)
        preferences.edit {
            putString(KEY, json)
        }
    }

    fun setFavoriteList(favorites: MutableList<String>) {
        val json = Gson().toJson(favorites)
        preferences.edit {
            putString(KEY, json)
        }
    }

    fun getFavorite(): MutableList<String> {
        val json = preferences.getString(KEY, "")
        val listType = object : TypeToken<List<String>>() {}.type
        return when (json) {
            "" -> mutableListOf()
            else -> Gson().fromJson<MutableList<String>>(json, listType) ?: mutableListOf<String>()
        }
    }
}