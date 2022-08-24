package uz.gita.mobilebankingcompose.utils.outlinedTextField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uz.gita.mobilebankingcompose.R

@Composable
fun AppOutlinedTextField(
value:String,
statusApp:Boolean,
focusState:Boolean

){
//    OutlinedTextField(
//        value = value,
//        singleLine = true,
//        placeholder = { Text("+998 00-000-00-00") },
//        onValueChange = { phone ->
//            statusApp = phone.length == 13
//            statusApp = phone.startsWith("+998") &&
//                    phone.substring(1).matches("^[0-9]*$".toRegex()) &&
//                    phone.length == 13
//            value = phone
//        },
//        /*visualTransformation = { mobileNumberFilter(it) },*/
//        modifier = Modifier
//            .fillMaxWidth()
//            .onFocusChanged { focusState = it.isFocused },
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = Color.Green,
//            unfocusedBorderColor = Color.Transparent,
//            backgroundColor = when (focusState) {
//                false -> colorResource(id = R.color.backText)
//                else -> colorResource(id = R.color.visibleTextColor)
//            }
//        ),
//        shape = RoundedCornerShape(12.dp),
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone,imeAction = ImeAction.Next),
//
//
//        )

}