package com.tc.metext.Data

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tc.metext.Domain.DTTModel

import kotlinx.coroutines.launch
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

private val dttm= DTTModel()
class DTTVM : ViewModel() {
    var outxt by mutableStateOf("")
        private set
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        // Handle the returned Uri
        coroutineScope.launch {
            val response = uri?.let { dttm.sendFileToApi(context, it) }
            if (response != null) {
                result = response.toString() // Update the result state with the response
            }
        }
    }

//
}