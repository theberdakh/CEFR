package com.example.cefr.utils

import okhttp3.ResponseBody
import org.json.JSONObject

object ErrorObjectMessage {

    fun getErrorObjectMessage(responseBody: ResponseBody?): String {
        return try {
            val errorObj = responseBody?.charStream()?.readText()
                ?.let { JSONObject(it) }
            errorObj?.getJSONObject("data")?.getString("error")
                ?: SMTH_WENT_WRONG
        } catch (e: Exception) {
            e.localizedMessage ?: SMTH_WENT_WRONG
        }
    }

    const val SMTH_WENT_WRONG = "Something went wrong"
}