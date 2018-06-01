package ikemura.com.simanchu_takara_sagashi_android

import android.content.Context
import com.google.gson.Gson
import ikemura.com.simanchu_takara_sagashi_android.model.Spot
import ikemura.com.simanchu_takara_sagashi_android.model.Spots

class SpotRepository(val context: Context) {

    /**
     * 一覧を取得
     */
    fun fetchList(): Spots {
        val jsonString = context.assets.open("spot.json").bufferedReader().use {
            it.readText()
        }
        return Gson().fromJson(jsonString, Spots::class.java)
    }

    /**
     * 詳細を取得
     */
    fun fetchDetail(id: String): Spot {
        return fetchList().spots.single { it.id == id }
    }
}