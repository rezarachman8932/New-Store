package com.app.store.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.store.R
import com.app.store.presentation.landing.MainActivity
import com.app.store.presentation.user.UserLoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent: Intent

        if (viewModel.isLoggedIn()) {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}