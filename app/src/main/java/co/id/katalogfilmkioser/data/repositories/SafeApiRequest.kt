package co.id.katalogfilmkioser.data.repositories

import android.util.Log
import retrofit2.Response
import java.io.IOException

/**
 * Created by Sainal Sultan on 6/18/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(response.code().toString())
        }
    }
}

class ApiException(message: String) : IOException(message)