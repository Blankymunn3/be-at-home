package io.be_at_home.api

import io.be_at_home.data.LoginData
import io.be_at_home.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface RemoteDataSource {

    fun userIdCheck(@Query("user_id") userID: String,
                    onResponse: (Response<SignUpResponse>) -> Unit,
                    onFailure: (Throwable) -> Unit)

    fun loginUserRepo(@Body loginData: LoginData,
                          onResponse: (Response<SignUpResponse>) -> Unit,
                          onFailure: (Throwable) -> Unit)

    fun signUpUserRepo(@Body userModel: UserModel,
                       onResponse: (Response<SignUpResponse>) -> Unit,
                       onFailure: (Throwable) -> Unit)

}