package com.example.losbrunchies

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class Alimentacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alimentacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageView: ImageView = findViewById(R.id.imgHuevo)
        val btnReceta = findViewById<TextView>(R.id.btnReceta)


        Glide.with(this)
            .asGif()
            .load(R.drawable.huevito_gif)
            .into(imageView)

        val customView = layoutInflater.inflate(R.layout.dialog_custom, null)

        val alertDialog = AlertDialog.Builder(this)
            .setView(customView) // Usa el diseño personalizado
            .create()

        val btnGuardar = customView.findViewById<TextView>(R.id.btnGuardar)
        val btnCerrar = customView.findViewById<TextView>(R.id.btnCerrar)

        btnReceta.setOnClickListener {
            alertDialog.show() // Muestra el diálogo
        }

        btnGuardar?.setOnClickListener {
            Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        btnCerrar?.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}