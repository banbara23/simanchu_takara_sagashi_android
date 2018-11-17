package ikemura.com.simanchu.ui.info

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import ikemura.com.simanchu.BuildConfig
import ikemura.com.simanchu.R
import kotlinx.android.synthetic.main.info_fragment.site
import kotlinx.android.synthetic.main.info_fragment.version_name
import kotlinx.android.synthetic.main.info_fragment.youtube

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        version_name.text = "Version ${BuildConfig.VERSION_NAME}"

        youtube.setOnClickListener {
            startCustomTab("https://youtu.be/VBWG-vBVFEY")
        }
        site.setOnClickListener {
            startCustomTab("http://www.city.ishigaki.okinawa.jp/matome/shima.html")
        }
    }

    /**
     * カスタムタブ起動
     */
    private fun startCustomTab(url: String) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()

        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(activity, Uri.parse(url))
    }
}
