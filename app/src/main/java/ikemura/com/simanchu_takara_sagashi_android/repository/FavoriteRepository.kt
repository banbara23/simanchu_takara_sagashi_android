package ikemura.com.simanchu_takara_sagashi_android.repository

import ikemura.com.simanchu_takara_sagashi_android.common.AppEnv
import ikemura.com.simanchu_takara_sagashi_android.common.PreferenceModel
import ikemura.com.simanchu_takara_sagashi_android.model.Spot

class FavoriteRepository {
    val preferenceModel = PreferenceModel(AppEnv.instance.mainApplication)
    val repository = SpotRepository(AppEnv.instance.mainApplication)
    /**
     * 一覧を取得
     */
    fun getFavoriteList(): List<Spot> {
        val favoriteList = preferenceModel.getFavorite()
        val allSpots = repository.getSpotList().spots
        return allSpots.filter { favoriteList.contains(it.id) }
    }

    /**
     * 詳細を取得
     */
    fun getSpot(id: String): Spot {
        val response = getFavoriteList()
        return response.filter { it.id == id }.first()
    }
}