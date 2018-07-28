package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.SpotRepository
import ikemura.com.simanchu_takara_sagashi_android.model.Spot
import ikemura.com.simanchu_takara_sagashi_android.ui.fullscreen.FullscreenActivity
import kotlinx.android.synthetic.main.spot_detail_fragment.*

/**
 * スポット詳細 Fragment
 */
class SpotDetailFragment : Fragment() {

    private lateinit var viewModel: SpotDetailViewModel
    private lateinit var spotId: String
    private var isFavorite = false
    private var spot: Spot? = null
    private val TAG: String = SpotDetailFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(Constants.ARG_ITEM_ID)) {
                spotId = it.getString(Constants.ARG_ITEM_ID, "")
                Log.d(TAG, "spotId=$spotId")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.spot_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupClickClick()
        setupFavorite()
    }

    private fun setupClickClick() {
        // toolbarの戻るボタン
        detail_toolbar.setNavigationOnClickListener {
            activity?.finish()
        }
        // 画像タップで全画面
        detail_image.setOnClickListener { startActivity(Intent(this.activity!!, FullscreenActivity::class.java)) }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(SpotDetailViewModel::class.java)
        val data = fetchSpotDetail(spotId)

        // 詳細データが取得できたら
        data.let {
            detail_title.text = it.name
            detail_level.text = "難易度：${it.level}"
            detail_place.text = "場所：${it.place}"
            description.text = it.description
        }
    }

    private fun setupFavorite() {
        favorite.setOnClickListener { view ->
            isFavorite = !isFavorite
            val status: String
            val drawable = if (isFavorite) {
                viewModel.addFavorite(spotId)
                status = "登録"
                R.drawable.ic_favorite_black_24dp
            } else {
                viewModel.removeFavorite(spotId)
                status = "解除"
                R.drawable.ic_favorite_border_black_24dp
            }

            favorite.setImageDrawable(ContextCompat.getDrawable(context!!, drawable))

            Snackbar.make(view, "お気に入りを${status}しました", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
        }
    }

    /**
     * リポジトリからスポット詳細を取得する
     */
    private fun fetchSpotDetail(spotId: String) = SpotRepository(this.context!!).fetchDetail(spotId)

}
