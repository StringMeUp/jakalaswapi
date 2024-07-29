package com.example.jakala_swapi.helper

import okhttp3.MediaType.Companion.toMediaType

object NetworkConstants {
    val DEFAULT_MEDIA_TYPE = "application/json; charset=UTF8".toMediaType()
    /** SwApi seems to have issues occasionally, hence such a long timeout. */
    const val C_R_W_TIMEOUT = 30L
}