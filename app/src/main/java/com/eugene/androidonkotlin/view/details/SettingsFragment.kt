package com.eugene.androidonkotlin.view.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.viewmodel.AppState
import com.eugene.androidonkotlin.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    private val KEY_ADULT = "KEY_ADULT"
    private var isAdultMode = false

    private val viewModel: HistoryViewModel by lazy { ViewModelProvider(this).get(HistoryViewModel::class.java) }
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }


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
            if (it.getPreferences(Context.MODE_PRIVATE).getBoolean(KEY_ADULT, false)) {
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

        history_fragment_recycler.adapter = adapter
        viewModel.historyLiveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllHistory()

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                history_fragment_recycler.visibility = View.GONE
            }

            is AppState.Success -> {
                history_fragment_recycler.visibility = View.VISIBLE
                adapter.setData(appState.movieSuccess)
            }

            is AppState.Error -> {
                history_fragment_recycler.visibility = View.GONE
            }
        }
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }
}