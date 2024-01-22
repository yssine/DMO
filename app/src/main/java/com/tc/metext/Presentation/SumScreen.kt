package com.tc.metext.Presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.tc.metext.Data.SumVM
import com.tc.metext.R
import kotlinx.coroutines.launch

private val svm = SumVM()

val Salsa = FontFamily(Font(R.font.salsa_regular))
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SumScreen(navController: NavHostController) {
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }
    var clipboardText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxSize()
//    ) {
//        // Text Input
//
//
//        // Text Output
//
//
//        // Buttons Row
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            // Copy Button
//
//        }
//    }
//





    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Summarize your text",
            fontSize = 20.sp,
            fontFamily = Salsa,
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = inputText,
            onValueChange = {
                inputText = it
                // Perform any processing or transformation based on the input
                outputText = "Processed: $it"
            },
            label = { Text("Input Text") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // You can handle the 'Done' action if needed
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .heightIn(160.dp,160.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {svm.viewModelScope.launch {svm.treatForAPI(inputText)}
        //            svm.treatForAPI(inputText)
        }) {
            Text(
                text = "Summarize",
            )
        }


        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result output:",
            fontSize = 20.sp,
            fontFamily = Salsa,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = svm.outxt,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(300.dp,300.dp)
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.onTertiary),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Copy Button
            Button(onClick = { clipboardManager.setText(AnnotatedString((svm.outxt)))
            }) {
                Text(
                    text = "Copy to clipboard",
                )
        }

            Button(onClick = {navController.navigate(Screen.Home.rout )
            }) {
                Text(
                    text = "Go To Home",
                )
        }

        }

    }
}













