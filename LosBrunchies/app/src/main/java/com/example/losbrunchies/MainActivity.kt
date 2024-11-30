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
import kotlin.text.clear

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
        binding.ivGoogle.setOnClickListener {
            loginWithGoogle()
        }

        // Configurar el boton de jugar
        binding.ivPlay.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }


        binding.tvRegis.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)

        }

    }


    private fun logout() {
        sharedPreferences.edit().clear().apply()
        val loginIntent = Intent(this, Registro::class.java)
        startActivity(loginIntent)
        finish()
    }

    private fun registrarsql() {
        val nick = binding.etAlias.text
        val pass = binding.etPass.text
        val user = UsuarioSQLite(nick.toString(), pass.toString())
        val result = Conexion.addUsuario(this, user)
        if (result != -1L) {
            binding.etAlias.clearComposingText()
            binding.etPass.clearComposingText()
        } else {
            Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_SHORT).show()
        }
    }

    private fun comprobarSesion() {
        val email = sharedPreferences.getString("email", null)
        val provider = sharedPreferences.getString("provider", null)
        val name = sharedPreferences.getString("name", null)
//      PRUEBA CONEXION PROVEEDOR NOMBRE Y EMAIL GOOGLE
//        if (email != null && provider != null) {
//           actulizarUI(provider, name ?: "Usuario", email)
//        }
    }

    private fun loginWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        lanzadorGoogleSignIn.launch(signInIntent)
    }

    private val lanzadorGoogleSignIn =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
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
                        // PRUEBA CONEXION PROVEEDOR NOMBRE Y EMAIL GOOGLE
//                        actulizarUI(provider, name, email)
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



    private fun mostrarWelcomeDialog(name: String) {
        AlertDialog.Builder(this)
            .setTitle("Sesión iniciada")
            .setMessage("Bienvenido $name. Puedes jugar con tu cuenta.")
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    /*
    PRUEBA CONEXION PROVEEDOR NOMBRE Y EMAIL GOOGLE
    private fun actulizarUI(provider: String, name: String, email: String) {
        binding.txtProveedor.text = provider
        binding.txtNombre.text = name
        binding.txtEmail.text = email
    }
     <TextView
            android:id="@+id/txtProveedor"
            android:layout_width="250dp"
            android:layout_height="wrap_content"
            android:text="Proveedor"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.496"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.122" />

        <TextView
            android:id="@+id/txtNombre"
            android:layout_width="250dp"
            android:layout_height="wrap_content"
            android:text="Nombre"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.496"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.175" />

        <TextView
            android:id="@+id/txtEmail"
            android:layout_width="250dp"
            android:layout_height="wrap_content"
            android:text="Email"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.496"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.075" />
     */
}
