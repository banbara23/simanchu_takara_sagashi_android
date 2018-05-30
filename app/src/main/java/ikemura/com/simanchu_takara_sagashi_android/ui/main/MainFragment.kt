package ikemura.com.simanchu_takara_sagashi_android.ui.main.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.R.id.topImage
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//        val text = viewModel.data.value
//        title.text = text
        setClickEvent()
    }

    private fun setClickEvent() {
        //カード1のクリック処理
        include_card1.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailFragment)
        }
        //カード2のクリック処理
        include_card2.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailFragment)
        }
        //カード3のクリック処理
        include_card3.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        topImage.resume()
    }

    override fun onPause() {
        super.onPause()
        topImage.pause()
    }
}
