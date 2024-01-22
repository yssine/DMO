package com.tc.metext.Presentation


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tc.metext.Data.DTTVM
import kotlinx.coroutines.launch


private val dvm = DTTVM()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DTTScreen(navController: NavHostController) {
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }



    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    var result by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        // Handle the returned Uri
        coroutineScope.launch {
            val response = uri?.let { dvm.HandleFile(context, it) }
            if (response != null) {
                result = response.toString() // Update the result state with the response
            }
        }
    }



    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Extract text from your document",
            fontSize = 20.sp,
            fontFamily = Salsa,
        )

//        Spacer(modifier = Modifier.height(10.dp))

//
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { launcher.launch("application/pdf") }) {
            Text("Extract Text")
        }
        Spacer(modifier = Modifier.height(16.dp))



        Text(
            text = "Result output:",
            fontSize = 20.sp,
            fontFamily = Salsa,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = dvm.outxt,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(485.dp,485.dp)
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.onTertiary),
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(3.dp),
//            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Copy Button
            Button(onClick = { clipboardManager.setText(AnnotatedString((dvm.outxt)))
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














