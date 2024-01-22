package com.tc.metext.Domain

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.FileInputStream
import java.util.concurrent.TimeUnit


class DTTModel {
    private val client = OkHttpClient.Builder()
        .readTimeout(300, TimeUnit.SECONDS)
        .writeTimeout(300, TimeUnit.SECONDS)
        .connectTimeout(300, TimeUnit.SECONDS)
        .build()

    suspend fun sendFileToApi(context: Context, uri: Uri): String = withContext(Dispatchers.IO) {

        val contentResolver = context.contentResolver
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileInputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
        val fileName = DocumentsContract.getDocumentId(uri) + ".pdf"
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("input_file", fileName, RequestBody.create("multipart/form-data".toMediaTypeOrNull(), fileInputStream.readBytes()))
            .addFormDataPart("language", "english")
            .build()

        val request = Request.Builder()
            .url("https://text-analysis12.p.rapidapi.com/text-mining/api/v1.1")
            .post(requestBody)
            .addHeader("X-RapidAPI-Key", "43802b79fbmsh807e705a4347ca8p1c3ea7jsndfade0f9e1b6")
            .addHeader("X-RapidAPI-Host", "text-analysis12.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()

        val responseBody = response.body?.string() ?: ""
//        Log.d("API_RESPONSE", responseBody)
        val json = JSONObject(responseBody)

        // Return only the 'text' field from the JSON
        json.optString("text", "")
    }


}