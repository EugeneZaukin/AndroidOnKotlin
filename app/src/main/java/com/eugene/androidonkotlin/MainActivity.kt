package com.eugene.androidonkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, MainFragment())
                .commitAllowingStateLoss()
        }

        initButton();
        initCycles();
    }

    //Задание 5а
    fun initButton() {
        val button1 = findViewById<Button>(R.id.button_test)
        button1.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "Кнопка нажата", Toast.LENGTH_LONG).show()
        })

        //Задание 5b
        val textData = MyDataClass("Привет друг!", 26)
        val text = findViewById<TextView>(R.id.text_view)
        val button2 = findViewById<Button>(R.id.button_data_class)
        button2.setOnClickListener(View.OnClickListener {
            text.setText("${textData.s}. Мой возраст ${textData.count}")
        })

        //Задание 5c
        val obj = textData.copy("Copy", 1)
        val button3 = findViewById<Button>(R.id.button_object_class)
        button3.setOnClickListener(View.OnClickListener {
            text.setText("${obj.s} ${obj.count}")
        })


    }

    fun initCycles() {
        val size = 5
        when(size) {
            1 -> Log.w("Size не равен ", size.toString())
            3 -> Log.w("Size не равен ", size.toString())
            else -> Log.w("Size равен ", size.toString())
        }

        for (i in 2..6 step 2) {
            Log.w("Цикл for", i.toString())
        }

        val items:Array<Int> = arrayOf(3, 5, 7)
        items.forEach {
            Log.w("Цикл foreach", it.toString())
        }

        val animal = "Dog"
        if (animal.equals("Dog")) {
            Log.w("Цикл if", animal)
        } else {
            Log.w("Цикл if", "Это животное не собака")
        }
    }
}