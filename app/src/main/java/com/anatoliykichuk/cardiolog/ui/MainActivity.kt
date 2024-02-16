package com.anatoliykichuk.cardiolog.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anatoliykichuk.cardiolog.R
import com.anatoliykichuk.cardiolog.databinding.ActivityMainBinding
import com.anatoliykichuk.cardiolog.ui.pages.records.CardioLogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CardioLogFragment.newInstance())
            .commit()
    }
}