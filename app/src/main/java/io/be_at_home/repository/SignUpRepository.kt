package io.be_at_home.repository

import io.be_at_home.api.RemoteDataSource
import io.be_at_home.api.RemoteDataSourceImpl
import io.be_at_home.api.SignUpResponse
import io.be_at_home.model.UserModel
import retrofit2.Response

class SignUpRepository {
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    fun userIdCheck(
        userId: String,
        onResponse: (Response<SignUpResponse>) -> Unit,
        onFailure: (Throwable) -> Unit) {
        remoteDataSource.userIdCheck(userId, onResponse, onFailure)
    }
    fun signUpUser(
        userModel: UserModel,
        onResponse: (Response<SignUpResponse>) -> Unit,
        onFailure: (Throwable) -> Unit)
    {
        remoteDataSource.signUpUserRepo(userModel, onResponse, onFailure)
    }
}