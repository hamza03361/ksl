package com.example.ksl

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class Splashscreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install the splash screen
        installSplashScreen()

        setContentView(R.layout.splashscreen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = getColor(R.color.secondthemecolor) // Change to your color resource
        }

        // Fade-in animation for the splash screen
        val fadeIn = AlphaAnimation(0.0f, 1.0f).apply {
            duration = 2000 // 2 seconds
        }

        val rootLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.rootLayout)
        rootLayout.startAnimation(fadeIn)

        // Delay of 2 seconds before transitioning to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val fadeOut = AlphaAnimation(1.0f, 0.0f).apply {
                duration = 1000 // 1 second fade out
            }
            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    val intent = Intent(this@Splashscreen, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
                override fun onAnimationRepeat(animation: Animation) {}
            })
            rootLayout.startAnimation(fadeOut)
        }, 2000) // 2 seconds delay
    }
}
