package ikemura.com.simanchu_takara_sagashi_android.ui.top

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.ui.detail.SpotDetailActivity
import ikemura.com.simanchu_takara_sagashi_android.ui.list.SpotListActivity
import kotlinx.android.synthetic.main.top_fragment.*

class TopFragment : Fragment() {

    companion object {
        fun newInstance() = TopFragment()
    }

    private lateinit var viewModel: TopViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.top_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TopViewModel::class.java)
//        val text = viewModel.data.value
//        title.text = text
        setupEvent()
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
