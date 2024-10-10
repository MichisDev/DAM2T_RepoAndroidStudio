package com.example.a02_variasactiviys

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_variasactiviys.databinding.ActivityVentana2Binding
import modelo.Persona

class Ventana2 : AppCompatActivity() {

    private lateinit var binding: ActivityVentana2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // inicializamos el binding (inflar el layout)
        binding = ActivityVentana2Binding.inflate(layoutInflater)
        setContentView(binding.root)
/*
EJEMPLO CON INTENT Y HASH TABLE
        // 1) Recogemos los parametros que nos llegan del objeto intent
        var nombre = intent.getStringExtra("nombre")
        var edad = intent.getStringExtra("edad")

        binding.tvTitulo.text = "Bienvenido $nombre $edad"

        //binding.tvTitulo.text = "Hola "+ nombre+" "+apellido MENOS ELEGANTE
*/
/*
EJEMPLO CON INTENT Y OBJETO PERSONA
        // 2) Recogemos los parametros que nos llegan del objeto persona
        var p = intent.getSerializableExtra("obj") as Persona

        binding.tvTitulo.text = "Bienvenido ${p.nombre} ${p.apellido}"
*/

/*
        // 3) Recogemos los parametros que nos llegan del objeto bundle
        val bundle = intent.getBundleExtra("objeto")
        val nom = bundle!!.getString("nombre1")
        val eda = bundle!!.getString("edad1")
        val p = bundle!!.getSerializable("persona") as Persona

        binding.tvTitulo.text = "Bienvenido con bundle dato a dato: $nom $eda"
        // binding.tvTitulo.text = "Con bundle dato a dato: "+nom+" "+eda MENOS ELEGANTE
        //binding.tvTitulo.text = "Con bundle objeto: "+p.toString() // APARECE EL TO STRING RARO NO RECOGE LOS PARAMETROS NO FUNKA
        // binding.tvTitulo.text = "Bienvenido con bundle objeto: ${p.nombre} ${p.edad}" // NO RECOGE LOS PARAMETROS NO FUNKA

*/
        // B. Devolver datos a la ventana 1
        binding.btDevolverActual.setOnClickListener {
            val stringToPass = binding.ptDevolver.text.toString()
            val intent = Intent()
            intent.putExtra("valor", stringToPass)
            setResult(RESULT_OK, intent)
            finish()
        }

        // 10.Devolver datos a la ventana 1 deprecated
        binding.btDevolverDeprecate.setOnClickListener {
            intent.putExtra("valor", binding.ptDevolver.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }


        binding.btVolver.setOnClickListener {
            finish()// destruye el activity actual y vuelve a la anterior
        }

    }
}