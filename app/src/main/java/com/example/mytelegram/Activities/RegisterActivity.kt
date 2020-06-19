package com.example.mytelegram.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytelegram.R
import com.example.mytelegram.UI.Fragments.EnterPhoneNumberFragment
import com.example.mytelegram.Utilities.initFirebase
import com.example.mytelegram.Utilities.replaceFragment
import com.example.mytelegram.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)

        title = getString(R.string.register_title_your_phone)

        replaceFragment(EnterPhoneNumberFragment(),false)
    }
}