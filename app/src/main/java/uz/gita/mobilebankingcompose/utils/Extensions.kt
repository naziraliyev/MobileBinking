package uz.gita.mobilebanking.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("mm:ss", Locale.getDefault())
    return format.format(date)
}