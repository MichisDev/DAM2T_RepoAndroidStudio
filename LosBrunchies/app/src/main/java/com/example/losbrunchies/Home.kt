package com.example.losbrunchies

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.losbrunchies.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el comportamiento de cierre de sesión
        sharedPreferences = getSharedPreferences("session_prefs", MODE_PRIVATE)

        // Configurar el botón de cierre de sesión
        binding.btCerrarSesion.setOnClickListener {
            logout()
        }

        // Configurar el botón de alimentación
        binding.btAli.setOnClickListener {
            val intent = Intent(this, Alimentacion::class.java)
            startActivity(intent)
        }

        // Configurar el botón de batalla
        binding.btBt.setOnClickListener {
            //val intent = Intent(this, Batalla::class.java)
            Toast.makeText(this, "Próximamente se implementara esta pantalla", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logout() {
        sharedPreferences.edit().clear().apply()
        val loginIntent = Intent(this, MainActivity::class.java)
        startActivity(loginIntent)
        finish()
    }
}