package ikemura.com.android.common

internal class AppEnv private constructor() {
    lateinit var mainApplication: MainApplication
    lateinit var preferenceModel: PreferenceModel

    fun init(mainApplication: MainApplication) {
        this.mainApplication = mainApplication
        preferenceModel = PreferenceModel(mainApplication)
    }

    companion object {
        val instance = AppEnv()
    }
}
