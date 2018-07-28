package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.ui.list.SpotListActivity

/**
 * スポット詳細のActivity
 */
class SpotDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spot_detail_activity)

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
