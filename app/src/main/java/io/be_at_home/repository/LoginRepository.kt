package io.be_at_home.repository

import io.be_at_home.api.RemoteDataSource
import io.be_at_home.api.RemoteDataSourceImpl

import io.be_at_home.api.SignUpResponse
import io.be_at_home.data.LoginData
import retrofit2.Response

class LoginRepository {
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    fun loginUser(
        loginData: LoginData,
        onResponse: (Response<SignUpResponse>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        remoteDataSource.loginUserRepo(loginData, onResponse, onFailure)
    }
}