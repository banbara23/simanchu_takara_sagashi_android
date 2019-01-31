package ikemura.com.simanchu_takara_sagashi_android

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.util.Random

fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) + start

/**
 * FragmentのViewModelを取得する
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(
    viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return viewModelFactory?.let {
        ViewModelProviders.of(this, it).get(T::class.java)
    } ?: ViewModelProviders.of(this).get(T::class.java)
}

fun <T : ViewDataBinding> Fragment.inflateWithBind(
    @LayoutRes layoutId: Int,
    container: ViewGroup?
): T {
    return DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
}

typealias BindingConfigurator<T> = (binding: T) -> Unit

fun <T : ViewDataBinding> Fragment.inflateWidBinding(
    @LayoutRes layoutId: Int,
    container: ViewGroup?,
    block: BindingConfigurator<T>): View =
        DataBindingUtil.inflate<T>(layoutInflater, layoutId, container, false)
                .also(block)
                .root