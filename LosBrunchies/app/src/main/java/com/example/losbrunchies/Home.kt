package com.example.losbrunchies

import adaptador.HuevoAdapter
import adaptador.RecetaAdapter
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.losbrunchies.databinding.ActivityHomeBinding
import modelo.Huevo
import modelo.HuevoDato
import modelo.Receta
import modelo.RecetaData

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var huevos: MutableList<Huevo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el comportamiento de cierre de sesión
        sharedPreferences = getSharedPreferences("session_prefs", MODE_PRIVATE)

        // ReciclerView
        huevos = HuevoDato.obtenerHuevo().toMutableList()

        var recyclerView: RecyclerView = binding.rvHuevos
        recyclerView.layoutManager =
            CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = HuevoAdapter(huevos)
        recyclerView.adapter = adapter

        // Configurar el boton para volver a la pantalla de inicio
        binding.ivBack.setOnClickListener(){
            finish()
        }

        binding.ivSeguir.setOnClickListener(){
            val intent = Intent(this, Alimentacion::class.java)
            startActivity(intent)

        }


    }


}