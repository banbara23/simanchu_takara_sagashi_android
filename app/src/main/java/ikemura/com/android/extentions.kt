package ikemura.com.android

import java.util.Random

fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) + start