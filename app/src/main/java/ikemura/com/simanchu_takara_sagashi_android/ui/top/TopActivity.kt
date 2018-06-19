package ikemura.com.simanchu_takara_sagashi_android.ui.top

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.ui.info.InfoFragment
import kotlinx.android.synthetic.main.top_activity.*

/**
 * メイン画面
 */
class TopActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //ホーム
                commitFragment(TopFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_info -> {
                //アプリ情報
                commitFragment(InfoFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    /**
     * onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_activity)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            commitFragment(TopFragment.newInstance())
        }
    }

    /**
     * Fragmentコミット
     */
    private fun commitFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
    }
}
