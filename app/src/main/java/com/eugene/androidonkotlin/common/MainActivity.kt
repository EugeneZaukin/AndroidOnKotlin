package com.eugene.androidonkotlin.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.listMovie.screen.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MainFragment())
                .commitAllowingStateLoss()
        }
    }
}