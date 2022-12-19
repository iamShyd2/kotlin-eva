package com.example.kotlin_eva.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_eva.R
import com.example.kotlin_eva.services.Statusbar
import com.example.kotlin_eva.components.TextFieldView
import com.example.kotlin_eva.services.AuthApi


class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        Statusbar.makeWhite(this)

        val button2 = findViewById<Button>(R.id.button2)

        val arrayList = ArrayList<TextFieldView>()

        val nameTextFieldView = findViewById<TextFieldView>(R.id.name)
        val emailTextFieldView = findViewById<TextFieldView>(R.id.email)
        val passwordTextFieldView = findViewById<TextFieldView>(R.id.password)
        val cPasswordTextFieldView = findViewById<TextFieldView>(R.id.c_password)

        arrayList.add(nameTextFieldView)
        arrayList.add(emailTextFieldView)
        arrayList.add(passwordTextFieldView)
        arrayList.add(cPasswordTextFieldView)

        button2.setOnClickListener {
            val valid = ArrayList<Boolean>()
            val values = HashMap<String, String>()

            for (textField in arrayList){
                valid.add(textField.isValid())
                values[textField.name] = textField.editText.text.toString()
            }
            if(valid.contains(false)) return@setOnClickListener
            Toast.makeText(applicationContext, "Valid", Toast.LENGTH_LONG).show()
            AuthApi.signUp(this, values)
                .start()
        }
    }

}