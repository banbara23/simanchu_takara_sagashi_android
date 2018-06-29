package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.SpotRepository
import kotlinx.android.synthetic.main.spot_detail_fragment.*

/**
 * 詳細画面のFragment
 */
class SpotDetailFragment : Fragment() {

    private lateinit var viewModel: SpotDetailViewModel
    private lateinit var spotId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(Constants.ARG_ITEM_ID)) {
                spotId = it.getString(Constants.ARG_ITEM_ID, "")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.spot_detail_fragment, container, false)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpotDetailViewModel::class.java)
        val data = fetchSpotDetail(spotId)
        // 詳細データが取得できたら
        data.let {
            // item_detail_text.text = it
//            setActivityTitle(it.name)
            detail_title.text = it.name
            detail_level.text = "難易度：${it.level}"
            detail_place.text = "場所：${it.place}"
            description.text = it.description
        }
    }

    /**
     * リポジトリからスポット詳細を取得する
     */
    private fun fetchSpotDetail(spotId: String) = SpotRepository(this.context!!).fetchDetail(spotId)

    /**
     * スポット名をタイトルにセット
     */
//    private fun setActivityTitle(name: String) {
//        activity?.toolbar_layout?.title = name
//    }
}
