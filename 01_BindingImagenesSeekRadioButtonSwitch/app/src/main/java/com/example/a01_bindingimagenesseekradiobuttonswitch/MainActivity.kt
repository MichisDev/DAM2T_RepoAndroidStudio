package com.example.a01_bindingimagenesseekradiobuttonswitch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a01_bindingimagenesseekradiobuttonswitch.R
import com.example.a01_bindingimagenesseekradiobuttonswitch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // propiedad de clase
    lateinit var myBinding: ActivityMainBinding

    // "MAIN" es el onCreate
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // DALE memoria a myBinding
        myBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(myBinding.root)


        // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(myBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // formas de mostrar con binding y sin el
        val miTag = "Michelle"
        /*val btAceptar = findViewById<Button>(R.id.btAceptar)
        val btBorrar = findViewById<Button>(R.id.btBorrar)
        val ptNombre = findViewById<EditText>(R.id.ptNombre)
        */


        myBinding.btAceptar.setOnClickListener {

            // println(ptNombre.text).toString()
            Log.i(miTag, "Hola${myBinding.ptNombre.text}")

            Toast.makeText(this, "Hola ${myBinding}", Toast.LENGTH_SHORT).show()
        }

        myBinding.btBorrar.setOnClickListener {
            myBinding.ptNombre.text.clear()
        }

        // cambiar icono burger
        val imBtImagen = findViewById<ImageButton>(R.id.imBtImagen)
        val imgIconoBurger = findViewById<ImageButton>(R.id.imgIconoBurger)

        imBtImagen.setOnClickListener {
            imgIconoBurger.setImageResource(R.drawable.ic_pizza)
        }


    }
}