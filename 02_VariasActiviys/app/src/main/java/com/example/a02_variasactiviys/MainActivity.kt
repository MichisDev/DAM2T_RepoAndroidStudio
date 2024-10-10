package com.example.a02_variasactiviys

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_variasactiviys.databinding.ActivityMainBinding
import modelo.Persona


class MainActivity : AppCompatActivity() {
    // variable global necesaria para el biding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // ahora con bundlle
        val p = Persona(binding.ptNombre.text.toString(), binding.ptEdad.text.toString())

        // Boton para navegar a la segunda ventana
        binding.btAceptar.setOnClickListener {
        /*
            // 1) Objeto Intent para pasar parametros a otra actividad utilizando hashTable
            var inte : Intent = Intent(this, Ventana2::class.java)
            inte.putExtra("nombre", binding.ptNombre.text.toString())
            inte.putExtra("edad", binding.ptEdad.text.toString())
            startActivity(inte)
        */
        /*
            // 2) Pasar objeto persona a otra actividad para no hacerlo dato a dato
            var inte : Intent = Intent(this, Ventana2::class.java)
            var p = Persona(binding.ptNombre.text.toString(), binding.ptEdad.text.toString())
            inte.putExtra("obj", p)
            startActivity(inte)

         */
            // 3) Pasar objeto persona a otra actividad con bundle
            var inte : Intent = Intent(this, Ventana2::class.java)
            val bundle = Bundle()
            bundle.putString("nombre1", binding.ptNombre.text.toString())
            bundle.putString("edad1", binding.ptEdad.text.toString())
            bundle.putSerializable("persona",p)
            inte.putExtra("objeto",bundle)
            startActivity(inte)

        }
    }
}