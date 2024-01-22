package com.tc.metext.Domain



import com.google.gson.Gson
import com.tc.metext.Model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.util.concurrent.TimeUnit

class ExtraModel {
    private val client = OkHttpClient.Builder()
        .readTimeout(300, TimeUnit.SECONDS)
        .writeTimeout(300, TimeUnit.SECONDS)
        .connectTimeout(300, TimeUnit.SECONDS)
        .build()
    private val gson = Gson()
    suspend fun Sentiment(input: String): String = withContext(Dispatchers.IO) {
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = """
{
    "language": "english",
    "text": "$input"
}
""".trimIndent().toRequestBody(mediaType)

        val request = Request.Builder()
            .url("https://text-analysis12.p.rapidapi.com/sentiment-analysis/api/v1.1")
            .post(body)
            .addHeader("content-type", "application/json")
            .addHeader("X-RapidAPI-Key", "43802b79fbmsh807e705a4347ca8p1c3ea7jsndfade0f9e1b6")
            .addHeader("X-RapidAPI-Host", "text-analysis12.p.rapidapi.com")
            .build()

        var response: Response? = null

        try {
            response = client.newCall(request).execute()
            val responseString = response.body?.string() ?: ""

            // Log the response string
//            Log.d("API_RESPONSE", responseString)

            val apiResponse = gson.fromJson(responseString, ApiResponse::class.java)

            // Retourne seulement le résumé
            return@withContext apiResponse.sentiment
        } catch (e: Exception) {
            // Gérer l'exception
            e.printStackTrace()
            return@withContext "Une erreur s'est produite lors de la récupération du résumé."
        } finally {
            response?.close() // Assurez-vous de fermer la réponse
        }
    }


}