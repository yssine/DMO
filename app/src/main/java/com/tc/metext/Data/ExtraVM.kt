package com.tc.metext.Data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ExtraVM: ViewModel() {
    var outxt by mutableStateOf("")
        private set


    fun treatForAPI(txt:String){

        outxt=txt.replace('\n', ' ')
//        println(outxt)

    }
}