package com.example.loginpage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
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

class Register_Page : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier
                    // .fillMaxHeight()
                    .fillMaxWidth()
                    //.padding(28.dp)
                )
                {
                    Register(this@Register_Page)
                }
            }
        }
    }
}

@Composable
fun Register(context: Context? = null){
    val focusManager= LocalFocusManager.current

    var FirstName= remember {
        mutableStateOf("")
    }
    var LastName= remember {
        mutableStateOf("")
    }
    var username= remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }
    var reEnter = remember {
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

    val isreEnterValid by derivedStateOf {
        reEnter.value.length > 7
    }
    var isreEnterVisible = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register",
            color = Color.Blue,
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "New User",
            color = Color.Blue,
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )

        Image(
            painter = painterResource(id = R.drawable.regg) ,contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .size(280.dp)
                .clip(CircleShape)
                .fillMaxWidth(),
            alignment = Alignment.Center
        )
        Column{
            OutlinedTextField(
                value = FirstName.value,
                onValueChange = {
                    FirstName.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "First Name")
                },
                label = {
                    Text(text = "First Name")
                },
                placeholder = {
                    Text(text = "Enter First Name")
                },
                modifier = Modifier
                    //.size(400.dp)
                    .fillMaxWidth()
                    .padding(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                shape = RoundedCornerShape(5.dp),

                )
            OutlinedTextField(
                value = LastName.value,
                onValueChange = {
                    LastName.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "Last Name")
                },
                label = {
                    Text(text = "Last Name")
                },
                placeholder = {
                    Text(text = "Enter Last Name")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                shape = RoundedCornerShape(5.dp),

                )
            OutlinedTextField(
                value = username.value,
                onValueChange = {
                    username.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.AttachEmail, contentDescription = "username")
                },
                label = {
                    Text(text = "E-Mail")
                },
                placeholder = {
                    Text(text = "Enter E-Mail")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                shape = RoundedCornerShape(5.dp),
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
                    .padding(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(5.dp),
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
            OutlinedTextField(
                value = reEnter.value,
                onValueChange = {
                    reEnter.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Info, contentDescription ="info" )
                },
                label = {
                    Text(text = "Re-Enter")
                },
                placeholder = {
                    Text(text = "Re-Enter")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 5.dp , end = 5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(5.dp),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.clearFocus()
                    }
                ),
                isError = !isreEnterValid,
                trailingIcon = {
                    IconButton(
                        onClick = { isreEnterVisible.value = !isreEnterVisible.value }
                    ) {
                        Icon(
                            imageVector = if(isreEnterVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Password"
                        )

                    }

                },
                visualTransformation = if (isreEnterVisible.value) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }

            )
            Button(
                onClick = {
                    if(username.value.length==0 && password.value.length==0 && reEnter.value.length==0)
                    {
                        Toast.makeText(context,"Enter details",Toast.LENGTH_SHORT).show() }

                    else if(username.value.length==0 && (password.value.length!=0 || reEnter.value.length!=0))
                    {
                        Toast.makeText(context,"Enter Username",Toast.LENGTH_SHORT).show() }

                    else if(password.value.length==0 && (username.value.length!=0 || reEnter.value.length!=0))
                    {
                        Toast.makeText(context,"Enter password",Toast.LENGTH_SHORT).show() }

                    else if(password.value==reEnter.value)
                    {
                        Toast.makeText(context,"Sign Up!",Toast.LENGTH_SHORT).show()
                    }
                    else if(password.value!=reEnter.value)
                    {
                        Toast.makeText(context,"Password mismatch",Toast.LENGTH_SHORT).show()
                    }


                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)


            ) {
                Text(
                    text = "SIGN UP",
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


}

@Preview(showBackground = true)
@Composable
fun Previeww() {
    LoginPageTheme {
        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth())
        {
            Register(null)
        }
    }
}
