package com.example.mbulaktestapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


 data class CheckPhoneResult (
    @SerializedName("id")
    @Expose
    var id:Int = 0
 )