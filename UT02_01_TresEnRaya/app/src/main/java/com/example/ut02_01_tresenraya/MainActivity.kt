package com.example.ut02_01_tresenraya

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.example.ut02_01_tresenraya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crea una instancia de ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Establece la vista raíz del layout INFLA
        setContentView(binding.root)

        // Accde a los elementos de la interfaz de usuario a través de ViewBinding
        binding.btUnJugador.setOnClickListener {
            val intent = Intent(this, UnJugadorActivity::class.java)
            startActivity(intent)
        }
        binding.btDosJugadores.setOnClickListener {
            val intent = Intent(this, DosJugadoresActivity::class.java)
            startActivity(intent)
        }
        binding.btSalir.setOnClickListener {
            Toast.makeText(this, "Has pulsado salir, ADIOS =)", Toast.LENGTH_SHORT).show()
            // Cierra la actividad
            finish()
        }


    }
}