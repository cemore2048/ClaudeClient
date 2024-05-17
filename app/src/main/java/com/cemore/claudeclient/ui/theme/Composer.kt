package com.cemore.claudeclient.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Composer(
    modifier: Modifier = Modifier,
    textFieldValue: TextFieldValue = TextFieldValue(),
    startIcon: @Composable (() -> Unit)? = null,
    placeHolderLabel: @Composable (() -> Unit)? = null,
    onTextChanged: (TextFieldValue) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchFieldColor: Color = Color.LightGray

    Row(
        modifier = modifier
            .background(searchFieldColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp)
            .defaultMinSize(minHeight = 56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (startIcon != null) {
            startIcon.invoke()
            Spacer(modifier = Modifier.width(8.dp))
        }
        BasicTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                onTextChanged(newValue)
            },
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .weight(1f),
            textStyle = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            ),
            cursorBrush = SolidColor(Color.White),
            decorationBox = { innerTextField ->
                if (textFieldValue.text.isEmpty()) {
                    placeHolderLabel?.invoke()
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
            }),
        )
    }
}

@Composable
fun ComposerHint(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        //color = MaterialTheme.colors.textSecondary,
        //fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun ComposerPreview() {
    Composer(
        placeHolderLabel = {
            Text("hello")
        }
    ) {

    }
}