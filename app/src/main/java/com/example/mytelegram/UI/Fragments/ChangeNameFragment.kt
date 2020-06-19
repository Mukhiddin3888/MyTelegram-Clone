package com.example.mytelegram.UI.Fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.mytelegram.MainActivity
import com.example.mytelegram.R
import com.example.mytelegram.Utilities.showToast
import kotlinx.android.synthetic.main.fragment_change_name.*


class ChangeNameFragment : Fragment(R.layout.fragment_change_name) {
    override fun onResume() {
        super.onResume()

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_confirm_change-> changeName()
        }
        return true
    }

    private fun changeName() {
        val name:String = settings_editName_firstName.text.toString()
        val surname:String = settings_editName_surName.text.toString()
        if(name.isEmpty()){
            showToast(getString(R.string.settings_toast_emty_name))
        }else{
            val fullname = "$name $surname"
        }


    }
}