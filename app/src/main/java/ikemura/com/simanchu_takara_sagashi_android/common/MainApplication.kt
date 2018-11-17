package ikemura.com.simanchu_takara_sagashi_android.common

import android.app.Application
import android.content.Context

class MainApplication : Application() {
    private lateinit var appEnv: AppEnv
    override fun onCreate() {
        super.onCreate()

        appEnv = AppEnv.instance
        appEnv.init(this)
    }

    fun getContext(): Context {
        return this
    }
}