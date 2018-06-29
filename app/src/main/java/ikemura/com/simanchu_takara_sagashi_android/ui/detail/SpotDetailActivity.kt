package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.ui.list.SpotListActivity
import kotlinx.android.synthetic.main.spot_detail_activity.*

/**
 * スポット詳細のActivity
 */
class SpotDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spot_detail_activity)
        setSupportActionBar(detail_toolbar)

//        fab.setOnClickListener { view ->
//
//            isFavorite = !isFavorite
//            val drawable = if (isFavorite) {
//                R.drawable.ic_favorite_black_24dp
//            } else {
//                R.drawable.ic_favorite_border_black_24dp
//            }
//
//            fab.setImageDrawable(ContextCompat.getDrawable(this, drawable))
//
//            Snackbar.make(view, "お気に入り登録/解除しました", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = SpotDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.ARG_ITEM_ID,
                            intent.getStringExtra(Constants.ARG_ITEM_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.activity_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    NavUtils.navigateUpTo(this, Intent(this, SpotListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
