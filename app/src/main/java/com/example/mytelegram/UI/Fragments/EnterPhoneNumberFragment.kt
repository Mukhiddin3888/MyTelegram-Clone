package com.example.mytelegram.UI.Fragments


import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.mytelegram.R
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {


    override fun onStart() {
        super.onStart()

        register_btn_next.setOnClickListener{sentCode()}
    }

    private fun sentCode() {
        if(register_input_phone_number.text.toString().isEmpty()){
                Toast.makeText(activity, getString(R.string.register_toast_enter_phone_num), Toast.LENGTH_SHORT).show()
        }else if(register_input_phone_number.text.toString().length < 4){
            Toast.makeText(activity, getString(R.string.register_toast_enter_phone_num), Toast.LENGTH_SHORT).show()
        }
        else{
            fragmentManager?.beginTransaction()
                ?.replace(R.id.registerDataContainer,EnterCodeFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

    }
}