package ikemura.com.simanchu_takara_sagashi_android.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.model.Spot
import ikemura.com.simanchu_takara_sagashi_android.ui.detail.SpotDetailFragment
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
                val fragment = SpotDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(Constants.ARG_ITEM_ID, spot.id)
                    }
                }
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
//        Picasso.get().load(item.).into(holder.imageView)
        holder.nameView.text = item.name
        holder.levelView.text = item.level
        holder.placeView.text = item.place

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
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