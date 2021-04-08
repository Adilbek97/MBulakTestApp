package com.example.mbulaktestapp.ui

sealed class ResultState<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultState<T>()
    data class Error(val exception: String): ResultState<Nothing>()
    data class ErrorMessageFromServer(val message: String): ResultState<Nothing>()
    object InProgress: ResultState<Nothing>()
}