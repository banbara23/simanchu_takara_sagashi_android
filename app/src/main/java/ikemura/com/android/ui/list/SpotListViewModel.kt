package ikemura.com.android.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ikemura.com.android.common.AppEnv
import ikemura.com.android.model.Spot
import ikemura.com.android.repository.SpotRepository

class SpotListViewModel : ViewModel() {
    private val repository = SpotRepository(AppEnv.instance.mainApplication)
    val spots = MutableLiveData<List<Spot>>()
    /**
     * 一覧を取得
     */
    fun fetchList() {
        val response = repository.getSpotList()
        spots.postValue(response.spots)
    }

    /**
     * 詳細を取得
     */
    fun fetchDetail(id: String): Spot {
        return repository.getSpot(id)
    }
}
