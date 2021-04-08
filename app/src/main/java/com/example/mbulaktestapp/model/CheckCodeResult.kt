package com.example.mbulaktestapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckCodeResult (
    @SerializedName("code")
    @Expose
    var code:Int = 0,

    @SerializedName("message")
    @Expose
    var message: String = ""
)