package com.example.segundocorte.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.segundocorte.R

class loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("CicloVida", "onCreate ejecutado")

        val boton2 = findViewById<Button>(R.id.BotonRegistro)
        val boton1 = findViewById<Button>(R.id.botoncomenzar)

        boton1.setOnClickListener {
            val intent = Intent(this, SesionActivity::class.java)
            startActivity(intent)
        }
        boton2.setOnClickListener {
            val intent = Intent(this, registroActivity::class.java)
            startActivity(intent)
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
