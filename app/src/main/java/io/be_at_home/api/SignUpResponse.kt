package io.be_at_home.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.be_at_home.model.UserModel

class SignUpResponse {
    @SerializedName("code")
    @Expose
    lateinit var code: String

    @SerializedName("message")
    @Expose
    lateinit var message: String

    @SerializedName("data")
    @Expose
    lateinit var data : UserModel
}