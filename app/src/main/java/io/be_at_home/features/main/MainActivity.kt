package io.be_at_home.features.main

import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import io.be_at_home.R
import io.be_at_home.adapter.FragmentAdapter
import io.be_at_home.databinding.ActivityMainBinding
import io.be_at_home.features.login.LoginActivity
import io.be_at_home.utils.*


class MainActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    val viewModel by GetViewModel(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actList.add(this@MainActivity)
        binding.viewModel = viewModel
        Utils.setIconTintDark(this@MainActivity, true)


    }
}