package ikemura.com.simanchu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * スポット
 */
@Parcelize
data class Spot(
        val id: String = "",
        val name: String = "",
        val description: String = "",
        val level: Int = 0,
        val address: String = "",
        val location: @RawValue List<Location> = listOf(),
        val image: String = "",
        val thumbnail: String = ""
) : Parcelable