package ikemura.com.simanchu_takara_sagashi_android.common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.squareup.moshi.Moshi


class PreferenceModel(private val context: Context) {
    companion object {
        const val KEY: String = "favorite"
    }

    private var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private var moshi = Moshi.Builder().build()
    private var moshiAdapter = moshi.adapter(Array<String>::class.java)

    fun setFavorite(id: String) {
//        val raw = preferences.getString(KEY, "")

        val array: Array<String>? = getFavorite()
        array ?: arrayOf("")
        array?.plus(id)
        val data = moshiAdapter.toJson(array)
        preferences.edit {
            putString(KEY, data)
        }
    }

    fun getFavorite(): Array<String>? {
        val raw = preferences.getString(KEY, "")
        return moshiAdapter.fromJson(raw)
    }
}