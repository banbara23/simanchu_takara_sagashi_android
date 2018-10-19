package ikemura.com.simanchu.helper

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import ikemura.com.simanchu.R

/**
 * 難易度のTextViewを設定するヘルパー
 */
object LevelViewHelper {
    fun setLevelTextView(context: Context, level: Int, textView: TextView) {
        //値セット
        textView.text = level.toString()
        //色セット
        textView.setTextColor(getLevelTextColor(context, level))
    }

    private fun getLevelTextColor(context: Context, level: Int): Int {
        return when (level) {
            1 -> ContextCompat.getColor(context, R.color.level_1)
            2 -> ContextCompat.getColor(context, R.color.level_2)
            3 -> ContextCompat.getColor(context, R.color.level_3)
            4 -> ContextCompat.getColor(context, R.color.level_4)
            5 -> ContextCompat.getColor(context, R.color.level_5)
            else -> {
                R.color.black
            }
        }
    }
}