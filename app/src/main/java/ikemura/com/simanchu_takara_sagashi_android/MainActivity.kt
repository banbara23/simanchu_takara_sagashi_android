package ikemura.com.simanchu_takara_sagashi_android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ikemura.com.simanchu_takara_sagashi_android.ui.main.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

}
