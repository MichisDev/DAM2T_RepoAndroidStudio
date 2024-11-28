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

        // Configurar el boton para volver a la pantalla de inicio
        binding.ivVolver.setOnClickListener {
            finish()
        }

        // Configurar el botón de alimentación
        binding.ivAli.setOnClickListener {
            val intent = Intent(this, Alimentacion::class.java)
            startActivity(intent)
        }

        // Configurar el botón de batalla
        binding.ivBat.setOnClickListener {
            //val intent = Intent(this, Batalla::class.java)
            Toast.makeText(this, "Próximamente se implementara esta pantalla", Toast.LENGTH_SHORT).show()
        }
    }


}