package com.eugene.androidonkotlin.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eugene.androidonkotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}