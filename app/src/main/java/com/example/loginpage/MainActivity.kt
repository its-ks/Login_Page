package com.example.loginpage

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.ui.theme.LoginPageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()){
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login(){
    val focusManager= LocalFocusManager.current

    var username= remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }
    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(username.value).matches()
    }
    val isPasswordValid by derivedStateOf {
        password.value.length > 7
    }
    var isPasswordVisible = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome",
            color = Color.Blue,
            fontSize = 28.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Please login-",
            color = Color.Blue,
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )

        Image(
            painter = painterResource(id = R.drawable.pro) ,contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .size(200.dp)
                .clip(CircleShape)
                .fillMaxWidth()
                .padding(15.dp),
            alignment = Alignment.Center

        )
        Card(
            modifier = Modifier
                .offset(y = 20.dp)
                .width(290.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(25.dp),
            elevation = 15.dp
        ) {
            Column {

                OutlinedTextField(
                    value = username.value,
                    onValueChange = {
                        username.value = it
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = "username")
                    },
                    label = {
                        Text(text = "Username")
                    },
                    placeholder = {
                        Text(text = "Enter username")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    shape = RoundedCornerShape(15.dp),
                    isError = !isEmailValid,
                    trailingIcon = {
                        if(username.value.isNotEmpty()){
                            IconButton(onClick = { username.value = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Clear",
//                                modifier = Modifier.clickable(onClick = { /* handle clear button click */ })
                                )

                            }
                        }
                    }
                )
                OutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Info, contentDescription ="info" )
                    },
                    label = {
                        Text(text = "Password")
                    },
                    placeholder = {
                        Text(text = "Enter password")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(15.dp),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.clearFocus()
                        }
                    ),
                    isError = !isPasswordValid,
                    trailingIcon = {
                        IconButton(
                            onClick = { isPasswordVisible.value = !isPasswordVisible.value }
                        ) {
                            Icon(
                                imageVector = if(isPasswordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Password"
                            )

                        }

                    },
                    visualTransformation = if (isPasswordVisible.value) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }

                )
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Blue, Color(0xFFBF55EC)
                                ),
                            )
                        )
                ) {
                    Text(
                        text = "Login",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                        //.padding(8.dp)
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ){
            TextButton (onClick = {}) {
                Text(
                    color = Color.Black,
                    fontStyle = FontStyle. Italic,
                    text = "Forgot password?",
                    modifier = Modifier
                        .padding(end = 33.dp),
                    textAlign = TextAlign.End
                )
            }
        }

        Button(
            onClick={},
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            colors = ButtonDefaults.buttonColors (backgroundColor = Color.White),
            shape = RoundedCornerShape(25.dp)
        ){
            Text(
                text = "Register",
                modifier=Modifier.padding(bottom = 10.dp),
                //fontWeight = FontWeight. Bold,
                color = Color.Black,
                fontSize = 16.sp)}

    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginPageTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Login()
        }
    }
}
