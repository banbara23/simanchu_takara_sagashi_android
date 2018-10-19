package ikemura.com.simanchu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 場所名、座標
 */
@Parcelize
data class Location(
        val place: String = "",
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
) : Parcelable
