package com.nolions.passwordtextfieldpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nolions.passwordtextfieldpractice.ui.theme.PasswordTextFieldPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordTextFieldPracticeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val text = remember { mutableStateOf("") }

                    PasswordTextField(
                        text = text.value,
                        labelText = "password",
                        description = "description",
                        onTextChanged = {
                            text.value = it
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PasswordTextField(
    text: String,
    labelText: String = "",
    description: String = "",
    onTextChanged: (text: String) -> Unit = {},
    isError: Boolean = false
) {
    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier
//                .background(color = colorResource(id = R.color.design_default_color_primary))
                .fillMaxWidth()
                .semantics { contentDescription = description },
            value = text,
            onValueChange = onTextChanged,
            placeholder = {
                Text(
                    text = labelText,
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            visualTransformation = visualPassword(showPassword.value),
            trailingIcon = {
                val (icon, iconColor) = setIconColor(showPassword.value)
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                textColor = Color.Black,
                cursorColor = Color.Black,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

/**
 * Icon 樣式&顏色
 *
 * @param isVisual
 * @return Pair<ImageVector, Color>
 */
@Composable
private fun setIconColor(isVisual: Boolean): Pair<ImageVector, Color> {
    return if (isVisual) {
        Pair(
            Icons.Filled.Visibility,
            colorResource(id = R.color.black)
        )
    } else {
        Pair(Icons.Filled.VisibilityOff, colorResource(id = R.color.black))
    }
}

/**
 * Password是否顯示
 *
 * @param isVisual
 * @return VisualTransformation
 */
private fun visualPassword(isVisual: Boolean) = if (isVisual) {
    VisualTransformation.None
} else {
    PasswordVisualTransformation()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PasswordTextFieldPracticeTheme {
        val text = remember { mutableStateOf("") }

        PasswordTextField(
            text = text.value,
            labelText = "password",
            description = "description",
            onTextChanged = {
                text.value = it
            },
            isError = true
        )
    }
}
