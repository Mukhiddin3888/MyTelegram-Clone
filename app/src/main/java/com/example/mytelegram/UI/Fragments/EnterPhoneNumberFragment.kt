package com.example.mytelegram.UI.Fragments


import androidx.fragment.app.Fragment
import com.example.mytelegram.Activities.RegisterActivity
import com.example.mytelegram.MainActivity
import com.example.mytelegram.R
import com.example.mytelegram.Utilities.AUTH
import com.example.mytelegram.Utilities.replaceActivity
import com.example.mytelegram.Utilities.replaceFragment
import com.example.mytelegram.Utilities.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mPhoneNumber: String

    override fun onStart() {
        super.onStart()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else {
                        showToast(task.exception?.message.toString())
                    }

                }

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }


            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
              //  super.onCodeSent(id, token)
                replaceFragment(EnterCodeFragment(mPhoneNumber,id))
            }
        }
        register_btn_next.setOnClickListener { sentCode() }
    }

    private fun sentCode() {
        if (register_input_phone_number.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone_num))

        } else if (register_input_phone_number.text.toString().length < 4) {
            showToast(getString(R.string.register_toast_enter_phone_num))
        } else {
            authUser()
        }

    }

    private fun authUser() {
        mPhoneNumber = register_input_phone_number.text.toString()

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback
        )


    }
}