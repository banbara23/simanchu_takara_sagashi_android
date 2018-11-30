package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 * スポット詳細 Fragment
 */
class SpotDetailFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: SpotDetailViewModel
    private var isFavorite = false
    private val TAG: String = SpotDetailFragment::class.java.simpleName
    private var spot: Spot? = null
        get() = arguments?.getParcelable(Constants.ARG_SPOT)
    private lateinit var googleMap: GoogleMap

    companion object {
        private const val MAP_ZOOM: Float = 10F
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
        setupMap()
    }

    private fun setupMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment = requireActivity().supportFragmentManager
//                .findFragmentById(R.id.map) as SupportMapFragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

    override fun onMapReady(_googleMap: GoogleMap) {
        googleMap = _googleMap
        val lat: Double = spot?.location?.first()!!.latitude
        val log: Double = spot?.location?.first()!!.longitude

        val sydney = LatLng(lat, log)
        googleMap.setMinZoomPreference(MAP_ZOOM)    //ズーム
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney)) //カメラ位置
        googleMap.addMarker(MarkerOptions().position(sydney))   //マーカー
        googleMap.setOnMarkerClickListener {
            //マーカークリック
            openGoogleMapApp(lat, log)
            false
        }
        googleMap.setOnMapClickListener { openGoogleMapApp(lat, log) } //マップクリック
    }

    /**
     * GoogleMapアプリを開く
     */
    private fun openGoogleMapApp(latLng: Double, longitude: Double) {

        var url = "geo:0,0?z=${Constants.DEFAULT_ZOOM}&q=$latLng,$longitude"

        try {
            val qs = "(" + URLEncoder.encode(spot?.name, "UTF-8") + ")"
            url += qs
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        Log.d(TAG, url)

        val gmmIntentUri = Uri.parse(url)

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        requireActivity().startActivity(mapIntent)
    }
}
