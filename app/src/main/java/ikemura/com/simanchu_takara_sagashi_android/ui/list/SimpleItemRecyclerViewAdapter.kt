package ikemura.com.simanchu_takara_sagashi_android.ui.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.model.Spot
import ikemura.com.simanchu_takara_sagashi_android.ui.detail.SpotDetailActivity
import ikemura.com.simanchu_takara_sagashi_android.ui.detail.SpotDetailFragment
import kotlinx.android.synthetic.main.list_item_content.view.*

class SimpleItemRecyclerViewAdapter(private val parentActivity: SpotListActivity,
                                    private val spots: List<Spot>,
                                    private val twoPane: Boolean) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Spot
            if (twoPane) {
                val fragment = SpotDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(SpotDetailFragment.ARG_ITEM_ID, item.id)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, SpotDetailActivity::class.java).apply {
                    putExtra(SpotDetailFragment.ARG_ITEM_ID, item.id)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = spots[position]
//        Picasso.get().load("1").into(holder.imageView)
        holder.nameView.text = item.name
        holder.levelView.text = item.level
        holder.placeView.text = item.place

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = spots.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.image
        val nameView: TextView = view.name
        val levelView: TextView = view.level
        val placeView: TextView = view.place
    }
}