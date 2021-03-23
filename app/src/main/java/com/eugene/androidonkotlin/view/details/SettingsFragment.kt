package com.eugene.androidonkotlin.view.details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import com.eugene.androidonkotlin.R

class SettingsFragment : Fragment() {
    private val KEY_ADULT = "KEY_ADULT"
    private var isAdultMode = false;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switchButton = view.findViewById<Switch>(R.id.switch_adult_content)

        activity?.let {
            if (it.getPreferences(Context.MODE_PRIVATE).getBoolean(KEY_ADULT, false) ) {
                switchButton.isChecked
            } else {
                switchButton.isEnabled
            }
        }

        switchButton.setOnCheckedChangeListener { buttonView, isChecked ->
            isAdultMode = isChecked
            activity?.let {
                with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                    putBoolean(KEY_ADULT, isAdultMode)
                        .apply()
                }
            }
        }
    }


    companion object {

        fun newInstance(): SettingsFragment {
            val fragment = SettingsFragment()
            return fragment
        }
    }
}