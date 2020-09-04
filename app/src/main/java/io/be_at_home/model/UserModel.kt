package io.be_at_home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserModel {
    @SerializedName("user_id")
    @Expose
    lateinit var userId: String

    @SerializedName("user_passwd")
    @Expose
    lateinit var userPW: String

    @SerializedName("user_name")
    @Expose
    var name: String = ""

    @SerializedName("user_email")
    @Expose
    var email: String = ""

    @SerializedName("user_phone")
    @Expose
    var phone: String = ""

    @SerializedName("user_area")
    @Expose
    var area: String = ""

    @SerializedName("user_type")
    @Expose
    lateinit var type: String

    @SerializedName("onPush")
    @Expose
    var isPushAllow: String = "0"

    @SerializedName("onMktAllow")
    @Expose
    var isMktAllow: String = ""
}