package ikemura.com.simanchu_takara_sagashi_android.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SpotDetailViewModel : ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    init {
        _data.value = "Hello, Jetpack!"
    }
}
