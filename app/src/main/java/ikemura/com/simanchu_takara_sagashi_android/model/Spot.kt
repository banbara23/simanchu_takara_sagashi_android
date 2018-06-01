package ikemura.com.simanchu_takara_sagashi_android.model

import com.google.gson.annotations.SerializedName

data class Spot(
        @SerializedName("address")
        var address: String = "",
        @SerializedName("description")
        var description: String = "",
        @SerializedName("latitude")
        var latitude: String = "",
        @SerializedName("latitude2")
        var latitude2: String = "",
        @SerializedName("level")
        var level: String = "",
        @SerializedName("longitude")
        var longitude: String = "",
        @SerializedName("longitude2")
        var longitude2: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("id")
        var id: String = "",
        @SerializedName("place")
        var place: String = "",
        @SerializedName("place2")
        var place2: String = ""
)