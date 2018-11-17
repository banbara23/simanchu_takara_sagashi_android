package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ikemura.com.simanchu_takara_sagashi_android.Constants
import ikemura.com.simanchu_takara_sagashi_android.R
import ikemura.com.simanchu_takara_sagashi_android.helper.LevelViewHelper
import ikemura.com.simanchu_takara_sagashi_android.model.Spot
import ikemura.com.simanchu_takara_sagashi_android.ui.fullscreen.FullscreenActivity
import kotlinx.android.synthetic.main.spot_detail_fragment.description
import kotlinx.android.synthetic.main.spot_detail_fragment.detail_image
import kotlinx.android.synthetic.main.spot_detail_fragment.detail_level
import kotlinx.android.synthetic.main.spot_detail_fragment.detail_place
import kotlinx.android.synthetic.main.spot_detail_fragment.detail_title
import kotlinx.android.synthetic.main.spot_detail_fragment.detail_toolbar
import kotlinx.android.synthetic.main.spot_detail_fragment.favorite

/**
 * スポット詳細 Fragment
 */
class SpotDetailFragment : Fragment() {

    private lateinit var viewModel: SpotDetailViewModel
    private var isFavorite = false
    private val TAG: String = SpotDetailFragment::class.java.simpleName
    private var spot: Spot? = null
        get() = arguments?.getParcelable(Constants.ARG_SPOT)

    companion object {
        fun newInstance(args: Bundle?): SpotDetailFragment {
            return SpotDetailFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.spot_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupClickClick()
        setupFavorite()
    }

    private fun setupClickClick() {
        // toolbarの戻るボタン
        detail_toolbar.setNavigationOnClickListener {
            //            activity?.supportPostponeEnterTransition()
            activity?.run {
                ActivityCompat.finishAfterTransition(this)
            }
        }
        // 画像タップで全画面
        detail_image.setOnClickListener {
            startActivity(Intent(requireActivity(), FullscreenActivity::class.java))
        }
        // お気に入りタップ
        favorite.setOnClickListener { view ->
            isFavorite = !isFavorite
            val status: String
            val drawable = if (isFavorite) {
                viewModel.addFavorite(spot!!.id)
                status = "登録"
                R.drawable.ic_favorite_black_24dp
            } else {
                viewModel.removeFavorite(spot!!.id)
                status = "解除"
                R.drawable.ic_favorite_border_black_24dp
            }

            favorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawable))

            Snackbar.make(view, "お気に入りを${status}しました", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(SpotDetailViewModel::class.java)
        // 詳細データが取得できたら
        spot?.let {
            Picasso.get().load(it.image).into(detail_image)
            detail_title.text = it.name
            LevelViewHelper.setLevelTextView(requireContext(), it.level, detail_level)
            detail_place.text = "場所：${it.location.first().place}"
            description.text = it.description
        }
    }

    /**
     * お気に入り表示設定
     */
    private fun setupFavorite() {
        isFavorite = viewModel.isFavorite(spot!!.id)
        val drawable = if (isFavorite) {
            viewModel.addFavorite(spot!!.id)
            R.drawable.ic_favorite_black_24dp
        } else {
            viewModel.removeFavorite(spot!!.id)
            R.drawable.ic_favorite_border_black_24dp
        }
        favorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawable))
    }
}
