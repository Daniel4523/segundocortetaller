package com.example.segundocorte.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.segundocorte.R

class perfilActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var txtNombre: TextView
    private lateinit var txtApellido: TextView
    private lateinit var txtTelefono: TextView
    private lateinit var txtEmail: TextView
    private lateinit var editNombre: EditText
    private lateinit var editApellido: EditText
    private lateinit var editTelefono: EditText
    private lateinit var btnEditar: Button
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var imgPerfil: ImageView
    private lateinit var btnCambiarImagen: Button
    private val PICK_IMAGE_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        Log.d("CicloVida", "onCreate ejecutado")
        imgPerfil = findViewById(R.id.imgPerfil)
        btnCambiarImagen = findViewById(R.id.btnCambiarImagen)

        btnCambiarImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtEmail = findViewById(R.id.txtEmail)

        editNombre = findViewById(R.id.editNombre)
        editApellido = findViewById(R.id.editApellido)
        editTelefono = findViewById(R.id.editTelefono)

        btnEditar = findViewById(R.id.btnEditar)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)


        cargarDatos()

        btnEditar.setOnClickListener {
            activarModoEdicion(true)
        }

        btnGuardar.setOnClickListener {
            guardarCambios()
        }

        btnCancelar.setOnClickListener {
            activarModoEdicion(false)
        }
        val boton1 = findViewById<Button>(R.id.btnActivityCentral)

        boton1.setOnClickListener {
            val intent = Intent(this, centralActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data


            imgPerfil.setImageURI(imageUri)


            sharedPreferences.edit().putString("imagen_uri", imageUri.toString()).apply()
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
    private fun cargarDatos() {
        val nombre = sharedPreferences.getString("nombre", "") ?: ""
        val apellido = sharedPreferences.getString("apellido", "") ?: ""
        val telefono = sharedPreferences.getString("telefono", "") ?: ""
        val email = sharedPreferences.getString("email", "No registrado") ?: "No registrado"

        txtNombre.text = nombre
        txtApellido.text = apellido
        txtTelefono.text = telefono
        txtEmail.text = email

        editNombre.setText(nombre)
        editApellido.setText(apellido)
        editTelefono.setText(telefono)


        val imagenUriString = sharedPreferences.getString("imagen_uri", null)
        if (!imagenUriString.isNullOrEmpty()) {
            val imageUri = android.net.Uri.parse(imagenUriString)
            imgPerfil.setImageURI(imageUri)
        }

        activarModoEdicion(false)
    }


    private fun activarModoEdicion(editar: Boolean) {
        txtNombre.visibility = if (editar) View.GONE else View.VISIBLE
        txtApellido.visibility = if (editar) View.GONE else View.VISIBLE
        txtTelefono.visibility = if (editar) View.GONE else View.VISIBLE
        txtEmail.visibility = View.VISIBLE

        editNombre.visibility = if (editar) View.VISIBLE else View.GONE
        editApellido.visibility = if (editar) View.VISIBLE else View.GONE
        editTelefono.visibility = if (editar) View.VISIBLE else View.GONE

        btnEditar.visibility = if (editar) View.GONE else View.VISIBLE
        btnGuardar.visibility = if (editar) View.VISIBLE else View.GONE
        btnCancelar.visibility = if (editar) View.VISIBLE else View.GONE
    }

    private fun guardarCambios() {
        val nuevoNombre = editNombre.text.toString().trim()
        val nuevoApellido = editApellido.text.toString().trim()
        val nuevoTelefono = editTelefono.text.toString().trim()

        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoTelefono.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show()
            return
        }

        sharedPreferences.edit().apply {
            putString("nombre", nuevoNombre)
            putString("apellido", nuevoApellido)
            putString("telefono", nuevoTelefono)
            apply()
        }

        cargarDatos()
        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
    }
}
