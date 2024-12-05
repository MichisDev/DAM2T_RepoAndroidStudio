package com.example.toolbar

import androidx.appcompat.widget.Toolbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val toolbar = findViewById.(R.id.menu_main)
        setSupportActionBar(toolbar) // toolbar es el id de tu Toolbar en el layout
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Habilita la flecha de volver
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "Ajustes", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_about -> {
                Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT).show()
                true
            }
            android.R.id.home -> { // Manejar el clic en la flecha de volver
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}