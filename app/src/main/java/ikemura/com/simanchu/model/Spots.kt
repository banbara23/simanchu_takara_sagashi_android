package ikemura.com.simanchu.model

import com.google.gson.annotations.SerializedName

/**
 * スポット一覧
 */
data class Spots(
        @SerializedName("spots")
        var spots: List<Spot> = listOf()
)