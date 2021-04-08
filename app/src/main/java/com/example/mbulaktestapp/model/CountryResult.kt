package com.example.mbulaktestapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class CountryResult (
    @SerializedName("id")
    @Expose
    var id: String = "",
    @SerializedName("name")
    @Expose
    var name: String = "",
    @SerializedName("iso_code")
    @Expose
    var isoCode: String = "",
    @SerializedName("phone_code")
    @Expose
    var phoneCode: String = "",
    @SerializedName("phone_length")
    @Expose
    var phoneLength: String = "",
    @SerializedName("phone_mask")
    @Expose
    var phoneMask: String = "",
    @SerializedName("phone_mask_small")
    @Expose
    var phoneMaskSmall: String = ""
)