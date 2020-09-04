package io.be_at_home.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DefaultResponse (
    @SerializedName("code")
    @Expose
    var code : String,
    @SerializedName("message")
    @Expose
    var message : String
)