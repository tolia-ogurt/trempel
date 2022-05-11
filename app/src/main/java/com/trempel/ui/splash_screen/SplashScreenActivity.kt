package com.trempel.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.trempel.R
import com.trempel.databinding.ActivitySplashScreenBinding
import com.trempel.ui.main_activity.MainActivity
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    private val binding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
        binding.ivSplashLogo.startAnimation(animation)
        activityScope.launch {
            delay(1000)
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStop() {
        activityScope.cancel()
        super.onStop()
    }
}