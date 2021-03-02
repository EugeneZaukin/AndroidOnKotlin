package com.eugene.androidonkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eugene.androidonkotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            supportFragmentManager.beginTransaction().replace(R.id.main_container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}