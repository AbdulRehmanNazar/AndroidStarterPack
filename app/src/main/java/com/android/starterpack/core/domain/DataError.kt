package com.android.starterpack.core.domain

/**
 * Sealed Error interface with defined errors like Remote and local
 */
sealed interface DataError : Error {

    sealed interface Remote : DataError {
        data object RequestTimeout : Remote
        data object TooManyRequests : Remote
        data object NoInternet : Remote
        data object Server : Remote
        data object Serialization : Remote
        data class Unknown(val message: String) : Remote
    }

    sealed interface Local : DataError {
        data object DiskFull : Local
        data class Unknown(val message: String) : Local
    }
}