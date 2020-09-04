package io.be_at_home.api

import androidx.lifecycle.LiveData
import io.be_at_home.data.LoginData
import io.be_at_home.model.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BeAtHomeAPI {

    @POST("/user/id_check")
    fun userIdCheck(@Query("user_id") userID: String) : Call<SignUpResponse>

    @POST("/user/join")
    fun signUpUser(@Body userModel: UserModel): Call<SignUpResponse>

    @POST("/user/login")
    fun loginUser(@Body loginData: LoginData): Call<SignUpResponse>

}