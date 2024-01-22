package com.tc.metext.Data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tc.metext.Domain.ExtraModel


private val em= ExtraModel()
class ExtraVM: ViewModel() {
    var outxt by mutableStateOf("")
        private set


    suspend fun treatForAPI(txt:String){

        var mtxt=txt.replace('\n', ' ')
//        println(outxt)
        outxt= em.Sentiment(mtxt)
    }
}