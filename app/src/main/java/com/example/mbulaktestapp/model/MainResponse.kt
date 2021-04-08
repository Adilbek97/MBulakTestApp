package com.example.mbulaktestapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainResponse<T: Any>(
    @SerializedName("code")
    @Expose
    val code:Int = 0,
    @SerializedName("result")
    @Expose
    val result:T? = null,
    @SerializedName("error")
    @Expose
    val error: Error? = null
)