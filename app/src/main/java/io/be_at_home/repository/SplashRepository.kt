package io.be_at_home.repository

import io.be_at_home.api.RemoteDataSource
import io.be_at_home.api.RemoteDataSourceImpl
import io.be_at_home.api.SignUpResponse
import retrofit2.Response

class SplashRepository {
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    fun loadUserInfoFromServer(
        userId: String,
        onResponse: (Response<SignUpResponse>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        remoteDataSource.userIdCheck(userId, onResponse, onFailure)
    }
}