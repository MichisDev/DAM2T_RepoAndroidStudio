package com.example.losbrunchies

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.losbrunchies.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseauth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var sharedPreferences: SharedPreferences

    val TAG = "MICAR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Configurar FirebaseAuth y SharedPreferences
        firebaseauth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("session_prefs", MODE_PRIVATE)

        // Verificar si hay una sesión activa
        comprobarSesion()

        // Configurar Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Configurar el botón de inicio de sesión con Google
        binding.btIniGoo.setOnClickListener {
            loginWithGoogle()
        }

        // Configurar el boton de jugar
        binding.btJugar.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun comprobarSesion() {
        val email = sharedPreferences.getString("email", null)
        val provider = sharedPreferences.getString("provider", null)
        val name = sharedPreferences.getString("name", null)

        if (email != null && provider != null) {
           actulizarUI(provider, name ?: "Usuario", email)
        }
    }

    private fun loginWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        lanzadorGoogleSignIn.launch(signInIntent)
    }

    private val lanzadorGoogleSignIn =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                manejarGoogleSignInResult(task)
            }
        }

    private fun manejarGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            account?.let {
                val credential = GoogleAuthProvider.getCredential(it.idToken, null)
                firebaseauth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        val email = it.email ?: ""
                        val provider = Proveedor.GOOGLE.name
                        val name = it.displayName ?: "Usuario"

                        guardarSesion(email, provider, name)
                        actulizarUI(provider, name, email)
                        mostrarWelcomeDialog(name)
                    } else {
                        Toast.makeText(this, authTask.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Error en Google Sign-In", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarSesion(email: String, provider: String, name: String) {
        sharedPreferences.edit().apply {
            putString("email", email)
            putString("provider", provider)
            putString("name", name)
            apply()
        }
    }

    private fun actulizarUI(provider: String, name: String, email: String) {
        binding.txtProveedor.text = provider
        binding.txtNombre.text = name
        binding.txtEmail.text = email
    }

    private fun mostrarWelcomeDialog(name: String) {
        AlertDialog.Builder(this)
            .setTitle("Sesión iniciada")
            .setMessage("Bienvenido $name. Puedes jugar con tu cuenta.")
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
