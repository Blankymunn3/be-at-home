package io.be_at_home.features.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.be_at_home.api.APIClient
import io.be_at_home.api.BeAtHomeAPI
import io.be_at_home.api.DefaultResponse
import io.be_at_home.api.SignUpResponse
import io.be_at_home.features.login.LoginActivity
import io.be_at_home.model.UserModel
import io.be_at_home.repository.SignUpRepository
import io.be_at_home.utils.LiveData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val liveData = LiveData()
    var userModel = UserModel()
    private val signUpRepository = SignUpRepository()
    val successLoginCommand = MutableLiveData<UserModel>()

    val responseMessage = MutableLiveData("")
    private val idCheck = MutableLiveData(false)

    val userID = MutableLiveData("")
    val userPW = MutableLiveData("")
    val userPwConFirm = MutableLiveData("")
    val userType = MutableLiveData("0")


    val isSaveButtonEnabled = liveData.mediatorLiveData(userID, userPW, userPwConFirm, idCheck) {
        !userID.value.isNullOrEmpty() && !userPW.value.isNullOrEmpty() && !userPwConFirm.value.isNullOrEmpty() && idCheck.value!!
    }

    fun userIdCheck() {
        viewModelScope.launch {
            signUpRepository.userIdCheck(userId = userID.value!!,
            onResponse = {
                if (it.isSuccessful) {
                    idCheck.value = it.body()!!.code == "200"
                    responseMessage.value = it.body()!!.message
                }
            },
            onFailure = {
                it.printStackTrace()
            })
        }
    }

    fun handleSignUpNextClick() {
        if (userPW.value!!.length < 8) {
            responseMessage.value = "비밀번호 길이는 최소 8자이상 가능합니다."
        }
        if (userPW.value != userPwConFirm.value) {
            responseMessage.value = "비밀번호를 확인해주세요."
        } else {
            userModel.userId = userID.value!!
            userModel.userPW = userPW.value!!
            userModel.type = userType.value!!

            signUpRepository.signUpUser(userModel = userModel,
            onResponse = {
                if (it.isSuccessful)
                    if (it.body()!!.code == "200") {
                        responseMessage.value = it.body()!!.message
                        successLoginCommand.value = it.body()!!.data
                    }
            },
            onFailure = {
                it.printStackTrace()
            })
        }
    }
}