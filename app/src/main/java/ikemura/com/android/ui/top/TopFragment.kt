package ikemura.com.android.ui.top

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import ikemura.com.android.Constants
import ikemura.com.android.R
import ikemura.com.android.databinding.TopFragmentBinding
import ikemura.com.android.ui.detail.SpotDetailActivity
import ikemura.com.android.ui.list.SpotListActivity
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this).get(TopViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.top_fragment, container, false)
        return binding.root
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
        spots.forEach { Log.d(TAG, "top recommend id:${it.id} name:${it.name}") }
        //todo:オススメ3件を表示設定
        spots.first().let {
            Picasso.get()
                    .load("https://mbaas.api.nifcloud.com/2013-09-01/applications/vZwusNeWZ3lS2PjO/publicFiles/24_thumb.JPG")
                    .into(binding.includeCard1.recommendImage)

            binding.includeCard1.recommendName.text = it.name
            binding.includeCard1.recommendPlace.text = it.location.first().place
            binding.includeCard1.recommendLevel.text = it.level
        }
        spots[1].let {
            //            Picasso.get().load(it.)
            binding.includeCard2.recommendName.text = it.name
            binding.includeCard2.recommendPlace.text = it.location.first().place
            binding.includeCard2.recommendLevel.text = it.level
        }
        spots[2].let {
            //            Picasso.get().load(it.)
            binding.includeCard3.recommendName.text = it.name
            binding.includeCard3.recommendPlace.text = it.location.first().place
            binding.includeCard3.recommendLevel.text = it.level
        }
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
