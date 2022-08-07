package com.app.store.presentation.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.store.R
import com.app.store.data.model.UserCreateRequest
import com.app.store.data.model.UserCreateSession
import com.app.store.presentation.landing.MainActivity
import kotlinx.android.synthetic.main.activity_user_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserLoginActivity : AppCompatActivity() {

    private val viewModel: UserLoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        viewModel.userCreateSession.observe(this) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        button_login.setOnClickListener { doSignIn() }
    }

    private fun doSignIn() {
        val username = edit_text_username.text.toString()
        val password = edit_text_password.text.toString()

        val user = UserCreateSession(
            login = username,
            password = password
        )

        if (viewModel.ableToRegister(username, password)) {
            viewModel.createSession(UserCreateRequest(user))
        }
    }

}