package com.eugene.androidonkotlin

enum class CodeErrors(val idMessage: Int) {
    NETWORK_ERROR(R.string.network_error),
    REQUEST_ERROR(R.string.request_error),
    SERVER_ERROR(R.string.server_error)
}