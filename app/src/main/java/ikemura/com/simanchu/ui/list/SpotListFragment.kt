package ikemura.com.simanchu.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import ikemura.com.simanchu.Constants
import ikemura.com.simanchu.R
import ikemura.com.simanchu.model.Spot
import ikemura.com.simanchu.ui.detail.SpotDetailActivity
import kotlinx.android.synthetic.main.spot_list_view.item_list

class SpotListFragment : Fragment(), OnListFragmentInteractionListener {

    companion object {
        fun newInstance() = SpotListFragment()
    }

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var viewModel: SpotListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.spot_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupRecyclerView(item_list)
        viewModel.fetchList()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(SpotListViewModel::class.java)
        viewModel.spots.observe(this, Observer {
            it ?: return@Observer
            setToAdapter(it)
        })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        listener = this
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(activity!!, viewModel.spots.value, false, listener)
    }

    private fun setToAdapter(spots: List<Spot>) {
        val adapter = item_list.adapter as SimpleItemRecyclerViewAdapter
        adapter.replaceAll(spots)
    }

    override fun onListFragmentInteraction(item: Spot) {
        val intent = Intent(activity, SpotDetailActivity::class.java)
        intent.putExtra(Constants.ARG_SPOT, item)
        activity?.startActivity(intent)
    }
}

interface OnListFragmentInteractionListener {
    fun onListFragmentInteraction(item: Spot)
}