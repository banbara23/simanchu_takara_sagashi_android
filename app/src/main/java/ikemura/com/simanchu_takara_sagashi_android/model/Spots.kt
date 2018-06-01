package ikemura.com.simanchu_takara_sagashi_android.model

import com.google.gson.annotations.SerializedName

data class Spots(
        @SerializedName("spot")
        var spots: List<Spot> = listOf()
)