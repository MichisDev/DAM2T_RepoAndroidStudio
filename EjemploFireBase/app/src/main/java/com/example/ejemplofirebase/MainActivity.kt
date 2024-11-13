package com.example.ejemplofirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejemplofirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //en esta fariable llevaremos el objeto que nos conecta con los Users de Firebase.
    private lateinit var firebaseauth : FirebaseAuth
    //en esta variable llevamos el binding con el layout.
    private lateinit var binding: ActivityMainBinding


    val TAG = "MICHEL"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //inicializamos el objeto firebaseauth que nos sirve para la autentificación de tipo genérico.
        firebaseauth = FirebaseAuth.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //------------------------------ Autenticación con email y password ------------------------------------
        binding.btRegistrar.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPass.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        irHome(
                            it.result?.user?.email ?: "",
                            Proveedor.BASIC
                        )  //Esto de los interrogantes es por si está vacío el email, que enviaría una cadena vacía.
                    } else {
                        showAlert("Error registrando al usuario. Comprueba el formato del email o que la contraseña sea segura")
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "La conexión ha fallado", Toast.LENGTH_SHORT).show()
                }
            } else {
                showAlert("Rellene los campos")
            }
        }

        binding.btLogin.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPass.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseauth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        irHome(
                            it.result?.user?.email ?: "",
                            Proveedor.BASIC
                        )  //Esto de los interrogantes es por si está vacío el email.
                    } else {
                        showAlert()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Conexión no establecida", Toast.LENGTH_SHORT).show()
                }
            } else {
                showAlert("Rellene los campos")
            }
        }
    }

    //************************************** Funciones auxiliares registro y login con externos **************************************

    private fun showAlert(msg:String = "Se ha producido un error autenticando al usuario"){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun irHome(email:String, provider:Proveedor, nombre:String = "Usuario"){
        Log.e(TAG,"Valores: ${email}, ${provider}, ${nombre}")
        val homeIntent = Intent(this, Home::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
            putExtra("nombre",nombre)
        }
        startActivity(homeIntent)
    }
    //***********************************************************************************************************

}