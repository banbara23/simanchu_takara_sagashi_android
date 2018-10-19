package ikemura.com.simanchu.model

/**
 * スポット
 */
data class Spot(
        val id: String = "",
        val name: String = "",
        val description: String = "",
        val level: Int = 0,
        val address: String = "",
        val location: List<Location> = listOf(),
        val image: String = "",
        val thumbnail: String = ""
)