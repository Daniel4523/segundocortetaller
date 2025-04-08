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

class SesionActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var botonIngresar: Button
    private lateinit var botonRegistro: Button
    private lateinit var botonRecupera: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion)

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        editTextEmail = findViewById(R.id.editTextUsuario)
        editTextPassword = findViewById(R.id.editTextContraseña)
        botonIngresar = findViewById(R.id.botoningresar)
        botonRegistro = findViewById(R.id.botonregistro)
        botonRecupera = findViewById(R.id.botonrecupera)

        botonIngresar.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString()

            if (!isValidEmail(email)) {
                Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isValidUser(email, password)) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, centralActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        botonRegistro.setOnClickListener {
            val intent = Intent(this, registroActivity::class.java)
            startActivity(intent)
        }

        botonRecupera.setOnClickListener {
            val intent = Intent(this, recuperacionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidUser(email: String, password: String): Boolean {
        val storedEmail = sharedPreferences.getString("email", null)
        val storedPassword = sharedPreferences.getString("password", null)
        return storedEmail == email && storedPassword == password
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


