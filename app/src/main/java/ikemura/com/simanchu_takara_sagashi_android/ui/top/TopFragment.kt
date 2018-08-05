package ikemura.com.simanchu_takara_sagashi_android.ui.top

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.ui.detail.SpotDetailActivity
import ikemura.com.simanchu_takara_sagashi_android.ui.list.SpotListActivity
import kotlinx.android.synthetic.main.top_fragment.include_card1
import kotlinx.android.synthetic.main.top_fragment.include_card2
import kotlinx.android.synthetic.main.top_fragment.include_card3
import kotlinx.android.synthetic.main.top_fragment.more
import kotlinx.android.synthetic.main.top_fragment.topImage

class TopFragment : Fragment() {

    companion object {
        fun newInstance() = TopFragment()
        private val TAG = TopFragment::class.java.simpleName
    }

    private lateinit var viewModel: TopViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this).get(TopViewModel::class.java)
        return inflater.inflate(R.layout.top_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvent()
        setupTopImage()
        setupRecommendSpots()
    }

    private fun setupTopImage() {
        val spot = viewModel.getTopImage()
        //todo:画像urlをPicassoで表示
//        Picasso.get().load(spot.url).into()
    }

    private fun setupRecommendSpots() {
        val spots = viewModel.getRecommendSpots()
        spots.forEach { Log.d(TAG, "${it.id} ${it.name}") }
        //todo:オススメ3件を表示設定
    }

    override fun onResume() {
        super.onResume()
        topImage.resume()
    }

    override fun onPause() {
        super.onPause()
        topImage.pause()
    }

    private fun setupEvent() {
        include_card1.setOnClickListener {
            navigateToDetail()
        }
        include_card2.setOnClickListener {
            navigateToDetail()
        }
        include_card3.setOnClickListener {
            navigateToDetail()
        }
        more.setOnClickListener {
            navigateToList()
        }
    }

    private fun navigateToDetail() {
        val intent = Intent(activity, SpotDetailActivity::class.java)
        intent.putExtra(Constants.ARG_ITEM_ID, "1")
        startActivity(intent)
    }

    private fun navigateToList() {
        startActivity(Intent(activity, SpotListActivity::class.java))
    }
}
