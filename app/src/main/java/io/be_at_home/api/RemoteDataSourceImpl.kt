package io.be_at_home.api

import io.be_at_home.data.LoginData
import io.be_at_home.model.UserModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl: RemoteDataSource {
    private val beAtHomeAPI = APIClient.beAtHomeAPI
    override fun userIdCheck(userID: String, onResponse: (Response<SignUpResponse>) -> Unit, onFailure: (Throwable) -> Unit) {
        beAtHomeAPI.userIdCheck(userID).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                onResponse(response)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onFailure(t)
            }

        })
    }

    override fun loginUserRepo(loginData: LoginData, onResponse: (Response<SignUpResponse>) -> Unit, onFailure: (Throwable) -> Unit) {
        beAtHomeAPI.loginUser(loginData).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                onResponse(response)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onFailure(t)
            }

        })
    }

    override fun signUpUserRepo(userModel: UserModel, onResponse: (Response<SignUpResponse>) -> Unit, onFailure: (Throwable) -> Unit) {
        beAtHomeAPI.signUpUser(userModel).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                onResponse(response)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onFailure(t)
            }

        })
    }
}