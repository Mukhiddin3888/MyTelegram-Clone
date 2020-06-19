package com.example.mytelegram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.mytelegram.Activities.RegisterActivity
import com.example.mytelegram.UI.Fragments.ChatsFragment
import com.example.mytelegram.UI.Objects.AppDrawer
import com.example.mytelegram.Utilities.AUTH
import com.example.mytelegram.Utilities.initFirebase
import com.example.mytelegram.Utilities.replaceActivity
import com.example.mytelegram.Utilities.replaceFragment
import com.example.mytelegram.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
     lateinit var mAppDrawer:AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if(AUTH.currentUser !=null){
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(),false)
        }else{
            replaceActivity(RegisterActivity())
        }

    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this,mToolbar)

        initFirebase()
    }
}