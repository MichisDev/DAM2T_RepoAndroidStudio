package com.example.losbrunchies

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.losbrunchies.databinding.ActivityMainBinding
import com.example.losbrunchies.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()


        // Configurar boton play
        binding.ivPlayRegis.setOnClickListener(){
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        // Configurar TVJugar


    }
}