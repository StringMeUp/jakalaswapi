package com.example.jakala_swapi.data.remotesource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

object RemoteSource {
    fun <T> launchResultFlow(apiResponse: suspend () -> Response<T>): Flow<Result<T>> =
        flow {
            emit(Result.Loading)
            val response = apiResponse.invoke()
            response.let {
                if (response.isSuccessful) {
                    emit(Result.Success(it.body()!!))
                } else {
                    emit(Result.Error())
                    println("Error stacktrace:: ${it.errorBody()} ")
                }
            }
        }.catch { httpErr ->
            println("Error stacktrace:: $httpErr ")
            emit(Result.Error(httpErr))
        }.flowOn(Dispatchers.IO)

    suspend fun <T> launchResult(apiResponse: suspend () -> Response<T>): Result<T> {
        return try {
            val response = apiResponse.invoke()
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                println("Error stacktrace:: ${response.errorBody()} ")
                Result.Error()
            }
        } catch (e: Exception) {
            println("Error stacktrace:: ${e.stackTrace} ")
            Result.Error()
        }
    }
}