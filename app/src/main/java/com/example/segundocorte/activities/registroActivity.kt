package com.example.segundocorte.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.segundocorte.R

class registroActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        val editNombre = findViewById<EditText>(R.id.editNombre)
        val editApellido = findViewById<EditText>(R.id.editApellido)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editTelefono = findViewById<EditText>(R.id.editTelefono)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val editPasswordConfirm = findViewById<EditText>(R.id.editPasswordConfirm)
        val checkTerms = findViewById<CheckBox>(R.id.checkTerms)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val textLoginRedirect = findViewById<TextView>(R.id.textLoginRedirect)

        btnRegister.setOnClickListener {
            val nombre = editNombre.text.toString().trim()
            val apellido = editApellido.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val telefono = editTelefono.text.toString().trim()
            val password = editPassword.text.toString()
            val passwordConfirm = editPasswordConfirm.text.toString()

            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || telefono.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != passwordConfirm) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!checkTerms.isChecked) {
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editor = sharedPreferences.edit()
            editor.putString("nombre", nombre)
            editor.putString("apellido", apellido)
            editor.putString("email", email)
            editor.putString("telefono", telefono)
            editor.putString("password", password)
            editor.apply()

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, SesionActivity::class.java)
            startActivity(intent)
            finish()

        }

        textLoginRedirect.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()

        }
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
