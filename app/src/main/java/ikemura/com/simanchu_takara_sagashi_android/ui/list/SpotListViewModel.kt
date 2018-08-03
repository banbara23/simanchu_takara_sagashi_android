package ikemura.com.simanchu_takara_sagashi_android.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ikemura.com.simanchu_takara_sagashi_android.common.AppEnv
import ikemura.com.simanchu_takara_sagashi_android.model.Spot
import ikemura.com.simanchu_takara_sagashi_android.repository.SpotRepository

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
