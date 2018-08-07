package ikemura.com.android.repository

import android.content.Context
import com.google.gson.Gson
import ikemura.com.android.model.Spot
import ikemura.com.android.model.Spots

class SpotRepository(val context: Context) {
    var spots = Spots()
    /**
     * 一覧を取得
     */
    fun getSpotList(): Spots {
        val jsonString = context.assets.open("spot.json").bufferedReader().use {
            it.readText()
        }
        spots = Gson().fromJson(jsonString, Spots::class.java)
        return spots
    }

    /**
     * 詳細を取得
     */
    fun getSpot(id: String): Spot {
        val response = if (spots.spots.isEmpty()) getSpotList() else spots
        return response.spots.first { it.id == id }
    }
}