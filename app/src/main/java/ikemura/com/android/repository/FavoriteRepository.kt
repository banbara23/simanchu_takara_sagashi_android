package ikemura.com.android.repository

import ikemura.com.android.common.AppEnv
import ikemura.com.android.common.PreferenceModel
import ikemura.com.android.model.Spot

class FavoriteRepository {
    private val preferenceModel = PreferenceModel(AppEnv.instance.mainApplication)
    private val repository = SpotRepository(AppEnv.instance.mainApplication)
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