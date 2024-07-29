package com.example.jakala_swapi.helper

import okhttp3.MediaType.Companion.toMediaType

object NetworkConstants {
    val DEFAULT_MEDIA_TYPE = "application/json; charset=UTF8".toMediaType()
    /** I changed my mind, no response call should last that long. It's just unacceptable. */
    const val C_R_W_TIMEOUT = 10L
}