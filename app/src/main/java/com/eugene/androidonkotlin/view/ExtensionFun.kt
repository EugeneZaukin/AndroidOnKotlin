package com.eugene.androidonkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.eugene.androidonkotlin.R
import java.lang.IllegalStateException
import kotlin.reflect.KClass

fun KClass<*>.createFragment(bundle: Bundle): Fragment =
    when (this) {
        MainFragment::class.java -> MainFragment.newInstance()
        DescriptionFragment::class -> DescriptionFragment.newInstance(bundle)
        else -> throw IllegalStateException("Error! fragment class doesnt exist")
    }

fun FragmentManager.addToBackStack(kClass: KClass<*>, bundle: Bundle) =
    beginTransaction()
        .replace(R.id.main_container, kClass.createFragment(bundle))
        .addToBackStack("")
        .commit()
