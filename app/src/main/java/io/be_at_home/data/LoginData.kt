package io.be_at_home.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("user_id")
    @Expose
    var userID: String,

    @SerializedName("user_passwd")
    @Expose
    var userPW: String
)