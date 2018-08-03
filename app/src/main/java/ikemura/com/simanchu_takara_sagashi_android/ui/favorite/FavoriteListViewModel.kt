package ikemura.com.simanchu_takara_sagashi_android.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ikemura.com.simanchu_takara_sagashi_android.model.Spot
import ikemura.com.simanchu_takara_sagashi_android.repository.FavoriteRepository

class FavoriteListViewModel : ViewModel() {
    private val repository = FavoriteRepository()
    val spots = MutableLiveData<List<Spot>>()
    /**
     * 一覧を取得
     */
    fun fetchList() {
        val response = repository.getFavoriteList()
        spots.postValue(response)
    }

    /**
     * 詳細を取得
     */
    fun fetchDetail(id: String): Spot {
        return repository.getSpot(id)
    }
}
