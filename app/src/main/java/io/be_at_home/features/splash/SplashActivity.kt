package io.be_at_home.features.splash

import android.content.Intent
import android.os.Bundle
import com.gun0912.tedpermission.util.ObjectUtils

import io.be_at_home.R
import io.be_at_home.databinding.ActivitySplashBinding
import io.be_at_home.features.login.LoginActivity
import io.be_at_home.features.main.MainActivity
import io.be_at_home.model.UserModel
import io.be_at_home.utils.*
import io.be_at_home.utils.Utils.showToast

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    private val binding by binding<ActivitySplashBinding>(R.layout.activity_splash)
    val viewModel by GetViewModel(SplashViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actList.add(this@SplashActivity)
        binding.viewModel = viewModel
        Utils.setIconTintDark(this@SplashActivity, true)
        BaseApplication.userModel = SharedPreferenceHelper.getUserDataToSharedPreference(this@SplashActivity)
        viewModel.userID.value = BaseApplication.userModel.userId

        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            doLaunchApp()
        }

        viewModel.responseMessage.observe(this@SplashActivity) {
            if (!ObjectUtils.isEmpty(it)) {
                showToast(it, this@SplashActivity)
                if (it == "네트워크 상태를 확인해주세요.") finish()
            }
        }
    }

    private fun doLaunchApp() {
        when {
            SharedPreferenceHelper.isSharedAutoLogin(this@SplashActivity) -> {
                viewModel.loadUserInfoFromServer()
            }
            else -> {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }


        viewModel.userModel.observe(this@SplashActivity) {
            startMainActivity(it)
        }
    }

    private fun startMainActivity(userModel: UserModel) {
        SharedPreferenceHelper.setUserDataToSharedPreference(this@SplashActivity, userModel)
        SharedPreferenceHelper.setSharedAutoLogin(this@SplashActivity, true)
        finish()
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }
}