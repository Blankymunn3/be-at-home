package io.be_at_home.features.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.be_at_home.api.APIClient
import io.be_at_home.api.BeAtHomeAPI
import io.be_at_home.api.SignUpResponse
import io.be_at_home.data.LoginData
import io.be_at_home.model.UserModel
import io.be_at_home.repository.LoginRepository
import io.be_at_home.utils.LiveData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel: ViewModel() {
    private val liveData = LiveData()
    private val loginRepository = LoginRepository()
    private lateinit var loginData: LoginData

    val successLoginCommand = MutableLiveData<UserModel>()
    val responseMessage = MutableLiveData("")

    val userID = MutableLiveData("")
    val userPW = MutableLiveData("")

    val errorLog = MutableLiveData("")

    val isSaveButtonEnabled = liveData.mediatorLiveData(userID, userPW) {
        !userID.value.isNullOrEmpty() && !userPW.value.isNullOrEmpty()
    }

    fun handleLoginClick() {
        loginData = LoginData(userID.value!!, userPW.value!!)
        viewModelScope.launch {
            loginRepository.loginUser(loginData = loginData,
                onResponse = {
                    if (it.isSuccessful) {
                        if (it.body()!!.code == "200") successLoginCommand.value = it.body()!!.data
                        else responseMessage.value = it.body()!!.message
                    }
                },
                onFailure = {
                    responseMessage.value = it.toString()
                }

            )
        }
    }
}