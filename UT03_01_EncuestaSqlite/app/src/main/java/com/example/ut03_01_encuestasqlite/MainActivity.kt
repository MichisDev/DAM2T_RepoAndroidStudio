package com.example.ut03_01_encuestasqlite

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.ut03_01_encuestasqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout con el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}