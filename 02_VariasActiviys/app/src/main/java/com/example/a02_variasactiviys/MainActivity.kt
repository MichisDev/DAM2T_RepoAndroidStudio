package com.example.a02_variasactiviys

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_variasactiviys.databinding.ActivityMainBinding
import prueba.Persona


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // falta con intent


        // Enviar un objeto persona a otra actividad
        //var p = Persona(binding.edNombre.text.toString(), binding.edApellido.text.toString()))
        //intent.putExtra("obj", p)

        // ahora con bundlle
        val bundle = Bundle()
        bundle.putString("nombre", binding.edNombre.text.toString())
        bundle.putString("apellido", binding.edApellido.text.toString())
        bundle.putSerializable("obj", Persona(binding.edNombre.text.toString(), binding.edApellido.text.toString()))
        intent.putExtra("objeto",bundle)

        binding.btAceptar.setOnClickListener {
            val intent = Intent(this, Ventana2::class.java)
            //intent.putExtra("nombre", binding.edNombre.text.toString())
            //intent.putExtra("apellido", binding.edApellido.text.toString())
            startActivity(intent)

        }
    }
}