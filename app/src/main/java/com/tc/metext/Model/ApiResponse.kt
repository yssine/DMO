package com.tc.metext.Model

data class ApiResponse(
    val app_version: String,
    val time_taken: Double,
    val msg: String,
    val ok: Boolean,
    val sentence_count: Int,
    val summary: String,
    val sentiment: String,
    val sentences: List<String>
)
