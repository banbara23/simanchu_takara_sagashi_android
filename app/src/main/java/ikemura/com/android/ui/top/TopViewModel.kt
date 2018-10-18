package ikemura.com.android.ui.top

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ikemura.com.android.common.AppEnv
import ikemura.com.android.model.Spot
import ikemura.com.android.random
import ikemura.com.android.repository.SpotRepository

class TopViewModel : ViewModel() {
    private var spotRepository: SpotRepository = SpotRepository(AppEnv.instance.mainApplication)
    companion object {
        private val TAG = this::class.java.simpleName
    }

    /**
     * トップのオススメSpotを3件取得
     */
    fun getRecommendSpots(): List<Spot> {
        val spots = spotRepository.getSpotList().spots
        var recommendSpots = listOf<Spot>()

        Log.d(TAG, "件数:${spots.size}")
        // オススメSpotが3件になるまでループ
        while (recommendSpots.size < 3) {
            //ランダムで1〜22のInt
            var id = (1..22).random()
            //ランダムIntが既に入ってるか？
            while ((recommendSpots.filter { it.id == id.toString() }).isNotEmpty()) {
                //既に入ってたのでランダムIntを再取得
                id = (1..22).random()
            }
            // オススメSpotに追加
            recommendSpots += spotRepository.getSpot(id.toString())
        }
        return recommendSpots
    }

    fun getRandamSpotImage(): String {
        val id = (1..22).random()
        return spotRepository.getSpot(id.toString()).image
    }
}
