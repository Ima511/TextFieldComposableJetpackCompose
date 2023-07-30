package com.example.textfieldcomposablejetpackcompose

import androidx.compose.runtime.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.textfieldcomposablejetpackcompose.ui.theme.TextFieldComposableJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldComposableJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    /*
    * TextField: Used to get the data from the user
    * Types of TextFields:
    *       1) TextFields
    *       2) OutlinedTextField
    *       3) BasicTextField
    * */

    var enteredValue by remember{
        mutableStateOf("")
    }

    var isUserBelow18 by remember{
        mutableStateOf(false)
    }

    var context = LocalContext.current.applicationContext


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(value = enteredValue,
            onValueChange = {newText -> enteredValue = newText},
            label = { Text(text = "Age", style = TextStyle(fontWeight = FontWeight.Bold))} ,
            placeholder = { Text(text = "Enter your age here", style = TextStyle(fontStyle = FontStyle.Italic))},
            leadingIcon = { IconButton(onClick = {
                Toast.makeText(context,"Clicked on Email Icon",Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
            }},
            isError = isUserBelow18,
            keyboardOptions =  KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    isUserBelow18 = validateAge(inputText = enteredValue)
                }
            ),
            trailingIcon = {
                IconButton(onClick = {
                    Toast.makeText(context,"Clicked on Close Icone", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }

        )
        if (isUserBelow18){
            Text(
                text = "You should be 18+",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

}

private fun validateAge(inputText: String): Boolean{
    return inputText.toInt() < 18
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextFieldComposableJetpackComposeTheme {
        Greeting("Android")
    }
}