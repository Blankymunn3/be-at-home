package io.be_at_home.features.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.gun0912.tedpermission.util.ObjectUtils
import io.be_at_home.R
import io.be_at_home.databinding.ActivitySignUpBinding
import io.be_at_home.features.main.MainActivity
import io.be_at_home.model.UserModel
import io.be_at_home.utils.BaseActivity
import io.be_at_home.utils.SharedPreferenceHelper
import io.be_at_home.utils.Utils
import io.be_at_home.utils.GetViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : BaseActivity() {
    private val binding by binding<ActivitySignUpBinding>(R.layout.activity_sign_up)
    val viewModel by GetViewModel(SignUpViewModel::class.java)

    private lateinit var matcher: Matcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actList.add(this@SignUpActivity)
        Utils.setIconTintDark(this@SignUpActivity, true)
        binding.viewModel = viewModel
        binding.isVisibled = true
        binding.btnSignUpPerson.isSelected = binding.isVisibled!!
        binding.btnSignUpCompany.isSelected = !binding.isVisibled!!

        viewModel.isSaveButtonEnabled.observe(this) {
            binding.btnSignupNext.isEnabled = it
        }

        viewModel.successLoginCommand.observe(this) {
            if (!ObjectUtils.isEmpty(it)) onSignUpNextPage(it)
        }
        viewModel.responseMessage.observe(this) {
            if (!ObjectUtils.isEmpty(it)) Utils.showToast(it, this)
        }

        Utils.visibleDeleteButton(this, binding.etSignupId, binding.delEdtId, viewModel.userID)
        Utils.visibleDeleteButton(this, binding.etSignupPassword, binding.delEdtPw, viewModel.userPW)
        Utils.visibleDeleteButton(this, binding.etSignupPasswordConfirm, binding.delEdtPwConfirm, viewModel.userPwConFirm)

        binding.etSignupId.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    viewModel.userIdCheck()
                }
            }
    }



    fun onClickDeleteEditTextContent(view: View) {
        when (view.id) {
            R.id.del_edt_id -> {
                binding.etSignupId.setText("")
            }
            R.id.del_edt_pw -> {
                binding.etSignupPassword.setText("")
            }
            R.id.del_edt_pw_confirm -> {
                binding.etSignupPasswordConfirm.setText("")
            }
        }
    }

    fun onClickGenderSelected(view: View) {
        when (view.id) {
            R.id.btn_sign_up_person -> {
                binding.isVisibled = true
            }
            R.id.btn_sign_up_company -> {
                binding.isVisibled = false
            }
        }
        binding.btnSignUpPerson.isSelected = binding.isVisibled!!
        binding.btnSignUpCompany.isSelected = !binding.isVisibled!!
        if (binding.isVisibled!!) viewModel.userType.value = "0"
        else viewModel.userType.value = "1"
    }

    private fun onSignUpNextPage(userModel: UserModel) {
        SharedPreferenceHelper.setUserDataToSharedPreference(this, userModel)
        startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slid_left, R.anim.slide_right)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        actList.remove(this@SignUpActivity)
    }
}

/*

        binding.etSignupEmail.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val pattern: Pattern =
                        Pattern.compile("^[0-9a-zA-Z][0-9a-zA-Z_\\-.+]+[0-9a-zA-Z]@[0-9a-zA-Z][0-9a-zA-Z_\\-]*[0-9a-zA-Z](\\.[a-zA-Z]{2,6}){1,2}\$")
                    matcher = pattern.matcher(binding.etSignupEmail.text.toString())
                    if (!matcher.matches()) {
                        Utils.showToast("이메일 형식으로 입력해주세요.", this@SignUpActivity)
                        viewModel.emailCheck.value = false
                    } else {
                        viewModel.emailCheck.value = true
                    }
                }
            }
        binding.etSignupPhone.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val pattern: Pattern = Pattern.compile("([0-9]{3})([0-9]{4})([0-9]{4})")
                    matcher = pattern.matcher(binding.etSignupPhone.text.toString())
                    if (!matcher.matches()) {
                        Utils.showToast("전화번호 형식으로 입력해주세요.", this)
                        viewModel.phoneCheck.value = false
                    } else {
                        viewModel.phoneCheck.value = true
                    }
                }
            }
*/