package com.example.mbulaktestapp.ui.phone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.mbulaktestapp.*
import com.example.mbulaktestapp.ui.ResultState
import com.example.mbulaktestapp.ui.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_phone_number.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhoneNumberActivity : AppCompatActivity() {
    private val phoneNumberViewModel: PhoneNumberViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        phoneNumberViewModel.getCountries()
        observes()
        onClicks()
    }

    private fun onClicks() {
        btn_next.setOnClickListener {
            val phoneNumber = et_number_phone.text.toString()
            if (!phoneNumber.isNullOrEmpty()){
                phoneNumberViewModel.checkPhoneResult(phoneNumber)
            } else {
                showToast("Пожалуйста заполните все обязательные поля")
            }
        }

        btn_next_from_code.setOnClickListener {
            val code = et_code.text.toString()
            if (!code.isNullOrEmpty()){
                phoneNumberViewModel.checkCodeResult(MyApplication.phoneId,code.toInt())
            } else {
                showToast("Пожалуйста заполните все обязательные поля")
            }
        }

        tv_dont_take_sms.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }

        iv_close.setOnClickListener {
            first_container.show()
            second_container.hide()
        }
    }

    override fun onBackPressed() {
        if (first_container.visibility != View.VISIBLE){
            first_container.show()
            second_container.hide()
        }else{
            super.onBackPressed()
        }
    }

    fun observes(){
        phoneNumberViewModel.getLoadingState().observe(this, Observer {
            when (it){
                is ResultState.Success -> {
                }
                is ResultState.InProgress -> {
                }
                is  ResultState.Error -> {
                }
                is ResultState.ErrorMessageFromServer -> {

                }
            }
        })

        phoneNumberViewModel.checkPhoneState().observe(this, Observer {
            when (it){
                is ResultState.Success -> {
                    MyApplication.phoneId = it.data.id
                    second_container.show()
                    first_container.hide()
                    container_send_code.show()
                    progress_bar.hide()
                }
                is ResultState.InProgress -> {
                    second_container.show()
                    first_container.hide()
                    container_send_code.hide()
                    progress_bar.show()
                }
                is  ResultState.Error -> {
                    second_container.hide()
                    first_container.show()
                }
                is ResultState.ErrorMessageFromServer -> {
                    showAlert(it.message){ dialog, which ->
                        dialog?.dismiss()
                    }
                    second_container.hide()
                    first_container.show()
                }
            }
        })

        phoneNumberViewModel.checkCodeState().observe(this, Observer {
            when (it){
                is ResultState.Success -> {
                    startActivity(Intent(this,RegistrationActivity::class.java))
                }
                is ResultState.InProgress -> {
                    second_container.show()
                    first_container.hide()
                    container_send_code.hide()
                    progress_bar.show()
                }
                is  ResultState.Error -> {
                    container_send_code.show()
                    progress_bar.hide()
                }
                is ResultState.ErrorMessageFromServer -> {
                    showAlert(it.message){ dialog, which ->
                        dialog?.dismiss()
                    }
                    container_send_code.show()
                    progress_bar.hide()
                }
            }
        })
    }
}