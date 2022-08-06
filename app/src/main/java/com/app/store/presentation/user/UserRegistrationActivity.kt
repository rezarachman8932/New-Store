package com.app.store.presentation.user

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.store.R
import com.app.store.data.model.UserCreateRequest
import com.app.store.data.model.UserCreateSession
import kotlinx.android.synthetic.main.activity_user_registration.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserRegistrationActivity : AppCompatActivity() {

    private val viewModel: UserRegistrationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        viewModel.userCreate.observe(this) {

        }

        button_register.setOnClickListener { doRegistration() }
    }

    private fun doRegistration() {
        val username = edit_text_username.text.toString()
        val email = edit_text_email.text.toString()
        val password = edit_text_password.text.toString()

        val user = UserCreateSession(
            email = email,
            login = username,
            password = password
        )

        if (viewModel.ableToRegister(username, email, password)) {
            viewModel.createUser(UserCreateRequest(user))
        }
    }

}