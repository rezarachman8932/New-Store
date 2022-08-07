package com.app.store.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.store.R
import com.app.store.presentation.landing.MainActivity
import com.app.store.presentation.user.UserLoginActivity
import com.app.store.shared.constant.CommonConstant
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (viewModel.isLoggedIn()) {
            text_splash_welcome.text = getString(R.string.welcome_with_login, viewModel.getUsername())
        } else {
            text_splash_welcome.text = getString(R.string.welcome_no_login)
        }

        var intent: Intent

        Handler().postDelayed({
            if (viewModel.isLoggedIn()) {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                intent = Intent(this, UserLoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, CommonConstant.DELAY_THREE_SECONDS)


    }

}