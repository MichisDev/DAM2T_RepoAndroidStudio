package com.example.losbrunchies

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.losbrunchies.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //en esta fariable llevaremos el objeto que nos conecta con los Users de Firebase.
    private lateinit var firebaseauth : FirebaseAuth
    //en esta variable llevamos el binding con el layout.
    private lateinit var binding: ActivityMainBinding
    // en esta variable llevamos el inicio con google
    private lateinit var googleSignInClient: GoogleSignInClient

    val TAG = "MICAR"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}