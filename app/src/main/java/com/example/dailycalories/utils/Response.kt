package com.example.dailycalories.utils

sealed class Response<out T> {

    //Success state
    data class Success<T>(val data: T) : Response<T>()

    //Failed state
    data class Failed<T>(val message: String) : Response<T>()


    companion object {

        //GET SUCCESS STATE
        fun <T> success(data: T) = Success(data)

        //GET FAILED STATE
        fun <T> failed(message: String) = Failed<T>(message)

    }

}