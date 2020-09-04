package io.be_at_home.features.main.fragment.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.be_at_home.R
import io.be_at_home.databinding.FragmentHomeBinding
import io.be_at_home.features.main.MainActivity
import io.be_at_home.utils.*

class HomeFragment: Fragment() {
    private lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): HomeFragment {
            val fragment = HomeFragment()
            fragment.arguments = bundle
            return fragment
        }
        @JvmStatic
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_home, null, false)
        val binding = FragmentHomeBinding.bind(view)
        binding.lifecycleOwner = this
        return binding.root
    }
}