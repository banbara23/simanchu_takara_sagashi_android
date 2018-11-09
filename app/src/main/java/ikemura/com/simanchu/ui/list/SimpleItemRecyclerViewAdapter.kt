package ikemura.com.simanchu.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ikemura.com.simanchu.Constants
import ikemura.com.simanchu.R
import ikemura.com.simanchu.helper.LevelViewHelper
import ikemura.com.simanchu.model.Spot
import ikemura.com.simanchu.ui.detail.SpotDetailFragment
import kotlinx.android.synthetic.main.list_item_content.view.image
import kotlinx.android.synthetic.main.list_item_content.view.level
import kotlinx.android.synthetic.main.list_item_content.view.name
import kotlinx.android.synthetic.main.list_item_content.view.place

class SimpleItemRecyclerViewAdapter(private val parentActivity: FragmentActivity,
        private var spots: List<Spot>?,
        private val twoPane: Boolean,
        private val listener: OnListFragmentInteractionListener?) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val spot = v.tag as Spot
            if (twoPane) {
                val arguments = Bundle().apply {
                    putParcelable(Constants.ARG_SPOT, spot)
                }
                val fragment = SpotDetailFragment.newInstance(arguments)
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
            } else {
                listener?.onListFragmentInteraction(spot)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = spots!![position]
        Picasso.get().load(item.thumbnail).into(holder.imageView)
        holder.nameView.text = item.name
        LevelViewHelper.setLevelTextView(parentActivity, item.level, holder.levelView)
        holder.placeView.text = item.location.first().place

        with(holder.itemView) {
            tag = item
//            setOnClickListener(onClickListener)
            setOnClickListener {
                listener?.onListFragmentInteraction(item)
            }
        }
    }

    fun replaceAll(spots: List<Spot>) {
        this.spots = spots
        notifyDataSetChanged()
    }

    override fun getItemCount() = if (spots == null) 0 else spots!!.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.image
        val nameView: TextView = view.name
        val levelView: TextView = view.level
        val placeView: TextView = view.place
    }
}