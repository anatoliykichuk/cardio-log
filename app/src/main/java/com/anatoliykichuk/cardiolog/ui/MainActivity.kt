package com.anatoliykichuk.cardiolog.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anatoliykichuk.cardiolog.R
import com.anatoliykichuk.cardiolog.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment()
        setFabOnClickListeners()
    }

    private fun addFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CardioLogFragment.newInstance())
            .commit()
    }

    private fun setFabOnClickListeners() {
        binding.addRecordFab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        binding.removeRecordFab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}