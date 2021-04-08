package com.example.mbulaktestapp.ui.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mbulaktestapp.R
import com.example.mbulaktestapp.hide
import com.example.mbulaktestapp.show
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        onClicks()
    }

    private fun onClicks() {
        btn_next2.setOnClickListener {
            first_container.hide()
            second_container.show()
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
}