/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.whole.wearosinterview.presentation

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.whole.wearosinterview.R
import com.whole.wearosinterview.presentation.theme.WearOsInterviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginForm(errorMessage: String, onSubmit: (String, String) -> Unit) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable { mutableStateOf("") }

    WearOsInterviewTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Login",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
            )


            Text(
                text = "Username", // Your hint text
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp, 1.dp, 1.dp, 1.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Left,
                fontSize = 10.sp,
            )
            BasicTextField(
                value = email,
                onValueChange = { newEmail ->
                    if (!TextUtils.isEmpty(newEmail)) {
                        email = newEmail
                    }
//                    email = email.plus(newEmail)
                    Log.d("TAG", "LoginForm: $email")
                },
                textStyle = TextStyle(fontSize = 10.sp, color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(3.dp)
                    )
                    .padding(4.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
            )

            Text(
                text = "Password", // Your hint text
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp, 1.dp, 1.dp, 1.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Left,
                fontSize = 10.sp,
            )
            BasicTextField(
                value = password,
                onValueChange = {
                    if (!TextUtils.isEmpty(it)) {
                        password = it
                    }
                },
                textStyle = TextStyle(fontSize = 10.sp, color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(3.dp)
                    )
                    .padding(4.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation()

            )

            if (!TextUtils.isEmpty(errorMessage)) {
                Text(
                    text = errorMessage,
                    fontSize = 10.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left,
                    color = Color.Red
                )
            }

            Button(
                onClick = {
                    onSubmit(email, password)
                },
                modifier = Modifier
                    .width(80.dp)
                    .height(20.dp)
                    .padding(1.dp)


            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = Color.White,
                    fontSize = 10.sp
                )
            }

            Text(
                text = "Don't have an account Sign Up.", // Your hint text
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 8.sp
            )

        }


    }
}


@Composable
fun LoginScreen() {
    var loginSuccessful by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    if (loginSuccessful) {
        DashboardScreen()
    } else {
        LoginForm(errorMessage) { username, password ->
            if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(username)
            ) {
                loginSuccessful = true
            } else {
                errorMessage = "Invalid username or password"
            }

        }
    }
}


@Composable
fun DashboardScreen() {
    Text(
        text = "Bluetooth Devices",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )


}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LoginScreen()
}