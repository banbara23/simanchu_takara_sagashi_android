package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ikemura.com.simanchu_takara_sagashi_android.common.AppEnv

/**
 * スポット詳細 ViewModel
 */
class SpotDetailViewModel : ViewModel() {
    private val _data = MutableLiveData<String>()
    private var preferenceModel = AppEnv.instance.preferenceModel
    val data: LiveData<String>
        get() = _data

    init {
        _data.value = "Hello, Jetpack!"
//        preferenceModel = PreferenceModel(Application().applicationContext)
    }

    fun addFavorite(spotId: String) {
        preferenceModel.setFavorite(spotId)
    }

    fun removeFavorite(spotId: String) {
        val favorite = preferenceModel.getFavorite()
        val removedFavorite = favorite.filter { it != spotId }
        preferenceModel.setFavoriteList(removedFavorite as MutableList<String>)
    }
}
