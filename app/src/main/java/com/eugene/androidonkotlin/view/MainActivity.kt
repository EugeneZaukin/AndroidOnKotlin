package com.eugene.androidonkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.view.details.SettingsFragment
import com.eugene.androidonkotlin.view.main.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.main_container, MainFragment.newInstance())
                .commitNow()
        }

        val bottomNavMenu = findViewById<BottomNavigationItemView>(R.id.navigation_settings)
        bottomNavMenu.setOnClickListener(View.OnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, SettingsFragment.newInstance())
                .commitAllowingStateLoss()
        })
    }
}