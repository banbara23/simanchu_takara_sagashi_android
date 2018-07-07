package ikemura.com.simanchu_takara_sagashi_android.ui.info

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ikemura.com.simanchu_takara_sagashi_android.BuildConfig
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.ui.top.TopViewModel
import kotlinx.android.synthetic.main.info_fragment.*

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var viewModel: TopViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        version_name.text = "Version ${BuildConfig.VERSION_NAME}"

        youtube.setOnClickListener({
            startCustomTab("https://youtu.be/VBWG-vBVFEY")

        })
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
