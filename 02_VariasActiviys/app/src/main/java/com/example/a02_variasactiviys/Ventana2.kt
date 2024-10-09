package com.example.a02_variasactiviys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_variasactiviys.databinding.ActivityVentana2Binding
import prueba.Persona

class Ventana2 : AppCompatActivity() {

    private lateinit var binding: ActivityVentana2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVentana2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //var nombre = intent.getStringExtra("nombre")
       // var apellido = intent.getStringExtra("apellido")
        //binding.tvTitulo.text = "Bienvenido $nombre $apellido"
       // binding.tvTitulo.text = "Hola "+ nombre+" "+apellido

        //val p : Persona = intent.getSerializableExtra("obj") as Persona
        //binding.tvTitulo.text = p.toString()

        //ahora con bundle
       //val bundle = intent.getStringExtra("objeto")
       // val nombre = bundle?.getString("nombre")
        //val apellido = bundle?.getString("apellido")
       // val p : Persona = bundle?.getSerializable("obj") as Persona
      //  binding.tvTitulo.text = "Bienvenido $nombre $apellido"

        binding.tvTitulo.text = "Con bundle el objeto: "+p.toString()

        binding.btVolver.setOnClickListener {
            finish()
        }

    }
}