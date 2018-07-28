package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.ui.list.SpotListActivity
import kotlinx.android.synthetic.main.spot_detail_activity.*

/**
 * スポット詳細のActivity
 */
class SpotDetailActivity : AppCompatActivity() {

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spot_detail_activity)
//        setSupportActionBar(detail_toolbar)

//        favorite.setOnClickListener { view ->
//
//            isFavorite = !isFavorite
//            val status: String
//            val drawable = if (isFavorite) {
//                status = "登録"
//                R.drawable.ic_favorite_black_24dp
//            } else {
//                status = "解除"
//                R.drawable.ic_favorite_border_black_24dp
//            }
//
//            favorite.setImageDrawable(ContextCompat.getDrawable(this, drawable))
//
//            Snackbar.make(view, "お気に入りを${status}しました", Snackbar.LENGTH_SHORT)
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
