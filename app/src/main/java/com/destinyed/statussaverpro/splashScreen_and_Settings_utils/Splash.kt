package com.destinyed.statussaverpro.splashScreen_and_Settings_utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.destinyed.statussaverpro.MainActivity
import com.destinyed.statussaverpro.R

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000.toLong())

        var rotateSplash = findViewById<ImageView>(R.id.rotateSplash)

        var animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        rotateSplash.animation = animation

    }
}