package ikemura.com.simanchu.ui.top

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import ikemura.com.simanchu.Constants
import ikemura.com.simanchu.R
import ikemura.com.simanchu.databinding.TopFragmentBinding
import ikemura.com.simanchu.helper.LevelViewHelper.setLevelTextView
import ikemura.com.simanchu.model.Spot
import ikemura.com.simanchu.ui.detail.SpotDetailActivity
import ikemura.com.simanchu.ui.list.SpotListActivity
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
    private lateinit var binding: TopFragmentBinding
    private lateinit var spots: List<Spot>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this).get(TopViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.top_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //トップ画面の各初期処理
        setupEvent()
        setupTopImage()
        setupRecommendSpots()
    }

    override fun onResume() {
        super.onResume()
        topImage.resume()   //上部の動く画像の再開
    }

    override fun onPause() {
        super.onPause()
        topImage.pause()   //上部の動く画像の停止
    }

    /**
     * 上部の動く画像の設定
     */
    private fun setupTopImage() {
        val url = viewModel.getRandamSpotImage()
        Picasso.get().load(url).into(binding.topImage)
    }

    /**
     * ３件のオススメスポット設定
     */
    private fun setupRecommendSpots() {
        spots = viewModel.getRecommendSpots()
        if (spots.isEmpty()) {
            return
        }
        spots.forEach { Log.d(TAG, "top recommend id:${it.id} name:${it.name}") }
        spots.first().let {
            Picasso.get()
                    .load(it.thumbnail)
                    .into(binding.includeCard1.recommendImage)

            binding.includeCard1.recommendName.text = it.name
            binding.includeCard1.recommendPlace.text = it.location.first().place

            setLevelTextView(requireContext(), it.level, binding.includeCard1.recommendLevel)
        }
        spots[1].let {
            Picasso.get()
                    .load(it.thumbnail)
                    .into(binding.includeCard2.recommendImage)
            binding.includeCard2.recommendName.text = it.name
            binding.includeCard2.recommendPlace.text = it.location.first().place
            setLevelTextView(requireContext(), it.level, binding.includeCard2.recommendLevel)
        }
        spots[2].let {
            Picasso.get()
                    .load(it.thumbnail)
                    .into(binding.includeCard3.recommendImage)
            binding.includeCard3.recommendName.text = it.name
            binding.includeCard3.recommendPlace.text = it.location.first().place
            setLevelTextView(requireContext(), it.level, binding.includeCard3.recommendLevel)
        }
    }

    /**
     * イベント設定
     */
    private fun setupEvent() {
        include_card1.setOnClickListener {
            navigateToDetail(spots[0], binding.includeCard1.recommendImage)
        }
        include_card2.setOnClickListener {
            navigateToDetail(spots[1], binding.includeCard2.recommendImage)
        }
        include_card3.setOnClickListener {
            navigateToDetail(spots[2], binding.includeCard3.recommendImage)
        }
        more.setOnClickListener {
            navigateToList()
        }
    }

    /**
     * オススメのスポット詳細に遷移
     */
    private fun navigateToDetail(spot: Spot, imageView: View) {
        val intent = Intent(activity, SpotDetailActivity::class.java)
        intent.putExtra(Constants.ARG_SPOT, spot)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), imageView, "spot")

        startActivity(intent, options.toBundle())
    }

    /**
     * スポット一覧に遷移
     */
    private fun navigateToList() {
        startActivity(Intent(activity, SpotListActivity::class.java))
    }
}
