package ikemura.com.simanchu_takara_sagashi_android.ui.top

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.ui.favorite.FavoriteListFragment
import ikemura.com.simanchu_takara_sagashi_android.ui.info.InfoFragment
import kotlinx.android.synthetic.main.top_activity.navigation

/**
 * メイン画面
 */
class TopActivity : AppCompatActivity() {
    private var selectedMenuItem = R.id.navigation_home

    //ボトムメニューの選択リスナー
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        //選択中のボトムアイテムを再選択したら画面を切り替えない
        if (selectedMenuItem == item.itemId) {
            return@OnNavigationItemSelectedListener false
        }
        //違うボトムアイテムを選択された時
        selectedMenuItem = item.itemId
        when (item.itemId) {
            R.id.navigation_home -> {
                //ホーム
                commitFragment(TopFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                //お気に入り
                commitFragment(FavoriteListFragment.newInstance())
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
        FirebaseAnalytics.getInstance(this)

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
