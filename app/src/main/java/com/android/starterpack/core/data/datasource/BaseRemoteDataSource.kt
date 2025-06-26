package com.android.starterpack.core.data.datasource

import android.util.Log
import com.android.starterpack.core.domain.DataError
import retrofit2.Response
import com.android.starterpack.core.domain.Result
import com.google.gson.JsonParseException
import java.io.IOException

/**
 * @Author: Abdul Rehman
 */
abstract class BaseRemoteDataSource {

    suspend fun <T> makeApiCall(apiCall: suspend () -> Response<T>): Result<T, DataError> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body)
                } else {
                    Result.Error(DataError.Remote.Unknown("Response body is null"))
                }
            } else {
                val error = mapHttpCodeToError(response.code())
                Result.Error(error)
            }

        } catch (e: IOException) {
            Result.Error(DataError.Remote.NoInternet)
        } catch (e: JsonParseException) {
            Result.Error(DataError.Remote.Serialization)
        } catch (e: Exception) {
            Log.e("BaseRepository", "Unhandled exception: ${e.message}", e)
            Result.Error(DataError.Remote.Unknown(e.localizedMessage ?: "Unknown error"))
        }
    }

    private fun mapHttpCodeToError(code: Int): DataError.Remote {
        return when (code) {
            408 -> DataError.Remote.RequestTimeout
            429 -> DataError.Remote.TooManyRequests
            in 500..599 -> DataError.Remote.Server
            else -> DataError.Remote.Unknown("HTTP $code")
        }
    }
}