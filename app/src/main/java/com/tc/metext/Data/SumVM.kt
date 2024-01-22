package com.tc.metext.Data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tc.metext.Domain.SumModel


private val sm= SumModel()
class SumVM: ViewModel() {
    var outxt by mutableStateOf("")
        private set


    suspend fun treatForAPI(txt:String){

        var ftx=txt.replace('\n', ' ')
//        println(outxt)
        outxt= sm.summarizeText(ftx)
    }

}