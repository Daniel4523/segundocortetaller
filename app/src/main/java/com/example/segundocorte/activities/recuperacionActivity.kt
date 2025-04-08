package com.example.segundocorte.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.segundocorte.R

class recuperacionActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextEmail: EditText
    private lateinit var buttonEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiry_recuperacioncontrase)

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        editTextEmail = findViewById(R.id.editEmail)
        buttonEnviar = findViewById(R.id.btnEnviarSolicitud)

        buttonEnviar.setOnClickListener {
            val email = editTextEmail.text.toString().trim()

            when {
                !isValidEmail(email) -> {
                    Log.d("Recuperacion", "Correo inválido ingresado: $email")
                    Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                }
                !isEmailRegistered(email) -> {
                    Log.d("Recuperacion", "Correo no registrado: $email")
                    Toast.makeText(this, "El correo no está registrado", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Log.d("Recuperacion", "Correo válido y registrado: $email")
                    Toast.makeText(
                        this,
                        "Se envió un correo para la recuperación de la contraseña",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this, SesionActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isEmailRegistered(email: String): Boolean {
        val storedEmails = sharedPreferences.all.values.map { it.toString() }
        return email in storedEmails
    }
    override fun onStart() {
        super.onStart()
        Log.d("CicloVida", "onStart ejecutado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CicloVida", "onResume ejecutado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CicloVida", "onPause ejecutado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CicloVida", "onStop ejecutado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("CicloVida", "onRestart ejecutado")
    }
}


