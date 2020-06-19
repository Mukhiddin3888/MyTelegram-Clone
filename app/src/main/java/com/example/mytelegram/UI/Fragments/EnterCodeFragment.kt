package com.example.mytelegram.UI.Fragments


import androidx.fragment.app.Fragment
import com.example.mytelegram.Activities.RegisterActivity
import com.example.mytelegram.MainActivity
import com.example.mytelegram.R
import com.example.mytelegram.Utilities.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {


    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {

            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })

    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val dataMap = mutableMapOf<String, Any>()

                val uid = AUTH.currentUser?.uid.toString()
                dataMap[CHILD_ID] = uid
                dataMap[CHILD_PHONE] = phoneNumber
                dataMap[CHILD_USERNAME] = uid


                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dataMap)
                    .addOnCompleteListener { task2 ->
                        if (task2.isSuccessful) {
                            showToast("Добро пожаловать")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        } else showToast(task2.exception?.message.toString())
                    }

            } else  showToast(task.exception?.message.toString())
        }
    }

}