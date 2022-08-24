package uz.gita.mobilebankingcompose.utils

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.SimpleDateFormat
import java.util.*

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("mm:ss", Locale.getDefault())
    return format.format(date)
}

fun isPhoneNumber(phone: String): Boolean {
    return phone.startsWith("+998") &&
            phone.substring(1).matches("^[0-9]*$".toRegex()) &&
            phone.length == 13
}

fun String.isPhoneNumber(phone: String): Boolean = phone.startsWith("+998") &&
        phone.substring(1).matches("^[0-9]*$".toRegex()) &&
        phone.length == 13

fun toPhoneNumber(phoneNumber: String) = phoneNumber.substring(4, phoneNumber.length)
fun toPhoneNumberUI(phoneNumber: String): String {
    return "" +
            "+998 " +
            "${phoneNumber.substring(0, 2)} " +
            "${phoneNumber.substring(2, 5)} " +
            "${phoneNumber.substring(5, 7)} " +
            phoneNumber.substring(7, 9)
}
fun phoneNumberFilter(text: AnnotatedString): TransformedText {
    val mask = "+998 00 000 00 00"
    // change the length
    val trimmed =
        if (text.text.length >= 13) text.text.substring(0..12) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 3 || i == 5 || i == 8 || i == 10) {
                append(" ")
            }
        }
        pushStyle(SpanStyle(color = Color.LightGray))
        append(mask.takeLast(mask.length - length))
        toAnnotatedString()
    }

//    private const val mask = "+998 00 000 00 00"
//    private const val test = "0123 45 678 9X 00"

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 5) return offset + 1
            if (offset <= 8) return offset + 2
            if (offset <= 10) return offset + 3
            if (offset <= 12) return offset + 4
            return 17
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 5) return offset - 1
            if (offset <= 8) return offset - 2
            if (offset <= 10) return offset - 3
            if (offset <= 12) return offset - 4
            return 13
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}

@Composable
fun PasswordTrailingIcon(onIconClick: () -> Unit, passwordVisible: Boolean) {
    val image = if (passwordVisible) Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff
    // Please provide localized description for accessibility services
    val description =
        if (passwordVisible) "Hide password" else "Show password"

    IconButton(onClick = { onIconClick.invoke() }) {
        Icon(imageVector = image, description)
    }
}

fun passwordVisualTransformation(passwordVisible: Boolean): VisualTransformation {
    return if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
}

@Composable
fun SetLanguage(language: String) {
    val locale = Locale(language)
    val configuration = LocalConfiguration.current
    configuration.setLocale(locale)
    val resources = LocalContext.current.resources
    resources.updateConfiguration(configuration, resources.displayMetrics)
    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        LocalContext.current.createConfigurationContext(configuration)
    } else {
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }*/
}