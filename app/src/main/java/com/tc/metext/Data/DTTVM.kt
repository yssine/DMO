package com.tc.metext.Data

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tc.metext.Domain.DTTModel

private val dttm= DTTModel()
class DTTVM : ViewModel() {
    var outxt by mutableStateOf("")
        private set
    suspend fun HandleFile(context: Context, uri: Uri){
        outxt= dttm.sendFileToApi(context,uri)
    }

//
}