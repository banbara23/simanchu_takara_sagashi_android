package ikemura.com.simanchu_takara_sagashi_android

import java.util.Random

fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) + start