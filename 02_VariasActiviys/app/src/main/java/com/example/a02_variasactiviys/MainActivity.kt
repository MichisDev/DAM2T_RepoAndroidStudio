package com.example.a02_variasactiviys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_variasactiviys.databinding.ActivityMainBinding
import modelo.Persona


class MainActivity : AppCompatActivity() {
    // variable global necesaria para el biding
    private lateinit var binding: ActivityMainBinding
    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // inicializamos el binding (inflar el layout)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ahora con bundlle
        //val p = Persona(binding.ptNombre.text.toString(), binding.ptEdad.text.toString())

        // 9. manera de reiniciar el activity
        binding.btReiniciar.setOnClickListener {
            var ine: Intent = intent
            finish()
            startActivity(ine)
        }

        // 10. deprecated
        binding.btEsperarDeprecated.setOnClickListener {
            var inte: Intent = Intent(this, Ventana2::class.java)
            startActivityForResult(inte, SECOND_ACTIVITY_REQUEST_CODE)
        }
/*
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {
                    val valor = data?.getStringExtra("valor")
                    binding.tvEEdad.text = valor
                }
            }

 */
            //B creamos el intent y lo lanzamos asi
            binding.btEsperarActual.setOnClickListener {
                var inte: Intent = Intent(this, Ventana2::class.java)
                startActivityForResult(inte, SECOND_ACTIVITY_REQUEST_CODE)
            }

            //     var resultLauncher = registerForActivityResult(ActiviyResultContracts.StartActivityForResult())
            //    { result ->
            //       if (result.resultCode == Activity.RESULT_OK) {
            //        val data: Intent? = result.data
            //        val valor = data?.getStringExtra("valor")
            //      binding.tvENombre.setText(returnString(valor))
            //  }
            // }}

            // Boton para navegar a la segunda ventana
            binding.btAceptar.setOnClickListener {
                var inte: Intent = Intent(this, Ventana2::class.java)
                startActivity(inte)

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

                /*
            // 3) Pasar objeto persona a otra actividad con bundle
            var inte : Intent = Intent(this, Ventana2::class.java)
            val bundle = Bundle()
            bundle.putString("nombre1", binding.ptNombre.text.toString())
            bundle.putString("edad1", binding.ptEdad.text.toString())
            bundle.putSerializable("persona",p)
            inte.putExtra("objeto",bundle)
            startActivity(inte)
*/
            }
        }
    }

