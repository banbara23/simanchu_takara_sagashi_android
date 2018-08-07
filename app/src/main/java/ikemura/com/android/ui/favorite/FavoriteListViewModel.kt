package ikemura.com.android.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ikemura.com.android.model.Spot
import ikemura.com.android.repository.FavoriteRepository

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
