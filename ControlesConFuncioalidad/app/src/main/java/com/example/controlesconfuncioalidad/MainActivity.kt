package com.example.controlesconfuncioalidad

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val miTag = "Michelle"
        val btAceptar = findViewById<Button>(R.id.btAceptar)
        val btBorrar = findViewById<Button>(R.id.btBorrar)
        val ptNombre = findViewById<EditText>(R.id.ptNombre)

        btAceptar.setOnClickListener {

            // println(ptNombre.text).toString()
           Log.i(miTag, "Hola${ptNombre.text}")

            Toast.makeText(this, "Hola ${ptNombre.text}", Toast.LENGTH_SHORT).show()
        }

        btBorrar.setOnClickListener {
            ptNombre.text.clear()
        }



    }
}