package com.example.losbrunchies

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.losbrunchies.databinding.ActivityAlimentacionBinding

class Alimentacion : AppCompatActivity() {
    private lateinit var binding: ActivityAlimentacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlimentacionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Configurar el botón de volver al home
        binding.btVol.setOnClickListener {
            finish()
        }

    }
}