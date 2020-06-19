package com.example.mytelegram.UI.Fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.mytelegram.Activities.RegisterActivity
import com.example.mytelegram.MainActivity
import com.example.mytelegram.R
import com.example.mytelegram.Utilities.AUTH
import com.example.mytelegram.Utilities.replaceActivity
import com.example.mytelegram.Utilities.replaceFragment

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
}

override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    activity?.menuInflater?.inflate(R.menu.settings_action_menu,menu)
}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name ->{
              replaceFragment(ChangeNameFragment())
            }

        }
        return true
    }
}




