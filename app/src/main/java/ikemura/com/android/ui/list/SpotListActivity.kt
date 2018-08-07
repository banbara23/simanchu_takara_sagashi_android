package ikemura.com.android.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import ikemura.com.android.R
import kotlinx.android.synthetic.main.spot_list_activity.*

class SpotListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
//    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spot_list_activity)

        setSupportActionBar(toolbar)
        toolbar.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        if (item_detail_container != null) {
//            twoPane = true
//        }
        if (savedInstanceState == null) {
            setupFragment()
        }
    }

    private fun setupFragment() {
        val fragment = SpotListFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .add(R.id.activity_list_container, fragment)
                .commit()
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
